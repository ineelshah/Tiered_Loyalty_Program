package brand.brandLanding_pck;
import java.util.*;
import brand.brandLanding.*;
import brand.brand;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import brand.brandLanding;
import brand.brandLanding_pck.loyaltyProgramModules.regular.regular;
import brand.brandLanding_pck.loyaltyProgramModules.tier.*;

public class loyaltyProgram {
	
	public loyaltyProgram() {
		
	}
	public void addBrandName(String id,String name) {
		//query to insert Brand name into database
	}
	public void display(brand b) {
		Scanner sc = new Scanner(System.in);
		System.out.println("--------------------------------------------");
		System.out.println("Add Loyalty Program");
		System.out.println("Enter the loyalty program name");
		String loyalty_name  = sc.nextLine();
		brand br = new brand();
		br.setlp_name(loyalty_name);
		
		addBrandName(br.getUnique_id(),br.getlp_name());
		
		System.out.println("Loyalty program added to the database");
		System.out.println("Please select an option from the menu:");
		System.out.println("1. Regular");
		System.out.println("2. Tier");
		System.out.println("3. Go Back");
		
		int choices = sc.nextInt();
		switch(choices)
		{
		case 1: 
			regular regularinstance = new regular();
			regularinstance.display();
			break;
		
		case 2:
			tier tierinstance = new tier();
			tierinstance.display();
			break;
		case 3:
			brandLanding brandLandinginstance = new brandLanding();
//			brandLandinginstance.display(b);
		}
		}
		
}
