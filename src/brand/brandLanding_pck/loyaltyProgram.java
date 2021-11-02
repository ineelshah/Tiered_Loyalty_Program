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
		//br.setlp_name(loyalty_name);
		
		// add Brand to the database addBrandName(br.getUnique_id(),br.getlp_name());
		
		System.out.println("Loyalty program added to the database");
		
		getLoyaltyProgram glp = new getLoyaltyProgram();
		//String lp_id = glp.getProgram_id();
		
		System.out.println("Please select an option from the menu:");
		System.out.println("1. Regular");
		System.out.println("2. Tier");
		System.out.println("3. Go Back");
		
		
		
		int choices = sc.nextInt();
		switch(choices)
		{
		case 1: 
			regular rginstance = new regular();
			String a = "regular";
			glp.setProgram_type(a);
			String lp_id = br.getLp_id();
			//String br_id = br.getUnique_id();
			rginstance.display(lp_id);
			break;
		
		case 2:
			tier tierinstance = new tier();
			String a = "tier";
			glp.setProgram_type(a);
			String lp_id1 = br.getLp_id();
			String br_id1 = br.getUnique_id();
			tierinstance.display(br);
			break;
		case 3:
			brandLanding brandLandinginstance = new brandLanding();
//			brandLandinginstance.display(b);
		}
		}
		
}
