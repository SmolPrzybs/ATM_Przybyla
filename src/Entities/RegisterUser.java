package Entities;

/**
 * @author mprzybyla
 */
public class RegisterUser extends User
{
    private String ConfirmPassword;
    public boolean isExist=false;
    public boolean hasRegistered=false;
    
    public RegisterUser(String firstName, String lastName, String password, String confirmPassword)
    {
        super(firstName, lastName, password);
        this.ConfirmPassword=confirmPassword;
    }
    
    public boolean ConfirmPasswordCheck()
    {
        if(this.Password.equals(this.ConfirmPassword))
        {
            return true;
        }
        return false;
    }
    
    public String getPassword()
    {
        return this.Password;
    }
}
