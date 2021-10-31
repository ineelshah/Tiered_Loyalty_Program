package brand;

import java.util.*;
import brand.brandLanding_pck.loyaltyProgram;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class brandLanding{
	
	public brandLanding()
	{
		//conn = ConnectionObj.getConnection();	
	}
	public void display() {
		Scanner sc = new Scanner(System.in);
		System.out.println("--------------------------------------------");
		System.out.println("Brand Page");
		System.out.println("Please select an option from the menu:");
		System.out.println("1. Add Loyalty Program");
		System.out.println("2. Add RE Rules");
		System.out.println("3. Update RE Rules");
		System.out.println("4. Add RR Rules");
		System.out.println("5. Update RR Rules");
		System.out.println("6. Validate Loyalty Program");
		System.out.println("7. Logout");
		System.out.println("--------------------------------------------");
		
		int choice= sc.nextInt();
		switch(choice)
		{
		case 1:
			loyaltyProgram loyaltyprogramInstance = new loyaltyProgram();
			loyaltyprogramInstance.display();
			break;
		}
	}
	
	public static void brandPageFunc() {
			
	}
	
}