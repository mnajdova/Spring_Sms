package mk.ukim.finki.mp.stateful.payment;


import org.springframework.stereotype.Component;

@Component
public class MockPaymentExecutor implements PaymentExecutor {

	
	
	
	@Override
	public boolean validatePaymentDetails(String name, String lastName,
			String creditCardNumber) {
		return true;
	}

	@Override
	public void executePayment(String creditCardNumber, double total) {
		// it is mock and does nothing
	}
	
	
	

}
