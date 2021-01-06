package myjdbc;

import java.sql.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.naming.spi.DirStateFactory.Result;

//import com.mysql.jdbc.PreparedStatement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class myjdbcViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtPer;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtRoll;

    @FXML
    private Label lblResult;

    @FXML
    private DatePicker dateDob;

    @FXML
    void doDelete(ActionEvent event) {
    	int roll=Integer.parseInt(txtRoll.getText());
    	PreparedStatement pst;
		try {
			pst = con.prepareStatement("delete from student where roll=?");
			int count=pst.executeUpdate();
	    	lblResult.setText(count+" records recorded");
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void doFetch(ActionEvent event) {
    	boolean jasus=false;
    	txtPer.clear();//to clear--in case invalid roll entered
    	txtName.clear();
    	//dateDob.clearDirty();
    	dateDob.getEditor().setText("");
    	try {
    		int roll=Integer.parseInt(txtRoll.getText());
			PreparedStatement pst = con.prepareStatement("select*from student where roll=?");
			pst.setInt(1, roll);
			ResultSet table=pst.executeQuery();
			while(table.next())//must else error
			{
				jasus=true;
				txtPer.setText(table.getFloat("per")+"");
				txtName.setText(table.getString("name"));
				java.sql.Date sqldate=table.getDate("DOB");
				dateDob.getEditor().setText(sqldate.toString());
				lblResult.setText("successfully received");//or can set lblresult=""as sir did
			}
			if(jasus==false)
				lblResult.setText("invalid record");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void doSave(ActionEvent event) {
    	int roll=Integer.parseInt(txtRoll.getText());
    	float per=Float.parseFloat(txtPer.getText());
    	String name=txtName.getText();
    	LocalDate ldob=dateDob.getValue();
    	java.sql.Date sqlDate=java.sql.Date.valueOf(ldob);
    	
    	PreparedStatement pst;
		try {
			pst = con.prepareStatement("insert into student values(?,?,?,?)");
			pst.setInt(1,roll);
	    	pst.setString(2,name);
	    	pst.setFloat(3,per);
	    	pst.setDate(4,sqlDate);
	    	
	    	int count=pst.executeUpdate();
	    	
	    	lblResult.setText(count+" records added");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void doShowAll(ActionEvent event) {
    	
    	try {
			PreparedStatement pst = con.prepareStatement("select*from student");
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				int roll=table.getInt("roll");
				float per=table.getFloat("per");
				String name=table.getString("name");
				java.sql.Date sqldate=table.getDate("DOB");
				System.out.println(roll+"\t"+name+"\t"+per+"\t"+sqldate.toString());				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void doUpdate(ActionEvent event) {//first fetch then update done
    	
    	int roll=Integer.parseInt(txtRoll.getText());
    	float per=Float.parseFloat(txtPer.getText());
    	String name=txtName.getText();
    	
    	LocalDate ldob=dateDob.getValue();
    	java.sql.Date sqlDate;//=java.sql.Date.valueOf(ldob);
    	String stringdob;
    	
    	//panga in date as if date not updated then, date in string form ,else in local date format
    	//if(ldob=="")//not updated
    	if(ldob==null)
    	{
    		stringdob=dateDob.getEditor().getText();
    		ldob=LocalDate.parse(stringdob);    		
    	}
    	java.sql.Date sqldate=java.sql.Date.valueOf(ldob);
    	
    	PreparedStatement pst;
		try {
			pst = con.prepareStatement("update student set name=?,per=?,DOB=? where roll=?");
			pst.setInt(4,roll);
	    	pst.setString(1,name);
	    	pst.setFloat(2,per);
	    	pst.setDate(3,sqldate);
	    	
	    	int count=pst.executeUpdate();
	    	
	    	lblResult.setText(count+" record updated");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	

    }
Connection con;
    @FXML
    void initialize() {
       con=doConnection.doConnect();

    }
}
