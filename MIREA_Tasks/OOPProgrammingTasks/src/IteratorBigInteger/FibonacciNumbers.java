package IteratorBigInteger;

import java.math.BigInteger;
import java.util.Iterator;

public class FibonacciNumbers implements Iterator<BigInteger> {
	private BigInteger x1,x2;
	public FibonacciNumbers() {
		x1 = BigInteger.ZERO;
		x2 = BigInteger.ONE;
	}
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public BigInteger next() {
		// TODO Auto-generated method stub
		BigInteger x3 = x1.add(x2);
		x1 = x2;
		x2 = x3;
		return x1;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

}
