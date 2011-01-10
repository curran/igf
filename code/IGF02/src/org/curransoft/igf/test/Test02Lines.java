package org.curransoft.igf.test;

import org.curransoft.igf.im.FillAndStroke;
import org.curransoft.igf.im.IGFApplicationAdapter;
import org.curransoft.igf.im.ImmediateModeGraphics;
import org.curransoft.igf.util.Interval;

public class Test02Lines extends IGFApplicationAdapter {
	double t = 0, tInterval = 0.1;

	double max = 50;
	Interval minMax = new Interval(0, max);
	Interval outerAngleRange = new Interval(-Math.PI / 2, Math.PI);
	Interval innerAngleRange = new Interval(0, Math.PI * 3);

	public static void main(String[] args) {
		TestUtils.testApplication(new Test02Lines(), new Test02Lines(), 500,
				500);
	}

	public void draw(ImmediateModeGraphics g) {
		t += tInterval;
		FillAndStroke style = g.style();
		style.setFillToWhite();
		g.drawBackground();

		style.setStroke(0);

		double x1 = 20, y1 = 20, x2 = 80, y2 = 20;
		style.setStrokeWeight(1);
		style.setStroke(.6);
		g.drawLine(x1, y1, x2, y2);

		style.setStrokeWeight(4);
		style.setStroke(.3);
		y1 = y2 = 40;
		g.drawLine(x1, y1, x2, y2);

		style.setStrokeWeight(10);
		style.setStroke(0);
		y1 = y2 = 70;
		g.drawLine(x1, y1, x2, y2);

		int offset = 100;
		y1 = 20;
		x1 = 20 + offset;
		y2 = 80;
		x2 = 20 + offset;
		style.setStrokeWeight(1);
		style.setStroke(.6);
		g.drawLine(x1, y1, x2, y2);

		style.setStrokeWeight(4);
		style.setStroke(.3);
		x1 = x2 = 40 + offset;
		g.drawLine(x1, y1, x2, y2);

		style.setStrokeWeight(10);
		style.setStroke(0);

		x1 = x2 = 70 + offset;
		g.drawLine(x1, y1, x2, y2);

		x1 = 20;
		y1 = 120;
		x2 = 100;
		y2 = 200;
		style.setStrokeWeight(1);
		style.setStroke(1, 0, 0);
		g.drawLine(x1, y1, x2, y2);

		offset = 50;
		x1 += offset;
		x2 += offset;
		style.setStrokeWeight(4);
		style.setStroke(0, 1, 0);
		g.drawLine(x1, y1, x2, y2);
		x1 += offset;
		x2 += offset;
		style.setStrokeWeight(10);
		style.setStroke(0, 0, 1);
		g.drawLine(x1, y1, x2, y2);

		// Interval xRange = new Interval(0, Math.PI);
		double outerX = 250, outerY = 220, outerRadius = 170;
		for (double i = 0; i < max; i++) {
			double outerAngle = minMax.transformTo(outerAngleRange, i);
			innerAngleRange.setMax(10+Math.sin(t/5)*10);
			double innerAngle = minMax.transformTo(innerAngleRange, i) + t / 4;

			style.setStrokeWeight(4 + Math.sin(outerAngle + t) * 3);
			double red = Math.sin(outerAngle + t) / 2 + .5;
			double green = Math.sin(outerAngle + t * 2.4) / 2 + .5;
			double blue = Math.sin(outerAngle + t * 3.1) / 2 + .5;

			style.setStroke(red, green, blue);

			double x = outerX + Math.cos(outerAngle) * outerRadius;
			double y = outerY + Math.sin(outerAngle) * outerRadius;

			double innerRadius = (Math.sin(outerAngle + t) + 1)
					* (Math.sin(t / 3) + 2) * 20;
			x1 = x + Math.cos(innerAngle) * innerRadius;
			y1 = y + Math.sin(innerAngle) * innerRadius;
			x2 = x - Math.cos(innerAngle) * innerRadius;
			y2 = y - Math.sin(innerAngle) * innerRadius;

			// x1 = Math.cos(angle);
			// y1 = Math.sin(angle);
			// x2 = Math.cos(angle);
			// y2 = Math.sin(angle);

			g.drawLine(x1, y1, x2, y2);
		}
	}
}
