package mk.ukim.finki.mp.stateful.service;

import java.util.List;

import mk.ukim.finki.mp.stateful.model.Contact;
import mk.ukim.finki.mp.stateful.model.Message;

public interface ContactServise {
	public String getNameFromNumber(String number);
	public Contact getContactFromNumber(String number);
	public double getSaldo(Contact contact);
	public List<Contact> getAllContacts();
	public List<Message> getAllSentMessagesBetweenContacts(String senderPhone, String reseiverPhone);
}
