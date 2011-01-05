package org.curransoft.igf.test;

import org.curransoft.igf.IGF;
import org.curransoft.igf.IGFApplication;
import org.curransoft.igf.IGFJava2D;
import org.curransoft.igf.IGFOpenGL;

public class TestUtils {

	public static void testApplication(IGFApplication application) {
		IGF gJava = new IGFJava2D(application);
		gJava.showFrame("Hello Java2D", 0, 0, 500, 500);

		IGF gOGL = new IGFOpenGL(application);
		gOGL.showFrame("Hello OpenGL", 500, 0, 500, 500);
	}
}
