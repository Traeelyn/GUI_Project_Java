package expenses;
import javax.swing.table.AbstractTableModel;

public class PaymentTM extends AbstractTableModel {
	private Expenses expenses;
	private String[] title = {"No", "TYPE", "Amount", "Name", "Expired Date", "Number"};
	
	public PaymentTM(Expenses e) {
		setExpenses(e);
	}
	
	public void add(Payment p)
	{
		expenses.add(p);
		fireTableDataChanged();
	}
	
	public int getColumnCount()
	{
		 // return 6;
		return title.length;
	}
	
	public int getRowCount()
	{
		//return expenses.numOfCashPayment() + expenses.numOfCreditCardPayment();
		return expenses.getPayments().size();
	}
	
	public Object getValueAt(int row, int col)
	{
		Payment p = expenses.getPayments().get(row);
		switch (col)
		{
		case 0:
			return row + 1;
		case 1:
			if (p instanceof CashPayment)
				return "Cash";
			else
				return "CreditCard";
		case 2:
			return p.getAmount(); // return new Double(p.getAmount());
		case 3:
			if (p instanceof CashPayment)
				return "-";
			else
				return ((CreditCardPayment) p).getName();
		case 4:
			if (p instanceof CashPayment)
				return "N/A";
			else
				return ((CreditCardPayment) p).getExpireDate();
		case 5:
			if (p instanceof CashPayment)
				return "-";
			else
				return ((CreditCardPayment) p).getNumber();
		default:
			return "";
		}
	}
	
	// ADDED FOR EXTRA delete function
	public void remove(int row) {
		expenses.getPayments().remove(row);
		fireTableDataChanged();
	}
	
	public Payment get(int i)			
	{
		return expenses.getPayments().get(i);
	}
	
	public void setExpenses(Expenses e)
	{
		expenses = e;
		
	}
	
	public String getColumnName(int col)
	{
		return title[col];
		
		/*
		switch (col)
		{
		case 0:
			return "No";
		case 1:
			return "Type";
		case 2:
			return "Amount";
		case 3:
			return "Name";
		case 4:
			return "Expired Date";
		case 5:
			return "Number";
		default:
			return "";
		}
		*/
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
	    if (expenses.getPayments().isEmpty()) {
	        return Object.class;
	    }
	    return getValueAt(0, columnIndex).getClass();
	}
}
