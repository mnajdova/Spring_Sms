package mk.ukim.finki.mp.stateful.sending;

import java.util.List;

import mk.ukim.finki.mp.stateful.dao.ContactDao;
import mk.ukim.finki.mp.stateful.model.Contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MockSendingExecutor implements SendingExecutor{

	@Autowired
	ContactDao contactDao;

	@Override
	public double sendSMS(Contact contactFrom, Contact contactTo, String smsBody) {
		System.out.println("Sending message from"+ contactFrom.getNameContact()+" to "+contactTo.getNameContact());
		return 5.0;
	}

	@Override
	public double getSaldo(Contact contact) {
		return 100.0;
	}

	@Override
	public boolean validateContacts(String sender, List<String> receivers) {
		Contact c=contactDao.getContactFromNumber(sender);
		if(c==null)
			return false;
		for(String receiver : receivers)
		{
			c = contactDao.getContactFromNumber(receiver);
			if(c==null)
				return false;
		}
		return true;
	}

}
