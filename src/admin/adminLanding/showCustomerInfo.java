package admin.adminLanding;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Scanner;

import admin.adminLandingPage;
import connection.ConnectionObj;

public class showCustomerInfo
{	
	static ResultSet rs=null;
	static Connection conn = null;
		
	public showCustomerInfo()
	{
		super();
		conn = ConnectionObj.getConnection();	
	}
	//done
	public void show(String customerId)
	{
		//get customer information on the basis of customerId
		//print customer information
		//if any error occurs print the error
		
		String query = "SELECT CUSTOMERID, LASTNAME, FIRSTNAME, ADDRESS, PHONE, WALLETID FROM CUSTOMER WHERE CUSTOMERID = '" + customerId + "'";
		
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			System.out.println("Error in showCustomerInfo");
		}  
		try {
			rs=stmt.executeQuery(query);
		} catch (SQLException e) {
			System.out.println("Error in showCustomerInfo");
		}  
		String walletId = "";
		try {

			while(rs.next()) {
			String cId = rs.getString("CUSTOMERID");
			String cLastName = rs.getString("LASTNAME");
			String cFirstName = rs.getString("FIRSTNAME");
			String cAddr = rs.getString("ADDRESS");
			String cPhone = rs.getString("PHONE");
			walletId = rs.getString("WALLETID");
			
			System.out.println("-------------------CUSTOMER INFO FOR: " + cId + " --------------------");
			System.out.println("Customer Id:" + cId);
			System.out.println("Customer Last Name:" + cLastName);
			System.out.println("Customer First Name:" + cFirstName);
			System.out.println("Customer Address:" + cAddr);
			System.out.println("Customer Phone:" + cPhone);
			System.out.println("Customer Wallet Id:" + walletId);
			System.out.println("-------------------------------------------------------------------");
			}
			
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println("It looks like you entered the incorrect Customer Id. Try again with a correct customer Id.");
		}
		
		System.out.println("------------------------------------------------------------");
		System.out.println("The list of loyalty programs this user is enrolled in:");
		getLoyaltyProgram(customerId);
		System.out.println("------------------------------------------------------------");
		
		String query2 = "SELECT POINTS FROM WALLET WHERE WALLETID = '" + walletId + "'";
		
		
		ResultSet rs2=null;
		Statement stmt2 = null;
		try {
			stmt2 = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		try {
			rs2=stmt2.executeQuery(query2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		try {
			while(rs2.next()) {
				System.out.println("Total Points in Wallet: " + rs2.getString("POINTS"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("------------------------------------------------------------");
		
	}
	
	public HashMap<Integer, String> getLoyaltyProgram(String userId) {
		
//		String query = "SELECT DISTINCT LOYALTY_PROGRAM_ID, LOYALTYU FROM WALLET_TRANSACTIONS WHERE WALLETID="+"'"+userId+"'";
		String query = "SELECT DISTINCT WT.LOYALTY_PROGRAM_ID, LP.PROGRAMNAME FROM WALLET_TRANSACTIONS WT LEFT JOIN LOYALTYPROGRAM LP ON WT.LOYALTY_PROGRAM_ID = LP.PROGRAMID WHERE WALLETID='" + userId +"'";
		Statement stmt = null;
		HashMap<Integer, String> hmap = new HashMap<>();
		int count = 1;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		try {
			rs=stmt.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		try {
			while(rs.next()) {
				hmap.put(count, rs.getString("LOYALTY_PROGRAM_ID"));
				System.out.println(count + ". " + rs.getString("PROGRAMNAME"));
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hmap;
		
	}
	
	public void display()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter customer Id");
		String customerId=sc.nextLine();
		System.out.println("1.show customer info");
		System.out.println("2.Go Back");
		int choice=sc.nextInt();
		switch(choice)
		{
		case 1:
			showCustomerInfo sbiObj=new showCustomerInfo();
			sbiObj.show(customerId);

			adminLandingPage alp1=new adminLandingPage();
			alp1.display();
			
			break;
		case 2:
			adminLandingPage alp2=new adminLandingPage();
			alp2.display();
			break;
			
		}
	}
}