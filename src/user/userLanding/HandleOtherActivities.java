package user.userLanding;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import connection.ConnectionObj;
import user.user;

public class HandleOtherActivities {
	static Connection conn = null;
	
	public HandleOtherActivities() {
		conn = ConnectionObj.getConnection();
	}
	
	public void addToWallet(String walletId, String programId, String referId, String activityName, int points) {
		int multiplier = Multiplier.getMultiplier(walletId, programId);
		String query = "";
		String dateStr = Activity.activityDate;

		if(dateStr.equals("SYSDATE")) {
			query = "INSERT INTO WALLET_TRANSACTIONS VALUES('" + walletId + "','" + programId + "', " + "OTHER_ACTIVITY_ID_SEQ.NEXTVAL" + ", " + "SYSDATE" + ", '"+ points + "', '" + null + "','" + activityName + "')";
		} else {
			query = "INSERT INTO WALLET_TRANSACTIONS VALUES('" + walletId + "','" + programId + "', " + "OTHER_ACTIVITY_ID_SEQ.NEXTVAL" + ", TO_DATE('" + dateStr + "', 'MM-DD-YYYY'), '"+ points + "', '" + null + "','" + activityName + "')";
		}
		Statement stmt = null;
		ResultSet rs=null;
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
	
	public void display(user u, String programId, String activityName, String activityId, int points) {
		
		
		Scanner sc = new Scanner(System.in);
		System.out.println("--------------------------------------------");
	
		System.out.println("Please select an option from the menu:");
		System.out.println("1. Perform Activity");
		System.out.println("2. Go back");
		int choice = sc.nextInt();
		
		switch(choice)
		{
			case 1:
				System.out.println("Thank you for performing activity.");
				String walletId = u.getUserId();
				int multiplier = Multiplier.getMultiplier(walletId, programId);
				addToWallet(walletId, programId, activityId, activityName, points*multiplier);
				display(u, programId, activityName, activityId, points);
				
				break;
			case 2:
				userLanding uLanding = new userLanding();
				uLanding.display(u);
				break;
		}
		
		
		
		
	}
}
