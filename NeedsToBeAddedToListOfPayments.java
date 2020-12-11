public class NeedsToBeAddedToListOfPayments {
 // gets list of payments for owner 
    public ArrayList<Payment> getOwnerPayments(Owner o) {
        ArrayList<Payment> py = new ArrayList<>();
        for (Payment pay : payments) {
            if (pay.getProperty().getPropertyOwner().equals(p)) {
                payments.add(pay);
            }
        }
        return py;
    }
    // gets list of payemnts for property
    public ArrayList<Payment> getPropertyPayments(Property p) {
        ArrayList<Payment> py = new ArrayList<>();
        for (Payment pay : payments) {
            if (pay.getProperty().equals(p)) {
                temp.add(pay);
            }
        }
        return py;
    }


}
