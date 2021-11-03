package login;
import mainMenu.mainMenu;
import user.user;
import brand.*;
import user.userLanding.*;
import java.util.*;
//import connection.ConnectionObj;
import admin.adminLanding.adminLanding;
//import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//done
public class loginPage
{
//	ResultSet rs=null;
//	Connection conn = null;
	/*public loginPage()
	{
		super();
		//conn = ConnectionObj.getConnection();	
	}*/
	
	public static void signIn()
	{
		Scanner sc=new Scanner(System.in);
		loginPage lp=new loginPage();
		adminLanding admin=new adminLanding();
		brandLanding Brand=new brandLanding();
		System.out.println("Enter username and password");
		String username=sc.nextLine();
		String password=sc.nextLine();
		user User=new user();
		User.setUserId(username);
		User.setPassword(password);
		boolean userPresent=true;
		int userType = 2;
		/*try 
		{
			userPresent=lp.validate(User);
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		if(userPresent)
		{
			//int userType=lp.checkAndGetUserType(User);
			if(userType==1)
			{
				admin.display();
			}
			else if(userType==2)
			{
				Brand.display();
			}
			else
			{
				//customer
				userLanding usrland=new userLanding();
				usrland.display();
			}
		}
	}
	
	
//	
//	public boolean validate(user User) throws SQLException
//	{
//		String select_user_query=generateSelectQuery();
//		Statement stmt=conn.createStatement();  
//		rs=stmt.executeQuery(select_user_query);  
//		while(rs.next())
//		{
//			System.out.println(rs.getString("CUSTOMERID"));
//		}
//		return true;
//	}
//	
	
	
//	
//	public int checkAndGetUserType(user User) {
//			
//			String userId = User.getUserId();
//			String password = User.getPassword();
//	//		String address = user.getRole();
//			
//			String insert_user_query = getInsertQuery(userId, password);
//			String type = "A";
//			Statement stmt;
//			try {
//				stmt = conn.createStatement();
//				rs=stmt.executeQuery(insert_user_query);  
//				type = rs.getString("USER_TYPE");
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}  
//			
//			int id = 1;
//			switch(type) {
//				case "C": 
//					id = 3;
//					break;
//				case "B": 
//					id = 2;
//					break;
//				case "A": 
//					id = 1;
//					break;
//				
//			}
//			return id;
//			
//		}
//		
//	public String generateSelectQuery()
//	{
//		return "select * from customer";
//	}
//	
//	public String getInsertQuery(String id, String pwd) {
//		return "INSERT INTO USERS(USERID, PASSWORD, USER_TYPE) VALUES (" + id + ", " + pwd + ")";
//	}
//	
//	
	public void display()
	{
		//loginPage lp=new loginPage();
		Scanner sc=new Scanner(System.in);
		System.out.println("1.Sign In");
		System.out.println("2.Go Back");
		int choice=sc.nextInt();
		switch(choice)
		{
			case 1:
				signIn();
				break;
			case 2:
				return;
		}
	}
	
}







	
	
	