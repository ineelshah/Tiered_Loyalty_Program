package brand.brandLanding_pck;
import brand.brand;
import brand.brandLanding;

import java.util.*;
import brand.brandLanding_pck.loyaltyProgramModules.regular.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class addRErules {
		public addRErules() {
			
		}
		public static void addRule(String act_id,String rule_id,String np) {
			// query to insert RERule
//			Activity id  = act_id
//			RE Rule id = rule_id
//			no of points = np
			
		}

		public void display(brand b) {
			Scanner sc = new Scanner(System.in);
			String lp_id = b.getLp_id();
			System.out.println("Enter the number of points:");
			String np = sc.nextLine();
			String act_id = null; //fetch this id from database using lp_id and LoyaltyProgram Table
			String rule_id = null; //fetch from the database.
			
			System.out.println("--------------------------------------------");
			System.out.println("RE Page");
			System.out.println("Please select an option from the menu:");
			System.out.println("1. Add RE Rule");
			System.out.println("2. Go Back");
			System.out.println("--------------------------------------------");
			int choice= sc.nextInt();
			switch(choice)
			{
			case 1:
				addRule(act_id,rule_id,np);
				break;
			case 2:
				brandLanding brandLandingInstance = new brandLanding();
				brandLandingInstance.display(b);
			
			
			// TODO Auto-generated method stub
			
		}
}
}
