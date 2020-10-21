package com.capgemini.addressbook;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class AddressBookMain {

	public static final Scanner SC = new Scanner(System.in);

	public static void AddressBookList() {
		if (AddressBook.hm.isEmpty()) {
			System.out.println("No AddressBook Exists, add new AddressBook First");
			System.out.println("Enter name of address book to be created");
			String name = SC.next();
			AddressBook obj = new AddressBook();
			AddressBook.hm.put(name, obj);
			System.out.println("Address Book Created");
		}
		for (Map.Entry<String, AddressBook> ab : AddressBook.hm.entrySet())
			System.out.println(ab.getKey());
	}

	public static void main(String[] args) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		System.out.println("Welcome to Address Book Program");
		String firstName, lastName, address, city, state, ph_no, email;
		int zip;
		while (true) {

			System.out.println("Choose your option");
			System.out.println("1.Add a contact");
			System.out.println("2.Edit a contact");
			System.out.println("3.Delete a contact");
			System.out.println("4.Create new address book");
			System.out.println("5.Write Address Book to file");
			System.out.println("6.Read Address Book from file");
			System.out.println("7.Exit");

			int choice = SC.nextInt();

			if (choice == 7)
				break;

			switch (choice) {

			case 1:

				AddressBookList();
				System.out.println("Enter the name of address book to add contact");
				String abook1 = SC.next();
				AddressBook a1 = AddressBook.hm.get(abook1);
				boolean flag = true;
				while (flag) {
					System.out.println("Enter the first name");
					firstName = SC.next();
					System.out.println("Enter the last name");
					lastName = SC.next();
					System.out.println("Enter the Address");
					address = SC.next();
					System.out.println("Enter the city");
					city = SC.next();
					System.out.println("Enter the state");
					state = SC.next();
					System.out.println("Enter the zip");
					zip = SC.nextInt();
					System.out.println("Enter the Phone Number");
					ph_no = SC.next();
					System.out.println("Enter the Email Id");
					email = SC.next();
					AddressBookContacts c = a1.create(firstName, lastName, address, city, state, zip, ph_no, email);
					a1.addContactDetails(c);

					System.out.println("Do You want to add more Contacts?(Y/N)");
					String response = SC.next();
					if (response.equals("Y"))
						flag = true;
					else
						flag = false;
				}

				break;
			case 2:
				AddressBookList();
				System.out.println("Enter the name of address book to add contact");
				String abook2 = SC.next();
				AddressBook a2 = AddressBook.hm.get(abook2);
				System.out.println("Enter the first name to edit");
				firstName = SC.next();
				a2.editContactDetails(firstName);
				System.out.println("Contact Details Editted");
				break;

			case 3:
				AddressBookList();
				System.out.println("Enter the name of address book to add contact");
				String abook3 = SC.next();
				AddressBook a3 = AddressBook.hm.get(abook3);
				System.out.println("Enter the first name to Delete");
				firstName = SC.next();
				a3.deleteContactDetails(firstName);
				System.out.println("Contact Details Deleted");
				break;

			case 4:

				System.out.println("Enter name of address book to be created");
				String name = SC.next();
				AddressBook obj = new AddressBook();
				AddressBook.hm.put(name, obj);
				System.out.println("Address Book Created");
				break;

			case 5:
				AddressBookList();
				System.out.println("Enter the name of address Book to write");
				String abook4 = SC.next();
				AddressBook a4 = AddressBook.hm.get(abook4);
				System.out.println("Writing to file");
				try {
					a4.writeToCSVFile(abook4);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			case 6:
				AddressBookList();
				System.out.println("Enter the name of address Book to read");
				String abook5 = SC.next();
				AddressBook a5 = AddressBook.hm.get(abook5);
				System.out.println("Reading from file");
				try {
					a5.readFromCSVFile(abook5);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}

		}
	}

}
