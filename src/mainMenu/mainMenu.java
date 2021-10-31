package mainMenu;

import java.util.Scanner;
import login.loginPage;

public class mainMenu {
	
	
	public void display() {
		System.out.println("------------------------------------------------------------------");
		System.out.println("Home Page");
		System.out.println("Please select an option from the menu: ");
		System.out.println("1. Login");
		System.out.println("2. Sign Up");
		System.out.println("3. Show Queries");
		System.out.println("4. Exit");
		System.out.println("------------------------------------------------------------------");
	}
	
	
	public static void main(String[] args) {
		
		mainMenu mainMenuInstance = new mainMenu();		
		mainMenuInstance.display();
		Scanner sc = new Scanner(System.in);
		int mainInput = 0;
		mainInput = sc.nextInt();
		
		while(mainInput != 4) {
			switch(mainInput) {
				case 1:
					loginPage loginInstance = new loginPage();
					loginInstance.displayLoginMenu();
					break;
			}	
			
			
			mainMenuInstance.display();
			mainInput = sc.nextInt();
			
			
		}
	}
}
