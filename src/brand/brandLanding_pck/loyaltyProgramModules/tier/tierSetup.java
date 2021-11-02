package brand.brandLanding_pck.loyaltyProgramModules.tier;

import java.util.*;
import brand.brandLanding_pck.loyaltyProgramModules.regular.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class tierSetup {
	public tierSetup() {
		
	}
	
	public static void tierSetupFunc() {
		// write the setup part to take brand input.
	}
	public void display() {
		Scanner sc = new Scanner(System.in);
		System.out.println("--------------------------------------------");
		System.out.println("Tier Setup Page");
		System.out.println("Please select an option from the menu:");
		System.out.println("1. Setup Tier");
		System.out.println("2. Go Back");
		int choice= sc.nextInt();
		switch(choice)
		{
		case 1:
			tierSetupFunc();
		case 2:
			return;
		}
	}
}
