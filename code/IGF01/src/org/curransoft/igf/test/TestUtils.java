package org.curransoft.igf.test;

import org.curransoft.igf.IGF;
import org.curransoft.igf.IGFApplication;
import org.curransoft.igf.IGFJava2D;
import org.curransoft.igf.IGFOpenGL;

public class TestUtils {
	/**
	 * Calls testApplication with w = h = 500;
	 */
	public static void testApplication(IGFApplication application1,
			IGFApplication application2) {
		testApplication(application1, application2, 500, 500);
	}

	/**
	 * Tests the given application using simultaneously a Java2D implementation
	 * and an OpenGL implementation. Two instances of the (same) application are
	 * required because otherwise, the same ID for fonts and images would be
	 * used across two different implementations, which would break one or the
	 * other.
	 */
	public static void testApplication(IGFApplication application1,
			IGFApplication application2, int w, int h) {
		IGF gJava = new IGFJava2D(application1);
		gJava.showFrame("Hello Java2D", 0, 0, w, h);

		IGF gOGL = new IGFOpenGL(application2);
		gOGL.showFrame("Hello OpenGL", w, 0, w, h);
	}
}
