package org.curransoft.igf;

/**
 * A convenience class to extend when implementing an IGFApplication. All
 * IGFApplication interface methods have dummy implementations, so subclasses
 * need only implement the relevant methods and can safely ignore the rest.
 * 
 * @author curran
 * 
 */
public class IGFApplicationAdapter implements IGFApplication {

	public void setup(IGF g) {

	}

	public void draw(IGF g) {

	}

	public void pointPressed(IGF g, int id, double x, double y) {

	}

	public void pointDragged(IGF g, int id, double x, double y, double px,
			double py) {

	}

	public void pointReleased(IGF g, int id, double x, double y) {

	}

	public void keyPressed(IGF g, IGFKey k) {

	}

	public void keyReleased(IGF g, IGFKey k) {

	}

	public void keyTyped(IGF g, IGFKey k) {

	}

	public boolean exit() {
		return true;
	}

}
