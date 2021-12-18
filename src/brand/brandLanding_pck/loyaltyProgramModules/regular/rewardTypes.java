package brand.brandLanding_pck.loyaltyProgramModules.regular;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Scanner;

import brand.brand;
import brand.brandLanding;
import brand.brandLanding_pck.loyaltyProgramModules.tier.tier;
import connection.ConnectionObj;

public class rewardTypes{
	static Connection conn = null;
	public rewardTypes() 
	{
		conn = ConnectionObj.getConnection();
	}
	//insert selected rewards in the lp_rewards table
	public void addToLpRewards(String lpId,String rewardCode,int quantity)
	{
		//This function will set programid which is the lpId and the activityCode
		String query="Insert into LP_REWARDS(PROGRAMID,REWARDID,QUANTITY) values(?,?,?)";
		PreparedStatement pstmt=null;
		try 
		{
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, lpId);
			pstmt.setString(2, rewardCode);
			pstmt.setInt(3,quantity);
			pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println("This reward has already been chosen. Kindly choose another one.");
		}		
	}
	
	public void display(String lp_id, int type) {
		Scanner sc = new Scanner(System.in);
		System.out.println("--------------------------------------------");
		System.out.println("Reward Page");
		
		//fetch list of rewards from the Reward table
		String query="SELECT REWARDID, REWARDNAME FROM REWARD";
		Statement stmt = null;
		ResultSet rs=null;
		int optionNumber=1;
		HashMap<Integer, String> hmap = new HashMap<>();
		
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(query);
			while(rs.next())
			{
				hmap.put(optionNumber, rs.getString("REWARDID"));
				System.out.println(optionNumber+". "+rs.getString("REWARDNAME"));
				optionNumber++;
			}
		} catch (SQLException e) {
			System.out.println("There was an error in fetching rewards. Please try again or contact the admin.");
		}
		


		System.out.println(optionNumber+". Go Back");
		
		System.out.println("--------------------------------------------");
		System.out.println("Enter the quantity of the desired reward option: (QTY): ");
		int quantity=Integer.parseInt(sc.nextLine());
		System.out.println("Please select an option from the menu:");
		int choice= sc.nextInt();
		rewardTypes reward=new rewardTypes();
		if(choice != optionNumber) {
			reward.addToLpRewards(lp_id,hmap.get(choice), quantity);
			reward.display(lp_id, type);
		} else {
			if(type == 1) {
				regular regularinstance = new regular();
				regularinstance.display(lp_id);	
			} else {
				tier tierinstance = new tier();
				tierinstance.display(lp_id);			
			}	
		}
//		rewardTypes reward=new rewardTypes();
//		switch(choice)
//		{
//		case 1:
//			//handles Gift Card
//			// add the reward_id to the database table REGULAR_LP_REWARDS add_activity(lp_id,choice)
//			reward.addToLpRewards(lp_id,"R01",quantity);
//			reward.display(lp_id);
//			break;
//		case 2:
//			//handles Free Product
//		    // add the reward_id to the database table REGULAR_LP_REWARDS  add_activity(lp_id,choice)
//			reward.addToLpRewards(lp_id,"R02",quantity);
//			reward.display(lp_id);
//			break;
//		case 3:
////			regular regularinstance = new regular();
////			regularinstance.display(lp_id);
//			brand b = new brand();
//			b.setLp_id(lp_id);
//			brandLanding brandLandingInstance = new brandLanding();
//			brandLandingInstance.display(b);
//			break;
//		}
	}
}