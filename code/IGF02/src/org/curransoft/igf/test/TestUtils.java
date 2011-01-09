package org.curransoft.igf.test;

import org.curransoft.igf.im.IGFApplication;
import org.curransoft.igf.im.Java2DImmediateModeGraphics;
import org.curransoft.igf.im.OpenGLImmediateModeGraphics;

/**
 * A utility class for running unit tests in multiple graphics implementations
 * (java2D, OpenGL).
 * 
 * @author curran
 * 
 */
public class TestUtils {
	/**
	 * Tests the given application using simultaneously a Java2D implementation
	 * and an OpenGL implementation. Two instances of the (same) application are
	 * required because otherwise, the same ID for fonts and images would be
	 * used across two different implementations, which would break one or the
	 * other.
	 */
	public static void testApplication(IGFApplication application1,
			IGFApplication application2, int w, int h) {
		Java2DImmediateModeGraphics gJava = new Java2DImmediateModeGraphics();
		gJava.showInFrame("Hello Java2D", 0, 0, w, h, application1);

		OpenGLImmediateModeGraphics gOGL = new OpenGLImmediateModeGraphics();
		gOGL.showInFrame("Hello OpenGL", w, 0, w, h, application2);
	}
}
