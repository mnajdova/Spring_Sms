package mk.ukim.finki.mp.stateful.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mk.ukim.finki.mp.stateful.model.Contact;
import mk.ukim.finki.mp.stateful.model.Message;
import mk.ukim.finki.mp.stateful.sending.SendingExecutor;

@Repository
public class MessageDaoImpl implements MessageDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private SendingExecutor sendingExecutor;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	/*
	@Override
	public double sendMessage(Contact contactFrom, List<Contact> contactsTo,
			String messageBody) {
		Message message = new Message();
		message.setContactTo(contactTo);
		message.setContactFrom(contactFrom);
		message.setMessageBody(messageBody);
		getCurrentSession().save(message);
		return sendingExecutor.sendSMS(contactFrom, contactTo, messageBody);
	}*/

	@Override
	public double sendMessage(Contact contactFrom, List<Contact> contactsTo,
			String messageBody) {
		Message message = new Message();
		message.setContactFrom(contactFrom);
		message.setContactsTo(contactsTo);
		message.setMessageBody(messageBody);
		getCurrentSession().save(message);
		
		double result=0.0;
		
		for(Contact c : contactsTo)
		{
			result+=sendingExecutor.sendSMS(contactFrom, c, messageBody);
		}
		return result;
	}

	@Override
	public boolean validateContacts(String sender, List<String> receivers) {
		return sendingExecutor.validateContacts(sender, receivers);
	}
	
}
