package user.userLanding;

import java.util.Scanner;

import user.user;
import user.user;
public class LoyaltyProgram {
	
//	public LoyaltyProgram() {
//		// connection object
//	}
	
//	public void getLoyaltyProgram() {
//		
//	}
	
//	public void enrollInLoyaltyProgram(int programId){
//		
//	}
	
	public void display(user u) {
		
		
		Scanner sc = new Scanner(System.in);
		System.out.println("--------------------------------------------");
		System.out.println("Please select id to enroll in loyalty program:");
		
		//fetch loyalty programs
		//getLoyaltyProgram()

		int programId = sc.nextInt();
		System.out.println("Please select an option from the menu:");
		System.out.println("1. Enroll in Loyalty Program");
		System.out.println("2. Go back");
		int choice = sc.nextInt();
		
		switch(choice)
		{
			case 1:
				//add selected programId to db
				//enrollInLoyaltyProgram(programId)
				
				break;
			case 2:
				userLanding uLanding = new userLanding();
				uLanding.display(u);
				break;
		}
		
		
		
		
	}

}
