package expenses;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

/**
 * Add payment dialog, but without VALIDATION
 * Hand written code
 * @author Kok CH
 *
 */
public class AddCCJD extends JDialog {
	private JLabel amountL, nameL, expDateL, numberL;
	private JTextField amountTF, nameTF, expDateTF, numberTF;
	private JButton okBtn, resetBtn, cancelBtn;
	
	private Payment pay;
	private JPanel panel;
	private JRadioButton rdbtnCashPayment;
	private JRadioButton rdbtnCreditCardPayment;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	public AddCCJD(JFrame frame)
	{
		super(frame, true);
		setTitle("Add Cash Payment");
		setBounds(100, 100, 200, 300);
		
		// set up the components
		JPanel p = new JPanel(new GridLayout(4,2,8,8));
		amountL = new JLabel("Amount:");
		nameL = new JLabel("Name:");
		expDateL = new JLabel("Expired Date:");
		numberL = new JLabel("Number");
		
		amountTF = new JTextField(10);
		nameTF = new JTextField(10);
		expDateTF = new JTextField(10);
		numberTF = new JTextField(10);
		
		p.add(amountL);		p.add(amountTF);
		p.add(nameL);		p.add(nameTF);
		p.add(expDateL);	p.add(expDateTF);
		p.add(numberL);		p.add(numberTF);
		
		okBtn = new JButton("OK");
		resetBtn = new JButton("Reset");
		cancelBtn = new JButton("Cancel");
		
		setChange(true);
		
		okBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				String amountStr = amountTF.getText().trim();
				String name = nameTF.getText().trim();
				String expDate = expDateTF.getText().trim();
				String number = numberTF.getText().trim();
				
				if (rdbtnCashPayment.isSelected()) {
					pay = new CashPayment(Double.parseDouble(amountStr));
				}
				else {
					pay = new CreditCardPayment(Double.parseDouble(amountStr),
						name, expDate, number);
				}
				
				amountTF.setText("");
				nameTF.setText("");
				expDateTF.setText("");
				numberTF.setText("");
				
				setVisible(false);
			}
		});
		
		cancelBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				amountTF.setText("");
				nameTF.setText("");
				expDateTF.setText("");
				numberTF.setText("");
				
				pay = null;
				
				setVisible(false);				
			}
		});

		resetBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				amountTF.setText("");
				nameTF.setText("");
				expDateTF.setText("");
				numberTF.setText("");				
			}
		});
		
		JPanel btnP = new JPanel();
		btnP.add(okBtn);
		btnP.add(resetBtn);
		btnP.add(cancelBtn);
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		rdbtnCashPayment = new JRadioButton("Cash Payment");
		buttonGroup.add(rdbtnCashPayment);
		rdbtnCashPayment.setSelected(true);
		rdbtnCashPayment.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (rdbtnCashPayment.isSelected()) {
					setTitle("Add Cash Payment");
					setChange(true);
				} else {
					setTitle("Add Credit Card Payment");
					setChange(false);
				}
			}
		});
		panel.add(rdbtnCashPayment);
		
		rdbtnCreditCardPayment = new JRadioButton("Credit Card Payment");
		buttonGroup.add(rdbtnCreditCardPayment);
		panel.add(rdbtnCreditCardPayment);
		getContentPane().add(p, "Center");
		getContentPane().add(btnP, "South");
		
	}
	
	public void setChange(boolean bool) {
		nameTF.setEnabled(!bool);
		expDateTF.setEnabled(!bool);
		numberTF.setEnabled(!bool);
	}
	
	public Payment getPayment()
	{
		return pay;
	}
	
	public static void main(String[] args)
	{
		JDialog j = new AddCCJD(new JFrame());
		j.pack();
		j.setVisible(true);
		System.out.println(((AddCCJD) j).getPayment());
	}
	
}
