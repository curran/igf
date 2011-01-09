package org.curransoft.igf.im;

/**
 * The API specification for ImmediateModeGraphics applications. Much influence
 * for the design came from the Processing project (processing.org).
 * 
 * @author curran
 * 
 */
public interface IGFApplication {
	/**
	 * Called once when the application is created.
	 */
	public void setup(ImmediateModeGraphics g);

	/**
	 * Called every frame.
	 */
	public void draw(ImmediateModeGraphics g);

	/**
	 * Called when a (mouse or touch) point is pressed
	 * 
	 * @param g
	 *            the graphics API
	 * @param id
	 *            the id of the point (always 0 for mouse events)
	 * @param x
	 *            the x location of the point in screen coordinates
	 * @param y
	 *            the y location of the point in screen coordinates
	 */
	public void pointPressed(ImmediateModeGraphics g, int id, double x, double y);

	/**
	 * Called when a (mouse or touch) point is dragged
	 * 
	 * @param g
	 *            the graphics API
	 * @param id
	 *            the id of the point (always 0 for mouse events)
	 * @param x
	 *            the x location of the point in screen coordinates
	 * @param y
	 *            the y location of the point in screen coordinates
	 * @param px
	 *            the previous x location of the point in screen coordinates
	 * @param py
	 *            the previous y location of the point in screen coordinates
	 */
	public void pointDragged(ImmediateModeGraphics g, int id, double x,
			double y, double px, double py);

	/**
	 * Called when a (mouse or touch) point is released
	 * 
	 * @param g
	 *            the graphics API
	 * @param id
	 *            the id of the point (always 0 for mouse events)
	 * @param x
	 *            the x location of the point in screen coordinates
	 * @param y
	 *            the y location of the point in screen coordinates
	 */
	public void pointReleased(ImmediateModeGraphics g, int id, double x,
			double y);

	/**
	 * Called when a key on the keyboard is pressed.
	 */
	public void keyPressed(ImmediateModeGraphics g, KeyboardKey k);

	/**
	 * Called when a key on the keyboard is released.
	 */
	public void keyReleased(ImmediateModeGraphics g, KeyboardKey k);

	/**
	 * Called when a key on the keyboard is typed (pressed and released, or held
	 * down to repeatedly "type" itself).
	 */
	public void keyTyped(ImmediateModeGraphics g, KeyboardKey k);

	/**
	 * Called when the user exits the program by closing the window or
	 * otherwise. If this method returns true, System.exit() will be called
	 * directly after this method finished running. Otherwise System.exit() will
	 * not be called.
	 */
	public boolean exit();
}
