package brand.brandLanding_pck;
import java.util.*;
import brand.brandLanding.*;
import brand.brand;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import brand.brandLanding;
import brand.brandLanding_pck.loyaltyProgramModules.regular.regular;
import brand.brandLanding_pck.loyaltyProgramModules.tier.*;
import connection.ConnectionObj;

public class loyaltyProgram {
	static int lpIdCounter=1;
	static Connection conn = null;
	public loyaltyProgram() {}
	
	//to add lpId to the brands table
	public void insertIntoBrand(String brandId,String lpId)
	{
		conn = ConnectionObj.getConnection();
		PreparedStatement pstmt=null;
		String query="INSERT into BRAND(LP_ID) values(?) where BRANDID='"+brandId+"'";
		
		try 
		{
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, lpId);
			pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	//insert lp information into lp table
	public void insertLp(String lpName,String lpId,String lpType)
	{
		String lpFlag="False";
		conn = ConnectionObj.getConnection();
		String query="INSERT into LOYALTYPROGRAM (PROGRAMNAME,PROGRAMID,PROGRAMTYPE,LPFLAG) values(?,?,?,?)";
		PreparedStatement pstmt=null;
		try 
		{
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, lpName);
			pstmt.setString(2, lpId);
			pstmt.setString(3, lpType);
			pstmt.setString(4, lpFlag);
			pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public String generateLpId()
	{
		String lpId="LP"+String.valueOf(lpIdCounter);
		return lpId;
	}
	
	public void display(brand b) {
		loyaltyProgram lp=new loyaltyProgram();
		Scanner sc = new Scanner(System.in);
		System.out.println("--------------------------------------------");
		System.out.println("Add Loyalty Program");
		System.out.println("Enter the loyalty program name");
		String lpName  = sc.nextLine();
		getLoyaltyProgram glp = new getLoyaltyProgram();
		glp.setLoyaltyProgramName(lpName);
		String lpId=generateLpId();
		System.out.println(lpId);
		glp.setLoyaltyProgramId(lpId);		
		b.setLp_id(lpId);
		System.out.println("Please select an option from the menu:");
		System.out.println("1. Regular");
		System.out.println("2. Tier");
		System.out.println("3. Go Back");
		int choices = sc.nextInt();
		switch(choices)
		{
		case 1: 
			regular rginstance = new regular();
			String a = "regular";
			glp.setProgram_type(a);
			lp.insertLp(lpName,lpId,a);
			//add the same lpid in brands table as fk
			lp.insertIntoBrand(b.getUnique_id(),lpId);
			rginstance.display(lpId);
			break;
		
		case 2:
			tier tierinstance = new tier();
			String ab = "tier";
			glp.setProgram_type(ab);
			lp.insertLp(lpName,lpId,ab);
			//add the same lpid in brands table as fk
			lp.insertIntoBrand(b.getUnique_id(),lpId);
			tierinstance.display(lpId);
			break;
		case 3:
			brandLanding brandLandinginstance = new brandLanding();
//			brandLandinginstance.display(b);
		}
		}
		
}
