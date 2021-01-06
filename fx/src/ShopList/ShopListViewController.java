/**
 * Sample Skeleton for 'ShopListView.fxml' Controller Class
 */

package ShopList;

import java.net.URL;
import javafx.collections.ObservableList;
import java.util.ResourceBundle;

import javax.swing.ListModel;


import javafx.event.ActionEvent;//IMPORT THIS 
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

//import javafx.scene.input.*;

public class ShopListViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="comboItems"
    private ComboBox<String> comboItems; // Value injected by FXMLLoader

    @FXML // fx:id="listModel"
    private ListView<String> listModel; // Value injected by FXMLLoader

    @FXML // fx:id="listPrice"
    private ListView<String> listPrice; // Value injected by FXMLLoader

    @FXML // fx:id="listModelSelected"
    private ListView<String> listModelSelected; // Value injected by FXMLLoader

    @FXML // fx:id="listPriceSelected"
    private ListView<String> listPriceSelected; // Value injected by FXMLLoader

    @FXML // fx:id="rad10"
    private RadioButton rad10; // Value injected by FXMLLoader

    @FXML // fx:id="rad20"
    private RadioButton rad20; // Value injected by FXMLLoader

    @FXML // fx:id="txtDisc"
    private Label txtDisc; // Value injected by FXMLLoader

    @FXML
    void do10(ActionEvent event) {  	
    	

    }

    @FXML
    void do20(ActionEvent event) {

    }
    @FXML
    private TextField txtTotalAmount;

    @FXML
    private TextField txtNet;

//boolean []modelMob,modelLap,priceMob,priceLap;
boolean[]indexMob=new boolean[4];//default value is false
boolean[]indexLap=new boolean[4];//if one index is seleceted it cant be selcted again
//boolean[]priceMob=new boolean[4];
//boolean[]priceLap=new boolean[4];


    @FXML
    void doBill(ActionEvent event) {
    	ObservableList<String>priceTotal=listPriceSelected.getItems();
    	float price=0.0f;
    	for(String ref:priceTotal)
    	{
    		price=price+Float.parseFloat(ref);
    	}
    	txtTotalAmount.setText(String.valueOf(price));;
    	
    	float disc=0.0f;
    	if(rad10.isSelected())
    		disc=0.1f;
    	else if(rad20.isSelected())//so that if none selected no discount
    		disc=0.2f;
    	disc=disc*price;
    	//System.out.println(disc);
    	txtDisc.setText(String.valueOf(disc));
    	txtNet.setText(String.valueOf(price-disc));

    }

    @FXML
    //can deelte  1 item from selected models using button or can delete selected
    //item but to delete multiple items , need a button 
    void deleteSelModel(MouseEvent event) {
//deelte 1 item selected
    }
    

    @FXML
    void doSelModelDelete(ActionEvent event)///////////////////??????????????????????????????????????? 
    {//delete button
    	 ObservableList<String> selItem=listModelSelected.getSelectionModel().getSelectedItems();
 		 ObservableList<Integer> selInd=listModelSelected.getSelectionModel().getSelectedIndices(); 
 		 for(Integer ref:selInd)
 		  System.out.println(ref);
 		
		for(String ref:selItem)
 	 	  System.out.println(ref);
 		// int count=0;
 			//Integer count =new Integer(0);
			 int count = 0;

/*
			 playerList.getItems().remove(selectedIdx);
		        // ^ this should return players
		players.remove(selectedIdx);
*/			 
			  listModelSelected.getItems().removeAll(selItem);
			  
			  for(Integer ref:selInd)
		 		  System.out.println(ref);
			  for(String ref:selItem)
		 	 	  System.out.println(ref);
			 /* for(Integer ref:selInd)
		 		{
		 			//listModelSelected.getItems().remove(ref-count);//make count of Intege type as re fis of Integer type
		 			//listPriceSelected.getItems().remove(ref-count);
		 			listPriceSelected.getItems().remove(ref-count);
		 			++count; 			
		 		}*/
 		
 	/*	for(Integer ref:selInd)
 		{
 			listModelSelected.getItems().remove(ref-count);//make count of Intege type as re fis of Integer type
 			listPriceSelected.getItems().remove(ref-count);
 			++count; 			
 		}
 		System.out.println("***********");
 		//ObservableList<Integer> selInd=listModelSelected.getSelectionModel().getSelectedIndices();
 		ObservableList<Integer> selInd=listModelSelected.getSelectionModel();
 		 for(Integer ref:selInd)
 	 		  System.out.println(ref);*/
    }
   
    @FXML
    void doSelectModel(MouseEvent event) {
    	 if (event.getClickCount() == 2) {
    		 String selItem=listModel.getSelectionModel().getSelectedItem();
     		 Integer selInd=listModel.getSelectionModel().getSelectedIndex();
     		 if((comboItems.getSelectionModel().getSelectedItem()).equals("Laptop"))
     			 {
     			 if(indexLap[selInd]==false)
     			 {
     				listModelSelected.getItems().addAll(selItem);     
     	     		String sellap=listPrice.getItems().get(selInd);     		
     	     		listPriceSelected.getItems().addAll(sellap); 
     	     		indexLap[selInd]=true;
     			 }
     			 else
     			 {
     				 //alert!
     				Alert al=new Alert(AlertType.ERROR);//.error or warning etc
     		    	 
     		    	al.setTitle("DATA ERROR");
     		    	al.setHeaderText("Unfilled Values");//by default- header is type of alert
     		    	al.setContentText("Already Selected!!");
     		    	al.show();
     			 }
     			 }
     		 else if((comboItems.getSelectionModel().getSelectedItem()).equals("Mobile"))
     		 {
     			 if(indexMob[selInd]==false)
     			 {
     				listModelSelected.getItems().addAll(selItem);     
     	     		String sellap=listPrice.getItems().get(selInd);     		
     	     		listPriceSelected.getItems().addAll(sellap);
     	     		indexMob[selInd]=true;
     			 }
     			 else
     			 {
     				Alert al=new Alert(AlertType.ERROR);//.error or warning etc
     		    	 
     		    	al.setTitle("DATA ERROR");
     		    	al.setHeaderText("Unfilled Values");//by default- header is type of alert
     		    	al.setContentText("Already Selected!!");
     		    	al.show();
     			 }    			
     		 }	
          }
    }
    
    @FXML
    void doitemSelected(ActionEvent event) {//can make combo/list editable
    //	System.out.println("**********");
    	//to do something when item from combo is selected
    	String item=comboItems.getSelectionModel().getSelectedItem();
    	if(item.equals("Laptop"))
    	{
    		ObservableList<String>mod=listModel.getItems();
    		listModel.getItems().removeAll(mod);
    		ObservableList<String>pri=listPrice.getItems();
    		listPrice.getItems().removeAll(pri);
    		String[]laptop={"DELL","HP","MAC","ASUS"};
    		listModel.getItems().addAll(laptop);
    		
    		String[]price={"100000","100000","200000","80000"};
    		listPrice.getItems().addAll(price);
    		
    		//listModel.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);???????
    		//can apply anywhere before selecting items????????????????/   		
    	
    		//System.out.println(selectedItems);alone wont work************		
    	}
    	else if(item.equals("Mobile"))
    	{
    		ObservableList<String>mod=listModel.getItems();
    		listModel.getItems().removeAll(mod);
    		ObservableList<String>pri=listPrice.getItems();
    		listPrice.getItems().removeAll(pri);
    		String[]mobile={"REDMI","OPPO","VIVO","SAMSUNG"};
    		listModel.getItems().addAll(mobile);
    		
    		String[]price={"21000","9000","15000","8000"};
    		listPrice.getItems().addAll(price);
    			
    	}
    }

    @FXML 
        void initialize() {
    	String []list={"Mobile","Laptop"};
    	//can select only 1 item
    	comboItems.getItems().addAll(list);
    	//listModel.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	listModelSelected.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
}
