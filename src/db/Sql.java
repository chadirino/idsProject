package db;

import windows.*;
import java.sql.ResultSet;
import net.proteanit.sql.*;

import java.sql.PreparedStatement;

public class Sql {
	
    // change password
    public static void setPassword(String user, String password) {
    	DbConnection.connect();
    	try {
			PreparedStatement pst = DbConnection.con.prepareStatement("Update userLogin set password = \"" + password + "\" where userName = \"" + user + "\"");
			pst.executeUpdate();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		DbConnection.disconnect();
    }
    
    // validating user login
    public static boolean validCredentials(String user, String password) {
    	DbConnection.connect();
		int counter = 0;
		try {
			PreparedStatement pst = DbConnection.con.prepareStatement("select * from userLogin where userName = \"" + user + "\" and password = \"" + password + "\"");
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				counter++;
			}
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		DbConnection.disconnect();
		
		if(counter == 0) {
			System.out.println("invalid credentials");
			return false;
		}
		else {
			System.out.println("valid credentials");
			return true;
		}
	}
    
    // get user type
    public static String getUserType(String user) {
    	DbConnection.connect();
		String type = "";
		try {
			PreparedStatement pst = DbConnection.con.prepareStatement("select * from userLogin where userName = \"" + user + "\"");
			ResultSet rs = pst.executeQuery();
			
			type = rs.getString("userType");
			System.out.println("the user is a(n): "+ type);
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		DbConnection.disconnect();
		
		
		return type;
	}

	public static void main(String[] args) {
		DbConnection.connect();
		DbConnection.disconnect();
	}
	
	//view users list
	public static void getUsers() {
		DbConnection.connect();
		try {
			PreparedStatement pst = DbConnection.con.prepareStatement("select \"employeeID\", \"userName\" from userLogin");
			ResultSet rs = pst.executeQuery();
			AdminGUI.tblView.setModel(DbUtils.resultSetToTableModel(rs));
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
		DbConnection.disconnect();
	}
	
	//view list of inventory items
	public static void getIngredients() {
		DbConnection.connect();
		try {
			PreparedStatement pst = DbConnection.con.prepareStatement("select * from ingredient");
			ResultSet rs = pst.executeQuery();
			IngredientGUI.tblNonEdit.setModel(DbUtils.resultSetToTableModel(rs));
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		DbConnection.disconnect();
	}

	//view list of inventory items
	public static void getPurchases() {
		DbConnection.connect();
		try {
			PreparedStatement pst = DbConnection.con.prepareStatement("select * from purchase");
			ResultSet rs = pst.executeQuery();
			PurchaseGUI.tblNonEdit.setModel(DbUtils.resultSetToTableModel(rs));
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		DbConnection.disconnect();
	}
	// sort by date
	    public static void setDateRange(Date date1, Date date2) {
	    	DbConnection.connect();
	    	try {
			PreparedStatement pst = DbConnection.con.prepareStatement("Select * from purchase where date > \"" + date1 + "\" and date < \"" + date2 + "\"");
			pst.executeUpdate();
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
			DbConnection.disconnect();
	    }
	
	// sort by date in ascending order
	    public static void sortAscending() {
	    	DbConnection.connect();
	    	try {
			PreparedStatement pst = DbConnection.con.prepareStatement("Select * from purchase order by date");
			pst.executeUpdate();
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
			DbConnection.disconnect();
	    }
	    
	 // sort by date in descending order
	    public static void sortDescending() {
	    	DbConnection.connect();
	    	try {
			PreparedStatement pst = DbConnection.con.prepareStatement("Select * from purchase order by date desc");
			pst.executeUpdate();
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
			DbConnection.disconnect();
	    }
	    
}


/*    
    
    

    //view list of inventory items
	public static void inventory_view() {
		connect();
		try {
			PreparedStatement pst = con.prepareStatement("select * from ingredient");
			ResultSet rs = pst.executeQuery();
			InventoryGUI.tblNonEdit.setModel(DbUtils.resultSetToTableModel(rs));
//			while (rs.next()) {
//				System.out.println(rs.getString("name") + "\t" + rs.getString("address"));
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		disconnect();

		}
	
	//view users list
	public static void user_view() {
		connect();
		try {
			PreparedStatement pst = con.prepareStatement("select \"Employee ID\", \"username\" from user");
			ResultSet rs = pst.executeQuery();
			AdminGUI.tblView.setModel(DbUtils.resultSetToTableModel(rs));
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		disconnect();

		}
	
	public static void user_add() {
		connect();
		try {
			//need to see which jtext fields will produce what to complete query
			PreparedStatement pst = con.prepareStatement("insert into user (\"Employee ID\", \"username\", \"password\") values");
			ResultSet rs = pst.executeQuery();
			//double-check Jtable being used
			AdminGUI.tblUpdate.setModel(DbUtils.resultSetToTableModel(rs));
//			while (rs.next()) {
//				System.out.println(rs.getString("name") + "\t" + rs.getString("address"));
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		disconnect();

		}
	
	//view purchases list
		public static void purchases_view() {
			connect();
			try {
				//query may need some adjustment
				PreparedStatement pst = con.prepareStatement("select * from purchases");
				ResultSet rs = pst.executeQuery();
				PurchasesGUI.tblNonEdit.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
			disconnect();

			}
	
		//add purchases
		public static void purchases_add() {
			connect();
			try {
				//query may still need some adjustment
				PreparedStatement pst = con.prepareStatement("insert into purchases (\"Purchase ID\", \"username\", \"ingredient\", \"qantity\") values(...........)"  );
				ResultSet rs = pst.executeQuery();
				PurchasesGUI.tblEdit.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
			disconnect();

			}
		
		


	public static void main(String[] args) {
		Sql s = new Sql();
		inventory_view();
	}



}
*/
