package brand.brandLanding_pck;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import brand.brand;
import brand.brandLanding;
import connection.ConnectionObj;

public class validateLoyaltyProgram {
	
	
	static Connection conn = null;	
	
	
	
	public validateLoyaltyProgram() {
		conn = ConnectionObj.getConnection();
	}

	
	public boolean checkLPHasType(String LPId) {
		
		String checkType = "SELECT PROGRAMTYPE FROM LOYALTYPROGRAM WHERE PROGRAMID = '" + LPId + "'";
		
		ResultSet rs=null;
		Statement stmt = null;
		String LPType = "";
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		try {
			rs=stmt.executeQuery(checkType);
			while(rs.next()) {
				LPType = rs.getString("PROGRAMTYPE");
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(LPType.equalsIgnoreCase("REGULAR") || LPType.equalsIgnoreCase("TIER")) {
			return true;
		}
		return false;
	}
	
	
	public boolean checkLPHasActivityId(String LPId) {
		
		String checkType = "SELECT ACTIVITYID FROM LP_ACTIVITY WHERE PROGRAMID = '" + LPId + "'";
		
		ResultSet rs=null;
		Statement stmt = null;
		List<String> LPActivity = new ArrayList<>();
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		try {
			rs=stmt.executeQuery(checkType);
			while(rs.next()) {
				LPActivity.add(rs.getString("ACTIVITYID"));
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(!(LPActivity.size() < 1) && !LPActivity.isEmpty()) {
			return true;
		}
		return false;
	}
	
	
	public boolean checkLPHasRERuleIdForEachActivity(String LPId) {
		
		String checkType = "SELECT ACTIVITYID, RERULEID FROM LP_ACTIVITY WHERE PROGRAMID = '" + LPId + "'";
		
		ResultSet rs=null;
		Statement stmt = null;
		boolean exists = false;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		try {
			rs=stmt.executeQuery(checkType);
			while(rs.next()) {
				String currAct = rs.getString("ACTIVITYID");
				String currRERule = rs.getString("RERULEID");
				if(currAct != null && !currAct.isEmpty()) {
					if(currRERule != null && !currRERule.isEmpty()) {
						exists = true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exists;

	}
	
	public boolean checkLPHasRewardId(String LPId) {
		
		String checkType = "SELECT REWARDID FROM LP_REWARDS WHERE PROGRAMID = '" + LPId + "'";
		
		ResultSet rs=null;
		Statement stmt = null;
		List<String> LPActivity = new ArrayList<>();
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		try {
			rs=stmt.executeQuery(checkType);
			while(rs.next()) {
				LPActivity.add(rs.getString("REWARDID"));
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(!(LPActivity.size() < 1) && !LPActivity.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public boolean checkEachRewardHasRRRule(String LPId) {
		
		String checkType = "SELECT REWARDID, RRRULEID FROM LP_REWARDS WHERE PROGRAMID = '" + LPId + "'";
		
		ResultSet rs=null;
		Statement stmt = null;
		boolean exists = false;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		try {
			rs=stmt.executeQuery(checkType);
			while(rs.next()) {
				String currReward = rs.getString("REWARDID");
				String currRRRule = rs.getString("RRRULEID");
				if(currReward != null && !currReward.isEmpty()) {
					if(currRRRule != null && !currRRRule.isEmpty()) {
						exists = true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exists;

	}
	
	public String getLPId(String bId) {
		String getLPId = "SELECT LP_ID FROM BRAND WHERE BRANDID = '" + bId + "'";
		String LPId = "";
		ResultSet rs=null;
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		try {
			rs=stmt.executeQuery(getLPId);
			while(rs.next()) {
				LPId = rs.getString("LP_ID");
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return LPId;
	}
	
	public void setValid(String LPId) {
		String setLPId = "UPDATE LOYALTYPROGRAM SET LPFLAG = 'VALID' WHERE PROGRAMID = '" + LPId + "'";
		ResultSet rs=null;
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		try {
			rs=stmt.executeQuery(setLPId);		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void display(brand b) {
		// TODO Auto-generated method stub
		String bId = b.getUnique_id();
		String LPId = b.getLp_id();
		if(LPId == null || LPId.equals("")) {
			LPId = getLPId(bId);
		}
		String programId = LPId;
		boolean flagA = checkLPHasType(LPId);
		boolean flagB = checkLPHasActivityId(LPId);
		boolean flagC = checkLPHasRERuleIdForEachActivity(LPId);
		boolean flagD = checkLPHasRewardId(LPId);
		boolean flagE = checkEachRewardHasRRRule(LPId);
		
		
		
		System.out.println("----------------Validation Result----------------");
		if(flagA && flagB && flagC && flagD && flagE) {
			System.out.println("The Loyalty Program is valid.");
			setValid(programId);
		} else {
			System.out.println("The Loyalty Program is invalid.");
		}
		
		brandLanding brandLandingInstance = new brandLanding();
		brandLandingInstance.display(b);
		
	}
	
}
