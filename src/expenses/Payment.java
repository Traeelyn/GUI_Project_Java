package expenses;
import java.util.*;

public class Payment implements java.io.Serializable {
	private double amount;
	private ArrayList<Product> items;
	
	public void add(Product p)
	{
		getItems().add(p);
	}
	
	public void addSomeItems()
	{
		int num = (int) (Math.random() * 4); // 0,1,2,3
		switch (num)
		{
		case 0: add(new Product("IPad", 1));
				break;
		case 1: add(new Product("Eraser", 4));
				add(new Product("Pen", 1));
				add(new Product("Scissors", 5));
				break;
		case 2: add(new Product("Sharpener", 1));
				add(new Product("Notebook", 3));
				break;
		case 3: add(new Product("Pencil", 2));
				add(new Product("Newspaper", 4));
				break;
		}
	}
	
	public Payment(double amount){
		this.amount = amount;
		setItems(new ArrayList<Product>());
		addSomeItems();
	}
	
	/**
	 * @return the items
	 */
	public ArrayList<Product> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(ArrayList<Product> items) {
		this.items = items;
	}

	public double getAmount()
	{
		return amount;
	}
	
	public String toString(){
		return "amount paid is " + amount;
	}
}
