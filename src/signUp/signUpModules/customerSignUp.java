package signUp.signUpModules;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import brand.brand;
import connection.ConnectionObj;
import user.user;
import login.loginPage;
import signUp.registrationPage;


public class customerSignUp
{
	static ResultSet rs=null;
	static Connection conn = null;
	
	static String userTypeCustomer = "c";
	
	public customerSignUp()
	{
		super();
		conn = ConnectionObj.getConnection();	
	}
	
	public void display()
	{
		System.out.println("Enter Customer Id:");
		System.out.println("Enter First Name:");
		System.out.println("Enter Last Name:");
		System.out.println("Enter the password:");
		System.out.println("Enter Address:");
		System.out.println("Enter Phone number:");
		
		Scanner sc=new Scanner(System.in);
		
		String customerId=sc.nextLine();
		String firstName=sc.nextLine();
		String lastName = sc.nextLine();
		String password = sc.nextLine();
		String address=sc.nextLine();
		String phoneNumber=sc.nextLine();
		
		user usr=new user();
		usr.setUserId(customerId);
		usr.setFirstName(firstName);
		usr.setLastName(lastName);
		usr.setPassword(password);
		usr.setAddress(address);
		usr.setPhoneNumber(phoneNumber);
		usr.setWalletId(customerId);
		
		System.out.println("1.Sign Up");
		System.out.println("2.Go Back");
		
		int choice=Integer.valueOf(sc.nextLine());
		
		switch(choice)
		{
			case 1:
				custFunct(usr);
				break;
			case 2:	
				//to go back to the registration page
				registrationPage regObj=new registrationPage();
				regObj.display();
				break;
		}
	}
	
	
	
	public void custFunct(user usr)
	{


		boolean custAdded=addCustomerToDB(usr);
		//if brand is added successfully navigate to login page
		if(custAdded)
		{
			loginPage lg=new loginPage();
			lg.display();
		} else {
			System.out.println("There was some error. Please input details correctly.");
			display();
		}
	}
	
	public boolean addCustomerToDB(user usr) {
		
		String custId = usr.getUserId();
		String firstName = usr.getFirstName();
		String lastName = usr.getLastName();
		String password = usr.getPassword();
		String address = usr.getAddress();
		String phone = usr.getPhoneNumber();
		String walletId = usr.getWalletId();
		

		
		String query = "INSERT INTO CUSTOMER VALUES('" + custId + "', '" + lastName + "', '" + firstName + "', '"+ address + "', '" + phone + "', '" + walletId + "')";
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}  
		try {
			rs=stmt.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("Customer exists.");
			return false;
		}  
		
		password = usr.getPassword();
		query = "INSERT INTO USER_DETAILS VALUES('" + custId + "', '" + password + "', '" + userTypeCustomer + "')";
		
		stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}  
		try {
			rs=stmt.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
			
		}  
		
		return true;
	}
	

	
	
}