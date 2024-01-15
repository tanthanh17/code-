package Assessment3;

import java.util.Comparator;

public class Record {
	private String name, birthday, phoneNumber, address;

	public Record(String name, String birthday, String phoneNumber, String address) {
		this.name = name;
		this.birthday = birthday;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}

	public Record() {
	};

// getting and setting the variables here
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String toString() {
		return name + "\n" + birthday + "\n" + phoneNumber + "\n" + address;
	}

	public static Comparator<Record> nameComparator = new Comparator<Record>() {

		@Override
		public int compare(Record r1, Record r2) {
			String name1 = r1.getName();
			String name2 = r2.getName();
			return name1.compareTo(name2);
		}

	};

}
