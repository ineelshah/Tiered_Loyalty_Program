package admin.adminLanding;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import admin.adminLandingPage;
import connection.ConnectionObj;
import rewardTypepck.rewardType;
public class addRewardType
{
	//done
	static ResultSet rs=null;
	static Connection conn = null;
	
//	static String userTypeBrand = "b";
	
	public addRewardType()
	{
		super();
		conn = ConnectionObj.getConnection();	
	}
	
	public void addRewardToDb(rewardType rew) {
		conn = ConnectionObj.getConnection();
		
		Statement stmt=null;
		
		String rewardCode=rew.getRewardCode();
		String rewardName=rew.getRewardName();
		
		String query="INSERT INTO REWARD VALUES('" + rewardCode + "', '" + rewardName +"')";
		
		try {
			stmt=conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs=stmt.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public void display()
	{
		Scanner sc=new Scanner(System.in);
		
		
		System.out.println("Enter the reward name:");
		String rewardName=sc.nextLine();
		System.out.println("Enter the reward code:");
		String rewardCode=sc.nextLine();

		System.out.println("1. Add Reward Type");
		System.out.println("2. Go Back");
		rewardType rew=new rewardType();
		rew.setRewardName(rewardName);
		rew.setRewardCode(rewardCode);
		
		int choice=sc.nextInt();
		switch(choice)
		{
			case 1:
				addRewardType art=new addRewardType();
				art.addRewardToDb(rew);
				art.display();
				break;
			case 2:
				adminLandingPage alp=new adminLandingPage();
				alp.display();
				break;
		}
	}

	
}