package brand.brandLanding_pck.loyaltyProgramModules.regular;

import java.util.*;
import brand.brandLanding_pck.loyaltyProgramModules.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class activityTypes{
	public activityTypes() {
		
	}

	public void display() {
		Scanner sc = new Scanner(System.in);
		System.out.println("--------------------------------------------");
		System.out.println("Activity Page");
		System.out.println("Please select an option from the menu:");
		System.out.println("1. Purchase");
		System.out.println("2. Leave a review");
		System.out.println("3. Refer a friend");
		System.out.println("4. Go Back");
		System.out.println("--------------------------------------------");
		String 
		int choice= sc.nextInt();
		switch(choice)
		{
		case 1:
			brandPurchase brandPurchaseinstance = new brandPurchase();
			brandPurchaseinstance.display();
			break;
		case 2:
			brandLeaveReview brandLeaveReviewinstance = new brandLeaveReview();
			brandLeaveReviewinstance.display();
			break;
		case 3:
			brandReferFriend brandReferFriendinstance = new brandReferFriend();
			brandReferFriendinstance.display();
			break;
		case 4:
			regular regularinstance = new regular();
			regularinstance.display();
			break;
		}
	}
}