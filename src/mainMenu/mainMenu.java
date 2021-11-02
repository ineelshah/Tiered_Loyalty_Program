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

		mainInput = Integer.valueOf(sc.nextLine());
		
		while(mainInput != 4) {
			boolean exitFlag = false;
			boolean returnedFromAJourney = false;
			switch(mainInput) {
				case 1:
					loginPage loginInstance = new loginPage();
					loginInstance.display();
					returnedFromAJourney = true;
					break;
				case 2:
					registrationPage registrationInstance = new registrationPage();
					registrationInstance.display();
					returnedFromAJourney = true;
					break;
				case 3:
					ShowQueries showQueriesInstance = new ShowQueries();
//					showQueriesInstance.display();
					returnedFromAJourney = true;
					break;
				default:
					System.out.println("Exiting program.");
					exitFlag = true;
					returnedFromAJourney = true;
					break;
			}	

			display();
			System.out.println("Input choice:");
			mainInput = Integer.valueOf(sc.nextLine());

			
			if(exitFlag) {
				break;
			}
				
		}

		
		
		
		System.out.println("Program Terminated.");
	}
}
