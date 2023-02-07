package Services;

import Database.Connect;
import Entities.RegisterUser;
import Entities.User;
import java.sql.*;
/**
 * @author mprzybyla
 */
public class UserService
{
    public static User Login(String _firstName, String _lastName, String _password)
    {
        String firstName = null;
        String lastName = null;
        String password = null;
        int Id=0;
        try
        {
            Connection conn = Connect.connect();
            String query = "SELECT ID, FIRSTNAME, LASTNAME, PASSWORD FROM USERS WHERE FIRSTNAME="+"'"+_firstName+"'"+" AND LASTNAME="+"'"+_lastName+"'";
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);
            while(result.next())
            {
                firstName=result.getString("FIRSTNAME");
                lastName=result.getString("LASTNAME");
                password=result.getString("PASSWORD");
                Id=result.getInt("ID");
                
            }
            if(firstName == null || lastName==null)
            {
                conn.close();
                return null;
            }
            
            User user = new User(firstName,lastName,password);
            user.Id=Id;
            boolean passwordCheck=user.passwordCheck(_password);
            
            if(passwordCheck==false)
            {
                conn.close();
                return null;
            }
            
            conn.close();
            return user;
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            return null;
        }
        
        
        
        
    }
    
    public static RegisterUser Register(RegisterUser registerUser)
    {
        String firstName="";
        String lastName="";
        
        try
        {
            Connection conn = Connect.connect();
            String query = "SELECT FIRSTNAME, LASTNAME FROM USERS WHERE FIRSTNAME="+"'"+registerUser.FirstName+"'"+" AND LASTNAME="+"'"+registerUser.LastName+"'";
            Statement stmt=conn.createStatement();
            ResultSet result = stmt.executeQuery(query);
            
            while(result.next())
            {
                firstName=result.getString("FIRSTNAME");
                lastName=result.getString("LASTNAME");
            }
            
            if (!firstName.equals("") || !lastName.equals(""))
            {
                conn.close();
                registerUser.isExist=true;
                return registerUser;
            }
            
            String command = "INSERT INTO USERS (FIRSTNAME, LASTNAME, PASSWORD) VALUES "+"(" +" '"+registerUser.FirstName+"', "+" '"+registerUser.LastName+"' ,"+" '"+registerUser.getPassword()+"')";
            String getUserIdQuery="SELECT ID FROM USERS WHERE FIRSTNAME= '"+ registerUser.FirstName+ "' AND LASTNAME= '"+ registerUser.LastName+"'";
            stmt.executeUpdate(command);
            int userId;
            ResultSet resultUserId = stmt.executeQuery(getUserIdQuery);
            userId=resultUserId.getInt("ID");
            String createBankAccount="INSERT INTO BANKACCOUNTSTABLE (USERID, AMOUNT) VALUES ("+userId+" ,"+0+")";
            stmt.executeUpdate(createBankAccount);
            conn.close();
            registerUser.hasRegistered=true;
            return registerUser;
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public static User getUserByFirstAndLastName(String FirstName, String LastName)
    {
        User user = new User();
       try
       {
           Connection conn = Connect.connect();
           String query = "SELECT ID, FIRSTNAME, LASTNAME FROM USERS WHERE FIRSTNAME="+"'"+FirstName+"'"+ " AND LASTNAME="+"'"+LastName+"'";
           Statement stmt = conn.createStatement();
           ResultSet results = stmt.executeQuery(query);
           
           while(results.next())
           {
               user.FirstName=results.getString("FIRSTNAME");
               user.LastName=results.getString("LASTNAME");
               user.Id=results.getInt("ID");
           }
           return user;
       }
       catch(SQLException e)
       {
           System.out.println(e.getMessage());
           return null;
       }
    }
}
