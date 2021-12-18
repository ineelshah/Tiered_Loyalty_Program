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

	public String getLPId(String bId) {
		String getLPId = "SELECT LP_ID FROM BRAND WHERE BRANDID = '" + bId + "'";
		String LPId = "";
		ResultSet rs=null;
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		try {
			rs=stmt.executeQuery(getLPId);
			while(rs.next()) {
				LPId = rs.getString("LP_ID");
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return LPId;
	}
	
	public HashMap<Integer, String> printExistingRulesForThisLP(String lpid) {
		String query = "SELECT R.REWARDID, R.REWARDNAME FROM LP_REWARDS LPA JOIN REWARD R ON LPA.REWARDID = R.REWARDID WHERE PROGRAMID = '" + lpid + "'";
		
		ResultSet rs=null;
		Statement stmt = null;
		HashMap<Integer, String> hmap = new HashMap<>();
		int count = 1;
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
			System.out.println("Available Categories: ");
			while(rs.next()) {
				hmap.put(count, rs.getString("REWARDID"));
				System.out.println(count + ". " + rs.getString("REWARDID") + "	: " + rs.getString("REWARDNAME"));
				count++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hmap;
	}
	
	public void display(brand b) {
		Scanner sc = new Scanner(System.in);
//		String programId = getLPId(b.getUnique_id());
		String lp_id = b.getLp_id();
		if(lp_id == null || lp_id.equals("")) {
			lp_id = getLPId(b.getUnique_id());
		}
		String programId = lp_id;
		System.out.println("Please enter brand reward redeeming rule code:");
		String rule_id = sc.next();

		HashMap<Integer, String> hmap = printExistingRulesForThisLP(programId);		
		System.out.println("Please enter reward category number from the list: (Input a number): ");
		int r_id = sc.nextInt();
		String reward_id = hmap.get(r_id);
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
			break;
		
		// TODO Auto-generated method stub
	}
}
}
