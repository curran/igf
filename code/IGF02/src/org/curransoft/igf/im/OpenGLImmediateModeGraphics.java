package org.curransoft.igf.im;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;

import com.sun.opengl.util.Animator;

/**
 * An OpenGL implementation of IGF immediate mode graphics. showInFrame() must
 * be called to initialize the application.
 * 
 * Pass -Dsun.awt.noerasebackground=true to the VM to eliminate flickering on
 * resize.
 * 
 * @author curran
 * 
 */
public class OpenGLImmediateModeGraphics extends AbstractImmediateModeGraphics {
	/**
	 * The graphics context used in display(). When the call to
	 * application.draw() begins, this variable is set to something, then when
	 * application.draw() ends, this variable is set back to null.
	 */
	GL gl = null;

	/**
	 * The width of the drawing area in pixels.
	 */
	int width;

	/**
	 * The height of the drawing area in pixels.
	 */
	int height;

	/**
	 * A flag indicating whether or not showFrame() has been called. Used for
	 * testing if that method was called twice.
	 */
	private boolean showFrameWasCalled = false;

	/**
	 * Initializes this immediate mode graphics implementation and shows it in a
	 * Frame. This method should only ever be called once per instance.
	 * 
	 * @param title
	 *            The title to display at the top of the frame
	 * @param x
	 *            the x offset of the frame
	 * @param y
	 *            the y offset of the frame
	 * @param width
	 *            the width of the frame (not the drawing area)
	 * @param height
	 *            the height of the frame (not the drawing area)
	 * @param application
	 *            the IGF application which will be embedded in this graphics
	 *            environment.
	 * @return the newly created Frame.
	 */
	public Frame showInFrame(String title, int x, int y, int width, int height,
			IGFApplication application) {
		if (showFrameWasCalled)
			throw new RuntimeException(
					"Attempted to call IGFOpenGL.showFrame() twice! Only one call to this function per instance is allowed.");
		showFrameWasCalled = true;

		Frame frame = new Frame();

		MouseAdapter listener = IGFUtils.makeMouseListener(this, application);
		WindowAdapter windowListener = IGFUtils.makeWindowListener(application);
		GLEventListener glListener = makeGLListener(application, frame);

		GLCanvas drawingArea = new GLCanvas();
		drawingArea.addMouseListener(listener);
		drawingArea.addMouseMotionListener(listener);
		drawingArea.addGLEventListener(glListener);

		frame.addWindowListener(windowListener);
		frame.add(drawingArea);
		frame.setResizable(true);

		frame.setTitle(title);
		frame.setBounds(x, y, width, height);
		frame.setVisible(true);
		drawingArea.requestFocus();

		// start animation
		new Animator(drawingArea).start();
		return frame;
	}

	/**
	 * Creates the GLEventListener responsible for delegating events to the
	 * application.
	 */
	private GLEventListener makeGLListener(final IGFApplication application,
			final Frame frame) {
		// make a final reference to "this" so it can be used in the anonymous
		// inner JPanel class.
		final OpenGLImmediateModeGraphics thiz = this;

		return new GLEventListener() {
			/**
			 * Called by JOGL on initialization
			 */
			public void init(GLAutoDrawable gLDrawable) {
				GL gl = gLDrawable.getGL();
				gl.glClearColor(1, 1, 1, 0);
				KeyListener keyListener = IGFUtils.makeKeyListener(thiz,
						application, frame);
				gLDrawable.addKeyListener(keyListener);
			}

			boolean firstRun = true;

			/**
			 * Called by JOGL every frame
			 */
			public void display(GLAutoDrawable gLDrawable) {

				// set the graphics hook
				gl = gLDrawable.getGL();

				// call application.setup() the first time through
				if (firstRun) {
					firstRun = false;
					application.setup(thiz);
				}

				// have the application draw itself
				application.draw(thiz);
				gl = null;
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

				thiz.width = width;
				thiz.height = height;
			}
		};
	}

	@Override
	public void drawLine(double x1, double y1, double x2, double y2) {
		setStrokeColor();
		double weight = style().getStrokeWeight();
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

	@Override
	public void drawCircle(double x, double y, double radius) {
		// TODO optimize this - make a circle display list and reuse that for
		// every circle
		setFillColor();
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
	public void drawBackground() {
		checkGL();
		FillAndStroke s = style();
		gl.glClearColor((float) s.getFillRed(), (float) s.getFillGreen(),
				(float) s.getFillBlue(), (float) s.getFillAlpha());
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void drawRectangle(double x, double y, double width, double height) {
		checkGL();
	}

	@Override
	public double getWidth() {
		return width;
	}

	@Override
	public double getHeight() {
		return height;
	}

	@Override
	public int loadImage(Image image) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void drawImage(int imageID, double x, double y) {
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void text(String textString, int fontID, double x, double y) {
		// TODO Auto-generated method stub

	}

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

	/**
	 * Sets the color state of OpenGL to the stroke color stored in
	 * super.style().
	 */
	private void setStrokeColor() {
		gl.glColor4d(style().getStrokeRed(), style().getStrokeGreen(), style()
				.getStrokeBlue(), style().getStrokeAlpha());
	}

	/**
	 * Sets the color state of OpenGL to the fill color stored in super.style().
	 */
	private void setFillColor() {
		FillAndStroke s = style();
		gl.glColor4d(s.getFillRed(), s.getFillGreen(), s.getFillBlue(),
				s.getFillAlpha());
	}

	/**
	 * Throws an exception if the current GL object is null.
	 */
	private void checkGL() {
		if (gl == null)
			throw new RuntimeException(
					"a draw function was called outside of draw()! This should never happen.");
	}

}
