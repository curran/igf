package org.curransoft.igf.rm;

import org.curransoft.igf.im.ImmediateModeGraphics;
import org.curransoft.igf.im.KeyboardKey;

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
	public void setup(ImmediateModeGraphics g) {

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
