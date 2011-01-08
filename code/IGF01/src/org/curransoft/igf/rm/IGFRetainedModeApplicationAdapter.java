package org.curransoft.igf.rm;

import org.curransoft.igf.IGF;
import org.curransoft.igf.IGFKey;

/**
 * A convenience class with which lets you subclass IGFRetainedModeApplication
 * without having to provide all methods. This class provides empty
 * implementations of all methods inherited from IGFRetainedModeApplication.
 * 
 * @author curran
 * 
 */
public class IGFRetainedModeApplicationAdapter extends
		IGFRetainedModeApplication {

	@Override
	public void setup(IGF g) {

	}

	@Override
	public void keyPressed(IGF g, IGFKey k) {

	}

	@Override
	public void keyReleased(IGF g, IGFKey k) {

	}

	@Override
	public void keyTyped(IGF g, IGFKey k) {

	}

	@Override
	public boolean exit() {
		return true;
	}

}
