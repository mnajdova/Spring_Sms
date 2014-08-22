package mk.ukim.finki.mp.stateful.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sound.midi.Receiver;

import mk.ukim.finki.mp.stateful.dao.ContactDao;
import mk.ukim.finki.mp.stateful.model.Contact;
import mk.ukim.finki.mp.stateful.model.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ContactServiseImpl implements ContactServise{

	@Autowired
	private ContactDao contactDao;

	@Override
	public String getNameFromNumber(String number) {
		return contactDao.getNameFromNumber(number);
	}

	@Override
	public Contact getContactFromNumber(String number) {
		return contactDao.getContactFromNumber(number);
	}

	@Override
	public double getSaldo(Contact contact) {
		return contactDao.getSaldo(contact);
	}

	@Override
	public List<Contact> getAllContacts() {
		return contactDao.getAllContacts();
	}

	@Override
	public List<Message> getAllSentMessagesBetweenContacts(String senderPhone,
			String reseiverPhone) {
		Contact sender = getContactFromNumber(senderPhone);
		Contact receiver = getContactFromNumber(reseiverPhone);
		List<Message> messages = sender.getMessagesSent();
		List<Message> result = new ArrayList<>();
		Iterator<Message> it = messages.iterator();
		while(it.hasNext())
		{
			Message msg = it.next();
			if(msg.getContactsTo().contains(receiver))
			{
				result.add(msg);
			}
		}
		return result;
	}

	
}
