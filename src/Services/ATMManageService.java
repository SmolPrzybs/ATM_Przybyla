
package Services;

import Database.Connect;
import Entities.ATMEntity;
import java.sql.*;

/**
 * @author mprzybyla
 */
public class ATMManageService 
{
    
    public static ATMEntity GetAtmInfo()
    {
        int pln10=0;
        int pln20=0;
        int pln50=0;
        int pln100=0;
        int pln200=0;
        int pln500=0;
        
        try
        {
            Connection conn = Connect.connect();
            String query = "SELECT ID, [10PLN], [20PLN], [50PLN], [100PLN], [200PLN], [500PLN] FROM ATMCONFIGURATION";
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);  
            
            while(result.next())
            {
                pln10=result.getInt("10PLN");
                pln20=result.getInt("20PLN");
                pln50=result.getInt("50PLN");
                pln100=result.getInt("100PLN");
                pln200=result.getInt("200PLN");
                pln500=result.getInt("500PLN");
            }
            
            ATMEntity atm = new ATMEntity(pln10,pln20,pln50,pln100,pln200,pln500);
            return atm;
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public static void UpdateAtm(ATMEntity updatedEntity)
    {
        try
        {
            Connection conn = Connect.connect();
            String query = "UPDATE ATMCONFIGURATION SET [10PLN]= "+updatedEntity.PLN10 +", [20PLN]= "+updatedEntity.PLN20+", [50PLN]= "+updatedEntity.PLN50+ ", [100PLN]= "+updatedEntity.PLN100+ ", [200PLN]= "+ updatedEntity.PLN200+ ", [500PLN]= "+ updatedEntity.PLN500 + " WHERE ID=1";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
