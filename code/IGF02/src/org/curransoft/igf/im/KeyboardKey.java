package org.curransoft.igf.im;

/**
 * An encapsulation of a keyboard key with modifiers, used when passing key
 * events to IGF applications.
 * 
 * @author curran
 * 
 */
public interface KeyboardKey {
	/**
	 * The constant returned from keyCode() when the up arrow has been pressed.
	 */
	public static final int UP = 0;
	/**
	 * The constant returned from keyCode() when the down arrow has been
	 * pressed.
	 */
	public static final int DOWN = 1;
	/**
	 * The constant returned from keyCode() when the left arrow has been
	 * pressed.
	 */
	public static final int LEFT = 2;
	/**
	 * The constant returned from keyCode() when the right arrow has been
	 * pressed.
	 */
	public static final int RIGHT = 3;
	/**
	 * The constant returned from keyCode() when a character has been pressed.
	 * In this case, the call to keyCode() should be followed up with a call to
	 * keyChar() to find out which character it is.
	 */
	public static final int CHAR = 4;

	/**
	 * Returns a code describing the key associated with the most recent key
	 * event. It only makes sense to call this from within keyPressed(),
	 * keyReleased() or keyTyped().
	 * 
	 * The returned value may be one of the following:
	 * <ul>
	 * <li>IGFKey.UP</li>
	 * <li>IGFKey.DOWN</li>
	 * <li>IGFKey.LEFT</li>
	 * <li>IGFKey.RIGHT</li>
	 * <li>IGFKey.CHAR</li>
	 * </ul>
	 * In the case when IGFKey.CHAR is returned, the key can be adequately
	 * represented with a character. A subsequent call to keyChar() will get the
	 * character representation of the key.
	 */
	int keyCode();

	/**
	 * Returns the character associated with the most recent key event. It only
	 * makes sense to call this from within keyPressed(), keyReleased() or
	 * keyTyped(). Only after keyCode() returns IGFKey.CHAR should keyChar() be
	 * called.
	 */
	char keyChar();

	/**
	 * Returns true if the Ctrl key is currently held down.
	 */
	boolean isCtrlDown();

	/**
	 * Returns true if the Shift key is currently held down.
	 */
	boolean isShiftDown();

	/**
	 * Returns true if the Alt key is currently held down.
	 */
	boolean isAltDown();

}
