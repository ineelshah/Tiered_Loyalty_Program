package admin.adminLanding;

import java.util.Scanner;

import admin.adminLandingPage;

public class showCustomerInfo
{
	//done
	public void show(String customerId)
	{
		//get customer information on the basis of customerId
		//print customer information
		//if any error occurs print the error
	}
	public void display()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter customer Id");
		String customerId=sc.nextLine();
		System.out.println("1.show customer info");
		System.out.println("2.Go Back");
		int choice=sc.nextInt();
		switch(choice)
		{
		case 1:
			showCustomerInfo sbiObj=new showCustomerInfo();
			sbiObj.show(customerId);
			break;
		case 2:
			adminLandingPage alp=new adminLandingPage();
			alp.display();
			break;
			
		}
	}
}