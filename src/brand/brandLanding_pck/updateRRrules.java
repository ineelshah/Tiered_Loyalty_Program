package brand.brandLanding_pck;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import brand.brand;
import brand.brandLanding;
import connection.ConnectionObj;

public class updateRRrules {
	
	static Connection conn = null;
	
	public updateRRrules() {
		conn = ConnectionObj.getConnection();
	}
	
	public static int updateVersion(String rule_id) {
		ResultSet rs=null;
		String query = "SELECT VERSION FROM RR_RULE_VERSION WHERE RULEID='"+rule_id+"'";
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
		String query1 = "INSERT INTO RR_RULE VALUES('" + rule_id + "', '" + np + "', "+newVersion+")";
		String query2 = "UPDATE RR_RULE_VERSION SET VERSION="+newVersion+" WHERE RULEID='"+rule_id+"'";
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
		System.out.println("1. Update RR Rule");
		System.out.println("2. Go Back");
		System.out.println("--------------------------------------------");
		int choice= sc.nextInt();
		switch(choice)
		{
		case 1:
			int newVersion = updateVersion(rule_id);
			updateRule(rule_id,np,newVersion);

			display(b);
			break;
		case 2:
			brandLanding brandLandingInstance = new brandLanding();
			brandLandingInstance.display(b);
		
		// TODO Auto-generated method stub
		
	}
}
}
