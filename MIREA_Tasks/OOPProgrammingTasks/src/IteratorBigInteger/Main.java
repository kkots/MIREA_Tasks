package IteratorBigInteger;

import java.math.BigInteger;

public class Main {
	public static void main (String[] args) {
		int count = 0;
		BigIntegerIterable collection = BigIntegerIterable.FIBONACCI;
		for (BigInteger a : collection) {
			System.out.println(a);
			count++;
			if (count > 20) break;
		}
		collection = BigIntegerIterable.NATURAL;
		for (BigInteger a : collection) {
			System.out.println(a);
			count++;
			if (count > 40) break;
		}
	}
}
