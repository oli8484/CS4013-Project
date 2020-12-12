/**
 * Management Functionality 
 *
 * @author (Oliver Nagy/19269749)
 * @version (1)
 */
import java.util.ArrayList;
public class ManagementSystem   {    
public ArrayList<Property> propertyList;
public ArrayList<Owner> propertyOwners;
public ArrayList<Payment> payment;
    
Property p;   
Owner o; 
ListOfProperties propertieslist = new ListOfProperties(); 
ListOfPayments payments = new ListOfPayments();   // ListOfPayments is a class which stores the list of payments   in a csv file 


public  ManagementSystem(){
}


//Gets property tax payment data for any property  
public ArrayList<Payment> taxPaymentHistory_ForProperty( Property p) {
 return payment.getPaymentsForProperty(p);
    }


//Gets tax payment data for any property owner 
public ArrayList<Payment> taxPaymentHistory_ForOwner(Owner o) {
return payment.getPaymentsForOwner(o);
}


//Gets list of overdue property tax for a selected year/with option 
// to select an area based on routing key/eircode
public ArrayList<Payment> overdueTaxForYear_BasedOnLocation(int year) {
ArrayList<Payment> newlist = payments.getPayments();
ArrayList<Payment> temp = new ArrayList<Payment>();
for (int i = 0; i < newlist.size(); i++) {
            if (newlist.get(i).getProperty().getOverdueTax() != 0 && newlist.get(i).getYearPaid() == year) {
                temp.add(newlist.get(i));
            }
        }
return temp;
    }
public ArrayList<Payment> listOverDueTaxByYear(int year) {
        ArrayList<Payment> newlist = payments.getPayments();
        ArrayList<Payment> temp = new ArrayList<Payment>();
        for (int i = 0; i < newlist.size(); i++) {
            if (newlist.get(i).getProperty().getOverdueTax() != 0 && newlist.get(i).getYearPaid() == year) {
                temp.add(newlist.get(i));
            }
        }
        return temp;
    }    
    
    
 //Gets tax stats for area based on eircode/routing key
public double statsForArea_BasedOnLocation(String eircode, String routingKey) {
 for (Property property3 : propertyList) {
            if (routingKey.equals(property3.getAddress().getEircode())) {
                return property3.getTax().getTaxableAmount();


            }
        }
return 0;
}


//Shows impacts relating to revenue collected when levies and property
//taxes change
public int impactOfChangedTaxRates_OnRevenue() {





}
}
