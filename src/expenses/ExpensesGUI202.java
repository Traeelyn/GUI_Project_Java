//package expenses;
//
//import java.awt.EventQueue;
//import java.io.ObjectInputStream;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JTextField;
//import java.awt.BorderLayout;
//
//public class ExpensesGUI202 extends JFrame {
//
//	private static final long serialVersionUID = 1L;
//	private JPanel contentPane;
//	private JTextField textField;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ExpensesGUI202 frame = new ExpensesGUI202();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the frame.
//	 */
//	public ExpensesGUI202() {
//		
//		JPanel panel = new JPanel();
//		getContentPane().add(panel, BorderLayout.CENTER);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 450, 300);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//
//		setContentPane(contentPane);
//		
//		textField = new JTextField();
//		contentPane.add(textField);
//		textField.setColumns(10);
//		
//		JLabel lblNewLabel = new JLabel("List of payments");
//		contentPane.add(lblNewLabel);
//		
////		Jmenu mnNewMenu = new Jmenu("File");
////		menuBar.add(mnNewMenuItem)
////		
////		
////		JmenuItem mntmNewMenuItem_1 = new JmenuItem("open"); 
////		
////		JMenuItem mntmNewMenuItem_1 = new JmenuItem("Save"); 
////		mntmNewMenuItem_1.addActionListener(new ActionListener() {
////			public void actionPerformed(ActionEvent e) {
////				int returnValue = fileChooser.showSaveDialog(MyExpensesGUI.this); 
////				if(returnValue == JFileChooser.APPROVE_OPTION) {
////					File file = fileChooser.getSelectedFile(); 
////					try (FileInputStream fis = new FileInputStream(file);
////							ObjectInputStream ois = new ObjectInputStream(fis);) {
////						expenses = (Expenses) ois.readObject();
////						paymentTM.setExpenses(expenses); 
////						PaymentTM.fireTableDataChanged(); 
////					} catch (Exception exp) {
////						JOptionPane.showMessageDialog(null, exp.getMessage());
////					}
////				}
////			}
////		})
////		
//		
//		
//		
//		
//		
//	}
//
//}
