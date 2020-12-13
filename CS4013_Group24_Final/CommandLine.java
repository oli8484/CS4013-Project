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

    public static void main(String[] args) {
        chooseTypeLogin();
    }

    public static void chooseTypeLogin() {
        System.out.println("Choose Login Type:");
        System.out.println("(U)ser or (M)oderator?");
        try {
            String type = scan.nextLine();
            if (type.equals("U")) {
                userLogin();
            } else if (type.equals("M")) {
                management();
            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            System.out.println("Formatting incorrect!");
            chooseTypeLogin();
        }
    }

    public static void userLogin() {
        System.out.println("USER LOGIN");
        System.out.println("Enter username:");
        String username = scan.nextLine();
        System.out.println("Enter password:");
        String password = scan.nextLine();
        Owner owner = new Owner(username, password);
        if (true) {//owner1.exists
            userMenu(owner);
        } else {
            System.out.println("User does not exist!");
            userLogin();
        }

    }

    public static void userMenu(Owner owner) {
        System.out.println("USER MENU! Pick option");
        System.out.println("(R)egister Property");
        System.out.println("(V)iew All Properties");
        System.out.println("(C)heck Balance Statement");
        System.out.println("(B)ack to USER LOGIN");
        String option = scan.nextLine();
        try {
            switch (option) {
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
                    throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            System.out.println("Formattign incorrect, try again!");
            userMenu(owner);
        }
    }

    public static void userRegisterProperty(Owner owner) {
        System.out.println("REGISTER PROPERTY! Enter details of property");
        boolean prinprivate;
        try {
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

            double marketvalue = Double.parseDouble(strmarketvalue);
            if (strprinprivate.equals("Y")) {
                prinprivate = true;
            } else if (strprinprivate.equals("N")) {
                prinprivate = false;
            } else {
                throw new NumberFormatException();
            }

            Property p1 = new Property(owners, address, postcode, marketvalue, locationcategory, prinprivate);
            owner.addPropertyToSystem(p1);
        } catch (NumberFormatException ex) {
            System.out.println("Incorrect formatting, try again!");
            userMenu(owner);
        }
        System.out.println(".........................\n Press ENTER to return");
        scan.nextLine();
        userMenu(owner);
    }

    public static void userViewProperty(Owner owner) {
        System.out.println("VIEW PROPERTIES!");
        System.out.println("Property Addres           Due Tax          Overdue Tax");

        for (Property ownedPropertie : owner.getOwnedProperties()) {
            System.out.println(ownedPropertie.getAddress() + "             " + ownedPropertie.getTax() + "             " + ownedPropertie.getTax());
        }

        System.out.println(".........................\n Press ENTER to return");
        scan.nextLine();
        userMenu(owner);

    }

    public static void userBalance(Owner owner) {
        System.out.println("VIEW BALANCE STATEMENT!");
        System.out.println("Enter Property Address:");
        try {
            String address = scan.nextLine();
            System.out.println("Enter Year;");
            String stryear = scan.nextLine();
            int year = Integer.parseInt(stryear);
            Property currentproperty = Tax.getPropertyByAddress(address);

            System.out.println("Tax Due: " + currentproperty.getTax());
            System.out.println("Pay Tax? (Y/N):");
            String pay = scan.nextLine();
            if (pay.equals("Y")) {
                System.out.println("Enter amount to pay:");
                String amount = scan.nextLine();
                Date date = Calendar.getInstance().getTime();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                String strDate = dateFormat.format(date);
                Payment pay1 = new Payment(strDate, Double.parseDouble(amount), currentproperty);
            } else if (pay.equals("N")) {
                userBalance(owner);
            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            System.out.println("Incorrect Formatting, try again!");
            userMenu(owner);
        }
    }

    public static void management() {
        System.out.println("MANAGEMENT! Pick an option");
        System.out.println("(T)ax Payments");
        System.out.println("(V)iew Overdue Tax");
        System.out.println("(S)tats By Location");
        System.out.println("(I)mpact of Sampled Tax Changes");
        System.out.println("(B)ack to choose login type");
        try {
            String option = scan.nextLine();
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
        } catch (NumberFormatException ex) {
            System.out.println("Formattign incorrect, try again!");
            management();
        }

    }

    public static void managementViewTax() {
        System.out.println("VIEW TAX");
        System.out.println("View by (A)ddress or by (O)wmer Name:");
        String viewtax = scan.nextLine();
        try {
            if (viewtax.equals("A")) {
                managementViewAddressTax();
            } else if (viewtax.equals("A")) {
                managementViewOwnerTax();
            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            System.out.println("Incorrect formatting, try again!");
            management();
        }

    }

    public static void managementViewAddressTax() {
        System.out.println("VIEW TAX BY ADDRESS");
        System.out.println("Enter Address!");
        try {
            String address = scan.nextLine();
            Property currentproperty = Tax.getPropertyByAddress(address);

            System.out.println("Year:          Tax Due:");
            for (int i = 2020; i > 2010; i--) {
                System.out.println(i + "             " + currentproperty.getPreviousYearData(i).getTax());
            }
        } catch (NumberFormatException ex) {
            System.out.println("Incorrect formatting,try again!");
            management();

        }
        System.out.println(".........................\n Press ENTER to return");
        scan.nextLine();
        management();

    }

    public static void managementViewOwnerTax() {
        System.out.println("VIEW TAX BY OWNER NAME");
        System.out.println("Enter Owner Name!");
        try {
            String ownername = scan.nextLine();
            Property currentproperty = Tax.getPropertiesByOwner(ownername).get(0);

            System.out.println("Year:          Tax Due:");
            for (int i = 2020; i > 2010; i--) {
                System.out.println(i + "            " + currentproperty.getPreviousYearData(i).getTax());
            }
        } catch (NumberFormatException ex) {
            System.out.println("Incorrect formatting,try again!");
            management();

        }

        System.out.println(".........................\n Press ENTER to return");
        scan.nextLine();
        management();

    }

    public static void managementViewOverDueTax() {
        System.out.println("VIEW OVERDUE TAX");
        try {
            System.out.println("Enter Year:");
            String stryear = scan.nextLine();
            System.out.println("Enter Eircode:");
            String streircode = scan.nextLine();
            int year = Integer.parseInt(stryear);
        } catch (NumberFormatException ex) {
            System.out.println("Incorrect formatting, try again!");
            management();
        }

        management();
    }

    public static void managementViewStatsByLocation() {
        System.out.println("VIEW STATS BY LOCATION");
        System.out.println("Enter Eircode:");
        try {
            String eircode = scan.nextLine();
            ArrayList<Property> arrlist = Tax.getPropertiesByEircodeRoutingKey(eircode.getRoutingKey());

            System.out.println("Property Address        Property Tax            Taxable Amount");

            for (int i = 0; i <= arrlist.size(); i++) {
                System.out.println(arrlist.get(i).getAddress());
                System.out.println(Double.toString(arrlist.get(i).getTax()));
                System.out.println(Double.toString(arrlist.get(i).getTax()));
            }
        } catch (NumberFormatException ex) {
            System.out.println("Incorrect formatting, try again!");
            management();
        }

        System.out.println(".........................\n Press ENTER to return");
        scan.nextLine();
        management();

    }

    public static void managementSampleTaxChanges() {
        System.out.println("SAMPLE TAX CHANGES");
        System.out.println("Enter New Tax Rate (Decimal)");
        try {
            String strtaxrate = scan.nextLine();
            double taxrate = Double.parseDouble(strtaxrate);
            System.out.println("New Revenue: " + impactOfChangedTaxRates_OnRevenue(taxrate));
        } catch (NumberFormatException ex) {
            System.out.println("Incorrect formatting, try again!");
            management();
        }
        System.out.println(".........................\n Press ENTER to return");
        scan.nextLine();
        management();
    }

}
