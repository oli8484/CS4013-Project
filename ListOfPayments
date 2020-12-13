/**
 * Class for ListOfPayment
 *
 * @author (Robert Mooney)
 * @version (1)
 */
import java.util.ArrayList;

public class ListOfPayments {
	
	private static ArrayList<String[]> paymentList;
	
	public static void main(String[] args) {
		paymentList = new ArrayList<String[]>();
		
		String[] p1 = { "12/12/2020", "3000", "21 West Lane", "A64 F3S4", "John Doe" };
		String[] p2 = { "25/09/2020", "2500.50", "7 Green", "G34 S2A1", "Vince Hogan" };
		String[] p3 = { "05/11/2020", "1275", "12 Red Avenue", "K84 J2H1", "Alan Smith" };
		paymentList.add(p1);
		paymentList.add(p2);
		paymentList.add(p3);
		
		csvhandler.serialiseStorage(paymentList, "List_Of_Payments.csv");
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
	
	public void addPayment(String[] p) {
		paymentList.add(p);
		PaymentListCSV();
	}
	
	public ArrayList<String[]> getPayments() {
		return paymentList;
	}
	
}
