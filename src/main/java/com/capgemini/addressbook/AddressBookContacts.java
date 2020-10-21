package com.capgemini.addressbook;

import com.opencsv.bean.CsvBindByName;

public class AddressBookContacts {
	@CsvBindByName
	public String firstName;

	@CsvBindByName
	public String lastName;

	@CsvBindByName
	public String address;

	@CsvBindByName
	public String city;

	@CsvBindByName
	public String state;

	@CsvBindByName
	public int zip;

	@CsvBindByName
	public String ph_no;

	@CsvBindByName
	public String email;

	public AddressBookContacts() {

	}

	public AddressBookContacts(String firstName, String lastName, String address, String city, String state, int zip,
			String ph_no, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.ph_no = ph_no;
		this.email = email;

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getaddress() {
		return address;
	}

	public void setaddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public String getPh_no() {
		return ph_no;
	}

	public void setPh_no(String ph_no) {
		this.ph_no = ph_no;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean equals(Object obj) {

		if (obj == this)
			return true;

		if (getClass() != obj.getClass())
			return false;

		AddressBookContacts c = (AddressBookContacts) obj;
		if (this.firstName.equals(c.firstName))
			return true;

		else
			return false;

	}

	public String toString() {
		return "First name : " + firstName + " ,Last name : " + lastName + " ,Address : " + address + " ,City : " + city
				+ " ,State : " + state + " ,Zip : " + zip + " ,Phone num : " + ph_no + " ,Email : " + email;
	}

}
