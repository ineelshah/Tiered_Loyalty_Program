package brand.brandLanding_pck.loyaltyProgramModules.tier;
import java.util.*;

import brand.brand;
import brand.brandLanding;
import brand.brandLanding_pck.loyaltyProgramModules.regular.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class tier {
		public tier() {
			
		}
		public void display(String lpId) {
			Scanner sc = new Scanner(System.in);
			System.out.println("--------------------------------------------");
			System.out.println("Tier Page");
			System.out.println("Please select an option from the menu:");
			System.out.println("1. Tier Setup");
			System.out.println("2. Add Activity Types");
			System.out.println("3. Add Reward Types");
			System.out.println("4. Go Back");
			int choice= sc.nextInt();
			int type = 2;
			switch(choice)
			{
			case 1: 
				tierSetup tierSetupinstance = new tierSetup();
				tierSetupinstance.display(lpId);
				break;
			case 2:
				activityTypes activityTypeinstance = new activityTypes();
				activityTypeinstance.display(lpId, type);
				break;
			case 3:
				rewardTypes rewardTypesinstance = new rewardTypes();
				rewardTypesinstance.display(lpId, type);
				break;
			case 4: 
				brandLanding brLanding = new brandLanding();
				brand b = new brand();
				b.setLp_id(lpId);
				b.setUnique_id(lpId);
				brLanding.display(b);
				break;
			}
		}
}
