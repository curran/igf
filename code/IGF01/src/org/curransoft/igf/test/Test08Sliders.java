package org.curransoft.igf.test;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import org.curransoft.igf.IGF;
import org.curransoft.igf.IGFApplicationAdapter;
import org.curransoft.igf.util.Interval;

/**
 * A test of sliders
 * 
 * @author curran
 * 
 */
public class Test08Sliders extends IGFApplicationAdapter {

	public static void main(String[] args) {
		TestUtils.testApplication(new Test08Sliders(), new Test08Sliders());
	}
	
	/**
	 * the global list of sliders to keep track of
	 */
	List<Slider> sliders = new ArrayList<Slider>();
	/**
	 * the global font used by sliders to draw their text
	 */
	int fontID;
	/**
	 * the slider which is currently being dragged
	 */
	Slider sliderBeingDragged;

	public Test08Sliders(){
		sliders.add(new Slider(20,30,280,30,0  ,1  ,0.5  ,15));
		sliders.add(new Slider(20,60,280,60,0  ,1  ,0.5  ,15));
	}
	
	class Slider {
		private boolean isBeingDragged = false;
		private Interval x, y, interval;
		private double value;
		private double knobX, knobY;
		double knobRadius;

		public Slider(double x1, double y1, double x2, double y2, double min,
				double max, double value, double knobSize) {
			this.interval = new Interval(min, max);
			this.x = new Interval(x1, x2);
			this.y = new Interval(y1, y2);
			this.knobRadius = knobSize;

			this.isBeingDragged = false;
			this.computeKnobXY();
			sliders.add(this);

		}

		public void startDrag() {
			this.isBeingDragged = true;
		}

		public void endDrag() {
			this.isBeingDragged = false;
		}

		public void computeKnobXY() {
			this.knobX = this.interval.transformTo(this.x, value);
			this.knobY = this.interval.transformTo(this.y, value);
		}

		public void draw(IGF g) {
			double x1 = x.getMin();
			double x2 = x.getMax();
			double y1 = y.getMin();
			double y2 = y.getMax();

			g.stroke(0);
			g.strokeWeight(2);
			g.line(x1, y1, x2, y2);
			g.fill(0);
			g.circle(this.knobX, this.knobY, knobRadius);
		}

		/**
		 * Sets the value of this slider. This has no effect when the user is
		 * dragging the slider. Returns true if successfully set, false when: -
		 * in a drag - the new value is the same as the old value
		 */
		public boolean setValue(double newValue) {
			if (this.isBeingDragged == true || value == newValue)
				return false;
			else
				return this._setValue(newValue);
		}

		private boolean _setValue(double newValue) {
			double min = this.interval.getMin();
			double max = this.interval.getMax();
			value = (newValue < min) ? min
					: ((newValue > max) ? max : newValue);
			this.computeKnobXY();
			return true;
		}

		public void setValueFromPixelCoordinates(double xPixel, double yPixel) {
			if (this.x.getMin() == this.x.getMax())
				this._setValue(this.y.transformTo(this.interval, yPixel));
			if (this.y.getMin() == this.y.getMax())
				this._setValue(this.x.transformTo(this.interval, xPixel));
			else
				this._setValue(this.x.transformTo(this.interval, xPixel)
						+ this.y.transformTo(this.interval, yPixel));
		}

		public boolean containsPoint(double xPixel, double yPixel) {
			double dx = xPixel - this.knobX;
			double dy = yPixel - this.knobY;
			return dx * dx + dy * dy < knobRadius * knobRadius;
		}

		public boolean isBeingDragged() {
			return this.isBeingDragged;
		}

		public void setMin(double newMin) {
			this.interval.setMin(newMin);
		}

		public void setMax(double newMax) {
			this.interval.setMax(newMax);
		}
	}

	@Override
	public void setup(IGF g) {
		fontID = g.loadFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
	}

	@Override
	public void draw(IGF g) {
		g.background(255);
		for (Slider s : sliders)
			s.draw(g);
	}

	@Override
	public void pointPressed(IGF g, int id, double x, double y) {
		sliderBeingDragged = null;
		for (Slider s : sliders)
			if (s.containsPoint(x, y)) {
				sliderBeingDragged = s;
				sliderBeingDragged.startDrag();
				break;
			}
	}

	@Override
	public void pointDragged(IGF g, int id, double x, double y, double px,
			double py) {
		if (sliderBeingDragged != null)
			sliderBeingDragged.setValueFromPixelCoordinates(x, y);
	}

	@Override
	public void pointReleased(IGF g, int id, double x, double y) {
		if (sliderBeingDragged != null) {
			sliderBeingDragged.endDrag();
			sliderBeingDragged = null;
		}
	}
}
