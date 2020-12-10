
/**
 * Class for Payment
 *
 * @author (Oliver Nagy)
 * @version (1)
 */
public class Payment {
    Property p;
    double amount;
    String dateOfPayment;
public Payment(String dateOfPayment, double amount, Property p) {
        this.amount = amount;
        this.p = p;
        this.dateOfPayment = dateOfPayment;
    }
public String getDateOfPayment() {
        return dateOfPayment;
    }
public Property getProperty() {
        return p;
    }
public double getAmount() {
        return amount;
    }

}