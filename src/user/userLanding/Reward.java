package user.userLanding;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import connection.ConnectionObj;
import user.user;

public class Reward {
	
	
	static Connection conn = null;
	static String rewardDate;
	
	public Reward() {
		if(rewardDate == null) rewardDate = "SYSDATE";
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
	public HashMap<Integer, String> getRewardId(String programId) {
		ResultSet rs=null;
		String query = "SELECT R.REWARDID, R.REWARDNAME FROM LP_REWARDS LPR LEFT JOIN REWARD R ON LPR.REWARDID = R.REWARDID WHERE PROGRAMID='"+programId+"'";
		Statement stmt = null;
		HashMap<Integer, String> hmap = new HashMap<>();
		int count = 1;
		
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
//				getRewardName(rewardId);	
				System.out.println(count + ". " + rewardName);
				hmap.put(count, rs.getString("REWARDID"));
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hmap;
		
	}
	public HashMap<Integer, String> getLoyaltyProgram(String walletId) {
		ResultSet rs=null;
		String query = "SELECT LOYALTY_PROGRAM_ID, PROGRAMNAME FROM WALLET_TRANSACTIONS W JOIN LOYALTYPROGRAM LP ON W.LOYALTY_PROGRAM_ID = LP.PROGRAMID WHERE W.WALLETID='" + walletId + "' AND W.ACTIVITY_NAME='JOIN'";
		Statement stmt = null;
		HashMap<Integer, String> hmap = new HashMap<>();
		int count = 1;
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
				hmap.put(count, rs.getString("LOYALTY_PROGRAM_ID"));
				System.out.println(count + ". " + rs.getString("PROGRAMNAME"));
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		hmap.put(count, "EXIT");
//		System.out.println(count + ". " + "EXIT");
		return hmap;
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
	
	public boolean deductPoints(int points, String walletId, String programId) {
		ResultSet rs=null;
		Statement stmt = null;
		String query1 = "SELECT POINTS FROM WALLET WHERE WALLETID='"+walletId+"' AND PROGRAMID='"+programId+"'";
		boolean flag = false;
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
		if(currentPoints >= 0) {
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
			return true;
		}
		return false;
		
	}
	
	
	public void recordTransaction(String walletId, String programId, String rewardId, int quantity, int points) {
		String query="INSERT into WALLET_REWARD_TRANSACTIONS (WALLETID, PROGRAMID, REWARDID, QUANTITY, POINTS, TRANSACTION_DATE) values(?,?,?,?,?,?)";
		PreparedStatement pstmt=null;
		try
		{
		pstmt=conn.prepareStatement(query);
		pstmt.setString(1, walletId);
		pstmt.setString(2, programId);
		pstmt.setString(3, rewardId);
		pstmt.setInt(4, quantity);
		pstmt.setInt(5, points);

		String dateStr = Reward.rewardDate;
		
		if(dateStr.equals("SYSDATE")) {
			pstmt.setDate(6, new java.sql.Date(System.currentTimeMillis()));
		} else {
		    SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
		    Date date = format.parse(dateStr);
			pstmt.setDate(6, new java.sql.Date(date.getTime()));
		}
		pstmt.executeUpdate();
		}
		catch(Exception e)
		{
		System.out.println(e);
		}
		}
	
	public void display(user u) {
		
		String walletId = u.getUserId();
		Scanner sc = new Scanner(System.in);
		System.out.println("--------------------------------------------");
		System.out.println("Please enter a date(MM-dd-yyyy) the reward needs to be performed. If no specific date, just press ENTER:");
		String date = sc.nextLine();
		if(date == null || date.equals("")) {
			date = "SYSDATE";
		}
		rewardDate = date;
		System.out.println("Please select id to select loyalty program:");
		HashMap<Integer, String> hmapLP = getLoyaltyProgram(walletId);
		int pId = sc.nextInt();
		String programId = hmapLP.get(pId);
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
				HashMap<Integer, String> hmapR = getRewardId(programId);	
				int rewardInt = sc.nextInt();
				String rewardId = hmapR.get(rewardInt);
				// validate quantity for customer
				boolean flag = validateQuantity(programId, rewardId, quantity);
				while(!flag) {
					System.out.println("Please select valid input for quantity: (If no valid quantity, press 0):");
					quantity = sc.nextInt();
					flag = validateQuantity(programId, rewardId, quantity);
					if(quantity == 0) break;
				}
				
				// check RR rule
				String ruleId = getRRRule(programId, rewardId);
				int points = getPoints(ruleId);
				points*= quantity;
				
				
				//deduct points from wallet
				boolean validFlag = deductPoints(points, walletId, programId);
				if(validFlag) {
					recordTransaction(walletId, programId, rewardId, quantity, points);
				} else {
					System.out.println("The selected quantity violates the number of points.");
				}
				uLanding.display(u);
				
				break;
			case 2:
				
				uLanding.display(u);
				break;
		}
			
	}

}
