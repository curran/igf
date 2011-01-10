package org.curransoft.igf.im;

/**
 * A convenience class to extend when implementing an IGFApplication. All
 * IGFApplication interface methods have dummy implementations, so subclasses
 * need only implement the relevant methods and can safely ignore the rest.
 * 
 * @author curran
 * 
 */
public class IGFApplicationAdapter implements IGFApplication {

	@Override
	public void setup(ImmediateModeGraphics g) {

	}

	@Override
	public void draw(ImmediateModeGraphics g) {

	}

	@Override
	public void pointPressed(ImmediateModeGraphics g, int id, double x, double y) {

	}

	@Override
	public void pointDragged(ImmediateModeGraphics g, int id, double x,
			double y, double px, double py) {

	}

	@Override
	public void pointReleased(ImmediateModeGraphics g, int id, double x,
			double y) {

	}

	@Override
	public void keyPressed(ImmediateModeGraphics g, KeyboardKey k) {

	}

	@Override
	public void keyReleased(ImmediateModeGraphics g, KeyboardKey k) {

	}

	@Override
	public void keyTyped(ImmediateModeGraphics g, KeyboardKey k) {

	}

	@Override
	public boolean exit() {
		return true;
	}

}
