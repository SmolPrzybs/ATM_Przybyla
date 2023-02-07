package Database;
import java.sql.*;
/**
 * @author mprzybyla
 */
public class Connect
{
    public static Connection connect()
    {
        Connection conn=null;
        
        try
        {
            //laczenie jdbc:sqlite:sciezka, dawac 2 backslashe
            String url="jdbc:sqlite:C:\\Java_Projekt\\ATM_Przybyla_db\\ATM_db.db";
            conn = DriverManager.getConnection(url);
            return conn;
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            return conn;
        }
                
    }
}
