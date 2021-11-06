package brand.brandLanding_pck;
import brand.brand;
import brand.brandLanding;
import connection.ConnectionObj;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class addRRrules {
	
	static Connection conn = null;
	public addRRrules() {
		conn = ConnectionObj.getConnection();
	}


	public static void addRuleId(String programId, String reward_id, String rule_id) {
		ResultSet rs=null;
		String query = "UPDATE LP_REWARDS SET RRRULEID='"+rule_id+"' WHERE PROGRAMID='"+programId+"'AND REWARDID='"+reward_id+"'";
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		try {
			rs=stmt.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void addRule(String rule_id, String np) {
		ResultSet rs=null;
		String query1 = "INSERT INTO RR_RULE VALUES('" + rule_id + "', '" + np + "', 1)";
		String query2 = "INSERT INTO RR_RULE_VERSION VALUES('" + rule_id + "', 1)";
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		try {
			rs=stmt.executeQuery(query1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs=stmt.executeQuery(query2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void display(brand b) {
		Scanner sc = new Scanner(System.in);
		String programId = "LP2";
		System.out.println("Please enter brand reward rule code:");
		String rule_id = sc.next();
		System.out.println("Please enter reward category:");
		String reward_id = sc.next();
		System.out.println("Enter the number of points:");
		String np = sc.next();
		
		System.out.println("--------------------------------------------");
		System.out.println("RR Page");
		System.out.println("Please select an option from the menu:");
		System.out.println("1. Add RR Rule");
		System.out.println("2. Go Back");
		System.out.println("--------------------------------------------");
		int choice= sc.nextInt();
		switch(choice)
		{
		case 1:
			addRule(rule_id,np);
			addRuleId(programId, reward_id, rule_id);
			display(b);
			break;
		case 2:
			brandLanding brandLandingInstance = new brandLanding();
			brandLandingInstance.display(b);
		
		// TODO Auto-generated method stub
	}
}
}
