package expenses;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableRowSorter;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class ExpensesGUI2022 extends JFrame {
	private Expenses expenses;
	private PaymentTM paymentTM;
	private JPanel contentPane;
	
	private JTable paymentTable;
	private JFileChooser fileChooser;
	
	private AddPaymentDialog2022 addDialog;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExpensesGUI2022 frame = new ExpensesGUI2022();
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
	public ExpensesGUI2022() {
		expenses = new Expenses("James Bond");
		paymentTM = new PaymentTM(expenses);
		fileChooser = new JFileChooser();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnVal = fileChooser.showOpenDialog(ExpensesGUI2022.this);
			    if (returnVal == JFileChooser.APPROVE_OPTION) 
		    	{
		    		File file = fileChooser.getSelectedFile();
		    		try (
		    			FileInputStream fis = new FileInputStream(file);
		    			ObjectInputStream ois = new ObjectInputStream(fis);) {
		    			expenses = (Expenses) ois.readObject();
		    			
		    			paymentTM.setExpenses(expenses);
		    			paymentTM.fireTableDataChanged();
		    		} catch (Exception exception)
		    		{
		    			JOptionPane.showMessageDialog(null, "Error in reading");
		    		}
		    	}
			}
		});
		mnFile.add(mntmOpen);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnVal = fileChooser.showSaveDialog(ExpensesGUI2022.this);
			    if (returnVal == JFileChooser.APPROVE_OPTION) 
		    	{
		    		File file = fileChooser.getSelectedFile();
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
		mnFile.add(mntmSave);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblListOfPayments = new JLabel("List of Payments:");
		contentPane.add(lblListOfPayments, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDialog = new AddPaymentDialog2022();
				addDialog.setVisible(true); 
				Payment p = addDialog.getPayment();
				//System.out.println("Payment created: " + p);
				paymentTM.add(p);
				//paymentTM.fireTableDataChanged();
			}
		});
		panel.add(btnAdd);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		paymentTable = new JTable(paymentTM);
		scrollPane.setViewportView(paymentTable);
		
		TableRowSorter<PaymentTM> sorter = new TableRowSorter<>(paymentTM);
		paymentTable.setRowSorter(sorter);
		

		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
		
		sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
		
		sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
		sortKeys.add(new RowSorter.SortKey(3, SortOrder.ASCENDING));

		sorter.setSortKeys(sortKeys);
		sorter.sort();
		sorter.setSortable(1, false);
		//sorter.setSortable(3, false);
		//sorter.setSortable(4, false);
		//sorter.setSortable(5, false);
	}

}
