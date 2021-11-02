package admin.adminLanding;
import activityTypepck.activityType;
import admin.adminLandingPage;

import java.util.*;
public class addActivityType
{	//done
	private void addActivityToDB(activityType act) {
		// add activity to the database
		
	}
	public void display()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Activity name");
		System.out.println("Activity code");
		String activityName=sc.nextLine();
		String activityCode=sc.nextLine();
		activityType act=new activityType();
		act.setActivityName(activityName);
		act.setActivityCode(activityCode);
		System.out.println("Add Activity Type");
		System.out.println("Go Back");
		int choice=sc.nextInt();
		switch(choice)
		{
			case 1:
				addActivityType actObj=new addActivityType();
				actObj.addActivityToDB(act); 
				actObj.display();
				break;
			case 2:
				adminLandingPage alp=new adminLandingPage();
				alp.display();
				break;
		}
	}

	
}