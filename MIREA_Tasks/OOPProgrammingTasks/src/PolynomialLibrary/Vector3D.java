package PolynomialLibrary;

public class Vector3D extends NumberField<Vector3D> {
	public double x, y, z;
	public Vector3D(double X, double Y, double Z) {
		x = X;
		y = Y;
		z = Z;
	}
	@Override
	public Vector3D add(Vector3D v) {
		return new Vector3D(x + v.x, y + v.y, z + v.z);
	}

	@Override
	public Vector3D multiply(Vector3D v) {
		return new Vector3D(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x);
	}
	
	@Override
	public String toString() {
		return "{x:"+x+", y:"+y+". z:"+z+"}";
	}
}
