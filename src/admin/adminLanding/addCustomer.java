package admin.adminLanding;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import user.*;
import admin.adminLandingPage;
import connection.ConnectionObj;
import login.loginPage;
public class addCustomer
{	//done
	
	
	static ResultSet rs=null;
	static Connection conn = null;
	
	static String userTypeCustomer = "c";
	
	public addCustomer()
	{
		super();
		conn = ConnectionObj.getConnection();	
	}
	public void display()
	{
		Scanner sc=new Scanner(System.in);
		
		user userObj=new user();
		
		System.out.println("Enter customer id");
		String userId=sc.nextLine();
		System.out.println("Enter the first name:");
		String firstName=sc.nextLine();
		System.out.println("Enter the last name:");
		String lastName=sc.nextLine();
		System.out.println("Enter the password");
		String password=sc.nextLine();
		System.out.println("Enter the address");
		String address=sc.nextLine();
		System.out.println("Enter the phone number");
		String phoneNumber=sc.nextLine();
		
		userObj.setUserId(userId);		
		userObj.setFirstName(firstName);	
		userObj.setLastName(lastName);
		userObj.setPassword(password);
		userObj.setAddress(address);
		userObj.setPhoneNumber(phoneNumber);


		System.out.println("1. Add user");
		System.out.println("2.Go Back");
		
		int choice=sc.nextInt();
		
		switch(choice)
		{
			case 1:
				boolean insertDone = custFunct(userObj);
				if(insertDone)
				{
					System.out.println("User information successfully inserted");
				}
				adminLandingPage alp=new adminLandingPage();
				alp.display();
				break;
			case 2:
				adminLandingPage alp1=new adminLandingPage();
				alp1.display();
				break;
		}
	}
	
	public boolean custFunct(user usr)
	{


		boolean custAdded=addCustomerToDB(usr);
		//if brand is added successfully navigate to login page
		if(custAdded)
		{
			return true;
		} else {
			return false;
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
			e.printStackTrace();
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
