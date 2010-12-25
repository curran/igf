package org.curransoft.igf;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;

/**
 * A Java2D implementation of IGF.
 * 
 * @author curran
 * 
 */
public class IGFJava2D implements IGF {
	/**
	 * The contained application
	 */
	final IGFApplication application;

	/**
	 * The currently active graphics. Set to something when paint() is called,
	 * set back to null when paint() exits.
	 */
	Graphics g;

	/**
	 * The component which is the drawing area for this
	 */
	Component drawingArea;

	/**
	 * Creates an instance of IGF backed by the Java2D API which will manage the given application.
	 */
	public IGFJava2D(IGFApplication application) {
		this.application = application;
	}
	
	public Frame showFrame(String title, int x, int y, int width, int height) {
		Frame frame = new Frame(title);
		frame.setBounds(x, y, width, height);
		
		MouseAdapter listener = IGFUtils.makeMouseListener(this,application);
		KeyListener keyListener = IGFUtils.makeKeyListener(this,application,frame);
		WindowAdapter windowListener = IGFUtils.makeWindowListener(application);

		drawingArea = makeDrawingArea(this);
		drawingArea.addMouseListener(listener);
		drawingArea.addMouseMotionListener(listener);

		frame.add(drawingArea);

		frame.addKeyListener(keyListener);
		frame.addWindowListener(windowListener);
		frame.setVisible(true);
		return frame;
	}
	/**
	 * @return a Java Component which captures the Graphics object, then calls
	 *         application.draw(). This will induce calls from the application
	 *         into "this", which will use the captured Graphics to do the
	 *         drawing.
	 * @param igf
	 *            the reference to "this" needed for inner class generation
	 */
	@SuppressWarnings("serial")
	private Component makeDrawingArea(final IGFJava2D igf) {
		return new Component() {
			public void paint(Graphics g) {
				igf.g = g;
				igf.application.draw(igf);
				igf.g = null;
			}
		};
	}

	

	/**
	 * Gets the width of the drawing area in pixels
	 */
	public double getWidth() {
		return drawingArea == null ? 1 : drawingArea.getWidth();
	}

	/**
	 * Gets the width of the drawing area in pixels
	 */
	public double getHeight() {
		return drawingArea == null ? 1 : drawingArea.getHeight();
	}

	public void line(double x1, double y1, double x2, double y2, double width) {
		g.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
	}

	public void background(int gray) {
		g.setColor(getGrayColor(gray));
		g.fillRect(0, 0, (int) getWidth(), (int) getHeight());
	}

	public void fill(int gray) {
		g.setColor(getGrayColor(gray));
	}

	/**
	 * Returns new Color(gray, gray, gray);
	 */
	private Color getGrayColor(int gray) {
		if (gray == 0)
			return Color.black;
		else if (gray == 255)
			return Color.white;
		else
			return new Color(gray, gray, gray);
	}
}
