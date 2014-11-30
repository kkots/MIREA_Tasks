package PolynomialLibrary;

import java.util.ArrayList;
import java.util.Arrays;

public class Polynomial<N extends NumberField<N>> {
	private ArrayList<N> coefficients;
	@SafeVarargs
	public Polynomial(N... args) throws IllegalArgumentException {
		//a0,a1*x,a2*x^2...
		if (args.length == 0) throw new IllegalArgumentException("May not create empty polynomials");
		for (N t : args) {
			if (t == null) throw new IllegalArgumentException("Polynomial may not contain null elements.");
		}
		coefficients = new ArrayList<N>(Arrays.asList(args));
	}
	private Polynomial() {
		//for internal use only
		coefficients = new ArrayList<N>();
	}
	public Polynomial<N> add(Polynomial<N> p) {
		int s1 = coefficients.size();
		int s2 = p.coefficients.size();
		int max = Math.max(s1, s2);
		Polynomial<N> result = new Polynomial<N>();
		for (int i = 0; i < max; i++) {
			N x1 = i < s1 ? coefficients.get(i) : null;
			N x2 = i < s2 ? p.coefficients.get(i) : null;
			if (x1 == null) {
				result.coefficients.add(i, x2);
			} else if (x2 == null) {
				result.coefficients.add(i, x1);
			} else {
				result.coefficients.add(i, x1.add(x2));
			}
		}
		return result;
	}
	public Polynomial<N> multiply(Polynomial<N> p) {
		Polynomial<N> result = new Polynomial<N>();
		//result.coefficients.ensureCapacity(coefficients.size()+coefficients.size()-2);
		for (int i = 0; i < coefficients.size(); i++) {
			for (int j = 0; j < p.coefficients.size(); j++) {
				N x1 = coefficients.get(i);
				N x2 = p.coefficients.get(j);
				int c = i + j;
				if (result.coefficients.size() <= c) {
					result.coefficients.add(c, x1.multiply(x2));
				} else {
					result.coefficients.set(c, result.coefficients.get(c).add(x1.multiply(x2)));
				}
			}
		}
		return result;
	}
	public Polynomial<N> multiplyByNumber(N x) {
		Polynomial<N> result = new Polynomial<N>();
		result.coefficients.ensureCapacity(coefficients.size());
		for (int i = 0; i < coefficients.size(); i ++) {
			result.coefficients.set(i, coefficients.get(i).multiply(x));
		}
		return result;
	}
	public N compute(N x) {
		N result = coefficients.get(coefficients.size()-1);
		for (int i = coefficients.size()-2; i >= 0; i--) {
			N a = coefficients.get(i);
			result = a.add(result.multiply(x));
		}
		return result;
	}
	public String toString() {
		String s = coefficients.get(0)+"+";
		for (int i = 1; i < coefficients.size(); i++) {
			N n = coefficients.get(i);
			s += n+"*x^"+i+"+";
		}
		return s.substring(0, s.length() - 1);
	}
	
}
