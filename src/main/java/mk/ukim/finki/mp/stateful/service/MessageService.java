package mk.ukim.finki.mp.stateful.service;

import java.util.List;

import mk.ukim.finki.mp.stateful.model.Contact;

public interface MessageService {
	public double sendMessage(Contact contactFrom, List<Contact> contactsTo, String messageBody);
	public boolean validateContacts(String sender, List<String> receivers);
}
