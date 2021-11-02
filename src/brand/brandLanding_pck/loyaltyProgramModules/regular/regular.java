package brand.brandLanding_pck.loyaltyProgramModules.regular;
import brand.*;
import java.util.*;
import brand.brandLanding_pck.loyaltyProgramModules.regular.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class regular{
	
	public regular() {
		
	}
	public void display() {
		Scanner sc = new Scanner(System.in);
		System.out.println("--------------------------------------------");
		System.out.println("Regular Page");
		System.out.println("Please select an option from the menu:");
		System.out.println("1. Add Activity Types");
		System.out.println("2. Add Reward Types");
		System.out.println("3. Go Back");
		int choice= sc.nextInt();
		switch(choice)
		{
		case 1:
			activityTypes activityTypeinstance = new activityTypes();
			activityTypeinstance.display();
			break;
		case 2:
			rewardTypes rewardTypesinstance = new rewardTypes();
			rewardTypesinstance.display();
			break;
		case 3: 
			return;
	}
}