package user.userLanding;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import connection.ConnectionObj;
import user.user;

public class Purchase {
	
	static Connection conn = null;
	
	public Purchase() {
		conn = ConnectionObj.getConnection();
	}

	public void addToPurchase(String programId, String purchaseId, String giftCardCode, String amount) {
		
		String query = "INSERT INTO PURCHASE VALUES('" + purchaseId + "', '" + giftCardCode + "', '" + programId + "', '" + amount + "', 'null')";
		Statement stmt = null;
		ResultSet rs=null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		try {
			rs=stmt.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	public void addToWallet(String walletId, String programId, String purchaseId) {
		String query = "INSERT INTO WALLET_TRANSACTIONS VALUES('" + walletId + "','" + programId + "', '" + purchaseId + "', '" + null + "', '"+ null + "', '" + null + "','PURCHASE')";
		Statement stmt = null;
		ResultSet rs=null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		try {
			rs=stmt.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
		
	public void display(user u, String programId) {
			
			Scanner sc = new Scanner(System.in);
			System.out.println("--------------------------------------------");
			System.out.println("Please enter gift card code, otherwise enter no");
			System.out.println("Please enter amount");
			String giftCardCode = sc.next();
			String amount = sc.next();
			System.out.println("Please select an option from the menu:");
			System.out.println("1. Purchase");
			System.out.println("2. Go back");
			int choice = sc.nextInt();
			
			switch(choice)
			{
				case 1:
					String walletId = u.getUserId();
					String purchaseId = "P1"; // should be unique for every purchase
					addToWallet(walletId, programId, purchaseId);
					addToPurchase(programId, purchaseId, giftCardCode, amount);
					System.out.println("Thanks for the purchase");
					Activity activity = new Activity();
					activity.display(u);
					
					break;
				case 2:
					userLanding uLanding = new userLanding();
					uLanding.display(u);
					break;
			}
			
			
			
			
		}

}
