package user.userLanding;

import java.util.Scanner;

import user.user;

public class Friend {
	
//	public Friend() {
//		
//	}
	
	//	public void addToWallet(user u, int programId, int activityId, String giftCardCode, int amount) {}
		
	public void display(user u, String programId, int activityId) {
			
			
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
					// addToWallet(u, programId, activityId);
					System.out.println("Thank you for Referring friend");
					display(u, programId, activityId);
					
					break;
				case 2:
					userLanding uLanding = new userLanding();
					uLanding.display(u);
					break;
			}
			
			
			
			
		}

}
