package GeometricalFigures;
/**
 * Created by student on 17.11.2014.
 */
public class Rectangle implements Figure {
	private float w, h;
	private float x, y;// top-left
	public Rectangle(float topLeftX, float topLeftY, float width, float height) {
		x = topLeftX;
		y = topLeftY;
		w = width;
		h = height;
	}

	@Override
	public boolean hitTest(float px, float py) {
		return px > x && px < x + w && py > y && py < y + h;
	}
}