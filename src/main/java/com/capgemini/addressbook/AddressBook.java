package com.capgemini.addressbook;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.google.gson.Gson;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class AddressBook {
	public static Map<String, AddressBook> hm = new HashMap<String, AddressBook>();
	public static Map<String, ArrayList<AddressBookContacts>> cityList = new HashMap<>();
	public static Map<String, ArrayList<AddressBookContacts>> stateList = new HashMap<>();

	public static String HOME = System.getProperty("user.home");
	public static String DIRECTORY = "addressbookoutputfile";
	public static String DIRECTORY_JSON = "jsonoutputfile";

	String addressBookName;
	Scanner sc = new Scanner(System.in);

	ArrayList<AddressBookContacts> list;

	public AddressBook() {
		list = new ArrayList<AddressBookContacts>();

	}

	public AddressBook(String addressBookName) {
		list = new ArrayList<AddressBookContacts>();
		this.addressBookName = addressBookName;

	}

	public AddressBookContacts create(String firstName, String lastName, String address, String city, String state,
			int zip, String ph_no, String email) {

		AddressBookContacts contact = new AddressBookContacts(firstName, lastName, address, city, state, zip, ph_no,
				email);
		return contact;
	}

	public void addContactDetails(AddressBookContacts contact) {

		int count = (int) list.stream().filter(i -> i.equals(contact)).count();
		if (count > 0)
			System.out.println("You tried to add duplicate contact, Contact already exits");

		else
			list.add(contact);

		if (cityList.containsKey(contact.getCity())) {
			cityList.get(contact.getCity()).add(contact);
		} else {
			cityList.put(contact.getCity(), new ArrayList<AddressBookContacts>());
			cityList.get(contact.getCity()).add(contact);
		}

		if (stateList.containsKey(contact.getState())) {
			stateList.get(contact.getState()).add(contact);
		} else {
			stateList.put(contact.getState(), new ArrayList<AddressBookContacts>());
			stateList.get(contact.getState()).add(contact);
		}
	}

	public void editContactDetails(String firstName) {

		int pos = 0;

		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getFirstName().equals(firstName))
				pos = i;

		while (true) {
			System.out.println("Choose the option to edit");
			System.out.println("1.Edit Last name");
			System.out.println("2.Edit Address");
			System.out.println("3.Edit City");
			System.out.println("4.Edit State");
			System.out.println("5.Edit Zip");
			System.out.println("6.Edit Phone Number");
			System.out.println("7.Edit Email");
			System.out.println("8.Exit");

			int choice = sc.nextInt();

			if (choice == 8)
				break;

			switch (choice) {
			case 1:
				System.out.println("Enter Last name for editing");
				list.get(pos).setLastName(sc.next());
				break;

			case 2:
				System.out.println("Enter Address for editing");
				list.get(pos).setaddress(sc.next());
				break;

			case 3:
				System.out.println("Enter city for editing");
				list.get(pos).setCity(sc.next());
				break;

			case 4:
				System.out.println("Enter state for editing");
				list.get(pos).setState(sc.next());
				break;

			case 5:
				System.out.println("Enter Zip for editing");
				list.get(pos).setZip(sc.nextInt());
				break;

			case 6:
				System.out.println("Enter Phone Number for editing");
				list.get(pos).setPh_no(sc.next());
				break;

			case 7:
				System.out.println("Enter email for editing");
				list.get(pos).setEmail(sc.next());
				break;

			}
		}
	}

	public void deleteContactDetails(String firstName) {
		int pos = 0;

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getFirstName().equals(firstName))
				pos = i;
		}
		list.remove(pos);
	}

	// UC 14
	public void writeToCSVFile(String AddressBookName)
			throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

		Path pathLoc = Paths.get("C:\\Users\\abhij\\eclipse-workspace\\AddressBook\\src\\main\\java\\addressbookoutputfile");
		if (Files.notExists(pathLoc))
			Files.createDirectory(pathLoc);

		Path fileLoc = Paths.get(pathLoc + "\\" + AddressBookName + ".csv");
		if (Files.notExists(fileLoc))
			Files.createFile(fileLoc);

		try (Writer writer = Files.newBufferedWriter(Paths.get(fileLoc.toUri()));) {

			StatefulBeanToCsv<AddressBookContacts> beanToCsv = new StatefulBeanToCsvBuilder(writer)
					.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();

			beanToCsv.write(list);

		}

	}

	public void readFromCSVFile(String AddressBookName) throws IOException {
		Path pathLoc = Paths.get("C:\\Users\\abhij\\eclipse-workspace\\AddressBook\\src\\main\\java\\addressbookoutputfile");
		Path fileLoc = Paths.get(pathLoc + "\\" + AddressBookName + ".csv");
		try (Reader reader = Files.newBufferedReader(Paths.get(fileLoc.toUri()));) {

			CsvToBean<AddressBookContacts> csvToBean = new CsvToBeanBuilder(reader).withType(AddressBookContacts.class)
					.withIgnoreLeadingWhiteSpace(true).build();

			Iterator<AddressBookContacts> AddressBookIterator = csvToBean.iterator();

			while (AddressBookIterator.hasNext()) {
				AddressBookContacts contact = AddressBookIterator.next();
				System.out.println("Firstname : " + contact.firstName);
				System.out.println("Lastname : " + contact.lastName);
				System.out.println("Address : " + contact.address);
				System.out.println("City : " + contact.city);
				System.out.println("State : " + contact.state);
				System.out.println("Zip : " + contact.zip);
				System.out.println("Phone number : " + contact.ph_no);
				System.out.println("Email : " + contact.email);
				System.out.println("**********************************");
			}
		}
	}

	// UC 15

	public void writeToJsonFile(String AddressBookName) throws IOException {
		Path pathLoc = Paths.get("C:\\Users\\abhij\\eclipse-workspace\\AddressBook\\src\\main\\java\\jsonoutputfile");
		if (Files.notExists(pathLoc))
			Files.createDirectory(pathLoc);

		String SAMPLE_JSON_FILE = "C:\\Users\\abhij\\eclipse-workspace\\AddressBook\\src\\main\\java\\jsonoutputfile" +".json";
		Gson gson = new Gson();
		String json = gson.toJson(list);
		FileWriter writer = new FileWriter(SAMPLE_JSON_FILE);
		writer.write(json);
		writer.close();
	}

	public void readFromJsonFile(String AddressBookName) throws IOException {
		Path pathLoc = Paths.get("C:\\Users\\abhij\\eclipse-workspace\\AddressBook\\src\\main\\java\\jsonoutputfile");
		if (Files.notExists(pathLoc))
			Files.createDirectory(pathLoc);

		String SAMPLE_JSON_FILE = "C:\\Users\\abhij\\eclipse-workspace\\AddressBook\\src\\main\\java\\jsonoutputfile"+ ".json";
		Gson gson = new Gson();

		BufferedReader br = new BufferedReader(new FileReader(SAMPLE_JSON_FILE));
		AddressBookContacts[] contact = gson.fromJson(br, AddressBookContacts[].class);
		List<AddressBookContacts> contactList = Arrays.asList(contact);
		for (AddressBookContacts a : contactList) {
			System.out.println("Firstname : " + a.firstName);
			System.out.println("Lastname : " + a.lastName);
			System.out.println("Address : " + a.address);
			System.out.println("City : " + a.city);
			System.out.println("State : " + a.state);
			System.out.println("Zip : " + a.zip);
			System.out.println("Phone number : " + a.ph_no);
			System.out.println("Email : " + a.email);
			System.out.println("**********************************");
		}
	}

}
