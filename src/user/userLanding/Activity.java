package user.userLanding;

import java.util.Scanner;

import user.user;

public class Activity {
	
//	public Activity() {
//		
//	}
//	
//	public void getLoyaltyProgram() {
//		
//	}
//	public void getActivity(int programId) {
//		
//	}
	
	public void display(user u) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("--------------------------------------------");
		System.out.println("Please select loyalty program id:");
		System.out.println(" ");
		
		// show loyalty programs for customer
		// getLoyaltyProgram(u);
		
		int programId = sc.nextInt();
		System.out.println("Please select activity id:");
		
		// show activities for selected loyalty program
		// getActivity(programId);
		
		int activityId = sc.nextInt();
		switch(activityId)
		{
			case 1:
				//purchase
				Purchase purchase = new Purchase();
				purchase.display(u, programId, activityId);
				
				break;
			case 2:
				//leave review 
				Review review = new Review();
				review.display(u, programId, activityId);
				
				break;
			case 3:
				//refer friend
				Friend friend = new Friend();
				friend.display(u, String.valueOf(programId));
				
				break;
			case 4:
				userLanding uLanding = new userLanding();
				uLanding.display(u);
				break;
		}
		
		
		
		
	}

}
