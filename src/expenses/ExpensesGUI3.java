package expenses;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableRowSorter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.RowSorter;
import javax.swing.SortOrder;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class ExpensesGUI3 extends JFrame {

	private JPanel contentPane;
	
	private Expenses expenses;
	private AddPaymentDialog apDialog;
	private JScrollPane scrollPane;
	private JTable pTable;
	private PaymentTM pModel;
	private JLabel lblListOfPayments;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmOpen;
	private JMenuItem mntmSaave;
	private JFileChooser fc;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Expenses expenses = new Expenses("James");
					ExpensesGUI3 frame = new ExpensesGUI3();//expenses);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ExpensesGUI3() {
		fc = new JFileChooser();
		expenses = new Expenses("James");

		apDialog = new AddPaymentDialog(this);
		pModel = new PaymentTM(expenses);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int returnVal = fc.showOpenDialog(ExpensesGUI3.this);
			    if (returnVal == JFileChooser.APPROVE_OPTION) 
		    	{
		    		File file = fc.getSelectedFile();
		    		try (
		    			FileInputStream fis = new FileInputStream(file);
		    			ObjectInputStream ois = new ObjectInputStream(fis);) {
		    			expenses = (Expenses) ois.readObject();
		    			
		    			pModel.setExpenses(expenses);
		    			pModel.fireTableDataChanged();
		    		} catch (Exception exception)
		    		{
		    			JOptionPane.showMessageDialog(null, "Error in reading");
		    		}
		    	}
			}
		});
		mnFile.add(mntmOpen);
		
		mntmSaave = new JMenuItem("Save");
		mntmSaave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showSaveDialog(ExpensesGUI3.this);
			    if (returnVal == JFileChooser.APPROVE_OPTION) 
		    	{
		    		File file = fc.getSelectedFile();
		    		try (FileOutputStream fos = new FileOutputStream(file);
		    				ObjectOutputStream oos = new ObjectOutputStream(fos);)
		    		{
		    			oos.writeObject(expenses);			
		    		} catch (Exception exp)
		    		{
		    			JOptionPane.showMessageDialog(null, "Error in writing");
		    			exp.printStackTrace();
		    		}
		    	}
			}
		});
		mnFile.add(mntmSaave);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				apDialog.pack(); // make compact size
				apDialog.setVisible(true); // make visible
				Payment p = apDialog.getPayment();
				if (p == null)
					JOptionPane.showMessageDialog(ExpensesGUI3.this, "Addition Aborted");
				else {
					pModel.add(p);
					pModel.fireTableDataChanged();
				}
					
			}
		});
		panel.add(btnAdd);

		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);		
		pTable = new JTable(pModel);
		scrollPane.setViewportView(pTable);		
		
		TableRowSorter<PaymentTM> sorter = new TableRowSorter<>(pModel);
		pTable.setRowSorter(sorter);
		
		lblListOfPayments = new JLabel("List of Payments:");
		contentPane.add(lblListOfPayments, BorderLayout.NORTH);
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
	}

}
