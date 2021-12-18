package brand.brandLanding_pck.loyaltyProgramModules.tier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import brand.brand;
import connection.ConnectionObj;


public class tierSetup {
	Connection conn=null;
	public tierSetup() {
		conn = ConnectionObj.getConnection();
	}
	public void insertTier(String Tiername,String points,String multiplier,String lpId,int precedence)
	{
		String query="Insert into LP_TIER_MAPPING values(?,?,?,?,?,?)";
		PreparedStatement pstmt=null;
		int lpTierId=precedence;
		try
		{
			pstmt=conn.prepareStatement(query);
			pstmt.setString(5,Tiername);
			pstmt.setString(4,points);
			pstmt.setString(3,multiplier);
			pstmt.setString(1,lpId);
			pstmt.setInt(2, lpTierId);
			pstmt.setInt(6, precedence);
			pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		
	}
	
	public void tierSetupFunc(String name[],String pt[],String mult[],
			String lpId,int precedence[]) {
		// write the setup part to take brand input.
		int size= name.length;
		tierSetup tierSetupObj=new tierSetup();
		for(int i=1;i<=size;i++) {
			//insert query (name[i],pt[i],mult[i],lp_id)
			tierSetupObj.insertTier(name[i-1],pt[i-1],mult[i-1],lpId,precedence[i-1]);
		}
	}
	public void display(String lpId) {
		Scanner sc = new Scanner(System.in);
		System.out.println("--------------------------------------------");
		System.out.println("Tier Setup Page");
		System.out.println("Enter the number of tiers (max 3):");
		int n = sc.nextInt();
		String name[]= new String[n];
		String pt[]= new String[n];
		String mult[] = new String[n];
		int precedence[] = new int[n];
		//to clean up the input buffer
		sc.nextLine();
		for(int i=1;i<=n;i++) {
			System.out.println("Enter name of tier in increasing order of precendence"+ i);
			String tierName = sc.nextLine();
			name[i-1] = tierName;
			System.out.println("Enter the points required for tier"+ i);
			String points = sc.nextLine();
			pt[i-1] = points;
			System.out.println("Enter the multiplier for the tier" + i);
			String mul = sc.nextLine();
			mult[i-1] = mul;
			precedence[i-1]=i;
		}
		System.out.println("Please select an option from the menu:");
		System.out.println("1. Setup Tier");
		System.out.println("2. Go Back");
		int choice= sc.nextInt();
		switch(choice)
		{
		case 1:
			tier tierInstance = new tier();
			tierSetup tierSetupObj=new tierSetup();
			tierSetupObj.tierSetupFunc(name,pt,mult,lpId,precedence);
			tierInstance.display(lpId);
			break;
		case 2:
			tier tierInstance1 = new tier();
			tierInstance1.display(lpId);
			break;
		}
	}
}
