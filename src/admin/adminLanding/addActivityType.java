package admin.adminLanding;
import activityTypepck.activityType;
import admin.adminLandingPage;
import connection.ConnectionObj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
public class addActivityType
{	//done
	private void addActivityToDB(activityType act) {
		// add activity to the database
		 ResultSet rs1=null;
		 ResultSet rs2=null;
		 Connection conn = null;
		 conn = ConnectionObj.getConnection();
		 Statement smt=null;
		 String activityCode=act.getActivityCode();
		 String activityName=act.getActivityName();
		 String query="INSERT INTO ACTIVITY VALUES('" + activityCode + "', '" + activityName+"')";
		 try 
		 {
			smt=conn.createStatement();
		 } 
		 catch (SQLException e) 
		 {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		  
	}
	public void display()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Activity name");
		System.out.println("Activity code");
		String activityName=sc.nextLine();
		String activityCode=sc.nextLine();
		activityType act=new activityType();
		act.setActivityName(activityName);
		act.setActivityCode(activityCode);
		System.out.println("Add Activity Type");
		System.out.println("Go Back");
		int choice=sc.nextInt();
		switch(choice)
		{
			case 1:
				addActivityType actObj=new addActivityType();
				actObj.addActivityToDB(act); 
				actObj.display();
				break;
			case 2:
				adminLandingPage alp=new adminLandingPage();
				alp.display();
				break;
		}
	}

	
}