package expenses;

import java.util.ArrayList;
import java.io.Serializable;

public class Expenses implements Serializable
{
    // create instance variable of the existing class
    private Payment payment1;
    private Payment payment2;
    private Payment payment3;

    private Payment[] p;

    // attribute
    private ArrayList<Payment> payments;
    private String name;

    // methods
    public Expenses(String name)        //Expenses mye = new Expenses("mr.kok");
    {
        // this.name = name;
        setName(name);
        setPayments(   new ArrayList<Payment>()   );
    }

    // setter and getter methods go here
    public void setName(String name) { this.name = name; }
    public void setPayments(ArrayList<Payment>payments) {this.payments = payments ;}
    public String getName() { return name; }
    public ArrayList<Payment> getPayments(){return payments ;} 

    //no checking
    //public void add(Payment p) { getPayments().add(p); }

    //checking
    public boolean add(Payment p)
    {
        if (getPayments().contains(p))
            return false;
        getPayments().add(p);
        return true;
    }
    
    public boolean isEmpty()
    {
    	return getPayments().size() == 0;
    }
    
    public double total()
    {
    	double total = 0;
    	for(int i=0; i<getPayments().size(); i++)
    		total += getPayments().get(i).getAmount();
    	return total;
    }
    
    public int numOfCashPayment()
    {
    	int numCash = 0;
    	for (Payment p: getPayments())
    		if(p instanceof CashPayment)
    			numCash ++;
    	return numCash;
    }
    
    public int numOfCreditCardPayment()
    {
    	return getPayments().size() - numOfCashPayment();
    }
    
    public String findPayments(double amount)
    {
    	String result = "";
    	for(Payment p: getPayments())
    		if(p.getAmount() >= amount)
    			result += p + "\n";
    	return result;
    }
    
    public String toString()
    {
    	String payment = "";
    	for(int i=0; i<getPayments().size(); i++)
    	{
    		payment += getPayments().get(i) + "\n";
    	}
    	return payment;
    }
    
    public String displayCashPayment()
    {
    	String cash = "";
    	for(Payment p: getPayments())
    		if (p instanceof CashPayment)
    			cash += p.toString() + "\n";
    	return cash;    			
    }
    
    public String displayCreditCardPayment()
    {
    	String credit = "";
    	for(Payment p: getPayments())
    		if (p instanceof CashPayment)
    			credit += p.toString() + "\n";
    	return credit;    			
    }
}

