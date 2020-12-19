import java.util.ArrayList;

public class PaymentList {
	
	private final String paymentListFile = "paymentList.csv";
	private ArrayList<Payment> paymentList;
	
	public PaymentList() {
		paymentList = new ArrayList<Payment>();
	}
	
	private void PaymentListCSV() {
		ArrayList<String[]> stringData = new ArrayList<>();
		String[] s = new String[5];
		s[0] = Payment.getDateOfPayment();
		s[1] = String.valueOf(Payment.getAmount());
		s[2] = Property.getAddress();
		s[3] = Property.getEircode();
		s[4] = User.getName();
		
		stringData.add(s);
	}
	
	public void addPayment(Payment p) {
		paymentList.add(p);
		PaymentListCSV();
	}
	
	public ArrayList<Payment> getPayments() {
		return paymentList;
	}
}