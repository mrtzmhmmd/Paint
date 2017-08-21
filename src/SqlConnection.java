import java.sql.DriverManager;

import com.mysql.jdbc.Connection;

public class SqlConnection {
	
    static Connection con=null;
    
    public static Connection getConnection()
    {
        if (con != null) return con;
        return getConnection("hashtest", "root", "abc*123");
    }

    private static Connection getConnection(String db_name,String user_name,String password)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/"+db_name+"?user="+user_name+"&password="+password);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return con;        
    }
}