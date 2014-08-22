package mk.ukim.finki.mp.stateful.dao;

import java.util.List;

import mk.ukim.finki.mp.stateful.model.Contact;
import mk.ukim.finki.mp.stateful.sending.SendingExecutor;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ContactDaoImpl implements ContactDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private SendingExecutor sendingExecutor;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public String getNameFromNumber(String number) {
		Contact contact = (Contact) getCurrentSession().get(Contact.class, number);
		if(contact!=null)
		{
			return contact.getNameContact();
		}
		else return null;
		/*
		@SuppressWarnings("unchecked")
		List<Contact> result = (List<Contact>) getCurrentSession()
				.createQuery("from Contact where phone=:number")
				.setParameter("number", number).list();
		if(result.size()==0)
			return null;
		else
			return result.get(0).getNameContact();*/
	}

	@Override
	public Contact getContactFromNumber(String number) {
		return (Contact) getCurrentSession().get(Contact.class, number);
		/*
		 @SuppressWarnings("unchecked")
		List<Contact> result = (List<Contact>) getCurrentSession()
				.createQuery("from Contact where phone=:number")
				.setParameter("number", number).list();
		if(result.size()==0)
			return null;
		else
			return result.get(0);*/
	}

	@Override
	public double getSaldo(Contact contact) {
		return sendingExecutor.getSaldo(contact);
	}

	@Override
	public List<Contact> getAllContacts() {
		List<Contact> result = getCurrentSession().createQuery("from Contact").list();
		return result;
	}
	
}
