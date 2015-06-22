package mk.ukim.finki.mp.stateful.service;

import java.util.List;

import mk.ukim.finki.mp.stateful.dao.MessageDao;
import mk.ukim.finki.mp.stateful.model.Contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MessageServiceImpl implements MessageService{

	@Autowired
	private MessageDao messageDao;

	@Override
	public double sendMessage(Contact contactFrom, List<Contact> contactsTo,
			String messageBody) {
		return messageDao.sendMessage(contactFrom, contactsTo, messageBody);
	}

	@Override
	public boolean validateContacts(String sender, List<String> receivers) {
		return messageDao.validateContacts(sender, receivers);
	}


}
