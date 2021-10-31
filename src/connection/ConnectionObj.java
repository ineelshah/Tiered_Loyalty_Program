package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class ConnectionObj {        
    static Connection con=null;
    public static Connection getConnection()
    {
        if (con != null) 
        	return con;
        return 
        		getConnection("root", "root");
    }

    private static Connection getConnection(String user_name,String password)
    {
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","root","root");  
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return con;        
    }
} 