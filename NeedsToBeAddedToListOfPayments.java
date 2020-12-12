public class NeedsToBeAddedToListOfPayments {
 // gets list of payments for owner 
    
public ArrayList<Payment> getPaymentsForOwner(Owner o) {
        ArrayList<Payment> py = new ArrayList<>();
        for (Payment pay : paymentList) {
            if (pay.getProperty().getPropertyOwner().equals(o)) {
                paymentList.add(pay);
            }
        }
        return py;
	
    }
    public ArrayList<Payment> getPaymentsForProperty(Property p) {
        ArrayList<Payment> py = new ArrayList<>();
        for (Payment pay : paymentList) {
            if (pay.getProperty().equals(p)) {
                paymentList.add(pay);
            }
        }
        return py;
    }


}
