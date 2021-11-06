package brand.brandLanding_pck.loyaltyProgramModules.regular;

import java.util.*;

import connection.ConnectionObj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;

public class activityTypes{
	static Connection conn = null;
	public activityTypes() {
		conn = ConnectionObj.getConnection();
	}
	
	
	public void addToLpActivity(String lpId,String lpCode)
	{
		//This function will insert lp id and code in lp_activity table
		String query="Insert into LP_ACTIVITY(PROGRAMID,ACTIVITYID) values(?,?)";
		PreparedStatement pstmt=null;
		try 
		{
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, lpId);
			pstmt.setString(2, lpCode);
			pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	
	
	public void display(String lpId) {
		Scanner sc = new Scanner(System.in);
		System.out.println("--------------------------------------------");
		System.out.println("Activity Page");
		System.out.println("Please select an option from the menu:");
		System.out.println("--------------------------------------------");
		//get list of activities from the database
		String query="SELECT ACTIVITY_NAME FROM ACTIVITY";
		Statement stmt = null;
		ResultSet rs=null;
		int optionNumber=1;
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(query);
			while(rs.next())
			{
				System.out.println(optionNumber+" "+rs.getString("ACTIVITY_NAME"));
				optionNumber++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(optionNumber+" Go Back");
		int choice= sc.nextInt();
		activityTypes act=new activityTypes();
		switch(choice)
		{
		case 1:
			//handles purchase activity
			// add the activity_id to the database table REGULAR_LP_ACTIVITY add_activity(lp_id,choice)
			act.addToLpActivity(lpId,"A1");
			act.display(lpId);
			break;
		case 2:
			//handles leave a review
		    // add the activity_id to the database table REGULAR_LP_ACTIVITY add_activity(lp_id,choice)
			//activityTypes act=new activityTypes();
			act.addToLpActivity(lpId,"A2");
			act.display(lpId);
			break;
		case 3:
			//handles refer a friend activity
			// add the activity_id to the database table REGULAR_LP_ACTIVITY add_activity(lp_id,choice)
			act.addToLpActivity(lpId,"A3");
			act.display(lpId);
			break;
		case 4:
			regular regularinstance = new regular();
			regularinstance.display(lpId);
			break;
		}
	}
}