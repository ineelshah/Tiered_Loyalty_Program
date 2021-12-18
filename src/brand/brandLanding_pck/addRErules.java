package brand.brandLanding_pck;
import brand.brand;
import brand.brandLanding;

import java.util.*;
import brand.brandLanding_pck.loyaltyProgramModules.regular.*;
import connection.ConnectionObj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class addRErules {
	
		//static ResultSet rs=null;
		static Connection conn = null;
		
		public addRErules() {
			conn = ConnectionObj.getConnection();
		}
		public static void addRuleId(String programId, String act_id, String rule_id) {
			ResultSet rs=null;
			String query = "UPDATE LP_ACTIVITY SET RERULEID='"+rule_id+"' WHERE PROGRAMID='"+programId+"'AND ACTIVITYID='"+act_id+"'";
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
			String query1 = "INSERT INTO RE_RULE VALUES('" + rule_id + "', '" + np + "', 1)";
			String query2 = "INSERT INTO RE_RULE_VERSION VALUES('" + rule_id + "', 1)";
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
			String query = "SELECT R.ACTIVITY_CODE, R.ACTIVITY_NAME FROM LP_ACTIVITY LPA JOIN ACTIVITY R ON LPA.ACTIVITYID = R.ACTIVITY_CODE WHERE PROGRAMID = '" + lpid + "'";
			
			ResultSet rs1=null;
			Statement stmt1 = null;
			HashMap<Integer, String> hmap = new HashMap<>();
			int count = 1;
			try {
				stmt1 = conn.createStatement();
			} catch (SQLException e) {
				e.printStackTrace();
			}  
			try {
				rs1=stmt1.executeQuery(query);
			} catch (SQLException e) {
				e.printStackTrace();
			} 
			try {
				System.out.println("Available Categories: ");
				while(rs1.next()) {
					hmap.put(count, rs1.getString("ACTIVITY_CODE"));
					System.out.println(count + ". " + rs1.getString("ACTIVITY_CODE") + "	: " + rs1.getString("ACTIVITY_NAME"));
					count++;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return hmap;
		}

		public void display(brand b) {
			Scanner sc = new Scanner(System.in);
			//String programId = b.getLp_id();
			String lp_id = b.getLp_id();
			if(lp_id == null || lp_id.equals("")) {
				lp_id = getLPId(b.getUnique_id());
			}
			String programId = lp_id;
			System.out.println("Please enter brand reward earning rule code:");
			String rule_id = sc.next();
			HashMap<Integer, String> hmap = printExistingRulesForThisLP(programId);
			System.out.println("Please enter activity category: (Input the number): ");
			
			int act_id_int = sc.nextInt();
			String act_id = hmap.get(act_id_int);
			System.out.println("Enter the number of points:");
			String np = sc.next();
			
			System.out.println("--------------------------------------------");
			System.out.println("RE Page");
			System.out.println("Please select an option from the menu:");
			System.out.println("1. Add RE Rule");
			System.out.println("2. Go Back");
			System.out.println("--------------------------------------------");
			int choice= sc.nextInt();
			switch(choice)
			{
			case 1:
				addRule(rule_id,np);
				addRuleId(programId, act_id, rule_id);
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
