package Services;
import Database.Connect;
import Entities.ATMEntity;
import Entities.Account;
import Entities.AccountHistoryEntity;
import Entities.TransferHistoryEntity;
import Entities.User;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * @author mprzybyla
 */
public class AccountService {
    
    public static int GetAccountBalance(int userId)
    {
        int balance=0;
        try
        {
            Connection conn = Connect.connect();
            String query="SELECT AMOUNT FROM BANKACCOUNTSTABLE WHERE USERID="+userId;
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);
            
            while(result.next())
            {
                balance += result.getInt("Amount");
            }
            conn.close();
            return balance;
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            return 0;
        }
                
    }
    
    public static int GetAccountId(int userId)
    {
        int _accountId=0;
        try
        {
            Connection conn = Connect.connect();
            String accountIdQuery = "SELECT ID FROM BANKACCOUNTSTABLE WHERE USERID= "+ userId;
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(accountIdQuery);
            while(result.next())
            {
                _accountId=result.getInt("ID");
            }
            return _accountId;
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            return 0;
        }
        
    }
    
    public static ATMEntity WithdrawCash(ATMEntity atm,int userId, int amount, int actualBalance)
    {
        int _rest=0;
        int _amount = amount;
        ATMEntity withdrawModel = new ATMEntity();
        
        //1360
        
        if(_amount>=500)
        {
            int pln500 = _amount/500; //1360/500=2
            if(pln500<=atm.PLN500)
            {
                atm.PLN500-=pln500; //5-2=3
                withdrawModel.PLN500=pln500;
                _rest = _amount-(pln500*500); //1360 - 1000 = 360
                _amount = _rest; //amount=360
                _rest=0;
            }
        }
        
        if(_amount >= 200)
        {
            int pln200 = _amount/200;  //360:200 = 1
            if(pln200<=atm.PLN200) //5-1 = 4;
            {
                atm.PLN200-=pln200;   //atm.pln200=4
                withdrawModel.PLN200=pln200;
                _rest = _amount - (pln200*200); //360-200 = 160;
                _amount = _rest;
                _rest=0;
            }
        }
        
        if(_amount>=100)
        {
            int pln100 = _amount/100;
            if(pln100<=atm.PLN100)
            {
                atm.PLN100-=pln100;
                withdrawModel.PLN100=pln100;
                _rest = _amount - (pln100*100);
                _amount = _rest;
                _rest=0;
            }
        }
        if(_amount>=50)
        {
            int pln50= _amount/50;
            if(pln50<=atm.PLN50)
            {
                atm.PLN50-=pln50;
                withdrawModel.PLN50=pln50;
                _rest = _amount - (pln50*50);
                _amount= _rest;
                _rest=0;
            }        
        }
        if(_amount>=20)
        {
            int pln20=_amount/20;
            if(pln20<=atm.PLN20)
            {
                atm.PLN20-=pln20;
                withdrawModel.PLN20=pln20;
                _rest = _amount - (pln20*20);
                _amount = _rest;
                _rest=0;
            }
            
        }
        if(_amount>=10)
        {
            int pln10 = _amount/10;
            if(pln10<=atm.PLN10)
            {
                atm.PLN10-=pln10;
                withdrawModel.PLN10=pln10;
                _rest = _amount - (pln10*10);
                _amount=_rest;
                _rest=0;
            }
        }
        atm.refresh();
        withdrawModel.refresh();
        actualBalance -= amount;
        int accountId = AccountService.GetAccountId(userId);
            
        try
        { 
            if(withdrawModel.PLN10<=0 && withdrawModel.PLN20<=0 && withdrawModel.PLN50<=0 && withdrawModel.PLN100<=0 && withdrawModel.PLN200<=0 && withdrawModel.PLN500<=0)
            {
                return withdrawModel;
            }
            
            Connection conn = Connect.connect();
            String updateAtmCommand = "UPDATE ATMCONFIGURATION SET [10PLN]= "+atm.PLN10+ ", [20pln]= "+ atm.PLN20 + ", [50pln]= "+atm.PLN50 + ", [100PLN]= "+ atm.PLN100+", [200PLN]= "+ atm.PLN200 + ", [500PLN]= " + atm.PLN500;
            String updateUserAccountBalance = "UPDATE BANKACCOUNTSTABLE SET [AMOUNT]= "+ actualBalance+" WHERE USERID= "+ userId;  
            String addHistoryCommand = "INSERT INTO BankAccountHistory  (AccountId, WithdrawAmount, DepositAmount, AccountBalance, OperationDate) VALUES ( " + accountId + ", "+ amount +", "+ 0 +" ," + actualBalance+", " +" datetime('now', '+1 hour'))";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(updateAtmCommand);
            stmt.executeUpdate(updateUserAccountBalance);
            stmt.executeUpdate(addHistoryCommand);
            conn.close();
            return withdrawModel;
        } 
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            return null;
        }
        
        
    }
    
    public static ArrayList<AccountHistoryEntity>GetAccountHistory(int accountId)
    {
        ArrayList<AccountHistoryEntity> accountHistory = new ArrayList<AccountHistoryEntity>();
        
        try
        {
            Connection conn = Connect.connect();
            String query = "SELECT WITHDRAWAMOUNT, DEPOSITAMOUNT, ACCOUNTBALANCE, OPERATIONDATE FROM BANKACCOUNTHISTORY WHERE ACCOUNTID= "+accountId;
            Statement stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery(query);
            
           
            
            while(results.next())
            {
                //LocalDateTime resultConvertedDateTime = results.getDate("OPERATIONDATE").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                String str = results.getString("OPERATIONDATE");
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
                accountHistory.add(new AccountHistoryEntity(results.getInt("WITHDRAWAMOUNT"),results.getInt("DEPOSITAMOUNT"),results.getInt("ACCOUNTBALANCE"), dateTime));
            }
            conn.close();
            return accountHistory;
            
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            return null;
        }
        
    }
    
    
    
    public static int DepositCash(ATMEntity atm, int userId, int pln10, int pln20, int pln50, int pln100, int pln200, int pln500)
    {
        int accountBalance = AccountService.GetAccountBalance(userId);
        int depositAmountSum = (10*pln10)+(20*pln20)+(50*pln50)+(100*pln100)+(200*pln200)+(500*pln500);
        int accountId=AccountService.GetAccountId(userId);
        accountBalance+=depositAmountSum;
        
        try
        {
            Connection conn = Connect.connect();
            String updateAccount = "UPDATE BANKACCOUNTSTABLE SET AMOUNT="+accountBalance+" WHERE USERID="+userId;
            String addHistoryEntry = "INSERT INTO BankAccountHistory  (AccountId, WithdrawAmount, DepositAmount, AccountBalance, OperationDate) VALUES ( " + accountId + ", "+ 0 +", "+ depositAmountSum +" ," + accountBalance+", " +" datetime('now', '+1 hour'))";
            Statement stmt = conn.createStatement();
            if(depositAmountSum>0)
            {
                stmt.executeUpdate(updateAccount);
                stmt.executeUpdate(addHistoryEntry);
                atm.PLN10+=pln10;
                atm.PLN20+=pln20;
                atm.PLN50+=pln50;
                atm.PLN100+=pln100;
                atm.PLN200+=pln200;
                atm.PLN500+=pln500;
                ATMManageService.UpdateAtm(atm);
            }
            return depositAmountSum;
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            return 0;
        }
        
        
    }
    
    public static boolean TransferCash(User fromUser, User toUser, int amount)
    {
        try
        {
            int fromUserAccountBalance = AccountService.GetAccountBalance(fromUser.Id);
            fromUserAccountBalance-=amount;
            int fromUserAccountId = AccountService.GetAccountId(fromUser.Id);
            String updateFromUserAccountBalance = "UPDATE BANKACCOUNTSTABLE SET AMOUNT="+ fromUserAccountBalance+" WHERE USERID="+ fromUser.Id;
            String addFromUserTransferHistory = "INSERT INTO TRANSFERHISTORY (RECEIVED, SENT, SENDERFIRSTNAME,SENDERLASTNAME, RECEIVERFIRSTNAME,RECEIVERLASTNAME,ACCOUNTBALANCE, OPERATIONDATE, ACCOUNTID)"
                    + "VALUES("+0+","+amount+","+"'"+fromUser.FirstName+"'"+","+"'"+fromUser.LastName+"'"+","+"'"+toUser.FirstName+"'"+","+"'"+toUser.LastName+"'"+","+fromUserAccountBalance+","+" datetime('now', '+1 hour'),"+fromUserAccountId+")";
            
            int toUserAccountBalance=AccountService.GetAccountBalance(toUser.Id);
            toUserAccountBalance+=amount;
            int toUserAccountId = AccountService.GetAccountId(toUser.Id);
            String updateToUserAccountBalance = "UPDATE BANKACCOUNTSTABLE SET AMOUNT="+ toUserAccountBalance+ " WHERE USERID="+toUser.Id;
            String addToUserTransferHistory = "INSERT INTO TRANSFERHISTORY (RECEIVED, SENT, SENDERFIRSTNAME,SENDERLASTNAME, RECEIVERFIRSTNAME,RECEIVERLASTNAME,ACCOUNTBALANCE, OPERATIONDATE, ACCOUNTID)"
                    + "VALUES("+amount+","+0+","+"'"+fromUser.FirstName+"'"+","+"'"+fromUser.LastName+"'"+","+"'"+toUser.FirstName+"'"+","+"'"+toUser.LastName+"'"+","+toUserAccountBalance+","+" datetime('now', '+1 hour'),"+toUserAccountId+")";
            
            
            Connection conn = Connect.connect();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(updateFromUserAccountBalance);
            stmt.executeUpdate(addFromUserTransferHistory);
            stmt.executeUpdate(updateToUserAccountBalance);
            stmt.executeUpdate(addToUserTransferHistory);
            conn.close();
            return true;
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public static ArrayList<TransferHistoryEntity>GetTransfersHistory(int accountId)
    {
        ArrayList<TransferHistoryEntity>listOfTransferEntries = new ArrayList<TransferHistoryEntity>();
        try
        {
            Connection conn = Connect.connect();
            String query = "SELECT RECEIVED, SENT, SENDERFIRSTNAME, SENDERLASTNAME, RECEIVERFIRSTNAME, RECEIVERLASTNAME, ACCOUNTBALANCE, OPERATIONDATE FROM TRANSFERHISTORY WHERE ACCOUNTID ="+accountId;
            Statement stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery(query);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            while(results.next())
            {
                int received = results.getInt("RECEIVED");
                int sent = results.getInt("SENT");
                String senderFirstName = results.getString("SENDERFIRSTNAME");
                String senderLastName = results.getString("SENDERLASTNAME");
                String receiverFirstName = results.getString("RECEIVERFIRSTNAME");
                String receiverLastName = results.getString("RECEIVERLASTNAME"); 
                int accountBalance = results.getInt("ACCOUNTBALANCE");
                String operationDateTimeStr = results.getString("OPERATIONDATE");
                LocalDateTime parsedOperationDateTime = LocalDateTime.parse(operationDateTimeStr, formatter);
                
                listOfTransferEntries.add(new TransferHistoryEntity(received, sent, senderFirstName,senderLastName,receiverFirstName,receiverLastName,accountBalance,parsedOperationDateTime));

            }
            return listOfTransferEntries;
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
