package expenses;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public class ProductTM extends AbstractTableModel {
	private ArrayList<Product> items;
	
	public int getColumnCount()
	{
		return 2;
	}
	
	public int getRowCount()
	{
		return getItems().size();
	}
	
	public Object getValueAt(int row, int col)
	{
		Product p = getItems().get(row);
		switch (col)
		{
		case 0:
			return p.getName();
		case 1:
			return p.getQty();
		default:
			return "";
		}
	}
	
	public String getColumnName(int col)
	{
		switch (col)
		{
		case 0: return "Name";
		case 1: return "Quantity";
		default: return "";
		}
	}
	public ProductTM()
	{
		setItems(new ArrayList<Product>());
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
		fireTableDataChanged();
	}
}
