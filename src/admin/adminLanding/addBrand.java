package admin.adminLanding;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;
import brand.*;
import connection.ConnectionObj;
import login.loginPage;
import admin.adminLandingPage;
public class addBrand
{	//done
	static ResultSet rs=null;
	static Connection conn = null;
	
	static String userTypeBrand = "b";
	
	public addBrand()
	{
		super();
		conn = ConnectionObj.getConnection();	
	}
	
	public void display()
	{
		Scanner sc=new Scanner(System.in);
		
		
		System.out.println("Enter brand id:");
		String brandId=sc.nextLine();
		System.out.println("Enter the brand name:");
		String brandName=sc.nextLine();
		System.out.println("Enter the brand password:");
		String brandPass=sc.nextLine();
		System.out.println("Enter the address:");
		String address=sc.nextLine();
		System.out.println("Enter the join date:");
		String joinDate=sc.nextLine();
		

		brand brandObj=new brand();

		brandObj.setUnique_id(brandId);
		brandObj.setName(brandName);
		brandObj.setPassword(brandPass);
		brandObj.setAddress(address);
		brandObj.setJoin_date(joinDate);
		
		System.out.println("1. Add brand");
		System.out.println("2.Go Back");
		
		int choice=sc.nextInt();
		
		switch(choice)
		{
			case 1:
				boolean insertSuccess = brandFunct(brandObj);
				if(insertSuccess)
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
	
	
	public boolean brandFunct(brand b)
	{


		boolean brandAdded=addBrandToDb(b);
		//if brand is added successfully navigate to login page
		if(brandAdded)
		{
			return true;
		} else {
			System.out.println("There was some error. Please input details correctly.");
			return false;
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
		
		String query = "INSERT INTO BRAND VALUES('" + brandId + "', '" + brandName + "', '" + address + "', '', '" + joinDate + "', '')";
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

	

}
