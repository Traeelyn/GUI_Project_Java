package expenses;

public class CashPayment extends Payment{
	public CashPayment(double amount){
		super(amount);
	}
	
	public String toString(){
		return "Cash payment, " + super.toString();
	}
}
