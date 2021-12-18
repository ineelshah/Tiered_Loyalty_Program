package sampleQueries;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import connection.ConnectionObj;
import user.user;
import user.userLanding.userLanding;

public class ShowQueries {
	
	static Connection conn = null;
	
	public ShowQueries() {
		conn = ConnectionObj.getConnection();
	}
	
	public void showResult1(String brandId) {
		ResultSet rs=null;
		String query = "SELECT C.CUSTOMERID, C.FIRSTNAME, C.LASTNAME\r\n"
				+ "FROM CUSTOMER C\r\n"
				+ "WHERE C.CUSTOMERID NOT IN(\r\n"
				+ "SELECT CLM.CUSTOMERID \r\n"
				+ "FROM CUSTOMER_LP_MASTER CLM\r\n"
				+ "WHERE CLM.LPID IN(\r\n"
				+ "SELECT B.LP_ID \r\n"
				+ "FROM BRAND B\r\n"
				+ "WHERE B.BRANDID='"+brandId+"')\r\n"
				+ ")";
		Statement stmt = null;
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
		System.out.println("Customer Id "+"|"+" First Name "+"|"+" Last Name ");
		try {
			while(rs.next()) {
				System.out.println(rs.getString("CUSTOMERID")+"|"+rs.getString("FIRSTNAME")+"|"+rs.getString("LASTNAME"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void showResult2() {
		ResultSet rs=null;
//		String query = "SELECT C.CUSTOMERID, C.FIRSTNAME, C.LASTNAME "
//				+ "FROM CUSTOMER C "
//				+ "WHERE C.CUSTOMERID IN ( "
//				+ "SELECT W.WALLETID "
//				+ "FROM WALLET W "
//				+ "WHERE W.POINTS='0')";
		String query = "SELECT WT.WALLETID, WT.LOYALTY_PROGRAM_ID, C.FIRSTNAME, C.LASTNAME, LP.PROGRAMNAME FROM WALLET_TRANSACTIONS WT LEFT JOIN CUSTOMER C ON WT.WALLETID = C.CUSTOMERID LEFT JOIN LOYALTYPROGRAM LP ON WT.LOYALTY_PROGRAM_ID = LP.PROGRAMID GROUP BY WT.WALLETID, WT.LOYALTY_PROGRAM_ID, C.FIRSTNAME, C.LASTNAME, LP.PROGRAMNAME  HAVING COUNT(*) = 1";
		Statement stmt = null;
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
		System.out.println("Customer Id "+"|"+" First Name "+"|"+" Last Name " + "| " + "Loyalty Program Id" + " | " + "Loyalty Program Name");
		try {
			while(rs.next()) {
				System.out.println(rs.getString("WALLETID")+"|"+rs.getString("FIRSTNAME")+"|"+rs.getString("LASTNAME")+"|"+rs.getString("LOYALTY_PROGRAM_ID")+"|"+rs.getString("PROGRAMNAME"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void showResult3(String brandId) {
		ResultSet rs=null;
		String query = "SELECT R.REWARDID,R.REWARDNAME\r\n"
				+ "FROM REWARD R\r\n"
				+ "WHERE R.REWARDID IN \r\n"
				+ "( \r\n"
				+ "SELECT LP.REWARDID\r\n"
				+ "FROM LP_REWARDS LP\r\n"
				+ "WHERE LP.PROGRAMID IN \r\n"
				+ "(\r\n"
				+ "SELECT LP_ID\r\n"
				+ "FROM BRAND \r\n"
				+ "WHERE BRANDID = '"+brandId+"'))";
		Statement stmt = null;
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
		System.out.println("Reward Id "+"|"+" Reward Name");
		try {
			while(rs.next()) {
				System.out.println(rs.getString("REWARDID")+" | "+rs.getString("REWARDNAME"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void showResult4() {
		ResultSet rs=null;
		String query = "\r\n"
				+ "SELECT LP.PROGRAMID,LP.PROGRAMNAME\r\n"
				+ "FROM  LOYALTYPROGRAM LP\r\n"
				+ "WHERE LP.PROGRAMID IN ( SELECT L.PROGRAMID\r\n"
				+ "                        FROM LP_ACTIVITY L\r\n"
				+ "                        WHERE L.ACTIVITYID IN ( SELECT A.ACTIVITY_CODE\r\n"
				+ "                                                FROM ACTIVITY A\r\n"
				+ "                                                WHERE A.ACTIVITY_NAME = 'REFER'))";
		Statement stmt = null;
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
		System.out.println("Program Id "+"|"+" Praogram Name");
		try {
			while(rs.next()) {
				System.out.println(rs.getString("PROGRAMID")+" | "+rs.getString("PROGRAMNAME"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void showResult5(String brandId) {
		ResultSet rs=null;
		String query = "SELECT W.ACTIVITY_NAME, COUNT(*) AS COUNT\r\n"
				+ "FROM  WALLET_TRANSACTIONS W\r\n"
				+ "WHERE W.LOYALTY_PROGRAM_ID = (SELECT B.LP_ID FROM BRAND B WHERE B.BRANDID = '"+brandId+"')\r\n"
				+ "GROUP BY W.ACTIVITY_NAME";
		Statement stmt = null;
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
		System.out.println("Activity name "+"|"+" Count");
		try {
			while(rs.next()) {
				System.out.println(rs.getString("ACTIVITY_NAME")+" | "+rs.getString("COUNT"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void showResult6(String brandId) {
		ResultSet rs=null;
		String query = "SELECT C.CUSTOMERID, C.FIRSTNAME, C.LASTNAME\r\n"
				+ "FROM CUSTOMER C\r\n"
				+ "WHERE C.CUSTOMERID IN\r\n"
				+ "(\r\n"
				+ "SELECT W.WALLETID\r\n"
				+ "FROM  WALLET_REWARD_TRANSACTIONS W\r\n"
				+ "WHERE W.PROGRAMID = (SELECT B.LP_ID FROM BRAND B WHERE B.BRANDID = '"+brandId+"')\r\n"
				+ "GROUP BY W.WALLETID\r\n"
				+ "HAVING COUNT(*)>=2\r\n"
				+ ")";
		Statement stmt = null;
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
		System.out.println("Customer Id "+"|"+" First Name "+"|"+" Last Name ");
		try {
			while(rs.next()) {
				System.out.println(rs.getString("CUSTOMERID")+"|"+rs.getString("FIRSTNAME")+"|"+rs.getString("LASTNAME"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void showResult7() {
		ResultSet rs=null;
		String query = "\r\n"
				+ "SELECT B.BRANDID, B.BRANDNAME\r\n"
				+ "FROM BRAND B\r\n"
				+ "WHERE B.LP_ID NOT IN\r\n"
				+ "(\r\n"
				+ "SELECT W.PROGRAMID\r\n"
				+ "FROM  WALLET_REWARD_TRANSACTIONS W\r\n"
				+ "GROUP BY W.PROGRAMID\r\n"
				+ "HAVING SUM(W.POINTS)>500\r\n"
				+ ")";
		Statement stmt = null;
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
		System.out.println("Brand Id "+"|"+"Brand Name");
		try {
			while(rs.next()) {
				System.out.println(rs.getString("BRANDID")+" | "+rs.getString("BRANDNAME"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void showResult8(String customerId, String brandId) {
		ResultSet rs=null;
		String query = "\r\n"
				+ "SELECT COUNT(*) AS COUNT\r\n"
				+ "FROM WALLET_TRANSACTIONS W\r\n"
				+ "WHERE W.WALLETID = '"+customerId+"' AND W.ACTIVITY_DATE >= TO_DATE('08-01-2021', 'MM-DD-YYYY') AND W.ACTIVITY_DATE <= TO_DATE('09-30-2021', 'MM-DD-YYYY')\r\n"
				+ "AND W.LOYALTY_PROGRAM_ID = (SELECT B.LP_ID FROM BRAND B WHERE B.BRANDID = '"+brandId+"')";
		Statement stmt = null;
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
		System.out.println("Count");
		try {
			while(rs.next()) {
				System.out.println(rs.getString("Count"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void display() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("--------------------------------------------");
		System.out.println("Please select query to execute from 1-8:");
		int choice = sc.nextInt();
		String brandId="";
		
		switch(choice)
		{
			case 1:
				System.out.println("--------------------------------------------");
				System.out.println("Please enter brand id:");
				brandId = sc.next();
				System.out.println("Results for 1 st query:");
				showResult1(brandId);			
				break;
			case 2:
				System.out.println("--------------------------------------------");
				System.out.println("Results for 2 nd query:");
				showResult2();
				break;
			case 3:
				System.out.println("--------------------------------------------");
				System.out.println("Please enter brand id:");
				brandId = sc.next();
				System.out.println("Results for 3 rd query:");
				showResult3(brandId);
				break;
			case 4:
				System.out.println("--------------------------------------------");
				System.out.println("Results for 4 th query:");
				showResult4();
				break;
			case 5:
				System.out.println("--------------------------------------------");
				System.out.println("Please enter brand id:");
				brandId = sc.next();
				System.out.println("Results for 5 th query:");
				showResult5(brandId);
				break;
			case 6:
				System.out.println("--------------------------------------------");
				System.out.println("Please enter brand id:");
				brandId = sc.next();
				System.out.println("Results for 6 th query:");
				showResult6(brandId);
				break;
			case 7:
				System.out.println("--------------------------------------------");
				System.out.println("Results for 7 th query:");
				showResult7();
				break;
			case 8:
				System.out.println("--------------------------------------------");
				System.out.println("Results for 8 th query:");
				System.out.println("Please enter brand id:");
				brandId = sc.next();
				System.out.println("Please enter customer id:");
				String customerId = sc.next();
				showResult8(customerId, brandId);
				break;
		}
			
	}
}
