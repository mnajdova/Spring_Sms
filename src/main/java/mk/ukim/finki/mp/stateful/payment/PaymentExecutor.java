package mk.ukim.finki.mp.stateful.payment;

public interface PaymentExecutor {

	public boolean validatePaymentDetails(String name, String lastName,
			String creditCardNumber);

	public void executePayment(String creditCardNumber, double total);
}
