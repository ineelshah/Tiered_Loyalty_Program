package signUp.signUpModules;
import java.util.*;
import signUp.registrationPage;
import brand.brand;
import login.loginPage;
public class brandSignUp
{
	public boolean addBrandToDb(brand b)
	{
		//write sql for inserting a new brand into the database
		return true;
	}
	public void brandFunct()
	{
		System.out.println("Enter the brand Id:");
		System.out.println("Enter the brand name");
		System.out.println("Address");
		System.out.println("Join Date");
		Scanner sc=new Scanner(System.in);
		String brandId=sc.nextLine();
		String brandName=sc.nextLine();
		String address=sc.nextLine();
		String joinDate=sc.nextLine();
		brand b=new brand();
		b.setUnique_id(brandId);
		b.setName(brandName);
		b.setAddress(address);
		b.setJoin_date(joinDate);
		boolean brandAdded=addBrandToDb(b);
		//if brand is added successfully navigate to login page
		if(brandAdded)
		{
			loginPage lg=new loginPage();
			lg.display();
		}
	}
	public void display()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("1.Sign Up");
		System.out.println("2.Go Back");
		int choice=sc.nextInt();
		switch(choice)
		{
		case 1:
			brandFunct();
			break;
		case 2:
			//to go back to the registration page
			registrationPage regObj=new registrationPage();
			regObj.display();
			break;
		}
	}
}