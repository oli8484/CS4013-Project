/**
 * Management Functionality 
 *
 * @author (Oliver Nagy/19269749)
 * @version (1)
 */


import java.util.ArrayList;
public class ManagementSystem extends User  {    
public ArrayList<Property> listOfProperties;
public ArrayList<Owner> propertyOwners;
public ArrayList<Payment> payment;
Property p;   
Owner o; 
SerialisationSurrogate object = new SerialisationSurrogate(); // ListOfProperties is a class which stores the properties of the owner  in a csv file 
ListOfPayments payments = new ListOfPayments();   // ListOfPayments is a class which stores the list of payments   in a csv file 


//Gets property tax payment data for any property  
public String taxPaymentHistory_ForProperty( Property p) {
 for(int i= 0; i<listOfProperties.size(); i++) {
            if(p.equals(i)) {
                p.getPayment();//Property class needs a get payment method
            } else {
                System.out.println("Error: No Records found ");
            }
        }
    }

//Gets tax payment data for any property owner 
public int taxPaymentHistory_ForOwner( String owner ) {





}
//Gets list of overdue property tax for a selected year/with option 
// to select an area based on routing key/eircode
public int overdueTaxForYear_BasedOnLocation(int year, String eircode,String routingKey) {





}
//Gets tax stats for area based on eircode/routing key
public int statsForArea_BasedOnLocation(String eircode, String routingKey) {





}
//Shows impacts relating to revenue collected when levies and property
//taxes change
public int impactOfChangedTaxRates_OnRevenue() {





}
}
