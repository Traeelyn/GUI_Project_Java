package expenses;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;


public class ExpensesGUI2 extends JFrame {
	private Expenses exp;
	private PaymentTM model;
	private JTable table;
	private AddCCJD dialog;
	private ListPayments listPaymentsDialog;
	
	private ProductTM pModel;
	private JTable pTable;
	
	private JButton addBtn, loadBtn, saveBtn, listBtn;
	
	final private JFileChooser fc;
	private File file;
	
	public ExpensesGUI2(Expenses e)
	{
		fc = new JFileChooser();
		exp = e;
		model = new PaymentTM(exp);
		table = new JTable(model);

		pModel = new ProductTM();
		pTable = new JTable(pModel);
		
		TableRowSorter<PaymentTM> sorter = new TableRowSorter<>(model);
		table.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
		
		sortKeys.add(new RowSorter.SortKey
				(0, SortOrder.ASCENDING));
		
		sortKeys.add(new RowSorter.SortKey
				(2, SortOrder.ASCENDING));

		sorter.setSortKeys(sortKeys);
		sorter.sort();
		sorter.setSortable(1, false);
		sorter.setSortable(3, false);
		sorter.setSortable(4, false);
		sorter.setSortable(5, false);
		
		
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lse)
			{
				int row = table.getSelectedRow();
				if (row != -1)
				{
					Payment p = model.get(row);
					pModel.setItems(p.getItems());
				}			
			}
		});

		
		dialog = new AddCCJD(this);
		
		addBtn = new JButton("Add");
		addBtn.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				dialog.pack();
				dialog.setVisible(true);
				Payment p = dialog.getPayment();
				if (p == null)
					JOptionPane.showMessageDialog(null, "Addition aborted!");
				else
				{
					model.add(p);
					model.fireTableDataChanged();
				}
			}
		});
		
		saveBtn = new JButton("Save");
		saveBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) {
				int returnVal = fc.showSaveDialog(ExpensesGUI2.this);
			    if (returnVal == JFileChooser.APPROVE_OPTION) 
		    	{
		    		file = fc.getSelectedFile();
		        	saveToFile();
		    	}
			}
		});
		
		loadBtn = new JButton("Load");
		loadBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) {
				int returnVal = fc.showOpenDialog(ExpensesGUI2.this);
			    if (returnVal == JFileChooser.APPROVE_OPTION) 
		    	{
		    		file = fc.getSelectedFile();
		        	loadFromFile();
		    	}
			}
		});
		
		listBtn = new JButton("List Payments");
		listBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				listPaymentsDialog = new ListPayments(exp);
				
				listPaymentsDialog.setVisible(true);
				
				//System.out.println("Suppose to list all payments in a dialog");
			}
		});
		
		
		JPanel btnP = new JPanel();
		btnP.add(addBtn);
		btnP.add(saveBtn);
		btnP.add(loadBtn);
		btnP.add(listBtn);
		
		JPanel btmP = new JPanel(new BorderLayout());
		btmP.add(new JLabel("List of Products:"), "North");
		btmP.add(new JScrollPane(pTable), "Center");
		
		JPanel bothT = new JPanel(new GridLayout(2,1));
		bothT.add(new JScrollPane(table));
		bothT.add(btmP);

		
		getContentPane().add(bothT, "Center");
		
		//getContentPane().add(new JScrollPane(table), "Center");
		getContentPane().add(new JLabel("List of payments:"), "North");
		getContentPane().add(btnP, "South"); //addBtn, "South");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void saveToFile() 
	{
		try (FileOutputStream fos = new FileOutputStream(file);
				ObjectOutputStream oos = new ObjectOutputStream(fos);)
		{
			oos.writeObject(exp);			
		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error in writing");
			e.printStackTrace();
		}
	}
	
	public void loadFromFile()
	{
		try
		{
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			exp = (Expenses) ois.readObject();
			
			model.setExpenses(exp);
			model.fireTableDataChanged();
		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error in reading");
		}
	}
	
	public static void main(String[] args) {
		Expenses e = new Expenses("James Bond");
		ExpensesGUI2 gui = new ExpensesGUI2(e);
		gui.setTitle("Expenses for " + e.getName());
		gui.setSize(400, 400);
		gui.setVisible(true);
	}

}
