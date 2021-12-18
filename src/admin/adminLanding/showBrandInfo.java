package admin.adminLanding;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
//done
import admin.adminLandingPage;
import connection.ConnectionObj;
public class showBrandInfo
{
	
	static ResultSet rs=null;
	static Connection conn = null;
		
	public showBrandInfo()
	{
		super();
		conn = ConnectionObj.getConnection();	
	}
	
	public void show(String brandId)
	{
		//get brand information on the basis of brandId
		//print brand information
		//if any error occurs print the error
		String query = "SELECT BRANDID, BRANDNAME, ADDRESS, PHONE, JOIN_DATE, LP_ID FROM BRAND WHERE BRANDID = '" + brandId + "'";
		
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			System.out.println("Error in showBrandInfo");
		}  
		try {
			rs=stmt.executeQuery(query);
		} catch (SQLException e) {
			System.out.println("Error in showBrandInfo");
		}  
		
		try {
			while(rs.next()) {
			String bId = rs.getString("BRANDID");
			String bName = rs.getString("BRANDNAME");
			String bAddr = rs.getString("ADDRESS");
//			String bPhone = rs.getString("PHONE");
			String bJoinDate = rs.getString("JOIN_DATE");
			String lpId = rs.getString("LP_ID");
			
			System.out.println("-------------------BRAND INFO FOR: " + bId + " --------------------");
			System.out.println("Brand Id:" + bId);
			System.out.println("Brand Name:" + bName);
			System.out.println("Brand Address:" + bAddr);
			System.out.println("Brand Join Date:" + bJoinDate);
			System.out.println("Brand Loyalty Program Id:" + lpId);
			System.out.println("-------------------------------------------------------------------");
			}
			
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println("It looks like you entered the incorrect Brand Id. Try again with a correct brand Id.");
		}
		
		String detailedQuery = "SELECT LP.PROGRAMNAME, LP.LPFLAG, LP.PROGRAMTYPE, A.ACTIVITY_NAME, R.REWARDNAME FROM BRAND B LEFT JOIN LOYALTYPROGRAM LP ON B.LP_ID = LP.PROGRAMID LEFT JOIN LP_ACTIVITY LPA ON LP.PROGRAMID = LPA.PROGRAMID LEFT JOIN ACTIVITY A ON LPA.ACTIVITYID = A.ACTIVITY_CODE LEFT JOIN LP_REWARDS LPR ON LPR.PROGRAMID = LP.PROGRAMID LEFT JOIN REWARD R ON LPR.REWARDID = R.REWARDID WHERE B.BRANDID = '" + brandId + "'";
		ResultSet rs2=null;
		Statement stmt2 = null;
		try {
			stmt2 = conn.createStatement();
		} catch (SQLException e) {
			System.out.println("Error in showBrandInfo");
		}  
		try {
			rs2=stmt2.executeQuery(detailedQuery);
		} catch (SQLException e) {
			System.out.println("Error in showBrandInfo");
		}  
		
		try {
			if(rs2.next()) {
				System.out.println("Loyalty Program Name: " + rs2.getString("PROGRAMNAME"));
				System.out.println("Loyalty Program Validity Status: " + rs2.getString("LPFLAG"));
				System.out.println("Loyalty Program Type: " + rs2.getString("PROGRAMTYPE"));
				System.out.println("_____________DETAILED VIEW____________");
				System.out.println("Activity Name: " + rs2.getString("ACTIVITY_NAME"));
				System.out.println("Reward Name: " + rs2.getString("REWARDNAME"));
				while(rs2.next()) {
					System.out.println("Activity Name: " + rs2.getString("ACTIVITY_NAME"));
					System.out.println("Reward Name: " + rs2.getString("REWARDNAME"));
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("It looks like you entered the incorrect Brand Id. Try again with a correct brand Id.");
		}
		
	}
	public void display()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter brand Id");
		String brandId=sc.nextLine();
		System.out.println("1.show brand info");
		System.out.println("2.Go Back");
		int choice=sc.nextInt();
		switch(choice)
		{
		case 1:
			showBrandInfo sbiObj=new showBrandInfo();
			sbiObj.show(brandId);

			adminLandingPage alp1=new adminLandingPage();
			alp1.display();
			
			break;
		case 2:
			adminLandingPage alp2=new adminLandingPage();
			alp2.display();
			break;
			
		}
	}
}