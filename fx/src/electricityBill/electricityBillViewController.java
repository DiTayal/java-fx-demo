package electricityBill;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.security.auth.callback.ConfirmationCallback;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class electricityBillViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtUnits;

    @FXML
    private TextField txtLoad;

    @FXML
    private TextField txtAc;

    @FXML
    private TextField txtCooler;

    @FXML
    private TextField txtType;

    @FXML
    private TextField txtUnitsBill;

    @FXML
    private TextField txtLoadBill;

    @FXML
    private TextField txtAcBill;

    @FXML
    private TextField txtCoolerBill;

    @FXML
    private TextField txtTotal;

    @FXML
    private TextField txtNet;

    @FXML
    private TextField txtDiscount;

    @FXML
    
    void doBill(ActionEvent event) {
    	
    	//if(txtUnits.getText()=="")----- == not work ,it does refernce comparison and getting null refernce and hence exception
    		
    	if(txtUnits.getText().equals(""))
    		showMsg("Fill Units");
    	else if (txtLoad.getText().equals(""))
    		showMsg("Fill Load");
    	/*else if (txtAc.getText()=="")---give default 0 as  number-do in constructor
    		showMsg("Fill number of AC");
    	else if (txtCooler.getText()=="")
    		showMsg(" Fill number of Coolers");*/
    	else if (txtType.getText().equals(""))
    		showMsg("Fill Type");
    	else
    	{//txtUnits.getText()--gives string, so type cast
    		float units=Float.parseFloat(txtUnits.getText());
    		float load=Float.parseFloat(txtLoad.getText());
    		float ac=Float.parseFloat(txtAc.getText());
    		float cooler=Float.parseFloat(txtCooler.getText());
    		float total=units*10+load*100+ac*100+cooler*50;
    		
    		float disc=0.0f;//done else give error that disc may not be initialised due to last else
    		
    		String type=(txtType.getText());
    		if(type.equals("d"))// equlas cm=ompare strings,so convert d char into string
    			disc=0.1f;
    		else if(type.equals("c"))
    			disc=0.2f;
    		else
    			showMsg("Enter d or c only");
    		
    		//txtUnitsBill=units*10;-error
    		//txtUnitsBill=String.valueOf(units*10);--error
    		txtUnitsBill.setText( String.valueOf(units*10));
    		txtLoadBill.setText(String.valueOf(load*100));
    		txtAcBill.setText(String.valueOf(ac*100));
    		txtCoolerBill.setText(String.valueOf(cooler*50));
    		txtTotal.setText(String.valueOf(total));
    		txtDiscount.setText(String.valueOf(total*disc));
    		txtNet.setText(String.valueOf(total-(total*disc))); 		
    	}
    }
    void showMsg(String str)//ALERT IS INBUILT CLASS....... COPY CODE
    {
    	//******************************
    	//https://code.makery.ch/blog/javafx-dialogs-official/
    	//******************************
    	
    	Alert al=new Alert(AlertType.ERROR);//.error or warning etc
    	 
    	al.setTitle("DATA ERROR");
    	al.setHeaderText("Unfilled Values");//by default- header is type of alert
    	al.setContentText(str);
    	al.show();
    }

    @FXML
    void doClear(ActionEvent event) {
    	txtUnits.setText("");
		txtLoad.setText("");
		txtType.setText("");
    	txtUnitsBill.setText("");
		txtLoadBill.setText("");
		txtAcBill.setText("");
		txtCoolerBill.setText("");
		txtTotal.setText("");
		txtDiscount.setText("");
		txtNet.setText("");//"         " is not emp 	
		txtAc.setText(String.valueOf(0));
		txtCooler.setText(String.valueOf(0));
    }

    @FXML
    void doClose(ActionEvent event) {
    	Alert al=new Alert(AlertType.CONFIRMATION);
    	al.setTitle("Confirmation Dialogue");
    	al.setContentText("Are you sure to Close?");
    	
    	//al.show()-------dont add here else show and wait not work----------************------------
    	Optional<ButtonType> result = al.showAndWait();
    	
        	if (result.get() == ButtonType.OK)
        	{
        	    // ... user chose OK
        		
        	}
        	else 
        	{
        	  System.exit(0);
        	}	
      }

    @FXML
    void initialize() {//contructor--already given content is useless,so amy remove
    	
    	txtAc.setText(String.valueOf(0));
		txtCooler.setText(String.valueOf(0));
        
    }
}
