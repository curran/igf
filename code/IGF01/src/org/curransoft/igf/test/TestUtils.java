package org.curransoft.igf.test;

import org.curransoft.igf.IGF;
import org.curransoft.igf.IGFApplication;
import org.curransoft.igf.IGFJava2D;
import org.curransoft.igf.IGFOpenGL;

public class TestUtils {

	public static void testApplication(IGFApplication application) {
		testApplication(application,500,500);
	}

	public static void testApplication(IGFApplication application, int w, int h) {
		IGF gJava = new IGFJava2D(application);
		gJava.showFrame("Hello Java2D", 0, 0, w, h);

		IGF gOGL = new IGFOpenGL(application);
		gOGL.showFrame("Hello OpenGL", w, 0, w, h);		
	}
}
