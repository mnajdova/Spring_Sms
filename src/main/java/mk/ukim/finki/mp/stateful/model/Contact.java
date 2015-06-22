package mk.ukim.finki.mp.stateful.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
public class Contact {

	@Id
	private String phone;
	private String nameContact;
	
	
	@OneToMany(mappedBy="contactFrom", fetch=FetchType.EAGER)
	private List<Message> messagesSent;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "contact_received",
			joinColumns = @JoinColumn (name = "CONTACT_ID"),
			inverseJoinColumns = @JoinColumn(name = "MESSAGE_ID"))
	private List<Message> messagesReceived;


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getNameContact() {
		return nameContact;
	}


	public void setNameContact(String nameContact) {
		this.nameContact = nameContact;
	}


	public List<Message> getMessagesSent() {
		return messagesSent;
	}


	public void setMessagesSent(List<Message> messagesSent) {
		this.messagesSent = messagesSent;
	}


	public List<Message> getMessagesReceived() {
		return messagesReceived;
	}


	public void setMessagesReceived(List<Message> messagesReceived) {
		this.messagesReceived = messagesReceived;
	}

	
}
