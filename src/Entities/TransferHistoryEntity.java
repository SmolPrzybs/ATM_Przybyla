package Entities;

import java.time.LocalDateTime;

/**
 * @author mprzybyla
 */
public class TransferHistoryEntity 
{
    public int Received;
    public int Sent;
    public String SenderFirstName;
    public String SenderLastName;
    public String ReceiverFirstName;
    public String ReceiverLastName;
    public int AccountBalance;
    public LocalDateTime OperationDateTime;

    public TransferHistoryEntity(int Received, int Sent, String SenderFirstName, String SenderLastName, String ReceiverFirstName, String ReceiverLastName, int AccountBalance, LocalDateTime OperationDateTime) {
        this.Received = Received;
        this.Sent = Sent;
        this.SenderFirstName = SenderFirstName;
        this.SenderLastName = SenderLastName;
        this.ReceiverFirstName = ReceiverFirstName;
        this.ReceiverLastName = ReceiverLastName;
        this.AccountBalance = AccountBalance;
        this.OperationDateTime = OperationDateTime;
    }
    
    
    
}
