package mk.ukim.finki.mp.stateful.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
public class Message {
	
	@Id
	@GeneratedValue
	private int id;
	
	
	@ManyToOne
	private Contact contactFrom;
	
	private String messageBody;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "contact_received",
			joinColumns = @JoinColumn(name = "MESSAGE_ID"),
			inverseJoinColumns = @JoinColumn(name = "CONTACT_ID"))
	private List<Contact> contactsTo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Contact getContactFrom() {
		return contactFrom;
	}

	public void setContactFrom(Contact contactFrom) {
		this.contactFrom = contactFrom;
	}

	public String getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

	public List<Contact> getContactsTo() {
		return contactsTo;
	}

	public void setContactsTo(List<Contact> contactsTo) {
		this.contactsTo = contactsTo;
	}

	
	
}
