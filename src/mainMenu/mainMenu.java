package mainMenu;

import java.util.Scanner;
import login.loginPage;
import sampleQueries.ShowQueries;
import signUp.registrationPage;

public class mainMenu {
	
	
	public static void display() {
		System.out.println("------------------------------------------------------------------");
		System.out.println("*****************************Home Page*****************************");
		System.out.println("Please select an option from the menu: ");
		System.out.println("1. Login");
		System.out.println("2. Sign Up");
		System.out.println("3. Show Queries");
		System.out.println("4. Exit");
		System.out.println("------------------------------------------------------------------");
	}
	
	
	public static void main(String[] args) {
		
		display();
		Scanner sc = new Scanner(System.in);
		int mainInput = 0;
		
		while(mainInput != 4) {
			mainInput = sc.nextInt();
			boolean exitFlag = false;
			switch(mainInput) {
				case 1:
					loginPage loginInstance = new loginPage();
					loginInstance.display();
					break;
				case 2:
					registrationPage registrationInstance = new registrationPage();
//					registrationInstance.display();
					break;
				case 3:
					ShowQueries showQueriesInstance = new ShowQueries();
//					showQueriesInstance.display();
					break;
				default:
					System.out.println("Exiting program.");
					exitFlag = true;
					break;
			}	
			
			if(!exitFlag) {
				display();
				mainInput = sc.nextInt();
			}			
			
		}

		
		
		
		sc.close();
		System.out.println("Program Terminated.");
	}
}
