package IteratorBigInteger;

import java.math.BigInteger;
import java.util.Iterator;

public class NaturalNumbers implements Iterator<BigInteger> {
	private BigInteger x = BigInteger.ONE;
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public BigInteger next() {
		// TODO Auto-generated method stub
		BigInteger previous = x;
		x = x.add(BigInteger.ONE);
		return previous;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException("No array is lying under this iterator.");
	}

}
