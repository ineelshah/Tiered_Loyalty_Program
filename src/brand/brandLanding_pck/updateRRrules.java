package brand.brandLanding_pck;

import java.util.Scanner;

import brand.brand;
import brand.brandLanding;

public class updateRRrules {
	public updateRRrules() {
		
	}
	public static void updateRule(String act_id,String rule_id,String np) {
		// query to update RERule
//		Activity id  = act_id
//		RR Rule id = rule_id
//		no of points = np
		
	}
	public void display(brand b) {
		Scanner sc = new Scanner(System.in);
		String lp_id = b.getLp_id();
		System.out.println("Enter the number of points:");
		String np = sc.nextLine();
		String act_id = null; //fetch this id from database using lp_id and LoyaltyProgram Table
		String rule_id = null; //fetch from the database.
		
		System.out.println("--------------------------------------------");
		System.out.println("RR Page");
		System.out.println("Please select an option from the menu:");
		System.out.println("1. Update RR Rule");
		System.out.println("2. Go Back");
		System.out.println("--------------------------------------------");
		int choice= sc.nextInt();
		switch(choice)
		{
		case 1:
			updateRule(act_id,rule_id,np);
			break;
		case 2:
			brandLanding brandLandingInstance = new brandLanding();
			brandLandingInstance.display(b);
		
		// TODO Auto-generated method stub
		
	}
}
