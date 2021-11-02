package admin.adminLanding;
import java.util.*;
import user.*;
import admin.adminLandingPage;
public class addCustomer
{	//done
	public boolean addCustomerToDb(user b)
	{
		//insert query to add brand to the database
		return true;

	}
	public void display()
	{
		Scanner sc=new Scanner(System.in);
		user userObj=new user();
		System.out.println("Enter customer id");
		String userId=sc.nextLine();
		System.out.println("Enter the username");
		String userName=sc.nextLine();
		System.out.println("Enter the password");
		String password=sc.nextLine();
		System.out.println("Enter the address");
		String address=sc.nextLine();
		System.out.println("Enter the phone number");
		String phoneNumber=sc.nextLine();
		userObj.setUserId(userId);		
		userObj.setUsername(userName);
		userObj.setPassword(password);
		userObj.setAddress(address);
		userObj.setPhoneNumber(phoneNumber);


		System.out.println("1. Add user");
		System.out.println("2.Go Back");
		int choice=sc.nextInt();
		switch(choice)
		{
			case 1:
				addCustomer addCustomerObj=new addCustomer();
				boolean insertDone=addCustomerObj.addCustomerToDb(userObj);
				if(insertDone)
				{
					System.out.println("User information successfully inserted");
				}
				adminLandingPage alp=new adminLandingPage();
				alp.display();
				break;
			case 2:
				adminLandingPage alp1=new adminLandingPage();
				alp1.display();
				break;
		}
	}
}
