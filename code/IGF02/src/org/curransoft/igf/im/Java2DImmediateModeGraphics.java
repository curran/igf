package org.curransoft.igf.im;

import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

/**
 * A Java2D implementation of IGF immediate mode graphics. showInFrame() must be
 * called to initialize the application.
 * 
 * @author curran
 * 
 */
public class Java2DImmediateModeGraphics extends AbstractImmediateModeGraphics {
	/**
	 * The currently active graphics. Set when paint() is called, set back to
	 * null when paint() exits.
	 */
	Graphics2D java2DGraphics = null;
	/**
	 * The Java Swing component which serves as the drawing area.
	 */
	Component drawingArea;

	/**
	 * The reusable array of X points which is used in drawLine().
	 */
	private static int[] lineXPoints = new int[4];
	/**
	 * The reusable array of Y points which is used in drawLine().
	 */
	private static int[] lineYPoints = new int[4];

	/**
	 * A flag indicating whether or not showFrame() has been called. Used for
	 * testing if that method was called twice.
	 */
	private boolean showFrameWasCalled = false;

	BasicStroke basicStroke;

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

		Frame frame = new Frame(title);
		frame.setBounds(x, y, width, height);

		MouseAdapter listener = IGFUtils.makeMouseListener(this, application);
		KeyListener keyListener = IGFUtils.makeKeyListener(this, application,
				frame);
		WindowAdapter windowListener = IGFUtils.makeWindowListener(application);

		drawingArea = makeDrawingArea(application);
		drawingArea.addMouseListener(listener);
		drawingArea.addMouseMotionListener(listener);

		frame.add(drawingArea);

		frame.addKeyListener(keyListener);
		frame.addWindowListener(windowListener);
		frame.setVisible(true);

		// animate at 30 frames per second
		int fps = 30;
		// period = time in milliseconds between successive task executions
		final long period = (long) (1000.0 / fps);
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				drawingArea.repaint();
			}
		}, 0, period);

		return frame;
	}

	/**
	 * @return a Java Component which captures the Graphics object when
	 *         repainting, then calls application.draw(). This will induce calls
	 *         from the application into "this", which will use the captured
	 *         Graphics to do the drawing.
	 * @param igf
	 *            the reference to "this" needed for inner class generation
	 */
	@SuppressWarnings("serial")
	private Component makeDrawingArea(final IGFApplication application) {
		// make a final reference to "this" so it can be used in the anonymous
		// inner JPanel class.
		final Java2DImmediateModeGraphics thiz = this;
		// use a JPanel rather than a Component to get its double buffering
		return new JPanel() {
			boolean firstRun = true;

			public void paint(Graphics javaGraphics) {
				// set the graphics hook
				thiz.java2DGraphics = (Graphics2D) javaGraphics;

				// call application.setup() the first time through
				if (firstRun) {
					firstRun = false;
					application.setup(thiz);
				}

				// have the application draw itself
				application.draw(thiz);

				// unset the graphics hook
				thiz.java2DGraphics = null;
			}
		};
	}

	@Override
	public void drawLine(double x1, double y1, double x2, double y2) {
		checkG();
		setUpStroke();

		double lineThickness = style().getStrokeWeight();
		if (lineThickness < 2)
			java2DGraphics.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
		else {
			IGFUtils.deriveLinePolygon(x1, y1, x2, y2, lineThickness,
					lineXPoints, lineYPoints);
			java2DGraphics.fillPolygon(lineXPoints, lineYPoints, 4);
		}
	}

	@Override
	public void drawCircle(double x, double y, double radius) {
		checkG();
		int r2 = (int) (radius * 2);
		if (style().isFillOn()) {
			setFillColor();
			java2DGraphics.fillOval((int) (x - radius), (int) (y - radius), r2,
					r2);
		}
		if (style().isStrokeOn()) {
			setUpStroke();
			java2DGraphics.drawOval((int) (x - radius), (int) (y - radius), r2,
					r2);
		}
	}

	@Override
	public void drawBackground() {
		checkG();
		setFillColor();
		java2DGraphics.fillRect(0, 0, (int) getWidth(), (int) getHeight());
	}

	@Override
	public double getWidth() {
		return drawingArea == null ? 1 : drawingArea.getWidth();
	}

	@Override
	public double getHeight() {
		return drawingArea == null ? 1 : drawingArea.getHeight();
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
	 * Throws an exception if the current Java Graphics "java2DGraphics" object
	 * is null.
	 */
	private void checkG() {
		if (java2DGraphics == null)
			throw new RuntimeException(
					"a draw function was called outside of draw()! This should never happen.");
	}

	/**
	 * Sets the color state of the current Java Graphics "java2DGraphics" object
	 * to the fill color stored in super.style().
	 */
	private void setFillColor() {
		java2DGraphics.setColor(style().getFillColor());
	}

	/**
	 * Sets the color state of the current Java Graphics "java2DGraphics" object
	 * to the stroke color stored in super.style().
	 */
	private void setUpStroke() {
		java2DGraphics.setColor(style().getStrokeColor());
		// TODO don't create so many new objects
		basicStroke = new BasicStroke((float) style().getStrokeWeight(),
				BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND);
		java2DGraphics.setStroke(basicStroke);
	}

	@Override
	public void drawRectangle(double x, double y, double width, double height) {
		// TODO Auto-generated method stub

	}
}
