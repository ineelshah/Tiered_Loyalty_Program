package brand;
import java.util.*;
import brand.brandLanding.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class loyaltyProgram {
	
	public loyaltyProgram() {
		
	}
	public void display() {
		Scanner sc = new Scanner(System.in);
		System.out.println("--------------------------------------------");
		System.out.println("Loyalty Program");
		System.out.println("Please select an option from the menu:");
		System.out.println("1. Regular");
		System.out.println("2. Tier");
		System.out.println("3. Go Back");
		
		int choices = sc.nextInt();
		switch(choices)
		{
		case 1: 
			break;
		}
		
	}
}