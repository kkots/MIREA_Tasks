package PolynomialLibrary;

public class Main {
	public static void main(String[] args) {
		Polynomial<ComplexNumber> p = new Polynomial<ComplexNumber>(
			new ComplexNumber(1,1),
			new ComplexNumber(2,0)
		);
		System.out.println(p.compute(new ComplexNumber(0,1)));
		Polynomial<ComplexNumber> p2 = new Polynomial<ComplexNumber>(
			new ComplexNumber(3,-2),
			new ComplexNumber(-1,2),
			new ComplexNumber(3,3)
		);
		System.out.println(p.multiply(p2));
		System.out.println(p2.multiply(p));
	}
}
