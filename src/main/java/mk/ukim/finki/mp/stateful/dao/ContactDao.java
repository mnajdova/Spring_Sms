package mk.ukim.finki.mp.stateful.dao;

import java.util.List;

import mk.ukim.finki.mp.stateful.model.Contact;

public interface ContactDao {
	public String getNameFromNumber(String number);
	public Contact getContactFromNumber(String number);
	public double getSaldo(Contact contact);
	public List<Contact> getAllContacts();
}
