package user.userLanding;
import java.util.*;

import user.user;
public class userLanding {
	public void display(user u) {
		Scanner sc = new Scanner(System.in);
		System.out.println("--------------------------------------------");
		System.out.println("Customer Page");
		System.out.println("Please select an option from the menu:");
		System.out.println("1. Enroll in Loyalty Program");
		System.out.println("2. Reward Activities");
		System.out.println("3. View Wallet");
		System.out.println("4. Redeem Points");
		System.out.println("5. Log out");
		System.out.println("--------------------------------------------");
		
		int choice= sc.nextInt();
		switch(choice)
		{
			case 1:
				// Enroll in loyalty program
				LoyaltyProgram lProgram = new LoyaltyProgram();
				lProgram.display(u);
				break;
			case 2:
				//
				Activity activity = new Activity();
				activity.display(u);
				break;
			case 3:
				//
				Wallet wallet = new Wallet();
				wallet.display(u);
				break;
			case 4:
				Reward reward = new Reward();
				reward.display(u);
				//
				break;
			case 5:
				//
				break;
		}
	}
}
