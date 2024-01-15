package Assessment3;

import java.io.*;
import java.util.*;

public class PhoneBook {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		boolean conti = true;

		// creating array list and setting the path
		ArrayList<Record> records = new ArrayList<>();
		ArrayList<String> result = new ArrayList<String>();
		String recordsFile = "D:\\\\ICT711\\\\Assessment3\\\\PhoneBookFile.txt";
		String resultFile = "D:\\ICT711\\Assessment3\\Result.txt";
		// object creation, pass the path and record into child class
		PhonebookManager phonebookManager = new PhonebookManager();
		phonebookManager.loadRecords(recordsFile, records);
		phonebookManager.loadQuery(records, result);
		phonebookManager.updateRecordFile(recordsFile, records);
		phonebookManager.updateResultFile(resultFile, result);

		while (conti == true) {
			System.out.println("------------Do you want to continue? y/n---------------" + "\n");
			String co = sc.nextLine();
			if (co.equals("y")) {
				phonebookManager.loadQuery(records, result);
				phonebookManager.updateRecordFile(recordsFile, records);
				conti = true;
			} else {

				phonebookManager.updateResultFile(resultFile, result);
				System.out.println("----------------Record saved in Result.txt!---------------");
				System.exit(0);
			}
		}
	}

}
// read the Query and check if the reccord exists
// ArrayList<String> listTemp = new ArrayList<String>();
// System.out.println("Please enter the query!");
// Scanner sq = new Scanner(System.in);
//
// String temp = "QUERY name nomi";
//
// boolean isln = list.containsAll();
// System.out.println(isln);