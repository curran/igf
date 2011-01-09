package org.curransoft.igf;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * A convenience class intended for use by IGF implementations for generating
 * generic IGF mouse and key listeners. These listeners just forward the events
 * to the IGFApplication.
 * 
 * @author curran
 * 
 */
public class IGFUtils {

	/**
	 * @return a mouse listener which forwards mouse events to the application.
	 * @param igf
	 *            the reference to "this" needed for inner class generation
	 */
	public static MouseAdapter makeMouseListener(final IGF igf,
			final IGFApplication application) {
		return new MouseAdapter() {
			int px, py;

			public void mouseReleased(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				application.pointReleased(igf, IGF.MOUSE_POINT_ID, x, y);
			}

			public void mousePressed(MouseEvent e) {
				px = e.getX();
				py = e.getY();
				application.pointPressed(igf, IGF.MOUSE_POINT_ID, px, py);
			}

			public void mouseDragged(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				application.pointDragged(igf, IGF.MOUSE_POINT_ID, x, y, px, py);
				px = x;
				py = y;
			}
		};
	}

	/**
	 * @return a window listener which forwards the "window close" event to the
	 *         application, then calls System.exit(0).
	 * @param igf
	 *            the reference to "this" needed for inner class generation
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
	 * @param igf
	 *            the reference to the IGF instance needed for anonymous inner
	 *            class generation
	 */
	public static KeyListener makeKeyListener(final IGF igf,
			final IGFApplication application, final Frame frame) {
		return new KeyListener() {
			public void keyTyped(KeyEvent e) {
				application.keyTyped(igf, makeIGFKey(e));
			}

			public void keyReleased(KeyEvent e) {
				application.keyReleased(igf, makeIGFKey(e));
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

				application.keyPressed(igf, makeIGFKey(e));
			}

			private IGFKey makeIGFKey(final KeyEvent e) {
				return new IGFKey() {

					@Override
					public int keyCode() {
						if (e.getKeyCode() == KeyEvent.VK_UP)
							return IGFKey.UP;
						else if (e.getKeyCode() == KeyEvent.VK_DOWN)
							return IGFKey.DOWN;
						else if (e.getKeyCode() == KeyEvent.VK_LEFT)
							return IGFKey.LEFT;
						else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
							return IGFKey.RIGHT;
						else
							return IGFKey.CHAR;
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

}
