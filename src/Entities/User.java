package Entities;

/**
 * @author mprzybyla
 */
public class User extends MainEntity
{
    
    public String FirstName;
    public String LastName;
    protected String Password;
    
    
    
    public User(String _firstName, String _lastName, String _password)
    {
        this.FirstName=_firstName;
        this.LastName=_lastName;
        this.Password=_password;
    }
    public User(){}
    
    public boolean passwordCheck(String _password)
    {
        if (_password.equals(this.Password))
        {
            return true;
        }
        return false;
    }
}
