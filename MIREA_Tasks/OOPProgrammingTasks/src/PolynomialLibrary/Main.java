package PolynomialLibrary;

public class Main {
	public static void main(String[] args) {
		Polynomial<ComplexNumber> p = new Polynomial<ComplexNumber>(
			new ComplexNumber(1,1),
			new ComplexNumber(2,0)
		);
		System.out.println(p.compute(new ComplexNumber(0,1)));
	}
}
