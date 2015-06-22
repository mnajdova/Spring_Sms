package mk.ukim.finki.mp.stateful.sending;

import java.util.List;

import mk.ukim.finki.mp.stateful.model.Contact;

public interface SendingExecutor {
	public double sendSMS(Contact contactFrom, Contact contactTo, String smsBody);
	public double getSaldo(Contact contact);
	public boolean validateContacts(String sender, List<String> receiver);
}
