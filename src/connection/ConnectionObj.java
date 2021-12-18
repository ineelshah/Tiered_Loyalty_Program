package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ConnectionObj {        
    static Connection con=null;
    public static Connection getConnection()
    {
        if (con != null) 
        	return con;
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input username for database: ");
        String uname = sc.next();
        System.out.println("Please input password for database: ");
        String pass = sc.next();
        
        if(uname == null || uname.isEmpty()) {
        	uname = "";
        }
        if(pass == null || pass.isEmpty()) {
        	pass = "";
        }
        return getConnection(uname, pass);
    }

    private static Connection getConnection(String user_name,String password)
    {
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
//            String url = "jdbc:oracle:thin:@localhost:1521:orcl";
              String url = "";
//            String userName = "root";
//            String password = "root";
            con=DriverManager.getConnection(url, user_name, password);  
        }
        catch(SQLException e)
        {
			System.out.println("Invalid username and password found. Please input the right credentials.");
//            e.printStackTrace();
        } catch (Exception e) {
			System.out.println("Oracle JDBC jar not found. Please setup the jar and try running the program.");
        }

        return con;        
    }
} 