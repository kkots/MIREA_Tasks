package IteratorBigInteger;

import java.math.BigInteger;
import java.util.Iterator;

public enum BigIntegerIterable implements Iterable<BigInteger> {
	FIBONACCI, NATURAL;
	
	
	//public BigIntegerIterable(IteratorType iteratorType) {
		//this.iteratorType = iteratorType;
	//}
	@Override
	public Iterator<BigInteger> iterator() {
		// TODO Auto-generated method stub
		switch (this) {
			case FIBONACCI: return new FibonacciNumbers();
			case NATURAL: return new NaturalNumbers();
		}
		throw new EnumConstantNotPresentException(BigIntegerIterable.class, null);
	}
}
