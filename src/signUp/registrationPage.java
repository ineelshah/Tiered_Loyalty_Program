package signUp;
import java.util.*;
import signUp.signUpModules.*;
public class registrationPage {
	public void display()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("1. Brand Sign-up");
		System.out.println("2. Customer Sign-up");
		System.out.println("3. Go Back");
		int choice=sc.nextInt();
		switch(choice)
		{
		case 1:
			brandSignUp brandSgnUp=new brandSignUp();
			brandSgnUp.display();
			break;
		case 2:
			customerSignUp custSgnUp=new customerSignUp();
			custSgnUp.display();
			break;
		case 3:
			return;
		}
	}
}