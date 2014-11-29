package GeometricalFigures;
/**
 * Created by student on 17.11.2014.
 */
public class Circle implements Figure {
	private float r, x, y;// radius, x, y

	public Circle(float radius, float x, float y) {
		r = radius;
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean hitTest(float px, float py) {
		float dx = px - x;
		float dy = py - y;
		return dx * dx + dy * dy < r * r;
	}
}