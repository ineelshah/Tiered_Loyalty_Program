package admin.adminLanding;
import java.util.*;
//done
import admin.adminLandingPage;
public class showBrandInfo
{
	public void show(String brandId)
	{
		//get brand information on the basis of brandId
		//print brand information
		//if any error occurs print the error
	}
	public void display()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter brand Id");
		String brandId=sc.nextLine();
		System.out.println("1.show brand info");
		System.out.println("2.Go Back");
		int choice=sc.nextInt();
		switch(choice)
		{
		case 1:
			showBrandInfo sbiObj=new showBrandInfo();
			sbiObj.show(brandId);
			break;
		case 2:
			adminLandingPage alp=new adminLandingPage();
			alp.display();
			break;
			
		}
	}
}