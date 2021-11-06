package user.userLanding;

import java.util.Scanner;

import user.user;

public class Wallet {
	
//	public Wallet() {
//		
//	}
	
//	public void getWalletDetails(user u) {
//		
//	}
	
	public void display(user u) {
			
			Scanner sc = new Scanner(System.in);
			System.out.println("--------------------------------------------");
			System.out.println("Wallet details");
			System.out.println(" ");
			
			// show wallet details for customer
			// getWalletDetails(u);
			

			System.out.println("Please select from below menu:");
			System.out.println("1. Go back");
			int activityId = sc.nextInt();
			switch(activityId)
			{
				case 1:
					userLanding uLanding = new userLanding();
					uLanding.display(u);
					break;
			}
			
			
			
			
	}

}
