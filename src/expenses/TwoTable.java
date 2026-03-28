package expenses;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class TwoTable extends JFrame {
	private JPanel contentPane;
	private JTable paymentTable;
	private JTable productTable;
	private Expenses expenses;
	private PaymentTM paymentModel;
	private ProductTM productModel;
	
	private AddCCJD accjd;
	private JFileChooser fileChooser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TwoTable frame = new TwoTable();
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
	public TwoTable() {
		expenses = new Expenses("James Bond");
		setTitle("Expenses for " + expenses.getName());
		paymentModel = new PaymentTM(expenses);
		productModel = new ProductTM();
		
		fileChooser = new JFileChooser();
		accjd = new AddCCJD(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int option = fileChooser.showOpenDialog(TwoTable.this);
				if(option == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					try (ObjectInputStream ois = new ObjectInputStream(
							new FileInputStream(file));)
					{
						
						expenses = (Expenses) ois.readObject();
						paymentModel.setExpenses(expenses);
						paymentModel.fireTableDataChanged();
						
						JOptionPane.showMessageDialog(TwoTable.this, 
							"File successfully loaded", "File loader", 1);	
					}
					catch (IOException ioe)
					{
						ioe.printStackTrace();
					}
					catch (ClassNotFoundException cnfe)
					{
						System.out.println(cnfe.getMessage());
						cnfe.printStackTrace();
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
				}
			}
		});
		mnFile.add(mntmOpen);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = fileChooser.showSaveDialog(TwoTable.this);
				File file = fileChooser.getSelectedFile();
				if(option == JFileChooser.APPROVE_OPTION) {
					try (ObjectOutputStream oos = new ObjectOutputStream(
							new FileOutputStream(file));) {
						
						oos.writeObject(expenses);
						oos.flush();
						JOptionPane.showMessageDialog(TwoTable.this, 
							"File successfully saved", "File loader", 1);
					}
					catch (IOException ioe)
					{
						ioe.printStackTrace();
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
				}
				//else {
				//	JOptionPane.showMessageDialog(TwoTable.this, 
				//			"File save aborted", "File loader", 1);
				//}
			}// actionPerformed()
		});
		mnFile.add(mntmSave);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		JMenuItem mntmAbount = new JMenuItem("Abount");
		mntmAbount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JOptionPane.showMessageDialog(TwoTable.this, 
					"A progrm created by James Bond\nUpdated version 1.0");
			}
		});
		mnHelp.add(mntmAbount);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 10));
		
		JPanel topPanel = new JPanel();
		topPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(topPanel);
		topPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblListOfPayments = new JLabel("List of Payments");
		topPanel.add(lblListOfPayments, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		topPanel.add(scrollPane, BorderLayout.CENTER);
		
		paymentTable = new JTable();
		
		TableRowSorter<PaymentTM> sorter = new TableRowSorter<>(paymentModel);
		paymentTable.setRowSorter(sorter);
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
		
		paymentTable.setModel(paymentModel);
		scrollPane.setViewportView(paymentTable);
		
		JPanel btnPanel = new JPanel();
		topPanel.add(btnPanel, BorderLayout.SOUTH);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accjd.setVisible(true); // will block
				Payment p = accjd.getPayment();
				if (p == null)
					JOptionPane.showMessageDialog(TwoTable.this, 
						"Addition Cancelled");
				else {
					paymentModel.add(p);
				}
			}
		});
		btnPanel.add(btnAdd);

		paymentTable.getSelectionModel().addListSelectionListener(
			new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lse)
			{
				int row = paymentTable.getSelectedRow();
				if (row != -1) // paymentTable.isRowSelected(row);
				{
					Payment p = paymentModel.get(row);
					productModel.setItems(p.getItems());
				}			
			}
		});
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = paymentTable.getSelectedRow();
				if (paymentTable.isRowSelected(row)) {
					paymentModel.remove(row);
					productModel.setItems(new ArrayList<>());
				}
				else
					JOptionPane.showMessageDialog(null,
						"Please select a row to delete");
			}
		});
		btnPanel.add(btnDelete);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(bottomPanel);
		bottomPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblListOfProducts = new JLabel("List of Products");
		bottomPanel.add(lblListOfProducts, BorderLayout.NORTH);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		bottomPanel.add(scrollPane_1, BorderLayout.CENTER);
		
		productTable = new JTable();
		productTable.setModel(productModel);
		scrollPane_1.setViewportView(productTable);
		
		JPanel panel = new JPanel();
		bottomPanel.add(panel, BorderLayout.SOUTH);
		
		JButton btnDelete_1 = new JButton("Delete");
		panel.add(btnDelete_1);
	}
}
