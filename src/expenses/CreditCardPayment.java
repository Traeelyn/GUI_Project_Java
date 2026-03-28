package expenses;

public class CreditCardPayment extends Payment{
	private String name;
	private String expireDate;
	private String number;
	
	public CreditCardPayment(double amount, String name, String expireDate, String number)
	{
		super(amount);
		this.name = name;
		this.expireDate = expireDate;
		this.number = number;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @param expireDate the expireDate to set
	 */
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public String getExpireDate()
	{
		return expireDate;
	}
	
	public String toString()
	{
		return "Using card (" + name + " [" + number + "] exp: " + expireDate + 
				") for credit-card payment, " + super.toString();
	}
}
