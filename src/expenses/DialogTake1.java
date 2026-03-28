package expenses;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Button;
import java.awt.BorderLayout;

public class DialogTake1 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DialogTake1 frame = new DialogTake1();
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
	public DialogTake1() {
		
	
	
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnNewButton = new JButton("Add");
		getContentPane().add(btnNewButton, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("New label");
		getContentPane().add(lblNewLabel, BorderLayout.CENTER);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddPaymentDialog2022 pD = new AddPaymentDialog2022(); 
				pD.setVisible(true);
				//System.out.println("I'm BACK.....");
				
				Payment p = pD.getPayment(); 
				if(p != null) {
					lblNewLabel.setText("Payment created: \n" +  p);
				}
				
				else {
					JOptionPane.showMessageDialog(DialogTake1.this, "Aborted");
				}
			
			}
		});
		contentPane.add(lblNewLabel); 
		contentPane.add(btnNewButton);
		
		
		
	}

}
