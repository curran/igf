import java.awt.Font;
import java.util.Map;

import org.curransoft.igf.IGF;
import org.curransoft.igf.IGFApplicationAdapter;
import org.curransoft.igf.IGFKey;
import org.curransoft.igf.IGFOpenGL;
import org.curransoft.igf.util.Interval;


public class PlottingTest extends IGFApplicationAdapter {
	Interval screenX;// = new Interval(0, g.getWidth());
	Interval screenY;// = new Interval(0, g.getHeight());
	Interval dataX;// = new Interval(xMin, xMax);
	Interval dataY;// = new Interval(yMin, yMax);
	Map<String, Country> countries;

	int fontID = IGF.INVALID_FONT_ID;

	public static void main(String[] args) {
		IGF gOGL = new IGFOpenGL(new PlottingTest());
		gOGL.showFrame("Hello DBPedia!", 0, 0, 500, 500);
	}

	public void setup(IGF g) {
		fontID = g.loadFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));

		countries = Main.executeSPARQL();

		double xMin = Double.POSITIVE_INFINITY, xMax = Double.NEGATIVE_INFINITY, yMin = Double.POSITIVE_INFINITY, yMax = Double.NEGATIVE_INFINITY;
		for (Country country : countries.values()) {
			if (country.latitude < xMin)
				xMin = country.latitude;
			if (country.latitude > xMax)
				xMax = country.latitude;
			if (country.longitude < yMin)
				yMin = country.longitude;
			if (country.longitude > yMax)
				yMax = country.longitude;
			System.out.println(country);
		}

		screenX = new Interval(0, g.getWidth());
		screenY = new Interval(0, g.getHeight());
		dataX = new Interval(xMin, xMax);
		dataY = new Interval(yMin, yMax);

	}

	@Override
	public void draw(IGF g) {
		// Interval2D bounds = new Interval2D();
		// Interval2D screen =
		// bounds.set(xMin, xMax, yMin, yMax);

		for (Country country : countries.values()) {
			double y = dataX.transformTo(screenX, country.latitude);
			double x = dataY.transformTo(screenY, country.longitude);
			int radius = 5;
			g.fill(0);
			g.circle(x, y, radius);
			g.text(country.uri, fontID, x, y);
			// System.out.println(country);
		}
	}

	@Override
	public void pointPressed(IGF g, int id, double x, double y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pointDragged(IGF g, int id, double x, double y, double px,
			double py) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pointReleased(IGF g, int id, double x, double y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(IGF g, IGFKey k) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(IGF g, IGFKey k) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(IGF g, IGFKey k) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean exit() {
		// TODO Auto-generated method stub
		return false;
	}
}
