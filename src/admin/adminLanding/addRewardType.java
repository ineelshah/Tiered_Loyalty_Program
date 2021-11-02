package admin.adminLanding;
import java.util.*;

import admin.adminLandingPage;
import rewardTypepck.rewardType;
public class addRewardType
{
	//done
	public void addRewardToDb(rewardType rew)
	{
		//add reward to database
	}
	public void display()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the reward name");
		System.out.println("Enter the reward code");
		String rewardName=sc.nextLine();
		String rewardCode=sc.nextLine();
		rewardType rew=new rewardType();
		rew.setRewardName(rewardName);
		rew.setRewardCode(rewardCode);
		System.out.println("Add Reward Type");
		System.out.println("Go Back");
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