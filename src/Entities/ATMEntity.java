package Entities;

/**
 * @author mprzybyla
 */
public class ATMEntity extends MainEntity
{
    
    public int PLN10;
    public int PLN20;
    public int PLN50;
    public int PLN100;
    public int PLN200;
    public int PLN500;
    public int TotalAvailableCash;
    
    public ATMEntity(int pln10, int pln20, int pln50, int pln100, int pln200, int pln500)
    {
        this.PLN10=pln10;
        this.PLN20=pln20;
        this.PLN50=pln50;
        this.PLN100=pln100;
        this.PLN200=pln200;
        this.PLN500=pln500;
        
        int sum = (pln10 * 10) + (pln20*20) + (pln50*50) + (pln100*100) + (pln200 * 200) + (pln500 * 500);
        this.TotalAvailableCash=sum;
               
    }
    
    public ATMEntity(ATMEntity entity)
    {
        this.Id=entity.Id;
        this.PLN10=entity.PLN10;
        this.PLN20=entity.PLN20;
        this.PLN50=entity.PLN50;
        this.PLN100=entity.PLN100;
        this.PLN200=entity.PLN200;
        this.PLN500=entity.PLN500;
        this.TotalAvailableCash=entity.TotalAvailableCash;
    }
    
    public ATMEntity()
    {
        this.PLN10=0;
        this.PLN20=0;
        this.PLN50=0;
        this.PLN100=0;
        this.PLN200=0;
        this.PLN500=0;
        this.TotalAvailableCash=0;
    }
    
    public void refresh()
    {
        this.TotalAvailableCash=(this.PLN10*10)+(this.PLN20*20)+(this.PLN50*50)+(this.PLN100*100)+(this.PLN200*200)+(this.PLN500*500);
    }
    
    
}
