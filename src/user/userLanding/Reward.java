package user.userLanding;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import connection.ConnectionObj;
import user.user;

public class Reward {
	
	
	static Connection conn = null;
	
	public Reward() {
		conn = ConnectionObj.getConnection();
	}
	
	public void getRewardName(String rewardId) {
		ResultSet rs=null;
		String query = "SELECT REWARDNAME FROM REWARD WHERE REWARDID='"+rewardId+"'";
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
		try {
			while(rs.next()) {
				String rewardName = rs.getString("REWARDNAME");
				System.out.println(rewardId+"."+rewardName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void getRewardId(String programId) {
		ResultSet rs=null;
		String query = "SELECT REWARDID FROM LP_REWARDS WHERE PROGRAMID='"+programId+"'";
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
			int x = 0;
		} 
		try {
			while(rs.next()) {
				String rewardId = rs.getString("REWARDID");
				getRewardName(rewardId);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void getLoyaltyProgram(String walletId) {
		ResultSet rs=null;
		String query = "SELECT LOYALTY_PROGRAM_ID FROM WALLET_TRANSACTIONS WHERE WALLETID='"+walletId+"' AND ACTIVITY_NAME='JOIN'";
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
		try {
			while(rs.next()) {
				System.out.println(rs.getString("LOYALTY_PROGRAM_ID"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	public boolean validateQuantity(String programId, String rewardId, int quantity){
		ResultSet rs=null;
		String query = "SELECT QUANTITY FROM LP_REWARDS WHERE PROGRAMID='"+programId+"' AND REWARDID='"+rewardId+"'";
		Statement stmt = null;
		int maxQuantity=0;
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
				maxQuantity = rs.getInt("QUANTITY");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maxQuantity>=quantity;
	}
	
	public String getRRRule(String programId, String rewardId){
		ResultSet rs=null;
		String query = "SELECT RRRULEID FROM LP_REWARDS WHERE PROGRAMID='"+programId+"' AND REWARDID='"+rewardId+"'";
		Statement stmt = null;
		String ruleId="";
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
				ruleId = rs.getString("RRRULEID");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ruleId;
	}
	
	public int getPoints(String ruleId){
		ResultSet rs=null;
		String query1 = "SELECT VERSION FROM RR_RULE_VERSION WHERE RULEID='"+ruleId+"'";
		String version="";
		Statement stmt = null;
		int points=0;
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
			while(rs.next()) {
				version = rs.getString("VERSION");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String query2 = "SELECT POINTS FROM RR_RULE WHERE RULEID='"+ruleId+"' AND VERSION='"+version+"'";
		try {
			rs=stmt.executeQuery(query2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		try {
			while(rs.next()) {
				points = Integer.valueOf(rs.getString("POINTS"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return points;
	}
	
	public void deductPoints(int points, String walletId, String programId) {
		ResultSet rs=null;
		Statement stmt = null;
		String query1 = "SELECT POINTS FROM WALLET WHERE WALLETID='"+walletId+"' AND PROGRAMID='"+programId+"'";
		
		int currentPoints=0;
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
			while(rs.next()) {
				currentPoints =rs.getInt("POINTS");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		currentPoints-=points;
		String query2 = "UPDATE WALLET SET POINTS='"+currentPoints+"' WHERE PROGRAMID='"+programId+"'AND WALLETID='"+walletId+"'";
		try {
			stmt = conn.createStatement();
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
	
	
	
	public void display(user u) {
		
		String walletId = u.getUserId();
		Scanner sc = new Scanner(System.in);
		System.out.println("--------------------------------------------");
		System.out.println("Please select id to select loyalty program:");
		getLoyaltyProgram(walletId);
		String programId = sc.next();
		System.out.println("Please select quantity:");
		int quantity = sc.nextInt();
		System.out.println("Please select an option from the menu:");
		System.out.println("1.Rewards Selection");
		System.out.println("2. Go back");	
		userLanding uLanding = new userLanding();
		int choice = sc.nextInt();
		
		switch(choice)
		{
			case 1:
				System.out.println("Please select reward id:");
				getRewardId(programId);	
				String rewardId = sc.next();
				// validate quantity for customer
				boolean flag = validateQuantity(programId, rewardId, quantity);
				while(!flag) {
					System.out.println("Please select valid input for quantity:");
					quantity = sc.nextInt();
					flag = validateQuantity(programId, rewardId, quantity);
				}
				
				// check RR rule
				String ruleId = getRRRule(programId, rewardId);
				int points = getPoints(ruleId);
				
				//deduct points from wallet
				deductPoints(points, walletId, programId);
				
				uLanding.display(u);
				
				break;
			case 2:
				
				uLanding.display(u);
				break;
		}
			
	}

}
