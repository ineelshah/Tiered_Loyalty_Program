package signUp.signUpModules;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;
import signUp.registrationPage;
import brand.brand;
import connection.ConnectionObj;
import login.loginPage;

//done
public class brandSignUp
{
	static ResultSet rs=null;
	static Connection conn = null;
	
	static String userTypeBrand = "b";
	
	public brandSignUp()
	{
		super();
		conn = ConnectionObj.getConnection();	
	}
	
	public void display()
	{
		System.out.println("Enter the brand Id:");
		System.out.println("Enter the brand name:");
		System.out.println("Enter password:");
		System.out.println("Address:");
		System.out.println("Join Date:");
		
		Scanner sc=new Scanner(System.in);
		
		String brandId=sc.nextLine().trim();
		String brandName=sc.nextLine().trim();
		String password=sc.nextLine().trim();
		String address=sc.nextLine().trim();
		String joinDate=sc.nextLine().trim();
		
		brand b=new brand();
		b.setUnique_id(brandId);
		b.setName(brandName);
		b.setPassword(password);
		b.setAddress(address);
		b.setJoin_date(joinDate);

		System.out.println("Please Select:");
		System.out.println("1.Sign Up");
		System.out.println("2.Go Back");
		
		int choice=sc.nextInt();
		
		switch(choice)
		{
			case 1:
				brandFunct(b);
				break;
			case 2:
				//to go back to the registration page
				registrationPage regObj=new registrationPage();
				regObj.display();
				break;
		}
	}
	
	public boolean addBrandToDb(brand b)
	{
		String brandId = b.getUnique_id();
		String brandName = b.getName();
		String address = b.getAddress();
		String joinDate = b.getJoin_date();
		
		if(joinDate == null || joinDate.isEmpty()) {
			SimpleDateFormat formatter= new SimpleDateFormat("MM-dd-yyyy");
			Date date = new Date(System.currentTimeMillis());
			joinDate = String.valueOf(formatter.format(date));
		}
		
		String query = "INSERT INTO BRAND VALUES('" + brandId + "', '" + brandName + "', '" + address + "', '', '" + joinDate + "')";
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
		
		String password = b.getPassword();
		query = "INSERT INTO USER_DETAILS VALUES('" + brandId + "', '" + password + "', '" + userTypeBrand + "')";
		
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

	
	public void brandFunct(brand b)
	{


		boolean brandAdded=addBrandToDb(b);
		//if brand is added successfully navigate to login page
		if(brandAdded)
		{
			loginPage lg=new loginPage();
			lg.display();
		} else {
			System.out.println("There was some error. Please input details correctly.");
			display();
		}
	}

}