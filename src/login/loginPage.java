package login;
import mainMenu.mainMenu;
import user.user;
import brand.*;
import connection.ConnectionObj;
import user.userLanding.*;
import java.util.*;
import admin.adminLandingPage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//done

public class loginPage
{
	static ResultSet rs1=null;
	static ResultSet rs2=null;
	static Connection conn = null;
	
	static String userTypeBrand = "b";
	static String userTypeCustomer = "c";
	static String userTypeAdmin = "a";
	
	public loginPage()
	{
		super();
		conn = ConnectionObj.getConnection();	
	}
	
	public void display()
	{
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Enter username and password in the following fields when prompted");

		System.out.println("Enter username:");
		String username=sc.nextLine();
		System.out.println("Enter password:");
		String password=sc.nextLine();
		
		System.out.println("1.Sign In");
		System.out.println("2.Go Back");
		int choice=sc.nextInt();
		switch(choice)
		{
			case 1:
				user User=new user();
				User.setUserId(username);
				User.setPassword(password);
				signIn(User);
				break;
			case 2:
//				sc.close();
				return;
		}
//		sc.close();
	}
	
	
	
	public static String getLpId(String brandId) {
		String doesUserExists = "SELECT LP_ID FROM BRAND WHERE BRANDID = '" + brandId + "'";
		Statement stmt = null;
		ResultSet rset=null;
		String lpId = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}  
		try {
			rset=stmt.executeQuery(doesUserExists);
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		
		try {
			while(rset.next())
				lpId = rset.getString("LP_ID");
			
			if(lpId != null && !lpId.equals("")) {
				return lpId;
			}
		} catch (Exception e) {
			System.out.println("Some error occured. Please try again!");
		}
		return null;
	}
	
	public static void signIn(user User)
	{
		boolean userPresent = checkUserCredentials(User);
		if(userPresent) {			
			String userType = checkAndGetUserType(User);
			brand BrandObj = new brand();
			if(userType.equals("b")) {				
				BrandObj.setUnique_id(User.getUserId());
				BrandObj.setLp_id(getLpId(BrandObj.getUnique_id()));
				callDisplayMenu(userType, User, BrandObj);				
			} else {
				callDisplayMenu(userType, User, BrandObj);	
			}
		} else {
			System.out.println("This user does not exist. Please sign up or check your credentials.");
		}
	}
	
	
	public static void callDisplayMenu(String userType, user User, brand BrandObj) {
		if(userType.equals("a")) {
			adminLandingPage admin = new adminLandingPage();
			admin.display();
		} else if(userType.equals("b")) {
			brandLanding Brand = new brandLanding();			
			Brand.display(BrandObj);
		} else if (userType.equals("c")) {
			userLanding userland = new userLanding();
			userland.display(User);
		} else {
			System.out.println("Invalid User Type Detected. Please check your username and password!");
		}
	}
	
	public static boolean checkUserCredentials(user User) {
		try {
			return validate(User);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean validate(user User) throws SQLException {
		String select_user_query=generateSelectQueryForPassword(User.getUserId());
		Statement stmt=conn.createStatement();  
		rs1=stmt.executeQuery(select_user_query);  
		String dbPassword = null;
		while(rs1.next())
			dbPassword = rs1.getString("PASSWORD");
		String inputPassword = User.getPassword();
		if(dbPassword != null &&  dbPassword.equals(inputPassword))
			return true;
		return false;
	}

	public static String checkAndGetUserType(user User) {
			
		String select_user_query=generateSelectQueryForUserType(User.getUserId());
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}  
		try {
			rs2=stmt.executeQuery(select_user_query);
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		String userType = "temp";
		try {
			while(rs2.next())
				userType = rs2.getString("USER_TYPE");
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		System.out.println(userType);
		return userType.substring(0, 1);
		
	}
		
	public static String generateSelectQueryForPassword(String userId)
	{
		return "SELECT PASSWORD FROM USER_DETAILS WHERE USERID = '" + userId + "'";
	}
	
	public static String generateSelectQueryForUserType(String userId)
	{
		return "SELECT USER_TYPE FROM USER_DETAILS WHERE USERID = '" + userId + "'";
	}
	
//	public String getInsertQuery(String id, String pwd) {
//		return "INSERT INTO USERS(USERID, PASSWORD, USER_TYPE) VALUES (" + id + ", " + pwd + ")";
//	}
	
	

}







	
	
	