package expenses;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Add payment dialog, but without VALIDATION
 * Generated using WindowBuilder
 * @author Kok CH
 *
 */
public class AddPaymentDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField tfAmount;
	private JTextField tfName;
	private JTextField tfExpiryDate;
	private JTextField tfNumber;
	private JRadioButton rdbtnCash;
	
	private Payment payment;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddPaymentDialog dialog = new AddPaymentDialog(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddPaymentDialog(JFrame parent) {
		super(parent, true); // true - modal dialog
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 2, 0, 4));
		{
			JLabel lblAmount = new JLabel("Amount:");
			contentPanel.add(lblAmount);
		}
		{
			tfAmount = new JTextField();
			contentPanel.add(tfAmount);
			tfAmount.setColumns(10);
		}
		{
			JLabel lblName = new JLabel("Name:");
			contentPanel.add(lblName);
		}
		{
			tfName = new JTextField();
			contentPanel.add(tfName);
			tfName.setColumns(10);
		}
		{
			JLabel lblExpiryDate = new JLabel("Expiry Date:");
			contentPanel.add(lblExpiryDate);
		}
		{
			tfExpiryDate = new JTextField();
			contentPanel.add(tfExpiryDate);
			tfExpiryDate.setColumns(10);
		}
		{
			JLabel lblNumber = new JLabel("Number:");
			contentPanel.add(lblNumber);
		}
		{
			tfNumber = new JTextField();
			contentPanel.add(tfNumber);
			tfNumber.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String amountStr = tfAmount.getText().trim();
						String name = tfName.getText().trim();
						String expiryDate = tfExpiryDate.getText().trim();
						String number = tfNumber.getText().trim();
						
						// read in other fields, do validation check
						
						double amount = Double.parseDouble(amountStr);
						if (rdbtnCash.isSelected())
							payment = new CashPayment(amount);
						else
							payment = new CreditCardPayment(amount, name, expiryDate, number);
						tfAmount.setText("");
						
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						payment = null;
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.NORTH);
			{
				 rdbtnCash = new JRadioButton("Cash Payment");
				buttonGroup.add(rdbtnCash);
				rdbtnCash.setSelected(true);
				
				panel.add(rdbtnCash);
			}
			{
				JRadioButton rdbtnCreditCardPayment = new JRadioButton("Credit Card Payment");
				rdbtnCreditCardPayment.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						if (rdbtnCreditCardPayment.isSelected()) {
							setTitle("Add Credit Card Payemnt");
							tfName.setEnabled(true);
							tfExpiryDate.setEnabled(true);
							tfNumber.setEnabled(true);
						} else {
							setTitle("Add Cash Payemnt");
							tfName.setEnabled(false);
							tfExpiryDate.setEnabled(false);
							tfNumber.setEnabled(false);
						}
					}
				});
				buttonGroup.add(rdbtnCreditCardPayment);
				
				//rdbtnCreditCardPayment.setSelected(true);
				panel.add(rdbtnCreditCardPayment);
			}
		}
	}

	public Payment getPayment() {
		return payment;
	}
	
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
}
