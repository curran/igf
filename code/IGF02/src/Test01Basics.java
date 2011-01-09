import org.curransoft.igf.im.FillAndStrokeParameters;
import org.curransoft.igf.im.IGFApplication;
import org.curransoft.igf.im.ImmediateModeGraphics;
import org.curransoft.igf.im.KeyboardKey;
import org.curransoft.igf.test.TestUtils;

/**
 * A unit test for fill/stroke styles on lines, window resizing, keyboard events
 * and mouse/touch events.
 * 
 * @author curran
 * 
 */
public class Test01Basics implements IGFApplication {

	public static void main(String[] args) {
		TestUtils.testApplication(new Test01Basics(), new Test01Basics(), 500,
				500);
	}

	public void setup(ImmediateModeGraphics g) {
		System.out.println("in setup");
	}

	public void draw(ImmediateModeGraphics g) {
		FillAndStrokeParameters style = g.style();
		style.setFillToWhite();
		g.drawBackground();

		double w = g.getWidth(), h = g.getHeight();
		double x1, y1, x2, y2;

		style.setStrokeWeight(1);
		style.setStrokeToBlack();

		// |/|
		x1 = 0;
		y1 = 0;
		x2 = w;
		y2 = h;
		g.drawLine(x1, y1, x2, y2);
		// |\|
		x1 = w;
		y1 = 0;
		x2 = 0;
		y2 = h;
		g.drawLine(x1, y1, x2, y2);
	}

	public void pointPressed(ImmediateModeGraphics g, int id, double x, double y) {
		System.out.println("in pointPressed");
	}

	public void pointDragged(ImmediateModeGraphics g, int id, double x,
			double y, double px, double py) {
		System.out.println("in pointDragged");
	}

	public void pointReleased(ImmediateModeGraphics g, int id, double x,
			double y) {
		System.out.println("in pointReleased");
	}

	public void keyPressed(ImmediateModeGraphics g, KeyboardKey k) {
		System.out.println("in keyPressed");
	}

	public void keyReleased(ImmediateModeGraphics g, KeyboardKey k) {
		System.out.println("in keyReleased");
	}

	public void keyTyped(ImmediateModeGraphics g, KeyboardKey k) {
		System.out.println("in keyTyped");
	}

	public boolean exit() {
		return true;
	}

}
