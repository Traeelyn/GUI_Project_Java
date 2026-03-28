package expenses;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class ExpensesGUI extends JFrame {
	private Expenses exp;
	private PaymentTM model;
	private JTable table;
	private AddCCJD dialog;
	
	private ProductTM pModel;
	private JTable pTable;
	
	private JButton addBtn;
	
	public ExpensesGUI(Expenses e)
	{
		exp = e;
		model = new PaymentTM(exp);
		table = new JTable(model);

		pModel = new ProductTM();
		pTable = new JTable(pModel);
		
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
				}
			}
		});
		
		JPanel btmP = new JPanel(new BorderLayout());
		btmP.add(new JLabel("List of Products:"), "North");
		btmP.add(new JScrollPane(pTable), "Center");
		
		JPanel bothT = new JPanel(new GridLayout(2,1));
		bothT.add(new JScrollPane(table));
		bothT.add(btmP);

		
		getContentPane().add(bothT, "Center");
		
		//getContentPane().add(new JScrollPane(table), "Center");
		getContentPane().add(new JLabel("List of payments:"), "North");
		getContentPane().add(addBtn, "South");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		Expenses e = new Expenses("James Bond");
		ExpensesGUI gui = new ExpensesGUI(e);
		gui.setTitle("Expenses for " + e.getName());
		gui.setSize(400, 400);
		gui.setVisible(true);
	}

}
