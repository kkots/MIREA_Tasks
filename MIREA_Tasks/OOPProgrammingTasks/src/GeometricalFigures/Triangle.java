package GeometricalFigures;

public class Triangle implements Figure {
	//all sides of the triangle are equal
	private double rot;
	//if rot is 0 the tip of the triangle is looking in y axis direction
	private float x,y,a;
	public Triangle(float centerX, float centerY, float side, double rotation) {
		x = centerX;
		y = centerY;
		a = side;
		rot = rotation;
	}
	@Override
	public boolean hitTest(float px, float py) {
		double dvx = Math.cos(rot);
		double dvy = Math.sin(rot);
		double dhx = dvy;
		double dhy = -dvx;
		double dx = px - x;
		double dy = py - y;
		double dxdv = dx*dvx + dy*dvy;
		double dxdh = dx*dhx + dy*dhy;
		double x1 = a/Math.sqrt(3);
		double x2 = x1/2;
		return Math.abs(dxdh)<a/2 && dxdv > -x2 && dxdv < x1 && (dxdv + x2)/(x1 + x2) < Math.abs(dxdh)/a*2;
	}

}
