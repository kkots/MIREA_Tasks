package IteratorBigInteger;

import java.math.BigInteger;
import java.util.Iterator;

public class BigIntegerIterable implements Iterable<BigInteger> {
	public static int FIBONACCI = 1;
	public static int NATURAL = 2;
	
	private Iterator<BigInteger> iteratorInstance;
	
	public BigIntegerIterable(int iteratorType) {
		switch (iteratorType) {
			case 1: iteratorInstance = new FibonacciNumbers(); break;
			case 2: iteratorInstance = new NaturalNumbers(); break;
		}
	}
	@Override
	public Iterator<BigInteger> iterator() {
		// TODO Auto-generated method stub
		return iteratorInstance;
	}

}
