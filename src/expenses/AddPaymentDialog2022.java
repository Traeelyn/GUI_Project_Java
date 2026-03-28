package expenses;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
/**
 * Add payment dialog, with VALIDATION
 * Generated using WindowBuilder
 * @author Kok CH
 *
 */
public class AddPaymentDialog2022 extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final ButtonGroup payemntTypeBG = new ButtonGroup();
	private JTextField amountTF;
	private JTextField nameTF;
	private JTextField expiryDateTF;
	private JTextField numberTF;
	private JRadioButton rdbtnCreditcardPayment;
	private Payment payment;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddPaymentDialog2022 dialog = new AddPaymentDialog2022();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddPaymentDialog2022() {
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 2, 0, 0));
		{
			JLabel lblAmount = new JLabel("Amount:");
			contentPanel.add(lblAmount);
		}
		{
			amountTF = new JTextField();
			contentPanel.add(amountTF);
			amountTF.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("Name:");
			contentPanel.add(lblNewLabel);
		}
		{
			nameTF = new JTextField();
			contentPanel.add(nameTF);
			nameTF.setColumns(10);
		}
		{
			JLabel lblExpiryDate = new JLabel("Expiry Date:");
			contentPanel.add(lblExpiryDate);
		}
		{
			expiryDateTF = new JTextField();
			contentPanel.add(expiryDateTF);
			expiryDateTF.setColumns(10);
		}
		{
			JLabel lblNumber = new JLabel("Number");
			contentPanel.add(lblNumber);
		}
		{
			numberTF = new JTextField();
			contentPanel.add(numberTF);
			numberTF.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String amountStr = amountTF.getText().trim();
						String name = nameTF.getText().trim();
						String expiryDate = expiryDateTF.getText().trim();
						String number = numberTF.getText().trim();

						if (!rdbtnCreditcardPayment.isSelected()) { // cash payment 
							if (amountStr.isEmpty()) {
								JOptionPane.showMessageDialog(AddPaymentDialog2022.this,
									"Please enter a numeric value!");
								return ;
							}	
							else {
								try {
									double amount = Double.parseDouble(amountStr);
									payment = new CashPayment(amount);
								} catch (NumberFormatException nfe) {
									JOptionPane.showMessageDialog(null, 
											"Invalid amount!");
										amountTF.setText("");
										amountTF.requestFocus();
										return ;									
								}
							}
						}  
						else { // for credit card payment
							if (amountStr.isEmpty() || name.isEmpty() || expiryDate.isEmpty() || 
								number.isEmpty()) {
								JOptionPane.showMessageDialog(AddPaymentDialog2022.this, 
									"Please enter text into all fields!");
								return ;
							}
							else {
								try {
									double amount = Double.parseDouble(amountStr);
									payment = new CreditCardPayment(amount, name, 
											expiryDate, number);
									
								} catch (NumberFormatException nfe) {
									JOptionPane.showMessageDialog(null, 
										"Invalid amount!");
									amountTF.setText("");
									amountTF.requestFocus();
									return ;
								}
							}
						} // end credit card
						
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.NORTH);
			{
				JRadioButton rdbtnCashPayment = new JRadioButton("Cash Payment");
				payemntTypeBG.add(rdbtnCashPayment);
				panel.add(rdbtnCashPayment);
			}
			{
				rdbtnCreditcardPayment = new JRadioButton("CreditCard Payment");
				rdbtnCreditcardPayment.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						if (rdbtnCreditcardPayment.isSelected()) {
							setTitle("Add CreditCard Payment");
							nameTF.setEnabled(true);
							expiryDateTF.setEnabled(true);
							numberTF.setEnabled(true);
						} else {
							setTitle("Add Cash Payment");
							nameTF.setEnabled(false);
							expiryDateTF.setEnabled(false);
							numberTF.setEnabled(false);
						}
					}
				});
				rdbtnCreditcardPayment.setSelected(true);
				payemntTypeBG.add(rdbtnCreditcardPayment);
				panel.add(rdbtnCreditcardPayment);
			}
		}
	}

	/**
	 * @return the payment
	 */
	public Payment getPayment() {
		return payment;
	}

	/**
	 * @param payment the payment to set
	 */
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	

}
