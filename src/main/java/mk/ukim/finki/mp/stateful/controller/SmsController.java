package mk.ukim.finki.mp.stateful.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mk.ukim.finki.mp.stateful.service.ContactServise;
import mk.ukim.finki.mp.stateful.service.MessageService;
import mk.ukim.finki.mp.stateful.model.Contact;
import mk.ukim.finki.mp.stateful.model.Message;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes(value = {"saldo", "contact", "contacts"})
public class SmsController {

	@Autowired
	ContactServise contactServise;
	
	@Autowired
	MessageService messageService;
	
	@RequestMapping(value = "/*")
	public String invalid() {
		return "redirect:insertPhoneNumber";
	}
	
	@RequestMapping(value = { "insertPhoneNumber" }, method = RequestMethod.GET)
	@Secured({"ROLE_PRIVATE", "ROLE_ADMIN"})
	public ModelAndView insertNumber(HttpServletResponse response, HttpServletRequest request,HttpSession session) {
		if(request.isUserInRole("ROLE_ADMIN"))
			return new ModelAndView("adminForm");
		
		Cookie cookie = getCookieForNumber(request);
		if(cookie==null)
		{
			ModelAndView modelAndView = new ModelAndView("insertPhoneNumber");
			return modelAndView;
		}
		else{
			setSession(cookie, session);
    		Contact contact = (Contact) session.getAttribute("contact");
    		List<Message> messages = contact.getMessagesReceived();
    		double saldo = Double.parseDouble(session.getAttribute("saldo").toString());
    		ModelAndView modelAndView = new  ModelAndView("allReceivedSmsTab");
    		modelAndView.addObject("contact", contact);
    		modelAndView.addObject("saldo", saldo);
    		modelAndView.addObject("messages", messages);
    		return modelAndView;
		}
	}
	
	@RequestMapping(value = { "insertPhoneNumber" }, method = RequestMethod.POST)
	@Secured("ROLE_PRIVATE")
	public ModelAndView test(String number, String remember, HttpServletResponse response,HttpSession session, HttpServletRequest request) {
		Contact contact = contactServise.getContactFromNumber(number);
		if(contact==null)
		{
			ModelAndView mav = new ModelAndView("insertPhoneNumber");
			mav.addObject("message", "Бројот кој го внесовте не е валиден");
			return mav;
		}
		else if(remember!=null)
		{
			Cookie cookie = new Cookie("number", number);
			cookie.setMaxAge(300);
			response.addCookie(cookie);
		}
		session.setAttribute("contact", contact);
		double saldo = contactServise.getSaldo(contact);
		session.setAttribute("saldo", saldo);
		return allReceivedMessages(session, request);
	}
	
	@RequestMapping(value = { "sendNewSms" }, method = RequestMethod.GET)
	@Secured("ROLE_PRIVATE")
	public ModelAndView insertNewNumber(HttpServletResponse response, HttpServletRequest request,HttpSession session) {
		if(getCookieForNumber(request)==null && session.getAttribute("contact")==null)
			return new ModelAndView("insertPhoneNumber");
		else if(getCookieForNumber(request)!=null && session.getAttribute("contact")==null)
			setSession(getCookieForNumber(request), session);
		
		List<Contact> contacts = contactServise.getAllContacts();
		String str="";
		for(Contact c : contacts)
		{
			str+="\""+c.getPhone()+"\",";
		}
		str=str.substring(0, str.length()-1);
		System.out.println(str);
		session.setAttribute("contacts", str);
		ModelAndView modelAndView = new ModelAndView("newSmsTab");
		Contact contact = (Contact) session.getAttribute("contact");
		modelAndView.addObject("contact", contact);
		modelAndView.addObject("contacts", str);
		modelAndView.addObject("message", "");
		return modelAndView;
	}
	
	@RequestMapping(value = {"sendNewSms"}, method = RequestMethod.POST)
	@Secured("ROLE_PRIVATE")
	public ModelAndView saveSms(@RequestParam String numberTo,@RequestParam  String numberFrom,@RequestParam  String content,HttpSession session){
		System.out.println(numberTo + " " + numberFrom + " "+content);
		double saldo = Double.parseDouble(session.getAttribute("saldo").toString());
		Contact contactFrom = contactServise.getContactFromNumber(numberFrom);
		String[] contactsFromString = numberTo.split(",");
		ArrayList<String> receivers = new ArrayList<>();
		for(int i=0;i<contactsFromString.length;i++)
		{
			receivers.add(contactsFromString[i]);
		}
		
		ModelAndView modelAndView = new ModelAndView("newSmsTab");
		modelAndView.addObject("contact", (Contact)session.getAttribute("contact"));
		
		if(messageService.validateContacts(numberFrom, receivers))
		{
			List<Contact> contactsTo = new ArrayList<Contact>();
			for(int i=0;i<contactsFromString.length;i++)
			{
				Contact contactTo = contactServise.getContactFromNumber(contactsFromString[i]);
				contactsTo.add(contactTo);
			}
			saldo-=messageService.sendMessage(contactFrom, contactsTo, content);
			modelAndView.addObject("message", "Вашата порака беше успешно пратена.");
		}
		else 
		{
			modelAndView.addObject("message", "Вашата порака не беше успешно пратена!");
		}
		modelAndView.addObject("saldo", saldo);
		return modelAndView;
	}
	
	@RequestMapping(value = {"allSentSms"}, method = RequestMethod.GET)
	@Secured("ROLE_PRIVATE")
	public ModelAndView allSentMessages(HttpSession session, HttpServletRequest request){
		if(getCookieForNumber(request)==null && session.getAttribute("contact")==null)
			return new ModelAndView("insertPhoneNumber");
		else if(getCookieForNumber(request)!=null && session.getAttribute("contact")==null)
			setSession(getCookieForNumber(request), session);
		
		Contact t = (Contact)session.getAttribute("contact");
		Contact c = contactServise.getContactFromNumber(t.getPhone());
		List<Message> messages = c.getMessagesSent();
		ModelAndView result = new ModelAndView("allSentSmsTab");
		String num=(String)session.getAttribute("number");
		result.addObject("messages", messages);
		return result;
	}
	
	@RequestMapping(value = {"allReceivedSms"}, method = RequestMethod.GET)
	@Secured("ROLE_PRIVATE")
	public ModelAndView allReceivedMessages(HttpSession session, HttpServletRequest request){
		if(getCookieForNumber(request)==null && session.getAttribute("contact")==null)
			return new ModelAndView("insertPhoneNumber");
		else if(getCookieForNumber(request)!=null && session.getAttribute("contact")==null)
			setSession(getCookieForNumber(request), session);
		
		Contact t = (Contact)session.getAttribute("contact");
		Contact c = contactServise.getContactFromNumber(t.getPhone());
		List<Message> messages = c.getMessagesReceived();
		ModelAndView result = new ModelAndView("allReceivedSmsTab");
		double saldo = Double.parseDouble(session.getAttribute("saldo").toString());
		result.addObject("messages", messages);
		result.addObject("contact", c);
		result.addObject("saldo", saldo);
		return result;
	}
	
	@RequestMapping(value = "/allmessages/{phone}", method = RequestMethod.GET)
	@Secured("ROLE_PRIVATE")
	public ModelAndView allMessagesOfContact(HttpSession session, @PathVariable String phone, HttpServletRequest request){
		if(getCookieForNumber(request)==null && session.getAttribute("contact")==null)
			return new ModelAndView("insertPhoneNumber");
		else if(getCookieForNumber(request)!=null && session.getAttribute("contact")==null)
			setSession(getCookieForNumber(request), session);
		Contact contact = (Contact) session.getAttribute("contact");
		List<Message> messagesSent = contactServise.getAllSentMessagesBetweenContacts(contact.getPhone(), phone);
		List<Message> messagesReceived = contactServise.getAllSentMessagesBetweenContacts(phone, contact.getPhone());
		ModelAndView modelAndView = new ModelAndView("allMessages");
		Contact other = contactServise.getContactFromNumber(phone);
		modelAndView.addObject("contact", contact);
		modelAndView.addObject("other", other);
		modelAndView.addObject("messagesSent", messagesSent);
		modelAndView.addObject("messagesReceived", messagesReceived);
		return modelAndView;
	}
	
	public Cookie getCookieForNumber(HttpServletRequest request)
	{
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			System.out.println(cookie.getName());
	        if(cookie.getName().equals("number")){
	        		return cookie;
	        }
		}
		return null;
	}
	
	public void setSession(Cookie cookie, HttpSession session)
	{
		String phone = cookie.getValue();
		Contact contact = contactServise.getContactFromNumber(phone);
		session.setAttribute("contact", contact);
		double saldo = contactServise.getSaldo(contact);
		session.setAttribute("saldo", saldo);
	}
	
	@RequestMapping(value = { "insertNewNumber" }, method = RequestMethod.GET)
	@Secured("ROLE_PRIVATE")
	public ModelAndView insertNewPhoneNumber(HttpServletResponse response, HttpServletRequest request,HttpSession session) {
		return new ModelAndView("insertNewNumber");
	}
	
	
	@RequestMapping(value = { "statistic" }, method = RequestMethod.GET)
	@Secured("ROLE_PRIVATE")
	public ModelAndView statistic(HttpServletResponse response, HttpServletRequest request,HttpSession session) {
		if(getCookieForNumber(request)==null && session.getAttribute("contact")==null)
			return new ModelAndView("insertPhoneNumber");
		else if(getCookieForNumber(request)!=null && session.getAttribute("contact")==null)
			setSession(getCookieForNumber(request), session);
		
		ModelAndView modelAndView = new ModelAndView("statistic");
		Contact c = (Contact)session.getAttribute("contact");
		Contact contact = contactServise.getContactFromNumber(c.getPhone());
		Integer brPrimeni = contact.getMessagesReceived().size();
		Integer brIsprateni = contact.getMessagesSent().size();
		modelAndView.addObject("brPrimeni", brPrimeni);
		modelAndView.addObject("brIsprateni", brIsprateni);
		return modelAndView;
	}
	
	
	
	
	@RequestMapping(value = { "admin" }, method = RequestMethod.GET)
	@Secured("ROLE_ADMIN")
	public ModelAndView adminForm(@RequestBody String body, HttpServletResponse response, HttpServletRequest request,HttpSession session) {
		System.out.println(body);
		return new ModelAndView("adminForm");
	}

	@RequestMapping(value = { "receive" }, method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody 
	@Secured("ROLE_ADMIN")
	public String receive(@RequestBody String body, HttpServletRequest request) {
		String tokens[]=body.split(",");
		String tokens1[]=tokens[0].split(":");
		String tokens2[]=tokens[1].split(":");
		String tokens3[] = tokens[2].split(":");
		String numberFrom = tokens1[1];
		numberFrom=numberFrom.substring(1, numberFrom.length()-1);
		System.out.println(numberFrom);
		String numberTo = tokens2[1];
		String messageBody = tokens3[1];
		numberTo = numberTo.substring(1, numberTo.length()-1);
		messageBody = messageBody.substring(1, messageBody.length()-2);
		System.out.println(messageBody);
		System.out.println(numberTo);
		List<String> receivers = new ArrayList<String>();
		if(messageService.validateContacts(numberTo, receivers))
			{
			Contact contactFrom = contactServise.getContactFromNumber(numberFrom);
			List<Contact> contactsTo = new ArrayList<>();
			contactsTo.add(contactServise.getContactFromNumber(numberTo));
			messageService.sendMessage(contactFrom, contactsTo, messageBody);
			return "{\"status\": \"1\"}";}
		else
			return "{\"status\": \"0\"}";
	}
	
}
