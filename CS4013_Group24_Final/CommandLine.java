/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author J
 */
public class CommandLine {

    public static Scanner scan = new Scanner(System.in);

    /**
     * Start entire program
     */
    public static void main(String[] args) {
        chooseTypeLogin();
    }

    /**
     * Choose to log in as a USER(owner) or a MODERATOR(admin)
     * Moderators have access to all owners property info
     * Owners have access to only their own property info
     */
    public static void chooseTypeLogin() {
        System.out.println("Choose Login Type:");
        System.out.println("(U)ser or (M)oderator?");
        try { //if entered something other than U or M, it repeats the method
            String type = scan.nextLine();
            if (type.equals("U")) {
                userLogin(); 
            } else if (type.equals("M")) {
                managementMenu();
            } else {
                throw new NumberFormatException(); //entered incorrect info
            }
        } catch (NumberFormatException ex) {
            System.out.println("Formatting incorrect!");
            chooseTypeLogin(); //repeat method
        }
    }

    /**
     * Owner Log in stage
     * Enter username and password to match an owner from the database to fetch info
     */
    public static void userLogin() {
        System.out.println("USER LOGIN");
        //enter in username and password
        System.out.println("Enter username:");
        String username = scan.nextLine();
        System.out.println("Enter password:");
        String password = scan.nextLine();
        Owner owner = new Owner(username, password);
        if (true) {//owner1.exists
            userMenu(owner);
        } else { //user not found on databse, repeats method
            System.out.println("User does not exist!");
            userLogin();
        }

    }
 /**
     * Menu to access other parts All options a owner has
     *
     */
    public static void userMenu(Owner owner) {
        System.out.println("USER MENU! Pick option");
        //enter single character to access the methods
        System.out.println("(R)egister Property");
        System.out.println("(V)iew All Properties");
        System.out.println("(C)heck Balance Statement");
        System.out.println("(B)ack to USER LOGIN");
        String option = scan.nextLine();
        try { //if entered something other than the specified characters
            switch (option) { //checking which character entered
                case "R":
                    userRegisterProperty(owner);
                    break;
                case "V":
                    userViewProperty(owner);
                    break;
                case "C":
                    userBalance(owner);
                    break;
                case "B":
                    userLogin();
                    break;
                default:
                    throw new NumberFormatException(); //something other than the specified characters were entered
            }
        } catch (NumberFormatException ex) { //repeat method
            System.out.println("Formattign incorrect, try again!");
            userMenu(owner);
        }
    }

    /**
     * GUI prompts you to enter information about your property 
     * Then stores the info to the database
     */
    public static void userRegisterProperty(Owner owner) {
        System.out.println("REGISTER PROPERTY! Enter details of property");
        boolean prinprivate;
        try { //if formatted incorrectly
            //enter in all info about the property
            System.out.println("Enter Property Owner(s):");
            String owners = scan.nextLine();
            System.out.println("Enter Address:");
            String address = scan.nextLine();
            System.out.println("Enter PostCode");
            String postcode = scan.nextLine();
            System.out.println("Enter Estimated Market Value:");
            String strmarketvalue = scan.nextLine();
            System.out.println("Enter Location Category (City/Large town/Small town/Village/Countryside):");
            String locationcategory = scan.nextLine();
            System.out.println("Enter Principal Private Residence (Y/N):");
            String strprinprivate = scan.nextLine();

            //convert marketvalue from string to double for practical use
            double marketvalue = Double.parseDouble(strmarketvalue);
            if (strprinprivate.equals("Y")) { //residence IS private
                prinprivate = true;
            } else if (strprinprivate.equals("N")) { //residence is NOT private
                prinprivate = false;
            } else {
                throw new NumberFormatException(); //private residence string formatted incorrectly
            }
            
            //construct property object for storage
            Property p1 = new Property(owners, address, postcode, marketvalue, locationcategory, prinprivate);
            owner.addPropertyToSystem(p1); //add it to database
        } catch (NumberFormatException ex) { //incorrect formatting, repeats method
            System.out.println("Incorrect formatting, try again!");
            userMenu(owner);
        }
        //after registered property, exit out to user menu
        System.out.println(".........................\n Press ENTER to return");
        scan.nextLine();
        userMenu(owner);
    }

     /**
     * View all properties belonging to owner Shows due/overdue tax to each property 
     */
    public static void userViewProperty(Owner owner) {
        System.out.println("VIEW PROPERTIES!");
        System.out.println("Property Addres           Due Tax          Overdue Tax");

        for (Property ownedPropertie : owner.getOwnedProperties()) { //print out info about properties
            System.out.println(ownedPropertie.getAddress() + "             " + ownedPropertie.getTax() + "             " + ownedPropertie.getTax());
        }

        //after displaying info, exit out to user menu
        System.out.println(".........................\n Press ENTER to return");
        scan.nextLine();
        userMenu(owner);

    }

    
     /**
     * Enter year and property name, displays balancing statement for the property on that year
     */
    public static void userBalance(Owner owner) {
        System.out.println("VIEW BALANCE STATEMENT!");
        
        //enter property address
        System.out.println("Enter Property Address:");
        try { //if formatted incorrectly, repeat method
            String address = scan.nextLine();
            System.out.println("Enter Year;");
            String stryear = scan.nextLine();
            int year = Integer.parseInt(stryear);
            //get the property which has that address
            Property currentproperty = Tax.getPropertyByAddress(address);

            //choice whether to pay the taxes
            System.out.println("Tax Due: " + currentproperty.getTax());
            System.out.println("Pay Tax? (Y/N):");
            String pay = scan.nextLine();
            if (pay.equals("Y")) { //if yes, enter amount to pay and keeps 'receipt' of it
                System.out.println("Enter amount to pay:");
                String amount = scan.nextLine();
                Date date = Calendar.getInstance().getTime();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                String strDate = dateFormat.format(date);
                Payment pay1 = new Payment(strDate, Double.parseDouble(amount), currentproperty);
            } else if (pay.equals("N")) { //if no, kick back to user menu
                userMenu(owner);
            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) { //formatted incorrectly
            System.out.println("Incorrect Formatting, try again!");
            userMenu(owner);
        }
    }

      /**
     * Management Log in stage - access to view info about all owners
     */
    public static void managementMenu() {
        System.out.println("MANAGEMENT! Pick an option");
        //enter character to invoke chosen method
        System.out.println("(T)ax Payments");
        System.out.println("(V)iew Overdue Tax");
        System.out.println("(S)tats By Location");
        System.out.println("(I)mpact of Sampled Tax Changes");
        System.out.println("(B)ack to choose login type");
        try { //if entered something other than specified character
            String option = scan.nextLine(); //takes in character
            //checks 'option' against these characters
            switch (option) {
                case ("T"):
                    managementViewTax();
                    break;
                case ("V"):
                    managementViewOverDueTax();
                    break;
                case ("S"):
                    managementViewStatsByLocation();
                    break;
                case ("I"):
                    managementSampleTaxChanges();
                    break;
                case ("B"):
                    chooseTypeLogin();
                    break;
                default:
                    throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) { //formatted wrong, kick back to management menu
            System.out.println("Formatting incorrect, try again!");
            managementMenu();
        }

    }
    
    /**
     * View tax for a certain property or for a certain property owner
     */
    public static void managementViewTax() {
        System.out.println("VIEW TAX");
        //enter character to view by property, or by owner
        System.out.println("View by (A)ddress or by (O)wmer Name:");
        String viewtax = scan.nextLine();
        try { //if entered something other than specified characters
            if (viewtax.equals("A")) {
                managementViewAddressTax();
            } else if (viewtax.equals("A")) {
                managementViewOwnerTax();
            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) { //if entered incorrectly, kick back to menu
            System.out.println("Incorrect formatting, try again!");
            managementMenu();
        }

    }

     /**
     * "Sub" method for managementViewTaxGUI Search database to display tax due for a certain property
     */
    public static void managementViewAddressTax() {
        System.out.println("VIEW TAX BY ADDRESS");
        //enter address of the property to view its taxes of
        System.out.println("Enter Address!");
        try { //if info incorrect
            String address = scan.nextLine();
             //create property object from the given address
            Property currentproperty = Tax.getPropertyByAddress(address);
            System.out.println("Year:          Tax Due:");
            //print out all tax due for these years 2020 to 2010
            for (int i = 2020; i > 2010; i--) {
                System.out.println(i + "             " + currentproperty.getPreviousYearData(i).getTax());
            }
        } catch (NumberFormatException ex) {
            System.out.println("Incorrect formatting,try again!");
            managementMenu();

        }
        
        //when done looking at tax, kick back to menu
        System.out.println(".........................\n Press ENTER to return");
        scan.nextLine();
        managementMenu();

    }

    /**
     * "Sub" method for managementViewTaxGUI Search database to display tax due for properties beloning to owner
     */
    public static void managementViewOwnerTax() {
        System.out.println("VIEW TAX BY OWNER NAME");
        //enter name of the owner to view their taxes
        System.out.println("Enter Owner Name!");
        try { //if info incorrect
            String ownername = scan.nextLine();
             //create property object from the given owner
            Property currentproperty = Tax.getPropertiesByOwner(ownername).get(0);
            System.out.println("Year:          Tax Due:");
            //print out all tax due for these years 2020 to 2010
            for (int i = 2020; i > 2010; i--) {
                System.out.println(i + "            " + currentproperty.getPreviousYearData(i).getTax());
            }
        } catch (NumberFormatException ex) {
            System.out.println("Incorrect formatting,try again!");
            managementMenu();

        }

        //when done looking at tax, kick back to menu
        System.out.println(".........................\n Press ENTER to return");
        scan.nextLine();
        managementMenu();

    }

    /**
     * Displays overdue tax for a specific location for a specific year
     * unfinished
     */
    public static void managementViewOverDueTax() {
        System.out.println("VIEW OVERDUE TAX");
        try { //if incorrect formatting
            //get year and eircode
            System.out.println("Enter Year:");
            String stryear = scan.nextLine();
            System.out.println("Enter Eircode:");
            String streircode = scan.nextLine();
            int year = Integer.parseInt(stryear);
        } catch (NumberFormatException ex) { //formatted wrong, kick back to menu
            System.out.println("Incorrect formatting, try again!");
            managementMenu();
        }

        managementMenu(); //when program done, kick back to menu
    }

    /**
     * Search by eircode and it shows property name, property tax, taxable amount for that eircode location
     */
    public static void managementViewStatsByLocation() {
        System.out.println("VIEW STATS BY LOCATION");
        //enter ericode
        System.out.println("Enter Eircode:");
        try { //if incorrect formatting
            //gets eircode routing key to get properties in that area
            String eircode = scan.nextLine();
            ArrayList<Property> arrlist = Tax.getPropertiesByEircodeRoutingKey(eircode.getRoutingKey());
            System.out.println("Property Address        Property Tax            Taxable Amount");
            //print out all info belonging to properties in that area
            for (int i = 0; i <= arrlist.size(); i++) {
                System.out.println(arrlist.get(i).getAddress());
                System.out.println(Double.toString(arrlist.get(i).getTax()));
                System.out.println(Double.toString(arrlist.get(i).getTax()));
            }
        } catch (NumberFormatException ex) { //formatted wrong, kick back to menu
            System.out.println("Incorrect formatting, try again!");
            managementMenu();
        }

        //when done looking, kick back to menu
        System.out.println(".........................\n Press ENTER to return");
        scan.nextLine();
        managementMenu();

    }

    /**
     * Enter proposed new tax rate, then shows new revenue
     */
    public static void managementSampleTaxChanges() {
        System.out.println("SAMPLE TAX CHANGES");
        //enter new tax rate
        System.out.println("Enter New Tax Rate (Decimal)");
        try { //if formatted incorrectly
            String strtaxrate = scan.nextLine();
            double taxrate = Double.parseDouble(strtaxrate); //convert from string to double for practical use
            System.out.println("New Revenue: " + impactOfChangedTaxRates_OnRevenue(taxrate)); //print revenue
        } catch (NumberFormatException ex) { //formatted wrong, kick back to menu
            System.out.println("Incorrect formatting, try again!");
            managementMenu();
        }
        //when done looking, kick back to menu
        System.out.println(".........................\n Press ENTER to return");
        scan.nextLine();
        managementMenu();
    }

}
