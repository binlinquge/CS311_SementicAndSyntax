import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Welcome {

	private JFrame frmWelcome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Welcome window = new Welcome();
					window.frmWelcome.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Welcome() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWelcome = 
				new JFrame();
		frmWelcome.setTitle("Welcome");
		frmWelcome.setBounds(100, 100, 641, 440);
		frmWelcome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWelcome.getContentPane().setLayout(null);
		
		JLabel lblWelcomeToC = new JLabel("CS311 Project");
		lblWelcomeToC.setFont(new Font("Apple Braille", Font.PLAIN, 30));
		lblWelcomeToC.setBounds(294, 79, 253, 41);
		frmWelcome.getContentPane().add(lblWelcomeToC);
		
		JLabel lblNewLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(0, 40, 283, 256);
		frmWelcome.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("");
		Image img1 = new ImageIcon(this.getClass().getResource("/bt00.png")).getImage();
		btnNewButton.setIcon(new ImageIcon(img1));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Com comp = new Com();
				comp.setVisible(true);
				frmWelcome.dispose();
			}
		});
		btnNewButton.setBounds(364, 186, 99, 70);
		frmWelcome.getContentPane().add(btnNewButton);
		
	}
}
