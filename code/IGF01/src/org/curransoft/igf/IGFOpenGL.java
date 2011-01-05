package org.curransoft.igf;

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
	 * The graphics context used in display(). When the call to
	 * application.draw() begins, this variable is set to something, then when
	 * application.draw() ends, this variable is set to null.
	 */
	GL gl;

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

			/**
			 * Called by JOGL every frame
			 */
			public void display(GLAutoDrawable gLDrawable) {
				gl = gLDrawable.getGL();
				gl.glClear(GL.GL_COLOR_BUFFER_BIT);
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
		frame.setTitle(title);
		frame.setBounds(x, y, width, height);
		frame.setVisible(true);
		drawingArea.requestFocus();
		return frame;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public void line(double x1, double y1, double x2, double y2) {
		gl.glColor3d(0, 0, 0);
		gl.glBegin(GL.GL_LINE);
		gl.glVertex2d(x1, y1);
		gl.glVertex2d(x2, y2);
		gl.glEnd();
	}

	public void background(int gray) {
	}

	public void fill(int gray) {
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
	public void fill(int red, int green, int blue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stroke(int gray) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stroke(int red, int green, int blue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void strokeWeight(double weight) {
		// TODO Auto-generated method stub
		
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
}
