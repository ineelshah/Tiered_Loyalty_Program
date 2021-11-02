package user.userLanding;

import java.util.Scanner;

import user.user;

public class Reward {
	
//	public class Reward() {
//		
//	}
	
//	public void getReward(user u) {
//		
//	}
//	public boolean validateQuantity(user u, int quantity){
//		
//	}
	
//	public void checkRRRule(user u, int rewardId) {
//		
//	}
//	public void addRewardToDb(user u) {
//		
//	}
	
	
	
	public void display(user u) {
		
		
		Scanner sc = new Scanner(System.in);
		System.out.println("--------------------------------------------");
		System.out.println("Please select id to select reward:");
		System.out.println("Please select quantity:");
		System.out.println(" ");
		
		//fetch and show rewards for customer
		//getReward(u)

		int rewardId = sc.nextInt();
		int quantity = sc.nextInt();
		
		System.out.println("Please select an option from the menu:");
		System.out.println("1. Reward Selection");
		System.out.println("2. Go back");
		int choice = sc.nextInt();
		
		switch(choice)
		{
			case 1:
				
				// validate quantity for customer
				// boolean flag = validateQuantity(u, quantity);
				// if(!flag) {
				//	System.out.println("Please select valid input for quantity:");
				//	display(u);
				//}
				
				// check RR rule
				// checkRRRule(u, rewardId);
				
				
				//add updated reward info to db
				//addRewardToDb()
				
				Activity activity = new Activity();
				activity.display(u);
				
				break;
			case 2:
				userLanding uLanding = new userLanding();
				uLanding.display(u);
				break;
		}
			
	}

}
