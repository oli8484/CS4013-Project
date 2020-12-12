public class NeedsToBeAddedToListOfPayments {
 // gets list of payments for owner 
    public ArrayList<Payment> getPaymentsForOwner(Owner o) {
        ArrayList<Payment> py = new ArrayList<>();
        for (Payment pay : payments) {
            if (pay.getProperty().getPropertyOwner().equals(p)) {
                payments.add(pay);
            }
        }
        return py;
    }
    // gets list of payemnts for property
    public ArrayList<Payment> getPropertyForProperty(Property p) {
        ArrayList<Payment> py = new ArrayList<>();
        for (Payment pay : payments) {
            if (pay.getProperty().equals(p)) {
                payments.add(pay);
            }
        }
        return py;
    }


}
