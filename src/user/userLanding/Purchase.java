package user.userLanding;

import java.util.Scanner;

import user.user;

public class Purchase {
	
//	public Purchase() {
//		
//	}

	
	//	public void addToWallet(user u, int programId, int activityId, String giftCardCode, int amount) {}
		
	public void display(user u, int programId, int activityId) {
			
			
			Scanner sc = new Scanner(System.in);
			System.out.println("--------------------------------------------");
			System.out.println("Please enter gift card code");
			System.out.println("Please enter amount");
			String giftCardCode = sc.next();
			int amount = sc.nextInt();
			System.out.println("Please select an option from the menu:");
			System.out.println("1. Purchase");
			System.out.println("2. Go back");
			int choice = sc.nextInt();
			
			switch(choice)
			{
				case 1:
					// add transaction to wallet table
					// addToWallet(u, programId, activityId, giftCardCode, amount);
					
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
