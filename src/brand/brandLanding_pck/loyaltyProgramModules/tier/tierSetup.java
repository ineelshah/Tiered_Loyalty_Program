package brand.brandLanding_pck.loyaltyProgramModules.tier;
import java.util.Scanner;

import brand.brand;


public class tierSetup {
	public tierSetup() {
		
	}
	
	public static void tierSetupFunc(String name[],String pt[],String mult[],
			String lp_id) {
		// write the setup part to take brand input.
		int size= name.length;
		for(int i=1;i<=size;i++) {
			//insert query (name[i],pt[i],mult[i],lp_id)
		}
	}
	public void display(brand b) {
		Scanner sc = new Scanner(System.in);
		String lp_id = b.getLp_id();
		System.out.println("--------------------------------------------");
		System.out.println("Tier Setup Page");
		System.out.println("Enter the number of tiers (max 3):");
		int n = sc.nextInt();
		String name[]= new String[n];
		String pt[]= new String[n];
		String mult[] = new String[n];
		for(int i=1;i<=n;i++) {
			System.out.println("Enter name of tier"+ i);
			String a = sc.nextLine();
			name[i] = a;
			System.out.println("Enter the points required for tier"+ i);
			String point = sc.nextLine();
			pt[i] = point;
			System.out.println("Enter the multiplier for the tier" + i);
			String mul = sc.nextLine();
			mult[i] = mul;
		}
		System.out.println("Please select an option from the menu:");
		System.out.println("1. Setup Tier");
		System.out.println("2. Go Back");
		int choice= sc.nextInt();
		switch(choice)
		{
		case 1:
			tierSetupFunc(name,pt,mult,lp_id);
			tier tierInstance = new tier();
			tierInstance.display(b);
		case 2:
			tier tierInstance1 = new tier();
			tierInstance1.display(b);
		}
	}
}
