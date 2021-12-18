package user.userLanding;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import connection.ConnectionObj;
import user.user;

public class Friend {
	
	static Connection conn = null;
	
	public Friend() {
		conn = ConnectionObj.getConnection();
	}

	public void addToReferFriend(String programId, String referId, String walletId) {
		
		String query = "INSERT INTO REFERFRIEND VALUES('"+ walletId + "','" + programId + "',null, '" + referId + "')";
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
	public void addToWallet(String walletId, String programId, String referId, int points) {
//		String query = "INSERT INTO WALLET_TRANSACTIONS VALUES('" + walletId + "','" + programId + "', '" + referId + "', '" + null + "', '"+ null + "', '" + null + "','REFERFRIEND')";
		String query = "";
		String dateStr = Activity.activityDate;
		if(dateStr.equals("SYSDATE")) {
			query = "INSERT INTO WALLET_TRANSACTIONS VALUES('" + walletId + "','" + programId + "', '" + referId + "', " + "SYSDATE" + ", '"+ points + "', '" + null + "','REFERFRIEND')";
		} else {
			query = "INSERT INTO WALLET_TRANSACTIONS VALUES('" + walletId + "','" + programId + "', '" + referId + "', " + "TO_DATE('" + dateStr + "', 'MM-DD-YYYY')" + ", '"+ points + "', '" + null + "','REFERFRIEND')";
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
	
	public String generateReferId()
	{
		//String lpId="LP"+String.valueOf(lpIdCounter);
		conn = ConnectionObj.getConnection();
		ResultSet rs=null;
		Statement stmt=null;
		int max=0;
		String query="select REFERID from REFERFRIEND";
		String temp="";
		String rId="";
		String finalString="";
		String maxValueString="";
		try
		{
			stmt=conn.prepareStatement(query);
			rs=stmt.executeQuery(query);
			
			while(rs.next())
			{
				temp=rs.getString("REFERID");
				String tempSubs=temp.substring(2);
				max=Math.max(max,Integer.parseInt(tempSubs));
					
			}	
			max++;
			maxValueString=String.valueOf(max);
			finalString="RF"+maxValueString;
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return finalString;
	}
		
	public void display(user u, String programId, int points) {
			
			
			Scanner sc = new Scanner(System.in);
			System.out.println("--------------------------------------------");
		
			System.out.println("Please select an option from the menu:");
			System.out.println("1. Refer a friend");
			System.out.println("2. Go back");
			int choice = sc.nextInt();
			
			switch(choice)
			{
				case 1:
					// add transaction to wallet table
					String walletId = u.getUserId();
					String referId = generateReferId(); // should be unique for every review
					addToReferFriend(programId, referId, walletId);
					int multiplier = Multiplier.getMultiplier(walletId, programId);
					addToWallet(walletId, programId, referId, points*multiplier);
					System.out.println("Thank you for Referring friend");
					display(u, programId, points);
					
					break;
				case 2:
					userLanding uLanding = new userLanding();
					uLanding.display(u);
					break;
			}
			
			
			
			
		}

}
