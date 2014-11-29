package IteratorBigInteger;

import java.math.BigInteger;
import java.util.Iterator;

public class NaturalNumbers implements Iterator<BigInteger> {
	private BigInteger x = BigInteger.ZERO;
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public BigInteger next() {
		// TODO Auto-generated method stub
		x = x.add(BigInteger.ONE);
		return x;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

}
