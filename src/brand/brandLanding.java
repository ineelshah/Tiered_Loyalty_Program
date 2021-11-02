package brand;

import java.util.*;
import brand.brandLanding_pck.loyaltyProgram;
import brand.brandLanding_pck.*;
import mainMenu.mainMenu;

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
	public void display(brand b) {
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
			loyaltyprogramInstance.display(b);
			break;
		case 2:
			addRErules addreRulesinstance = new addRErules();
			addreRulesinstance.display(b);
			break;
		case 3:
			updateRErules updateRErulesinstance = new updateRErules();
			updateRErulesinstance.display(b);
			break;
		case 4:
			addRRrules addRRrulesinstance = new addRRrules();
			addRRrulesinstance.display(b);
			break;
		case 5:
			updateRRrules updateRRrulesinstance = new updateRRrules();
			updateRRrulesinstance.display(b);
			break;
		case 6:
			validateLoyaltyProgram validateLoyaltyPrograminstance = new validateLoyaltyProgram();
			validateLoyaltyPrograminstance.display(b);
			break;
		case 7: 
			mainMenu mainMenuInstance = new mainMenu();
			mainMenu.display();
		}
	}
	
}