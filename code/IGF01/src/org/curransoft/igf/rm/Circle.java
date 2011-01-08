package org.curransoft.igf.rm;

import org.curransoft.igf.IGF;

/**
 * A circle.
 * 
 * @author curran
 * 
 */
public class Circle extends AbstractGraphic {
	protected double x, y, radius;
	
	public Circle(double x, double y, double radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	
	@Override
	public void draw(IGF g) {
		//sets the fill, stroke and strokeWeight
		super.draw(g);
		g.circle(x, y, radius);
	}

	

	@Override
	public boolean containsPoint(double x, double y) {
		// perform a circle inside test
		double dx = x - this.x;
		double dy = y - this.y;
		return dx * dx + dy * dy < radius * radius;
	}
}
