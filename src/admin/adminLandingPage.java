package admin;
import java.util.*;
import admin.adminLanding.*;
import mainMenu.*;
public class adminLandingPage
{
	public void display()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("1. Add brand");
		System.out.println("2. Add customer");
		System.out.println("3. Show brand information");
		System.out.println("4. Show customer information");
		System.out.println("5. Add activity type");
		System.out.println("6. Add reward type");
		System.out.println("7. Log out");
		int choice=sc.nextInt();
		switch(choice)
		{
			case 1:
				addBrand addBrandObj=new addBrand();
				addBrandObj.display();
				break;
			case 2:
				addCustomer addCustomerObj=new addCustomer();
				addCustomerObj.display();
				break;
			case 3:
				showBrandInfo showBrandInfoObj=new showBrandInfo();
				showBrandInfoObj.display();
				break;
			case 4:
				showCustomerInfo showCustomerInfoObj=new showCustomerInfo();
				showCustomerInfoObj.display();
				break;
			case 5:
				addActivityType addActivityTypeObj=new addActivityType();
				addActivityTypeObj.display();
				break;
			case 6:
				addRewardType addRewardTypeObj=new addRewardType();
				addRewardTypeObj.display();
				break;
			case 7:
				//log out and go to home page
//				mainMenu.display();
				break;
		}
	}
}