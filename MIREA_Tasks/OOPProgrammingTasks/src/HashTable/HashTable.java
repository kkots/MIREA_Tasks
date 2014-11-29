package HashTable;

import java.util.ArrayList;

public class HashTable implements MyMap<Integer, Integer> {
	private ArrayList<Integer> list;
	
	public HashTable() {
		list = new ArrayList<Integer>();
	}
	@Override
	public Integer get(Integer key) {
		return list.get(key);
	}

	@Override
	public void put(Integer key, Integer value) {
		list.ensureCapacity(key);
		list.set(key, value);
	}

	@Override
	public void remove(Integer key) {
		list.remove(key);
		list.trimToSize();
	}

	@Override
	public int size() {
		int count = 0;
		for (Integer a : list) {
			if (a != null) count++;
		}
		return count;
	}
}
