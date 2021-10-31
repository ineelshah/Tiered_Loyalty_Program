import code.mainMenu.main_menu;
import code.User.user;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class loginPage
{
	ResultSet rs=null;
	Connection conn = null;
	public loginPage()
	{
		conn = ConnectionObj.getConnection();	
	}
	public static void Main(String args)
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("1.Sign In");
		System.out.println("2.Go Back");
		int choice=sc.nextInt();
		switch(choice)
		{
			case 1:
			String username=sc.nextLine();
			String password=sc.nextLine();
			User user=new User();
			user.setUserId(username);
			user.setPassword(password)
			validate(user);
			
			break;
			case 2://go to main menu
					break;
		}
	}
}



public int checkAndGetUserType(User user) {
		
		String userId = user.getUserId();
		String password = user.getPassword();
//		String address = user.getRole();
		
		String insert_user_query = getInsertQuery(userId, password);
		String type = "A";
		Statement stmt;
		try {
			stmt = conn.createStatement();
			rs=stmt.executeQuery(insert_user_query);  
			type = rs.getString("USER_TYPE");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		int id = 1;
		switch(type) {
			case "C": 
				id = 3;
				break;
			case "B": 
				id = 2;
				break;
			case "A": 
				id = 1;
				break;
			
		}
		return id;
		
	}



	public int validate(User user) throws SQLException
	{
		String select_user_query=generateSelectQuery();
		Statement stmt=conn.createStatement();  
		rs=stmt.executeQuery(select_user_query);  
		while(rs.next())
		{
			System.out.println(rs.getString("CUSTOMERID"));
		}
		return 0;
	}
	
	public String generateSelectQuery()
	{
		return "select * from customer";
	}
	
	public String getInsertQuery(String id, String pwd) {
		return "INSERT INTO USERS(USERID, PASSWORD, USER_TYPE) VALUES (" + id + ", " + pwd + ")";
	}