package HashTable;

import java.util.Arrays;

public class HashTable implements MyMap<String, Integer> {
	//guaranteed to use minimum amounts of memory
	private String[] indexes;
	private Integer[] values;
	//indexes.length == values.length ALWAYS
	
	public HashTable() {
		indexes = new String[0];
		values = new Integer[0];
	}

	@Override
	public int size() {
		return indexes.length;
	}
	@Override
	public Integer get(String key) {
		for (int i = 0; i < indexes.length; i++) {
			if (indexes[i].equals(key)) return values[i];
		}
		return null;
	}
	@Override
	public void put(String key, Integer value) {
		for (int i = 0; i < indexes.length; i++) {
			if (indexes[i].equals(key)) {
				values[i] = value;
				return;
			}
		}
		indexes = Arrays.copyOf(indexes, indexes.length + 1);
		values = Arrays.copyOf(values, values.length + 1);
		indexes[indexes.length - 1] = key;
		values[values.length - 1] = value;
	}
	@Override
	public void remove(String key) {
		for (int i = 0; i < indexes.length; i++) {
			if (indexes[i].equals(key)) {
				for (int j = i; j < indexes.length - 1; j++) {
					indexes[j] = indexes[j + 1];
					values[j] = values[j + 1];
				}
				indexes = Arrays.copyOf(indexes, indexes.length - 1);
				values = Arrays.copyOf(values, values.length - 1);
				return;
			}
		}
	}
}
