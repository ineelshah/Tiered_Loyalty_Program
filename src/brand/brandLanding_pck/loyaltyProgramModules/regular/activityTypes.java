package brand.brandLanding_pck.loyaltyProgramModules.regular;

import java.util.*;

import connection.ConnectionObj;

import java.sql.Connection;
import java.sql.DriverManager;
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
	
	public void display(String lp_id) {
		Scanner sc = new Scanner(System.in);
		System.out.println("--------------------------------------------");
		System.out.println("Activity Page");
		System.out.println("Please select an option from the menu:");
		/*System.out.println("1. Purchase");
		System.out.println("2. Leave a review");
		System.out.println("3. Refer a friend");
		System.out.println("4. Go Back");*/
		System.out.println("--------------------------------------------");
		//get list of activities from the database
		String query="'Select * from ACTIVITY'";
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
		switch(choice)
		{
		case 1:
			// add the activity_id to the database table REGULAR_LP_ACTIVITY add_activity(lp_id,choice)
			break;
		case 2:
		    // add the activity_id to the database table REGULAR_LP_ACTIVITY add_activity(lp_id,choice)
			break;
		case 3:
			// add the activity_id to the database table REGULAR_LP_ACTIVITY add_activity(lp_id,choice)
			break;
		case 4:
			regular regularinstance = new regular();
			//regularinstance.display(br_id,lp_id);
			break;
		}
	}
}