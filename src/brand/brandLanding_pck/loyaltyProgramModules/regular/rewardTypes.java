package brand.brandLanding_pck.loyaltyProgramModules.regular;

import java.util.Scanner;

public class rewardTypes{
	public rewardTypes() {
		
	}
	public void display(String lp_id) {
		Scanner sc = new Scanner(System.in);
		System.out.println("--------------------------------------------");
		System.out.println("Reward Page");
		System.out.println("Please select an option from the menu:");
		System.out.println("1. Gift Card");
		System.out.println("2. Free Product");
		System.out.println("3. Go Back");
		System.out.println("--------------------------------------------");
		int choice= sc.nextInt();
		switch(choice)
		{
		case 1:
			// add the reward_id to the database table REGULAR_LP_REWARDS add_activity(lp_id,choice)
			break;
		case 2:
		    // add the reward_id to the database table REGULAR_LP_REWARDS  add_activity(lp_id,choice)
			break;
		case 3:
			regular regularinstance = new regular();
			regularinstance.display(lp_id);
			break;
		}
	}
}