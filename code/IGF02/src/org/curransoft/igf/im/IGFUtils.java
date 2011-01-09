package org.curransoft.igf.im;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * A convenience class intended for use by IGF implementations for
 * <ul>
 * <li>generating generic IGF mouse and key listeners. These listeners just
 * forward the events to the IGFApplication.</li>
 * <li>generating polygons for weighted lines</li>
 * </ul>
 * 
 * @author curran
 * 
 */
public class IGFUtils {
	/**
	 * The reusable array of X points which is used in deriveLinePolygon().
	 */
	private static double[] lineXPoints = new double[4];
	/**
	 * The reusable array of Y points which is used in deriveLinePolygon().
	 */
	private static double[] lineYPoints = new double[4];

	/**
	 * @return a mouse listener which forwards mouse events to the application.
	 * @param g
	 *            the reference to the graphics instance which will be passed to
	 *            applications
	 */
	public static MouseAdapter makeMouseListener(final ImmediateModeGraphics g,
			final IGFApplication application) {
		return new MouseAdapter() {
			int px, py;

			public void mouseReleased(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				application.pointReleased(g,
						ImmediateModeGraphics.MOUSE_POINT_ID, x, y);
			}

			public void mousePressed(MouseEvent e) {
				px = e.getX();
				py = e.getY();
				application.pointPressed(g,
						ImmediateModeGraphics.MOUSE_POINT_ID, px, py);
			}

			public void mouseDragged(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				application.pointDragged(g,
						ImmediateModeGraphics.MOUSE_POINT_ID, x, y, px, py);
				px = x;
				py = y;
			}
		};
	}

	/**
	 * @return a window listener which forwards the "window close" event to the
	 *         application, then calls System.exit(0).
	 */
	public static WindowAdapter makeWindowListener(
			final IGFApplication application) {
		return new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (application.exit())
					System.exit(0);
			}
		};
	}

	/**
	 * @return a mouse listener which forwards keyboard events to the
	 *         application. Also impelments F11 to toggle fullscreen and ESC to
	 *         exit fullscreen mode.
	 * @param g
	 *            the reference to the graphics instance which will be passed to
	 *            applications
	 */
	public static KeyListener makeKeyListener(final ImmediateModeGraphics g,
			final IGFApplication application, final Frame frame) {
		return new KeyListener() {
			public void keyTyped(KeyEvent e) {
				application.keyTyped(g, makeKeyboardKey(e));
			}

			public void keyReleased(KeyEvent e) {
				application.keyReleased(g, makeKeyboardKey(e));
			}

			public void keyPressed(KeyEvent e) {
				/**
				 * F11 to toggle fullscreen.
				 */
				if (e.getKeyCode() == KeyEvent.VK_F11)
					if (frame.getExtendedState() == Frame.MAXIMIZED_BOTH)
						frame.setExtendedState(Frame.NORMAL);
					else
						frame.setExtendedState(Frame.MAXIMIZED_BOTH);
				/**
				 * ESC to exit fullscreen mode.
				 */
				else if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
					if (frame.getExtendedState() == Frame.MAXIMIZED_BOTH)
						frame.setExtendedState(Frame.NORMAL);

				application.keyPressed(g, makeKeyboardKey(e));
			}

			private KeyboardKey makeKeyboardKey(final KeyEvent e) {
				return new KeyboardKey() {

					@Override
					public int keyCode() {
						if (e.getKeyCode() == KeyEvent.VK_UP)
							return KeyboardKey.UP;
						else if (e.getKeyCode() == KeyEvent.VK_DOWN)
							return KeyboardKey.DOWN;
						else if (e.getKeyCode() == KeyEvent.VK_LEFT)
							return KeyboardKey.LEFT;
						else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
							return KeyboardKey.RIGHT;
						else
							return KeyboardKey.CHAR;
					}

					@Override
					public char keyChar() {
						return e.getKeyChar();
					}

					@Override
					public boolean isCtrlDown() {
						return e.isControlDown();
					}

					@Override
					public boolean isAltDown() {
						return e.isAltDown();
					}

					@Override
					public boolean isShiftDown() {
						return e.isShiftDown();
					}
				};
			}
		};
	}

	/**
	 * Derives the polygon boundary for a weighed line from (x1,y1) to (x2,y2).
	 * 
	 * @param weight
	 *            the thickness of the line
	 * @param lineXPoints
	 *            the resulting 4 X coordinates are written into this array.
	 * @param lineYPoints
	 *            the resulting 4 Y coordinates are written into this array.
	 */
	public static void deriveLinePolygon(double x1, double y1, double x2,
			double y2, double weight, double[] lineXPoints, double[] lineYPoints) {
		if (y1 == y2 || x1 == x2) {
			if (y1 == y2) {
				y1 -= weight / 2;
				y2 = y1 + weight;
			} else if (x1 == x2) {
				x1 -= weight / 2;
				x2 = x1 + weight;
			}
			lineXPoints[0] = x1;
			lineYPoints[0] = y1;
			lineXPoints[1] = x1;
			lineYPoints[1] = y2;
			lineXPoints[2] = x2;
			lineYPoints[2] = y2;
			lineXPoints[3] = x2;
			lineYPoints[3] = y1;
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

			lineXPoints[0] = xa;
			lineYPoints[0] = ya;
			lineXPoints[1] = xb;
			lineYPoints[1] = yb;
			lineXPoints[2] = xc;
			lineYPoints[2] = yc;
			lineXPoints[3] = xd;
			lineYPoints[3] = yd;
		}
	}

	/**
	 * Derives the polygon boundary for a weighed line from (x1,y1) to (x2,y2).
	 * This method is not thread safe, as it reuses a static scratch array.
	 * 
	 * @param weight
	 *            the thickness of the line
	 * @param lineXPoints
	 *            the resulting 4 X coordinates are written into this array.
	 * @param lineYPoints
	 *            the resulting 4 Y coordinates are written into this array.
	 */
	public static void deriveLinePolygon(double x1, double y1, double x2,
			double y2, double weight, int[] lineXPointsAsInts,
			int[] lineYPointsAsInts) {
		deriveLinePolygon(x1, y1, x2, y2, weight, lineXPoints, lineYPoints);
		for (int i = 0; i < 4; i++) {
			lineXPointsAsInts[i] = (int) lineXPoints[i];
			lineYPointsAsInts[i] = (int) lineYPoints[i];
		}
	}

}
