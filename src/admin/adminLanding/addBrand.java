package admin.adminLanding;
import java.util.*;
import brand.*;
import admin.adminLandingPage;
public class addBrand
{	//done
	public boolean addBrandToDb(brand b)
	{
		//insert query to add brand to the database
		return true;

	}
	public void display()
	{
		Scanner sc=new Scanner(System.in);
		brand brandObj=new brand();
		System.out.println("Enter unique id");
		String brandId=sc.nextLine();
		System.out.println("Enter the brand name");
		String brandName=sc.nextLine();
		System.out.println("Enter the address");
		String address=sc.nextLine();
		System.out.println("Enter the join date");
		String joinDate=sc.nextLine();
		brandObj.setAddress(address);
		brandObj.setUnique_id(brandId);
		brandObj.setName(brandName);
		brandObj.setJoin_date(joinDate);
		System.out.println("1. Add brand");
		System.out.println("2.Go Back");
		int choice=sc.nextInt();
		switch(choice)
		{
			case 1:
				addBrand addBrandObj=new addBrand();
				boolean insertDone=addBrandObj.addBrandToDb(brandObj);
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
