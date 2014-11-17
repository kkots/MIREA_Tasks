import java.util.ArrayList;

public class AddressBook {
	public ArrayList<Address> addresses = new ArrayList<Address>();

	public AddressBook() {
	}

	public Address addAddress(String name, String telephone, String email) {
		Address addr = new Address(name, telephone, email);
		addresses.add(addr);
		return addr;
	}

	public Address getByIndex(int index) {
		if (index >= addresses.size())
			return null;
		return addresses.get(index);
	}

	public String print(Address entry) {
		return "name:" + entry.name + "\ntelephone:" + entry.telephone
				+ "\nemail:" + entry.email;
	}
	public String[] printAll() {
		String[] ar = new String[addresses.size()*3];
		for (int i = 0; i < addresses.size(); i++) {
			Address elem = addresses.get(i);
			ar[i*3] = elem.name;
			ar[i*3 + 1] = elem.telephone;
			ar[i*3 + 2] = elem.email;
		}
		return ar;
	}

	public Address delete(int index) {
		if (index >= addresses.size())
			return null;
		Address addr = addresses.get(index);
		addresses.remove(index);
		return addr;
	}

	public int getCount() {
		return addresses.size();
	}

	public int[] findByName(String name) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < addresses.size(); i++) {
			if (addresses.get(i).name.indexOf(name) != -1) {
				result.add(i);
			}
		}
		int[] result2 = new int[result.size()];
		for (int i = 0; i < result2.length; i++) {
			result2[i] = result.get(i);
		}
		return result2;
	}
}
