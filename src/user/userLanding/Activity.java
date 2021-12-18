package user.userLanding;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Scanner;

import connection.ConnectionObj;
import user.user;

public class Activity {
	
	static ResultSet rs=null;
	static ResultSet rs2=null;
	static Connection conn = null;
	
	static String activityDate;
	
	public Activity() {
		if(activityDate == null) activityDate = "SYSDATE";
		conn = ConnectionObj.getConnection();
	}
	
	public HashMap<Integer, String> getLoyaltyProgram(String userId) {
		
//		String query = "SELECT DISTINCT LOYALTY_PROGRAM_ID, LOYALTYU FROM WALLET_TRANSACTIONS WHERE WALLETID="+"'"+userId+"'";
		String query = "SELECT DISTINCT WT.LOYALTY_PROGRAM_ID, LP.PROGRAMNAME FROM WALLET_TRANSACTIONS WT LEFT JOIN LOYALTYPROGRAM LP ON WT.LOYALTY_PROGRAM_ID = LP.PROGRAMID WHERE WALLETID='" + userId +"'";
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
		return hmap;
		
	}
	
	public String getActivityName(String activityId) {
		String activityName="";
		String query = "SELECT ACTIVITY_NAME FROM ACTIVITY WHERE ACTIVITY_CODE="+"'"+activityId+"'";
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
				activityName = rs.getString("ACTIVITY_NAME");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return activityName;
	}
	public HashMap<Integer, String> getActivity(String programId) {
		
//		String query = "SELECT DISTINCT ACTIVITYID FROM LP_ACTIVITY WHERE PROGRAMID="+"'"+programId+"'";
		
		String query = "SELECT ACTIVITYID, ACTIVITY_NAME FROM LP_ACTIVITY LPA LEFT JOIN ACTIVITY A ON LPA.ACTIVITYID = A.ACTIVITY_CODE WHERE PROGRAMID = '" + programId + "'";
		
		Statement stmt = null;
		int count = 1;
		HashMap<Integer, String> hmap = new HashMap<>();
		
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		try {
			rs2=stmt.executeQuery(query);
			while(rs2.next()) {
				String activityId = rs2.getString("ACTIVITYID");
				String activityName = getActivityName(activityId);
				hmap.put(count, activityId);
				System.out.println(count + ". " + activityName);
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(count + ". " + "Exit");
		hmap.put(count, "EXIT");
		return hmap;
		
	}
	
	
	public int getPoints(String programId, String activityId) {
		
		String query = "SELECT POINTS FROM LP_ACTIVITY LPA LEFT JOIN RE_RULE RER ON LPA.RERULEID = RER.RULEID WHERE PROGRAMID = '" + programId + 
						"' AND ACTIVITYID = '" + activityId + "'";
		int points = 1;
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
				if(rs.getString("POINTS") != null && !rs.getString("POINTS").equals("")) {
					points = Integer.valueOf(rs.getString("POINTS"));
				}				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return points;
	}
	
	public void display(user u) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("--------------------------------------------");
		System.out.println("Please select loyalty program you want to go to: (Input just the number)");
		System.out.println(" ");
		String userId = u.getUserId();
		// show loyalty programs for customer
		HashMap<Integer, String> hmap = getLoyaltyProgram(userId);
		
		int programIdentifier = sc.nextInt();
		String programId = hmap.get(programIdentifier);
		System.out.println("Please select activity id:");
		
		// show activities for selected loyalty program
		
		HashMap<Integer, String> activityMap = getActivity(programId);
		
//		System.out.println("1. Purchase");
//		System.out.println("2. Review");
//		System.out.println("3. Refer A Friend");
		
		
		
		int activityId = sc.nextInt();
		

		
		String activityCode = activityMap.get(activityId);
		String activityName = getActivityName(activityCode);
		boolean flag = false;
		
		if (activityCode.equalsIgnoreCase("EXIT")) {
			userLanding uLanding = new userLanding();
			uLanding.display(u);
			flag = true;
		}
		
		if(!flag) {
			System.out.println("Please enter a date(MM-dd-yyyy) the activity was performed. If no specific date, just press ENTER:");
			Scanner sc2 = new Scanner(System.in);
			String date = sc2.nextLine();
			if(date == null || date.equals("")) {
				date = "SYSDATE";
			}
			activityDate = date;
		
			if(activityName.equalsIgnoreCase("PURCHASE")) {
				System.out.println("Purchase");
				Purchase purchase = new Purchase();
				int points = getPoints(programId, activityCode);
				purchase.display(u, programId, points);
			} else if (activityName.equalsIgnoreCase("LEAVEREVIEW") || activityName.contains("REVIEW")) {
				System.out.println("Review");
				Review review = new Review();
				int points = getPoints(programId, activityCode);			
				review.display(u, programId, points);
			} else if (activityName.equalsIgnoreCase("REFER A FRIEND") || activityName.contains("REFER")) {
				System.out.println("Friend");
				Friend friend = new Friend();
				int points = getPoints(programId, activityCode);
				friend.display(u, String.valueOf(programId), points);
			} else if (activityCode.equalsIgnoreCase("EXIT")) {
				userLanding uLanding = new userLanding();
				uLanding.display(u);
			} else {
				HandleOtherActivities other = new HandleOtherActivities();
				int points = getPoints(programId, activityCode);
				other.display(u, programId, activityName, activityCode, points);
			}
		}
		
//		switch(activityId)
//		{
//			case 1:
//				//purchase
//				System.out.println("Purchase");
//				Purchase purchase = new Purchase();
//				purchase.display(u, programId);
//				
//				break;
//			case 2:
//				//leave review
//				System.out.println("Review");
//				Review review = new Review();
//				review.display(u, programId);
//				
//				break;
//			case 3:
//				//refer friend
//				System.out.println("Friend");
//				Friend friend = new Friend();
//				friend.display(u, String.valueOf(programId));
//
//				break;
//			case 4:
//				userLanding uLanding = new userLanding();
//				uLanding.display(u);
//				break;
//		}
		
		
		
		
	}

}
