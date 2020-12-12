/**
 * Invoke start method
 * Invoke chooseTypeLoginGUI
 * Choice 1)log in as user   2)log in as management
 *
 * 1)Log in as user
 *   enter name then  a),b),c)
 * a)Register Property: Enter property details, stores to system
 * b)view all properties belonging to specified name given
 *   shows property name, tax due, overdue tax
 * c)view balance statement for a property on a certain year
 *   shows name, year, tax due, and an option to pay tax
 *
 * 2)Log in as management
 *   a),b),c),d)
 * a)view tax payments of all properties and years
 *   search by year or by property name
 * b)view overdue tax for each year
 *   search by eircode also
 * c)view stats for a location
 *   shows
 * d)view impact of tax changes
 *   shows
 *
 */
 

/**
 * NEED TO ADD: way to pay tax is search function necessary in view property??
 *
 */
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUIConcept extends Application {

    Owner owner1;
    Property currentproperty;

    private String regownerName;
    private String regaddress;
    private String regeircode;
    private double regestimatedMarketValue;
    private String reglocationCategory;
    private boolean regprincipalPrivateResidence;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        chooseTypeLoginGUI(stage); //starts entire program
    }

    /**
     * Decide whether to log in as a owner or part of management
     */
    public void chooseTypeLoginGUI(Stage stage) {
        Button userbtn = new Button("Log in as USER");
        Button managementbtn = new Button("Log in as MANAGEMENT");
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        hbox.getChildren().add(userbtn);
        hbox.getChildren().add(managementbtn);

        userbtn.setOnAction((ActionEvent event) -> {
            userLoginGUI(stage);
        });

        managementbtn.setOnAction((ActionEvent event) -> {
            managementGUI(stage);
        });

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(hbox);
        BorderPane.setAlignment(hbox, Pos.CENTER);

        Scene scene = new Scene(borderPane, 255, 30);
        stage.setTitle("Choose Login");
        stage.setScene(scene);
        stage.show();  //display GUI 
    }

    /**
     * Owner Log in stage - uses name to search database
     */
    public void userLoginGUI(Stage stage) {
        Button backbtn = new Button("BACK");
        Label namelabel = new Label("Enter Name");
        Label passwordlabel = new Label("Enter Password");
        TextField nametext = new TextField();
        TextField passwordtext = new TextField();
        Button loginbtn = new Button("Login");
        namelabel.setStyle("-fx-font-weight: bold");
        passwordlabel.setStyle("-fx-font-weight: bold");

        HBox namebox = new HBox();
        namebox.setSpacing(10);
        namebox.getChildren().add(namelabel);
        namebox.getChildren().add(nametext);

        HBox passwordbox = new HBox();
        passwordbox.setSpacing(10);
        passwordbox.getChildren().add(passwordlabel);
        passwordbox.getChildren().add(passwordtext);

        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.getChildren().add(backbtn);
        vbox.getChildren().add(namebox);
        vbox.getChildren().add(passwordbox);
        vbox.getChildren().add(loginbtn);

        backbtn.setOnAction((event) -> { //invoke balanceGUI GUI
            chooseTypeLoginGUI(stage);
        });

        //matches name to arraylist which has owners and will return info to disply later
        //if owner doesnt exist, throws error 
        loginbtn.setOnAction((ActionEvent event) -> {
            if (!nametext.getText().isEmpty() && !passwordtext.getText().isEmpty()) {
                //owner1 = new Owner(nametext.getText(), passwordtext.getText());
                if (true) {//owner1.exists
                    userMenuGUI(stage);
                } else {
                    Alert a = new Alert(AlertType.NONE);
                    a.setAlertType(AlertType.ERROR);
                    a.setContentText("Owner info is wrong or not on record!");
                    a.show();
                }
            }

        });

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vbox);
        BorderPane.setAlignment(vbox, Pos.CENTER);

        Scene scene = new Scene(borderPane, 250, 130);
        stage.setTitle("User Login");
        stage.setScene(scene);
        stage.show();  //display GUI
    }

    /**
     * Menu to access other parts All options a owner has
     *
     */
    public void userMenuGUI(Stage stage) {
        HBox hbox = new HBox();
        hbox.setSpacing(7);
        hbox.setAlignment(Pos.CENTER);

        //buttons to acces the other GUI's
        Button btnreg = new Button("Register Property");
        Button btnview = new Button("View All Properties");
        Button btnbal = new Button("View Balance Statement");
        Button backbtn = new Button("BACK");

        //add buttons to box
        hbox.getChildren().add(backbtn);
        hbox.getChildren().add(btnreg);
        hbox.getChildren().add(btnview);
        hbox.getChildren().add(btnbal);

        backbtn.setOnAction((event) -> { //back to user login
            userLoginGUI(stage);
        });

        btnreg.setOnAction((event) -> { //invoke registerProperty GUI
            userRegisterProperty(stage);

        });
        btnview.setOnAction((event) -> { //invoke viewPropertyGUI GUI
            userViewPropertyGUI(stage);
        });
        btnbal.setOnAction((event) -> { //invoke balanceGUI GUI
            userBalanceGUI(stage);
        });

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(hbox);
        BorderPane.setAlignment(hbox, Pos.CENTER);

        Scene scene = new Scene(borderPane, 450, 30);
        stage.setTitle("Action");
        stage.setScene(scene);
        stage.show(); //Display GUI

    }

    /**
     * GUI prompts you to enter information about your property Click Submit
     * button to store the info/ register the property Back button returns to
     * Menu GUI
     *
     */
    public void userRegisterProperty(Stage stage) {
        VBox vboxlabel = new VBox();
        vboxlabel.setSpacing(19);
        vboxlabel.setStyle("-fx-font-weight: bold");
        //Adds labels to vbox, easier than delcaring them all seperately
        String[] namearr = {"Property Owner(s):", "Address:", "PostCode:", "Estimated Market Value:", "Location Category:", "Principal Private Residence:"};
        for (String namearr1 : namearr) {
            vboxlabel.getChildren().add(new Label(namearr1));
        }

        VBox vboxtext = new VBox();
        vboxtext.setSpacing(10);
        //TextFields to enter info into which will be stored
        TextField nametext = new TextField();
        TextField addresstext = new TextField();
        TextField eircodetext = new TextField();
        TextField valuetext = new TextField();
        TextField locationtext = new TextField();
        TextField residencetext = new TextField();
        vboxtext.getChildren().add(nametext);
        vboxtext.getChildren().add(addresstext);
        vboxtext.getChildren().add(eircodetext);
        vboxtext.getChildren().add(valuetext);
        vboxtext.getChildren().add(locationtext);
        vboxtext.getChildren().add(residencetext);

        //Back button to return to userMenuGUI
        Button registerbtn = new Button("Register Property Info");
        Button backbtn = new Button("BACK");

        //Info to let you know what to enter on these lines. 'Naming conventions'
        Label infolabel = new Label("Location Category:(City/Large town/Small town/Village/Countryside)");
        Label infolabel2 = new Label("Principal private residence:(Y/N)");
        infolabel.setStyle("-fx-font-weight: bold");
        infolabel2.setStyle("-fx-font-weight: bold");
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.getChildren().add(infolabel);
        vbox.getChildren().add(infolabel2);

        //hbox containing the back & register button
        HBox hboxbtn = new HBox();
        hboxbtn.setSpacing(210);
        hboxbtn.getChildren().add(backbtn);
        hboxbtn.getChildren().add(registerbtn);
        vbox.getChildren().add(hboxbtn);

        BorderPane borderPane = new BorderPane();
        borderPane.setRight(vboxtext);
        BorderPane.setAlignment(vboxtext, Pos.CENTER_LEFT);

        borderPane.setLeft(vboxlabel);
        BorderPane.setAlignment(vboxlabel, Pos.CENTER_RIGHT);

        borderPane.setBottom(vbox);
        BorderPane.setAlignment(vbox, Pos.BOTTOM_CENTER);

        //registers property, takes in all info and stores into an array list or properties
        //if formatting is wrong, gives error to try again
        registerbtn.setOnAction((event) -> {
            try {
                regownerName = nametext.getText();
                regaddress = addresstext.getText();
                regeircode = eircodetext.getText();
                regestimatedMarketValue = Double.parseDouble(valuetext.getText());
                reglocationCategory = locationtext.getText();
                if (residencetext.equals("Y")) {
                    regprincipalPrivateResidence = true;
                } else if (residencetext.equals("N")) {
                    regprincipalPrivateResidence = false;
                } else {
                    throw new NumberFormatException();
                }
                Property p1 = new Property(regownerName, regaddress, regeircode, regestimatedMarketValue, reglocationCategory, regprincipalPrivateResidence);
                owner1.addPropertyToSystem(p1);
            } catch (NumberFormatException ex) {
                Alert a = new Alert(AlertType.NONE);
                a.setAlertType(AlertType.ERROR);
                a.setContentText("Wrong formatting, try again!");
                a.show();
            }

        });

        backbtn.setOnAction((event) -> { //Backs out to userMenuGUI
            userMenuGUI(stage);

        });

        Scene scene = new Scene(borderPane, 400, 300);
        stage.setTitle("Register Property");
        stage.setScene(scene);
        stage.show(); //Display GUI

    }

    /**
     * GUI to view all properties belonging to owner Shows due/overdue tax to
     * each property
     */
    public void userViewPropertyGUI(Stage stage) {
        Button backbtn = new Button("BACK"); //return to userMenuGUI
        Label namelabel = new Label("Property Name");
        Label duelabel = new Label("Due Tax");
        Label overduelabel = new Label("Overdue Tax");

        //add above all to boxes for display
        VBox vbox = new VBox();
        HBox titlebox = new HBox();

        vbox.setSpacing(10);
        titlebox.setSpacing(130);

        titlebox.getChildren().add(namelabel);
        titlebox.getChildren().add(duelabel);
        titlebox.getChildren().add(overduelabel);
        titlebox.setStyle("-fx-font-weight: bold");

        vbox.getChildren().add(backbtn);
        vbox.getChildren().add(titlebox);

        backbtn.setOnAction((event) -> { //return to userMenuGUI
            userMenuGUI(stage);
        });
        
        //names of all properties belonging to owner
        VBox propertybox = new VBox();
        propertybox.setSpacing(10);
        for (Property ownedPropertie : owner1.getOwnedProperties()) {
            propertybox.getChildren().add(new Label(ownedPropertie.getAddress()));
        }
        
        //tax belonging to all properties of owner
        VBox taxbox = new VBox();
        taxbox.setSpacing(10);
        for (Property ownedPropertie : owner1.getOwnedProperties()) {
            propertybox.getChildren().add(new Label(Double.toString(ownedPropertie.getTax())));
        }

        HBox hbox1 = new HBox();
        hbox1.setSpacing(100);
        hbox1.getChildren().add(propertybox);
        hbox1.getChildren().add(taxbox);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(vbox);
        BorderPane.setAlignment(vbox, Pos.CENTER);

        Scene scene = new Scene(borderPane, 500, 500);
        stage.setTitle("Property Viewer");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Enter year and property name Displays balancing statement for the
     * property on that year
     */
    public void userBalanceGUI(Stage stage) {
        //sets up top of screen with labels, textfields etc
        TextField propertytext = new TextField();
        TextField yeartext = new TextField();
        Label propertylabel = new Label("Property Address:");
        Label yearlabel = new Label("Year:");
        Label paytaxlabel = new Label("Pay Tax");
        propertylabel.setStyle("-fx-font-weight: bold");
        yearlabel.setStyle("-fx-font-weight: bold");
        paytaxlabel.setStyle("-fx-font-weight: bold");
        Button submitbtn = new Button("Get Statement");
        Button backbtn = new Button("BACK");

        HBox hboxproperty = new HBox();
        HBox hboxyear = new HBox();
        HBox hboxbtn = new HBox();
        hboxproperty.setSpacing(10);
        hboxyear.setSpacing(70);
        hboxbtn.setSpacing(60);
        hboxproperty.getChildren().add(propertylabel);
        hboxproperty.getChildren().add(propertytext);
        hboxyear.getChildren().add(yearlabel);
        hboxyear.getChildren().add(yeartext);
        hboxbtn.getChildren().add(backbtn);
        hboxbtn.getChildren().add(submitbtn);

        HBox hbox2 = new HBox();
        hbox2.setSpacing(90);
        hbox2.setStyle("-fx-font-weight: bold");
        Label taxduelabel = new Label("Tax Due");
        hbox2.getChildren().add(taxduelabel);
        hbox2.getChildren().add(paytaxlabel);

        HBox infobox = new HBox();
        infobox.setSpacing(90);
        Label actualtaxduelabel;
        actualtaxduelabel = new Label();
        Button paytaxbtn = new Button("Pay Tax");
        infobox.getChildren().add(actualtaxduelabel);
        infobox.getChildren().add(paytaxbtn);

        Label taxpaylabel = new Label("Amount of Tax to Pay:");
        TextField taxpaytext = new TextField();
        HBox taxbox = new HBox();
        taxbox.setSpacing(10);
        taxbox.getChildren().add(taxpaylabel);
        taxbox.getChildren().add(taxpaytext);

        VBox vboxfinal = new VBox();
        vboxfinal.setSpacing(10);
        vboxfinal.getChildren().add(hboxproperty);
        vboxfinal.getChildren().add(hboxyear);
        vboxfinal.getChildren().add(hboxbtn);
        vboxfinal.getChildren().add(hbox2);
        vboxfinal.getChildren().add(infobox);
        vboxfinal.getChildren().add(taxbox);
        
        //gets year and address
        //returns the tax for that address for that year
        //if formatted wrong, gives error
        submitbtn.setOnAction((ActionEvent event) -> {
            try {
                if (!yeartext.getText().isEmpty() && !propertytext.getText().isEmpty()) { //if empty, dont do anything
                    int year = Integer.parseInt(yeartext.getText());
                    String address = propertytext.getText();
                    currentproperty = Tax.getPropertyByAddress(address);
                    actualtaxduelabel.setText(Double.toString(currentproperty.getTax()));
                }
            } catch (NumberFormatException ex) {
                Alert a = new Alert(AlertType.NONE);
                a.setAlertType(AlertType.ERROR);
                a.setContentText("Formatting incorrect! Try again");
                a.show();
            }

        });

        backbtn.setOnAction((ActionEvent event) -> { //Backs out to userMenuGUI
            userMenuGUI(stage);
        });

        //ability to pay tax on the property
        //creates record of the tax payment for keeps
        //if formatted wrong, gives error
        paytaxbtn.setOnAction((ActionEvent event) -> {
            try {
                Date date = Calendar.getInstance().getTime();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                String strDate = dateFormat.format(date);
                Payment pay1 = new Payment(strDate, Double.parseDouble(taxpaytext.getText()), currentproperty);
            } catch (NumberFormatException ex) {
                Alert a = new Alert(AlertType.NONE);
                a.setAlertType(AlertType.ERROR);
                a.setContentText("Formatting incorrect! Try again");
                a.show();
            }
        });

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(vboxfinal);
        BorderPane.setAlignment(vboxfinal, Pos.TOP_LEFT);

        Scene scene = new Scene(borderPane, 500, 500);
        stage.setTitle("Balancing Statement");
        stage.setScene(scene);
        stage.show(); //Display GUI
    }

    /**
     * Management Log in stage - access to view info about all owners
     */
    public void managementGUI(Stage stage) {
        Button viewtaxbtn = new Button("View Tax Payments");
        Button viewoverduetaxbtn = new Button("View Overdue Tax");
        Button viewstatsbtn = new Button("View Stats By Location");
        Button viewtaxchangesbtn = new Button("View Impacts Of Tax Changes");
        Button backbtn = new Button("BACK");
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.getChildren().add(viewtaxbtn);
        vbox.getChildren().add(viewoverduetaxbtn);
        vbox.getChildren().add(viewstatsbtn);
        vbox.getChildren().add(viewtaxchangesbtn);
        vbox.getChildren().add(backbtn);

        viewtaxbtn.setOnAction((ActionEvent event) -> { //Invoke managementViewTaxGUI
            managementViewTaxGUI(stage);
        });
        viewoverduetaxbtn.setOnAction((ActionEvent event) -> { //Invoke managementViewOverDueTaxGUI
            managementViewOverDueTaxGUI(stage);
        });
        viewstatsbtn.setOnAction((ActionEvent event) -> { //Invoke
            managementViewStatsByLocation(stage);
        });
        viewtaxchangesbtn.setOnAction((ActionEvent event) -> { //Invoke
            managementSampleTaxChanges(stage);
        });
        backbtn.setOnAction((ActionEvent event) -> { //Backs out to userMenuGUI
            chooseTypeLoginGUI(stage);
        });

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vbox);
        BorderPane.setAlignment(vbox, Pos.TOP_LEFT);

        Scene scene = new Scene(borderPane, 240, 150);
        stage.setTitle("Management");
        stage.setScene(scene);
        stage.show(); //Display GUI
    }

    /**
     * View tax due for any property name by year due
     */
    public void managementViewTaxGUI(Stage stage) {
        Button backbtn = new Button("BACK");
        Button searchyear = new Button("Search By Address");
        Button searchproperty = new Button("Search By Owmer Name");
        HBox hbox = new HBox();
        hbox.setSpacing(5);
        hbox.getChildren().add(backbtn);
        hbox.getChildren().add(searchyear);
        hbox.getChildren().add(searchproperty);

        backbtn.setOnAction((ActionEvent event) -> { //Backs out to managementGUI
            managementGUI(stage);
        });
        searchyear.setOnAction((ActionEvent event) -> { //Invoke managementViewAddressTaxGUI
            managementViewAddressTaxGUI(stage);
        });
        searchproperty.setOnAction((ActionEvent event) -> { //Invoke managementViewOwnerTaxGUI
            managementViewOwnerTaxGUI(stage);
        });

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(hbox);
        BorderPane.setAlignment(hbox, Pos.TOP_LEFT);

        Scene scene = new Scene(borderPane, 320, 30);
        stage.setTitle("View Tax");
        stage.setScene(scene);
        stage.show(); //Display GUI   
    }

    /**
     * "Sub" method for managementViewTaxGUI Search database to display tax due
     * for all properties for a specific year
     */
    public void managementViewAddressTaxGUI(Stage stage) {
        Button backbtn = new Button("BACK");
        Button addressbtn = new Button("Search");
        Label addresslabel = new Label("Enter Address");
        addresslabel.setStyle("-fx-font-weight: bold");
        TextField addresstext = new TextField();
        
        Label yearlabel=new Label("Year");
        Label taxlabel=new Label("Tax");
        HBox labelbox=new HBox();
        labelbox.setSpacing(150);
        labelbox.getChildren().add(yearlabel);
        labelbox.getChildren().add(taxlabel);
        labelbox.setStyle("-fx-font-weight: bold");
        
        HBox lastbox=new HBox();
        lastbox.setSpacing(100);
        VBox vboxyear=new VBox();
        vboxyear.setSpacing(10);
        //label for all years to display taxes for
        for(int i=2020;i>2010;i--)
            vboxyear.getChildren().add(new Label(Integer.toString(i)));
        lastbox.getChildren().add(vboxyear);
        
        
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        HBox hbox = new HBox();
        hbox.setSpacing(5);
        hbox.getChildren().add(addresslabel);
        hbox.getChildren().add(addresstext);
        hbox.getChildren().add(addressbtn);
        vbox.getChildren().add(backbtn);
        vbox.getChildren().add(hbox);
        vbox.getChildren().add(labelbox);
        vbox.getChildren().add(lastbox);
        
        VBox taxbox=new VBox();
        taxbox.setSpacing(10);

        backbtn.setOnAction((ActionEvent event) -> { //Backs out to managementViewTaxGUI
            managementViewTaxGUI(stage);
        });

        //returns taxes for specific years for the property with the entered address and displays them
        addressbtn.setOnAction((ActionEvent event) -> {
            currentproperty=Tax.getPropertyByAddress(addresstext.getText());
            for(int i=2020;i>2010;i--)
            {
                taxbox.getChildren().add(new Label(currentproperty.getPreviousYearData(i).getTax()));
                lastbox.getChildren().add(taxbox);
            }
        });

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vbox);
        BorderPane.setAlignment(vbox, Pos.TOP_LEFT);

        Scene scene = new Scene(borderPane, 450, 300);
        stage.setTitle("View Tax By Address");
        stage.setScene(scene);
        stage.show(); //Display GUI  
    }

    /**
     * "Sub" method for managementViewTaxGUI Search database to display tax due
     * for a property for all years
     */
    public void managementViewOwnerTaxGUI(Stage stage) {
        Button backbtn = new Button("BACK");
        Button ownerbtn = new Button("Search");
        Label ownerlabel = new Label("Enter Owner name");
        ownerlabel.setStyle("-fx-font-weight: bold");
        TextField ownertext = new TextField();
        
        Label yearlabel=new Label("Year");
        Label taxlabel=new Label("Tax");
        HBox labelbox=new HBox();
        labelbox.setSpacing(150);
        labelbox.getChildren().add(yearlabel);
        labelbox.getChildren().add(taxlabel);
        labelbox.setStyle("-fx-font-weight: bold");
        
        HBox lastbox=new HBox();
        lastbox.setSpacing(100);
        VBox vboxyear=new VBox();
        vboxyear.setSpacing(10);
        //label for all years to display taxes for
        for(int i=2020;i>2010;i--)
            vboxyear.getChildren().add(new Label(Integer.toString(i)));
        lastbox.getChildren().add(vboxyear);
        
        
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        HBox hbox = new HBox();
        hbox.setSpacing(5);
        hbox.getChildren().add(ownerlabel);
        hbox.getChildren().add(ownertext);
        hbox.getChildren().add(ownerbtn);
        vbox.getChildren().add(backbtn);
        vbox.getChildren().add(hbox);
        vbox.getChildren().add(labelbox);
        vbox.getChildren().add(lastbox);
        
        VBox taxbox=new VBox();
        taxbox.setSpacing(10);

        backbtn.setOnAction((ActionEvent event) -> { //Backs out to managementViewTaxGUI
            managementViewTaxGUI(stage);
        });

        //returns taxes for specific years for the properties of owner and displays them
        ownerbtn.setOnAction((ActionEvent event) -> {
            currentproperty=Tax.getPropertiesByOwner(ownertext.getText()).get(1);
            for(int i=2020;i>2010;i--)
            {
                taxbox.getChildren().add(new Label(currentproperty.getPreviousYearData(i).getTax()));
                lastbox.getChildren().add(taxbox);
            }
        });

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vbox);
        BorderPane.setAlignment(vbox, Pos.TOP_LEFT);

        Scene scene = new Scene(borderPane, 450, 300);
        stage.setTitle("View Tax By Owner Name");
        stage.setScene(scene);
        stage.show(); //Display GUI   
    }

    /**
     * Displays overdue tax for a specific location for a specific year
     */
    //unfinished
    public void managementViewOverDueTaxGUI(Stage stage) {
        Button backbtn = new Button("BACK");
        Button yearbtn = new Button("Search");
        Label yearlabel = new Label("Enter Year");
        TextField yeartext = new TextField();
        Button eircodebtn = new Button("Search");
        Label eircodelabel = new Label("Enter Eircode");
        TextField eircodetext = new TextField();
        yearlabel.setStyle("-fx-font-weight: bold");
        eircodelabel.setStyle("-fx-font-weight: bold");

        HBox yearbox = new HBox();
        HBox eircodebox = new HBox();
        yearbox.setSpacing(5);
        eircodebox.setSpacing(5);
        VBox vbox = new VBox();
        vbox.setSpacing(5);

        yearbox.getChildren().add(yearlabel);
        yearbox.getChildren().add(yeartext);
        yearbox.getChildren().add(yearbtn);
        eircodebox.getChildren().add(eircodelabel);
        eircodebox.getChildren().add(eircodetext);
        eircodebox.getChildren().add(eircodebtn);
        vbox.getChildren().add(backbtn);
        vbox.getChildren().add(yearbox);
        vbox.getChildren().add(eircodebox);

        backbtn.setOnAction((ActionEvent event) -> { //Backs out to managementViewTaxGUI
            managementGUI(stage);
        });

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vbox);
        BorderPane.setAlignment(vbox, Pos.TOP_LEFT);

        Scene scene = new Scene(borderPane, 450, 300);
        stage.setTitle("View Overdue Tax");
        stage.setScene(scene);
        stage.show(); //Display GUI  

    }

    /**
     * Search by eircode Shows property name, property tax, taxable amount
     */
    public void managementViewStatsByLocation(Stage stage) {
        Button backbtn = new Button("BACK");
        Label eircodelabel = new Label("Eircode");
        eircodelabel.setStyle("-fx-font-weight: bold");
        TextField eircodetext = new TextField();
        Button searchbtn = new Button("Search");
        HBox eircodebox = new HBox();
        eircodebox.setSpacing(7);
        VBox vbox = new VBox();
        vbox.setSpacing(7);

        HBox infobox=new HBox();
        VBox propbox=new VBox();
        VBox taxbox=new VBox();
        VBox taxablebox=new VBox();
            
        backbtn.setOnAction((ActionEvent event) -> { //Backs out to managementGUI
            managementGUI(stage);
        });
        
        //gets all properties based on eircode routing key
        //displays property address, tax, taxable amount for each one
        searchbtn.setOnAction((ActionEvent event) -> {
            ArrayList<Property> arrlist=Tax.getPropertiesByEircodeRoutingKey(eircodetext.getText().getRoutingKey());
            for(int i=0;i<=arrlist.size();i++){
               propbox.getChildren().add(new Label(arrlist.get(i).getAddress()));
               taxbox.getChildren().add(new Label(Double.toString(arrlist.get(i).getTax())));
               taxablebox.getChildren().add(new Label(Double.toString(arrlist.get(i).getTax())));
            }
                
        });

        Label propertylabel = new Label("Property Name");
        Label propertytaxlabel = new Label("Property Tax");
        Label propertytaxable = new Label("Taxable Amount");
        HBox propertybox = new HBox();
        propertybox.setStyle("-fx-font-weight: bold");
        propertybox.setSpacing(90);
        propertybox.getChildren().add(propertylabel);
        propertybox.getChildren().add(propertytaxlabel);
        propertybox.getChildren().add(propertytaxable);

        eircodebox.getChildren().add(eircodelabel);
        eircodebox.getChildren().add(eircodetext);
        eircodebox.getChildren().add(searchbtn);
        vbox.getChildren().add(backbtn);
        vbox.getChildren().add(eircodebox);
        vbox.getChildren().add(propertybox);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vbox);
        BorderPane.setAlignment(vbox, Pos.TOP_LEFT);

        Scene scene = new Scene(borderPane, 450, 300);
        stage.setTitle("View Statistics By Location");
        stage.setScene(scene);
        stage.show(); //Display GUI  
    }

    /**
     * Enter proposed new tax rate Shows how much revenue you will get from that
     * tax rate
     */
    public void managementSampleTaxChanges(Stage stage) {
        Button backbtn = new Button("BACK");
        Label taxratelabel = new Label("Enter New Tax Rate (Decimal)");
        taxratelabel.setStyle("-fx-font-weight: bold");
        TextField taxratetext = new TextField();
        Button taxratebtn = new Button("View Sample Revenue");
        HBox taxratebox = new HBox();
        VBox vbox = new VBox();
        taxratebox.setSpacing(7);
        vbox.setSpacing(7);

        Label revenuelabel=new Label("New Revenue");
        Label revenuelabel2=new Label();
        
        taxratebox.getChildren().add(taxratelabel);
        taxratebox.getChildren().add(taxratetext);
        taxratebox.getChildren().add(taxratebtn);
        vbox.getChildren().add(backbtn);
        vbox.getChildren().add(taxratebox);

        backbtn.setOnAction((ActionEvent event) -> { //Backs out to managementGUI
            managementGUI(stage);
        });
        
        //Shows new revenue for new proposed tax amount
        taxratebtn.setOnAction((ActionEvent event) -> {
            double taxrate=Double.parseDouble(taxratetext.getText());
            revenuelabel2.setText(impactOfChangedTaxRates_OnRevenue(taxrate));
        });

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vbox);
        BorderPane.setAlignment(vbox, Pos.TOP_LEFT);

        Scene scene = new Scene(borderPane, 550, 300);
        stage.setTitle("Sample Tax Rate Changes");
        stage.setScene(scene);
        stage.show(); //Display GUI  
    }

}