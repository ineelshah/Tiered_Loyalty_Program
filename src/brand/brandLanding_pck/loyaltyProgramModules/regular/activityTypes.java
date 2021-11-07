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
		String query="SELECT ACTIVITY_CODE, ACTIVITY_NAME FROM ACTIVITY";
		Statement stmt = null;
		ResultSet rs=null;
		int optionNumber = 1;
		HashMap<Integer, String> hmap = new HashMap<>();
		System.out.println("----------------------------------------------------");
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(query);
			while(rs.next())
			{
				hmap.put(optionNumber, rs.getString("ACTIVITY_CODE"));
				System.out.println(optionNumber + ". " + rs.getString("ACTIVITY_NAME"));
				optionNumber++;
			}
		} catch (SQLException e) {
			System.out.println("There was an error in fetching activities. Please contact the admin.");
		}
		
		System.out.println(optionNumber + ". Go Back");
		System.out.println("----------------------------------------------------");
		int choice= sc.nextInt();
		activityTypes act=new activityTypes();
		if(choice != optionNumber) {
			act.addToLpActivity(lpId,hmap.get(choice));
			act.display(lpId);
		} else {
			regular regularinstance = new regular();
			regularinstance.display(lpId);			
		}
//		switch(choice)
//		{
//		case 1:
//			//handles purchase activity
//			// add the activity_id to the database table REGULAR_LP_ACTIVITY add_activity(lp_id,choice)
//			act.addToLpActivity(lpId,"A1");
//			act.display(lpId);
//			break;
//		case 2:
//			//handles leave a review
//		    // add the activity_id to the database table REGULAR_LP_ACTIVITY add_activity(lp_id,choice)
//			//activityTypes act=new activityTypes();
//			act.addToLpActivity(lpId,"A2");
//			act.display(lpId);
//			break;
//		case 3:
//			//handles refer a friend activity
//			// add the activity_id to the database table REGULAR_LP_ACTIVITY add_activity(lp_id,choice)
//			act.addToLpActivity(lpId,"A3");
//			act.display(lpId);
//			break;
//		case 4:
//			regular regularinstance = new regular();
//			regularinstance.display(lpId);
//			break;
//		}
	}
}