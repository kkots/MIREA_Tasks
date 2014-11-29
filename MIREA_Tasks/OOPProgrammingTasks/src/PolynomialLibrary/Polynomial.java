package PolynomialLibrary;

import java.util.ArrayList;

public class Polynomial<N extends NumberField<N>> {
	private ArrayList<N> coefficients;
	@SafeVarargs
	public Polynomial(N... args) {
		//a0,a1*x,a2*x^2...
		coefficients = new ArrayList<N>(args.length);
		for (N t : args) {
			coefficients.add(t);
		}
	}
	public Polynomial<N> add(Polynomial<N> p) {
		int s1 = coefficients.size();
		int s2 = p.coefficients.size();
		int max = Math.max(s1, s2);
		Polynomial<N> result = new Polynomial<N>();
		result.coefficients.ensureCapacity(max);
		for (int i = 0; i < max; i++) {
			N x1 = i < s1 ? coefficients.get(i) : null;
			N x2 = i < s2 ? p.coefficients.get(i) : null;
			if (x1 == null) {
				result.coefficients.set(i, x2);
			} else if (x2 == null) {
				result.coefficients.set(i, x1);
			} else {
				result.coefficients.set(i, x1.add(x2));
			}
		}
		return result;
	}
	public Polynomial<N> multiply(Polynomial<N> p) {
		Polynomial<N> result = new Polynomial<N>();
		result.coefficients.ensureCapacity(coefficients.size()+coefficients.size()-2);
		for (int i = 0; i < coefficients.size(); i++) {
			for (int j = 0; j < p.coefficients.size(); j++) {
				N x1 = coefficients.get(i);
				N x2 = p.coefficients.get(i);
				int c = i + j;
				N x0 = result.coefficients.get(c);
				if (x1 != null && x2 != null) {
					if (x0 == null) {
						result.coefficients.set(c, x1.multiply(x2));
					} else {
						result.coefficients.set(c, x0.add(x1.multiply(x2)));
					}
				}
			}
		}
		return result;
	}
	public Polynomial<N> multiplyByNumber(N x) {
		Polynomial<N> result = new Polynomial<N>();
		result.coefficients.ensureCapacity(coefficients.size());
		for (int i = 0; i < coefficients.size(); i ++) {
			N a = coefficients.get(i);
			if (a != null) result.coefficients.set(i, a.multiply(x));
		}
		return result;
	}
	public N compute(N x) {
		N result = x.getZero();
		for (int i = 0; i <coefficients.size(); i++) {
			N a = coefficients.get(i);
			if (a != null) result = result.add(a.multiply(x.power(i)));
		}
		return result;
	}
}
