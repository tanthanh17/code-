package Assessment3;

import java.io.*;
import java.util.*;

public class PhonebookManager {

	// read phonebook text file using scanner class and save into arraylist
	public void loadRecords(String pathName, ArrayList<Record> records) {

		try {
			Scanner sf = new Scanner(new File(pathName));
			while (sf.hasNextLine()) {
				Record record = new Record();
				record.setName(sf.nextLine());
				record.setBirthday(sf.nextLine());
				record.setPhoneNumber(sf.nextLine());
				record.setAddress(sf.nextLine());
				records.add(record);
			}
			sf.close();
		} catch (FileNotFoundException e) {
			System.out.println("Couldn't found the file!");
			e.printStackTrace();
		}

	}

	public void loadQuery(ArrayList<Record> records, ArrayList<String> result) {

		String que;
		boolean found = false;
		int index = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("-----------------Please enter the query:---------------" + "\n");
		que = sc.nextLine();

		// seperate the query one by one word
		ArrayList<String> word = new ArrayList<>();
		String[] words = que.trim().split("[;\\s+;]");
		for (int i = 0; i < words.length; i++) {
			word.add(words[i]);
		}
		// remove character such as space
		for (int i = 0; i < word.size(); i++) {
			if (word.get(i).equals("")) {
				word.remove(word.get(i));
			}
		}

//		add name jimmy; birthday 09-09-1990; phone 88884444; address 9001 Chester Crescent
		if (word.get(0).equals("add")) {

//			save query value in new value
			String name = word.get(2);
			String birthday = word.get(4);
			String phoneNumber = word.get(6);
			String add1 = word.get(8);
			String add2 = word.get(9);
			String add3 = word.get(10);
			String address = add1.concat(" ").concat(add2).concat(" ").concat(add3);

//			find if the record exist already by name and birthday
			for (int i = 0; i < records.size(); i++) {
				if ((records.get(i).getName().equals(name)) && (records.get(i).getBirthday().equals(birthday))) {
					found = true;
					index = i;
				}
			}
			if (found) {
				records.get(index).setPhoneNumber(phoneNumber);
				records.get(index).setAddress(address);
				System.out.println("------------------Record updated!-----------------" + "\n");

//				save query 
				result.add("\n" + "Query: " + que + "\n");
				result.add((records.get(index)).toString() + "\n");
			} else {
				Record newRecord = new Record(name, birthday, phoneNumber, address);
				records.add(newRecord);
				System.out.println("------------------New record added!-----------------" + "\n");

				// save query
				result.add("\n" + "Query: " + que + "\n");
				result.add(newRecord.toString() + "\n");
			}

			System.out.println(records);
			index = 0;
			found = false;

//			delete method
		} else if (word.get(0).equals("delete")) {
//			clone records
			ArrayList<Record> tempRecord = new ArrayList<>(records);
			String name = word.get(2);

//			search by name
			if (word.contains("birthday") == false) {
				for (int i = 0; i < tempRecord.size(); i++) {
					if (tempRecord.get(i).getName().equals(name)) {
						tempRecord.remove(i);
						System.out.println("------------------Record deleted!-----------------" + "\n");

//						save query
						result.add("\n" + "Query: " + que + "\n");
						result.add((records.get(i)).toString() + "\n");
					}
				}
			} else {
//				search by name and birthday
				String birthday = word.get(4);
				for (int i = 0; i < tempRecord.size(); i++) {
					if (tempRecord.get(i).getName().equals(name)
							&& (tempRecord.get(i).getBirthday().equals(birthday))) {
						tempRecord.remove(i);
						System.out.println("------------------Record deleted1!-----------------" + "\n");

//						save query
						result.add("\n" + "Query: " + que + "\n");
						result.add((records.get(i)).toString() + "\n");
					}
				}
			}

//			reduce records array length by one after deletion
			records.remove(records.size() - 1);
			for (int i = 0; i < tempRecord.size(); i++) {
//				update records with temprory record data
				String nameTemp = tempRecord.get(i).getName();
				String birthdayTemp = tempRecord.get(i).getBirthday();
				String phoneNumberTemp = tempRecord.get(i).getPhoneNumber();
				String addressTemp = tempRecord.get(i).getAddress();

				records.get(i).setName(nameTemp);
				records.get(i).setBirthday(birthdayTemp);
				records.get(i).setPhoneNumber(phoneNumberTemp);
				records.get(i).setAddress(addressTemp);

			}
			System.out.println(records);
		} else if (word.get(0).equals("query")) {
//			clone Record
			List<Record> tempRecord = new ArrayList<Record>();

			if (word.contains("name")) {
				String name = word.get(2);
				for (int i = 0; i < records.size(); i++) {
					if (records.get(i).getName().equals(name)) {
						tempRecord.add(records.get(i));
					}
				}
			} else if (word.contains("birthday")) {
				String birthday = word.get(2);
				for (int i = 0; i < records.size(); i++) {
					if (records.get(i).getBirthday().equals(birthday)) {
						tempRecord.add(records.get(i));
					}
				}
			} else if (word.contains("phoneNumber")) {
				String phoneNumber = word.get(2);
				for (int i = 0; i < records.size(); i++) {
					if (records.get(i).getPhoneNumber().equals(phoneNumber)) {
						tempRecord.add(records.get(i));
					}
				}
			} else if (word.contains("all")) {
				tempRecord = records;
			} else
				System.out.println("---------------Wrong query!" + "\n");
			sortAlphabetically(tempRecord);
			System.out.println("name  birthday  phoneNumber address");
			for (Record e : tempRecord) {
				System.out
						.println(e.getName() + " " + e.getBirthday() + " " + e.getPhoneNumber() + " " + e.getAddress());
			}
		} else
			System.out.println("-----------Wrong query!-------------" + "\n");

	}

	public void sortAlphabetically(List<Record> tempRecord) {
		Collections.sort(tempRecord, Record.nameComparator);
	}

	public void updateRecordFile(String fileName, ArrayList<Record> records) throws FileNotFoundException {
		File upFile = new File(fileName);
		PrintWriter output = new PrintWriter(upFile);

		for (Record r : records) {
			output.println(r);
		}
		output.close();
	}

	public void updateResultFile(String fileName, ArrayList<String> result) throws FileNotFoundException {
		File upFile = new File(fileName);
		PrintWriter output = new PrintWriter(upFile);

		for (String r : result) {
			output.println(r);
		}
		output.close();
	}
}