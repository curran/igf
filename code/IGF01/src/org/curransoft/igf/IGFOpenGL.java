package org.curransoft.igf;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.util.HashMap;
import java.util.Map;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.j2d.TextRenderer;

/**
 * An OpenGL implementation of IGF.
 * 
 * Pass -Dsun.awt.noerasebackground=true to the VM for no flickering on resize.
 * 
 * @author curran
 * 
 */

public class IGFOpenGL implements IGF {
	Frame frame;
	GLCanvas drawingArea;
	Animator animator;

	/**
	 * The size of the drawing area in pixels.
	 */
	int width, height;

	/**
	 * The weight of the stroke.
	 */
	double weight;

	/**
	 * The graphics context used in display(). When the call to
	 * application.draw() begins, this variable is set to something, then when
	 * application.draw() ends, this variable is set to null.
	 */
	GL gl;

	/**
	 * A flag indicating whether or not showFrame() has been called. Used for
	 * testing if that method was called twice.
	 */
	private boolean showFrameWasCalled = false;

	/**
	 * The mapping from font ids to their TextRenderer instances.
	 */
	private Map<Integer, TextRenderer> textRenderers = new HashMap<Integer, TextRenderer>();
	/**
	 * The counter used to generate font ids.
	 */
	private int fontIDCounter = 0;

	/**
	 * Whether or not fill is enabled.
	 */
	boolean fillOn = true;

	/**
	 * The red component of the fill color (between 0 and 1).
	 */
	private double fillR;
	/**
	 * The green component of the fill color (between 0 and 1).
	 */
	private double fillG;
	/**
	 * The blue component of the fill color (between 0 and 1).
	 */
	private double fillB;

	/**
	 * The alpha (transparency) component of the fill color (between 0 and 1).
	 */
	private double fillA;
	/**
	 * Whether or not stroke is enabled.
	 */
	boolean strokeOn = false;
	/**
	 * The red component of the stroke color (between 0 and 1).
	 */
	private double strokeR;
	/**
	 * The green component of the stroke color (between 0 and 1).
	 */
	private double strokeG;
	/**
	 * The blue component of the stroke color (between 0 and 1).
	 */
	private double strokeB;

	/**
	 * The alpha (transparency) component of the stroke color (between 0 and 1).
	 */
	private double strokeA;

	public IGFOpenGL(IGFApplication application) {

		MouseAdapter listener = IGFUtils.makeMouseListener(this, application);
		WindowAdapter windowListener = IGFUtils.makeWindowListener(application);
		GLEventListener glListener = makeGLListener(application);

		drawingArea = new GLCanvas();
		drawingArea.addMouseListener(listener);
		drawingArea.addMouseMotionListener(listener);
		drawingArea.addGLEventListener(glListener);

		animator = new Animator(drawingArea);

		frame = new Frame();
		frame.addWindowListener(windowListener);
		frame.add(drawingArea);
		frame.setResizable(true);

	}

	private GLEventListener makeGLListener(final IGFApplication application) {
		final IGFOpenGL igf = this;
		return new GLEventListener() {
			/**
			 * Called by JOGL on initialization
			 */
			public void init(GLAutoDrawable gLDrawable) {
				GL gl = gLDrawable.getGL();
				gl.glClearColor(1, 1, 1, 0);
				KeyListener keyListener = IGFUtils.makeKeyListener(igf,
						application, frame);
				gLDrawable.addKeyListener(keyListener);
			}

			boolean firstCallToDisplay = true;

			/**
			 * Called by JOGL every frame
			 */
			public void display(GLAutoDrawable gLDrawable) {
				gl = gLDrawable.getGL();
				gl.glClear(GL.GL_COLOR_BUFFER_BIT);
				if (firstCallToDisplay) {
					firstCallToDisplay = false;
					application.setup(igf);
				}
				application.draw(igf);
			}

			/**
			 * Needed by JOGL but never called
			 */
			public void displayChanged(GLAutoDrawable gLDrawable,
					boolean modeChanged, boolean deviceChanged) {
			}

			/**
			 * Called by JOGL on window resize
			 */
			public void reshape(GLAutoDrawable gLDrawable, int x, int y,
					int width, int height) {
				GL gl = gLDrawable.getGL();
				gl.glMatrixMode(GL.GL_PROJECTION);
				gl.glLoadIdentity();
				gl.glOrtho(0, width, height, 0, -1, 1);

				igf.width = width;
				igf.height = height;
			}
		};
	}

	public Frame showFrame(String title, int x, int y, int width, int height) {
		if (showFrameWasCalled)
			throw new RuntimeException(
					"Attempted to call IGFOpenGL.showFrame() twice! Only one call to this function per instance is allowed.");
		showFrameWasCalled = true;

		frame.setTitle(title);
		frame.setBounds(x, y, width, height);
		frame.setVisible(true);
		drawingArea.requestFocus();
		animator.start();
		return frame;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public void line(double x1, double y1, double x2, double y2) {
		gl.glColor3d(strokeR, strokeG, strokeB);
		if (y1 == y2) {
			gl.glBegin(GL.GL_QUADS);
			y1 -= weight / 2;
			y2 = y1 + weight;
			gl.glVertex2d(x1, y1);
			gl.glVertex2d(x1, y2);
			gl.glVertex2d(x2, y2);
			gl.glVertex2d(x2, y1);
			gl.glEnd();
		} else if (x1 == x2) {
			gl.glBegin(GL.GL_QUADS);
			x1 -= weight / 2;
			x2 = x1 + weight;
			gl.glVertex2d(x1, y1);
			gl.glVertex2d(x1, y2);
			gl.glVertex2d(x2, y2);
			gl.glVertex2d(x2, y1);
			gl.glEnd();
		} else {
			double a = x2 - x1;
			double b = y2 - y1;
			double c = Math.sqrt(a * a + b * b);
			double ratio = weight / 2 / c;
			double d = a * ratio;
			double e = b * ratio;
			double xa = x1 + e;
			double ya = y1 - d;
			double xb = x1 - e;
			double yb = y1 + d;
			double xc = x2 - e;
			double yc = y2 + d;
			double xd = x2 + e;
			double yd = y2 - d;
			gl.glBegin(GL.GL_QUADS);
			gl.glVertex2d(xa, ya);
			gl.glVertex2d(xb, yb);
			gl.glVertex2d(xc, yc);
			gl.glVertex2d(xd, yd);
			gl.glEnd();
		}
	}

	public void background(int gray) {
	}

	public void circle(double x, double y, double radius) {

		// TODO optimize this - make a circle display list and reuse that for
		// every circle

		gl.glColor3d(0, 0, 0);
		gl.glBegin(GL.GL_TRIANGLE_FAN);
		gl.glVertex2d(x, y);
		for (double angle = 0; angle < 360; angle += 5) {
			double x1 = x + Math.sin(angle) * radius;
			double y1 = y + Math.cos(angle) * radius;
			gl.glVertex2d(x1, y1);
		}
		gl.glEnd();
	}

	@Override
	public void fill(int gray) {
		fill(gray, gray, gray);
	}

	@Override
	public void noFill() {
		fillOn = false;
	}

	@Override
	public void fill(int gray, int alpha) {
		fill(gray, gray, gray, alpha);
	}

	@Override
	public void fill(int red, int green, int blue) {
		fillR = red / 255.0;
		fillG = green / 255.0;
		fillB = blue / 255.0;
		fillA = 1;
	}

	@Override
	public void fill(int red, int green, int blue, int alpha) {
		fillR = red / 255.0;
		fillG = green / 255.0;
		fillB = blue / 255.0;
		fillA = alpha / 255.0;
	}

	@Override
	public void stroke(int gray) {
		stroke(gray, gray, gray);
	}

	@Override
	public void stroke(int red, int green, int blue) {
		strokeR = red / 255.0;
		strokeG = green / 255.0;
		strokeB = blue / 255.0;
	}

	@Override
	public void strokeWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public int loadImage(Image image) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void image(int imageID, double x, double y, double width,
			double height) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getImageWidth(int imageID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getImageHeight(int imageID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int loadFont(Font font) {
		TextRenderer renderer = new TextRenderer(font);
		int fontID = fontIDCounter++;
		textRenderers.put(fontID, renderer);
		return fontID;
	}

	@Override
	public void text(String textString, int fontID, double x, double y) {
		double scale = 1;
		double rotation = 0;
		text(textString, fontID, x, y, scale, rotation);
	}

	@Override
	public void text(String textString, int fontID, double x, double y,
			double scale, double rotation) {
		TextRenderer renderer = textRenderers.get(fontID);
		renderer.beginRendering((int) getWidth(), (int) getHeight()); //
		renderer.setColor((float) fillR, (float) fillG, (float) fillB,
				(float) fillA);
		renderer.draw(textString, (int) x, (int) y);
		renderer.endRendering();
	}

	// TODO add text rendering support:
	/*
	 * //returns a font id int loadFont(Font font); void text(int fontID, String
	 * text, double x, double y)
	 * 
	 * renderer = new TextRenderer(new Font("SansSerif", Font.BOLD, 36));
	 * 
	 * In the display method of your GLEventListener, add:
	 * 
	 * renderer.beginRendering(drawable.getWidth(), drawable.getHeight()); //
	 * optionally set the color renderer.setColor(1.0f, 0.2f, 0.2f, 0.8f);
	 * renderer.draw("Text to draw", xPosition, yPosition); // ... more draw
	 * commands, color changes, etc. renderer.endRendering(); } from
	 * http://www.cse.unsw
	 * .edu.au/~cs3421/jogl/javadoc_public/com/sun/opengl/util
	 * /j2d/TextRenderer.html
	 */

	@Override
	public double getTextWidth(String textString, int fontID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getTextHeight(String textString, int fontID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void stroke(int gray, int alpha) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stroke(int red, int green, int blue, int alpha) {
		// TODO Auto-generated method stub

	}

}
