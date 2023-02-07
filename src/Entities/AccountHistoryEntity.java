package Entities;

import java.time.LocalDateTime;

/**
 * @author mprzybyla
 */
public class AccountHistoryEntity extends MainEntity
{
    public int AccountId;
    public int WithdrawAmount;
    public int DepositAmount;
    public int AccountBalance;
    public LocalDateTime OperationDate;
    
    public AccountHistoryEntity()
    {
        this.AccountId=0;
        this.WithdrawAmount=0;
        this.DepositAmount=0;
        this.AccountBalance=0;
        this.OperationDate=LocalDateTime.MIN;
    }
    
    public AccountHistoryEntity(int _withdrawAmount, int _depositAmount, int _accountBalance, LocalDateTime _operationDateTime)
    {
        this.WithdrawAmount=_withdrawAmount;
        this.DepositAmount=_depositAmount;
        this.AccountBalance=_accountBalance;
        this.OperationDate=_operationDateTime;
    }
            
}
