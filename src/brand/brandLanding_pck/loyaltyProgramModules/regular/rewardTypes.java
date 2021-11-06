package brand.brandLanding_pck.loyaltyProgramModules.regular;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

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
			System.out.println(e);
		}		
	}
	
	public void display(String lp_id) {
		Scanner sc = new Scanner(System.in);
		System.out.println("--------------------------------------------");
		System.out.println("Reward Page");
		System.out.println("Enter the quantity of the desired reward option:");
		int quantity=Integer.parseInt(sc.nextLine());
		System.out.println("Please select an option from the menu:");
		
		//fetch list of rewards from the Reward table
		String query="SELECT * FROM REWARD";
		Statement stmt = null;
		ResultSet rs=null;
		int optionNumber=1;
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(query);
			while(rs.next())
			{
				System.out.println(optionNumber+" "+rs.getString("REWARDNAME"));
				optionNumber++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(optionNumber+" Go Back");
		System.out.println("--------------------------------------------");
		int choice= sc.nextInt();
		rewardTypes reward=new rewardTypes();
		switch(choice)
		{
		case 1:
			//handles Gift Card
			// add the reward_id to the database table REGULAR_LP_REWARDS add_activity(lp_id,choice)
			reward.addToLpRewards(lp_id,"R01",quantity);
			break;
		case 2:
			//handles Free Product
		    // add the reward_id to the database table REGULAR_LP_REWARDS  add_activity(lp_id,choice)
			reward.addToLpRewards(lp_id,"R02",quantity);
			break;
		case 3:
			regular regularinstance = new regular();
			regularinstance.display(lp_id);
			break;
		}
	}
}