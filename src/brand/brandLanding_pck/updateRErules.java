package brand.brandLanding_pck;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import brand.brand;
import brand.brandLanding;
import connection.ConnectionObj;

public class updateRErules {
	
	static Connection conn = null;
	
	public updateRErules() {
		conn = ConnectionObj.getConnection();
	}
	
	public static int updateVersion(String rule_id) {
		ResultSet rs=null;
		String query = "SELECT VERSION FROM RE_RULE_VERSION WHERE RULEID='"+rule_id+"'";
		Statement stmt = null;
		int newVersion=0;
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
		try {
			while(rs.next()) {
				newVersion = rs.getInt("VERSION");
				newVersion+=1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newVersion;
	}
	public static void updateRule(String rule_id,String np,int newVersion) {
		
		ResultSet rs=null;
		String query1 = "INSERT INTO RE_RULE VALUES('" + rule_id + "', '" + np + "', "+newVersion+")";
		String query2 = "UPDATE RE_RULE_VERSION SET VERSION="+newVersion+" WHERE RULEID='"+rule_id+"'";
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
	
	
	public void printExistingRulesForThisLP(String lpid) {
		String query = "SELECT RERULEID, ACTIVITYID FROM LP_ACTIVITY WHERE PROGRAMID = '" + lpid + "'";
		
		ResultSet rs=null;
		Statement stmt = null;

		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		try {
			rs=stmt.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		try {
			while(rs.next()) {
				System.out.println("|  " + rs.getString("RERULEID") + " 		| " + rs.getString("ACTIVITYID") + "	 | ");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void display(brand b) {
		Scanner sc = new Scanner(System.in);
		//String lp_id = b.getLp_id();
		
		System.out.println("--------------------------------------------");
		System.out.println("RE Page");		
		System.out.println("Please enter data  AS PROMPTED from existing rules you have defined:");
		System.out.println("|RE RULE ID	| :	| ACTIVITY ID|");
		printExistingRulesForThisLP(b.getLp_id());
		System.out.println("Input the REWARD RULE CODE: ");
		String rule_id = sc.next();
		System.out.println("Please enter ACTIVITY CODE:");
		String act_id = sc.next();
		System.out.println("Enter the number of points:");
		String np = sc.next();
		System.out.println("Please select an option from the menu:");
		System.out.println("1. Update RE Rule");
		System.out.println("2. Go Back");
		int choice= sc.nextInt();
		System.out.println("--------------------------------------------");
		
		switch(choice)
		{
		case 1:
			int newVersion = updateVersion(rule_id);
			updateRule(rule_id,np,newVersion);
			display(b);
			break;
		case 2:
			break;
		// TODO Auto-generated method stub
		
		
	}
}
}
