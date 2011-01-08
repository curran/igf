package org.curransoft.igf.test;

import org.curransoft.igf.IGF;
import org.curransoft.igf.IGFApplicationAdapter;

/**
 * A simple IGF test which draws several lines with varying thicknesses and
 * orientations.
 * 
 * @author curran
 * 
 */
public class Test03LinesFractal extends IGFApplicationAdapter {
	private double maxStrokeWeight = 10;
	private double minStrokeWeight = 1;
	private double maxGray = 200;
	private double minGray = 0;
	private double maxXOffset = 200;
	private double minXOffset = 0;
	

	public static void main(String[] args) {
		TestUtils.testApplication(new Test03LinesFractal(),512,512);
	}

	public void draw(IGF g) {
		g.background(255);
		g.stroke(0);

		double margin = 10;
		double size = 100;
		
		double x = margin;
		double y = margin;
		double w = x+size;//g.getWidth() - 2 * margin;
		double h = y+size;//g.getHeight() - 2 * margin;

		int depth = 0;
		int maxDepth = 3;
		drawGrid(g, x, y, w, h, depth, maxDepth);
	}

	private void drawGrid(IGF g, double x, double y, double w, double h,
			int depth, int maxDepth) {

		double pDepth = 1.0-((double)depth / maxDepth);
		g.strokeWeight(minStrokeWeight + pDepth
				* (maxStrokeWeight - minStrokeWeight));
		g.fill((int) (minGray + pDepth * (maxGray - minGray)));
		

		// draw vertical lines
		for (int i = 0; i < 3; i++) {
			double p = i / 2.0;
			double x1 = x + p * w;
			double y1 = y;
			double x2 = x1;
			double y2 = y + h;
			g.line(x1, y1, x2, y2);
		}

		// draw horizontal lines
		for (int i = 0; i < 3; i++) {
			double p = i / 2.0;
			double x1 = x;
			double y1 = y + p * h;
			double x2 = x + w;
			double y2 = y1;
			g.line(x1, y1, x2, y2);
		}

		if (depth < maxDepth) {
			double nextW = w / 2;
			double nextH = h / 2;
			int nextDepth = depth + 1;
			double xOffset = minXOffset + pDepth * (maxXOffset - minXOffset);
			for (int i = 0; i < 2; i++)
				for (int j = 0; j < 2; j++) {
					double nextX = x + i * w / 2 + xOffset;
					double nextY = y + j * h / 2 + xOffset;
					drawGrid(g, nextX, nextY, nextW, nextH, nextDepth, maxDepth);
				}
		}
	}
}
