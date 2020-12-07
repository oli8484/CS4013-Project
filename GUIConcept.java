/**
 * Invoke start method
 * Invoke promptNameGUI, prompts to enter the owners name  
 * Select to a)register property, b)view properties, c)view balance
 * 
 * a)Enter in details, stores it
 * b)Displays all properties belonging to logged in owner
 * c)gets balance of selected property and year 
 */
  



package testing;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUIConcept extends Application{
    
    public static void main(String[] args){
    launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        promptNameGUI(stage);
    }
    
    public void promptNameGUI(Stage stage){
     VBox vbox = new VBox();
     vbox.setSpacing(7);
     vbox.setAlignment(Pos.CENTER);
     
     Label namelabel=new Label("Enter Name");
     TextField nametext=new TextField();
     Button btnlog=new Button("Login");
     namelabel.setStyle("-fx-font-weight: bold");

     vbox.getChildren().add(namelabel);
     vbox.getChildren().add(nametext);
     vbox.getChildren().add(btnlog);
    
      btnlog.setOnAction((ActionEvent event) -> { //matches name to arraylist??
          if(nametext.getText().equals("h"))
              postLoginGUI(stage);
          else System.out.println("gay");
     });
     
     BorderPane borderPane = new BorderPane();
     borderPane.setCenter(vbox);
     BorderPane.setAlignment(vbox, Pos.CENTER);
    
     Scene scene = new Scene(borderPane, 220, 90);
     stage.setTitle("LOGIN"); 
     stage.setScene(scene); 
     stage.show(); 
}
    
    
    public void postLoginGUI(Stage stage){
        HBox hbox=new HBox();
        hbox.setSpacing(7);
        hbox.setAlignment(Pos.CENTER);
    
        Button btnreg=new Button("Register Property");
        Button btnview=new Button("View all Properties");
        Button btnbal=new Button("View Balance Statement");
           
        hbox.getChildren().add(btnreg);
        hbox.getChildren().add(btnview);
        hbox.getChildren().add(btnbal);
        
        btnreg.setOnAction((event) -> {
            registerGUI(stage);
   
});
        btnview.setOnAction((event) -> {
            viewGUI(stage);   
});
        btnbal.setOnAction((event) -> {
            balanceGUI(stage);   
});
    
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(hbox);
        BorderPane.setAlignment(hbox, Pos.CENTER);
        
         Scene scene = new Scene(borderPane, 400, 30);
        stage.setTitle("Action"); 
        stage.setScene(scene); 
        stage.show();
    
    }
    
    public void registerGUI(Stage stage){
      VBox vboxlabel =new VBox();
      vboxlabel.setSpacing(19);
      vboxlabel.setStyle("-fx-font-weight: bold");
      String[] namearr={"Property Owner(s):","Address:","PostCode:","Estimated Market Value:","Location Category:","Principal Private Residence:"};
        for (String namearr1 : namearr) {
            vboxlabel.getChildren().add(new Label(namearr1));
        }
     
      VBox vboxtext=new VBox();
      vboxtext.setSpacing(10);     
      TextField nametext=new TextField();
      TextField addresstext=new TextField();
      TextField eircodetext=new TextField();
      TextField valuetext=new TextField();
      TextField locationtext=new TextField();
      TextField residencetext=new TextField();
      vboxtext.getChildren().add(nametext);
      vboxtext.getChildren().add(addresstext);
      vboxtext.getChildren().add(eircodetext);
      vboxtext.getChildren().add(valuetext);
      vboxtext.getChildren().add(locationtext);
      vboxtext.getChildren().add(residencetext);
      
      Button btn=new Button("Register Property Info");
      Button backbtn = new Button("BACK");
      
      Label infolabel=new Label("Location Category:(City/Large town/Small town/Village/Countryside)");
      Label infolabel2=new Label("Principal private residence:(Y/N)");
      infolabel.setStyle("-fx-font-weight: bold");
      infolabel2.setStyle("-fx-font-weight: bold");
      VBox vbox = new VBox();
      vbox.setSpacing(10);
      vbox.getChildren().add(infolabel);
      vbox.getChildren().add(infolabel2);
      
      HBox hboxbtn=new HBox();
      hboxbtn.setSpacing(210);
      hboxbtn.getChildren().add(backbtn);
      hboxbtn.getChildren().add(btn);
      vbox.getChildren().add(hboxbtn);
      
        BorderPane borderPane = new BorderPane();
        borderPane.setRight(vboxtext);
        BorderPane.setAlignment(vboxtext, Pos.CENTER_LEFT);
       
        borderPane.setLeft(vboxlabel);
        BorderPane.setAlignment(vboxlabel, Pos.CENTER_RIGHT);
        
        borderPane.setBottom(vbox);
        BorderPane.setAlignment(vbox, Pos.BOTTOM_CENTER);
        
        btn.setOnAction((event) -> {
            //store the info into the place??
   
});
        
        backbtn.setOnAction((event) -> { //backs out to post login gui
            postLoginGUI(stage);
   
});
        
        Scene scene = new Scene(borderPane, 400, 300);
        stage.setTitle("Register Property"); 
        stage.setScene(scene); 
        stage.show();
      
    }
     
    /**
     * Needs to also take in arraylist as param to display them
     */
    public void viewGUI(Stage stage){
        Button searchbtn = new Button("Search By Year");
        Button backbtn=new Button("BACK");
        TextField searchtext = new TextField("Search by Year [eg '2019']");
        Label namelabel=new Label("Property Name");
        Label duelabel=new Label("Due Tax");
        Label overduelabel=new Label("Overdue Tax");
       

        VBox vbox=new VBox();
        HBox searchbox=new HBox();
        HBox titlebox=new HBox();
        vbox.setSpacing(10);
        titlebox.setSpacing(130);
        searchbox.setSpacing(10);
        

        
        searchbox.getChildren().add(searchtext);
        searchbox.getChildren().add(searchbtn);
        
        titlebox.getChildren().add(namelabel);
        titlebox.getChildren().add(duelabel);
        titlebox.getChildren().add(overduelabel);
        titlebox.setStyle("-fx-font-weight: bold");

       
        vbox.getChildren().add(backbtn);
        vbox.getChildren().add(searchbox);
        vbox.getChildren().add(titlebox);
        
        backbtn.setOnAction((event) -> {
                postLoginGUI(stage);
});
        
         searchbtn.setOnAction((event) -> {
                //waiting.....
         });
        
        
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(vbox);
        BorderPane.setAlignment(vbox, Pos.CENTER);
        
        Scene scene = new Scene(borderPane, 500, 500);
        stage.setTitle("Property Viewer"); 
        stage.setScene(scene); 
        stage.show();
    }
    
     public void balanceGUI(Stage stage){
         TextField propertytext =new TextField();
         TextField yeartext=new TextField();
         Label propertylabel=new Label("Property name:");
         Label yearlabel=new Label("Year:");
         Button submitbtn=new Button("Get Statement");
         Button backbtn=new Button("BACK");
         VBox vboxtext=new VBox();
         VBox vboxlabel=new VBox();
         HBox hbox=new HBox();
         vboxtext.setSpacing(7);
         vboxlabel.setSpacing(20);
         hbox.setSpacing(30);
         vboxlabel.setStyle("-fx-font-weight: bold");
         
       
         vboxlabel.getChildren().add(yearlabel);
         vboxlabel.getChildren().add(propertylabel);
         vboxlabel.getChildren().add(backbtn);
         vboxtext.getChildren().add(yeartext);
         vboxtext.getChildren().add(propertytext);
         vboxtext.getChildren().add(submitbtn);
         
      
        
         hbox.getChildren().add(vboxlabel);
         hbox.getChildren().add(vboxtext);
         
         submitbtn.setOnAction((ActionEvent event) -> {
             //searches arraylist by year and property then displays info
         });
         
         backbtn.setOnAction((ActionEvent event) -> {
                postLoginGUI(stage);
         });
       
       
                                 
         
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(hbox);
        BorderPane.setAlignment(hbox, Pos.TOP_LEFT);
        
        Scene scene = new Scene(borderPane, 500, 500);
        stage.setTitle("Balancing Statement"); 
        stage.setScene(scene); 
        stage.show();
    }
    
  
}


