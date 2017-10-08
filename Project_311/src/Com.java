import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.TextArea;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyledEditorKit;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;

public class Com extends JFrame {

	private JPanel contentPane;
	private SimpleAttributeSet attr = new SimpleAttributeSet();    
	public String code = new String();
	public Lexica lex = new Lexica();
	public int ct = 0;
	public Syntax stx = new Syntax();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Com frame = new Com();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void decorateKeyWords(JTextPane tp) {  
		//int cur_index = 0;
		int index = 0,index2 = 0;
		int counter;
		int com_count = 0;
		int result_info=-1;
		int[] arr_err = new int[1000];
		int err_count = 0,cur_err=0;;
		  String text = code;  
		  index2 = code.length();
		  StyledDocument doc = tp.getStyledDocument();  
		  SimpleAttributeSet attribute1 = new SimpleAttributeSet();
		  index = 0;
		  StyleConstants.setForeground(attribute1, Color.black);  
		  doc.setCharacterAttributes(index, index2, attribute1, true);  
		  for (counter=0;counter<=lex.lex_num;counter++)
		  {
			  index = text.indexOf(lex.lexeme[counter],index);
			  switch(lex.token[counter])
			  {
			  case 33:
			  case 34:
			  case 99:
			  case 98:
			  case 50:
			  case 51:
				  if (lex.token[counter] == 33 && lex.lexeme[counter].equals("for"))
				  {
					  result_info = stx.check_for(lex.token, counter);
					  if (result_info > -1)
					  {
						  arr_err[err_count] = result_info;
						  err_count++;
					  }
				  }else
					  if (lex.token[counter] == 33 && lex.lexeme[counter].equals("while"))
					  {
						  result_info = stx.check_while(lex.token, counter);
						  if (result_info > -1)
						  {
							  arr_err[err_count] = result_info;
							  err_count++;
						  }
					  }else
						  if (lex.token[counter] == 33 && lex.lexeme[counter].equals("switch"))
						  {
							  result_info = stx.check_switch(lex.lexeme,lex.token, counter);
							  if (result_info > -1)
							  {
								  arr_err[err_count] = result_info;
								  err_count++;
							  }
						  }else if (lex.token[counter] == 33 && lex.lexeme[counter].equals("if"))
						  {
							  result_info = stx.check_if(lex.lexeme,lex.token, counter,0);
							  if (result_info > -1)
							  {
								  arr_err[err_count] = result_info;
								  err_count++;
							  }
						  }else if (lex.token[counter] == 33 && (lex.lexeme[counter].equals("scanf") || lex.lexeme[counter].equals("printf")))
						  {
							  result_info = stx.check_scanf(lex.token, counter);
							  if (result_info > -1)
							  {
								  arr_err[err_count] = result_info;
								  err_count++;
							  }
						  }
							  
				  StyleConstants.setForeground(attribute1, Color.blue);  
				  doc.setCharacterAttributes(index, lex.lexeme[counter].length(), attribute1, true);  
				  break;
			  case 40:
			  case 41:
				  StyleConstants.setForeground(attribute1, Color.pink);  
				  doc.setCharacterAttributes(index, lex.lexeme[counter].length(), attribute1, true);  
				  break;
			  case 61:
				  StyleConstants.setForeground(attribute1, Color.orange);  
				  doc.setCharacterAttributes(index, lex.lexeme[counter].length(), attribute1, true);  
				  break;
			  case 30:
			  case 32:				  
				  StyleConstants.setForeground(attribute1, Color.green);  
			      doc.setCharacterAttributes(index, lex.lexeme[counter].length(), attribute1, true);  
			      break;
			  case 31:
				  com_count++;
				  StyleConstants.setForeground(attribute1, Color.green);  
				  doc.setCharacterAttributes(index, lex.lexeme[counter].length(), attribute1, true);  
				  break;
			  case 100:
				  StyleConstants.setForeground(attribute1, Color.red);  
				  doc.setCharacterAttributes(index, lex.lexeme[counter].length(), attribute1, true);  
				  break;
			  case 28:
			  case 29:
			  case 4:
			  case 5:
			  case 9:
			  case 3:
			  case 2:
				  StyleConstants.setForeground(attribute1, Color.gray);  
				  doc.setCharacterAttributes(index, lex.lexeme[counter].length(), attribute1, true);  
				  break;
			  case 10:
			  case 11:
			  case 12:
			  case 13:
			  case 14:
			  case 15:
				  StyleConstants.setForeground(attribute1, Color.black);  
				  doc.setCharacterAttributes(index, lex.lexeme[counter].length(), attribute1, true);  
				  break;
			  case 35:
			  case 36:
			  case 37:
			  case 38:
			  case 39:
			  case 42:
			  case 43:
			  case 44:
			  case 45:
				  StyleConstants.setForeground(attribute1, Color.darkGray);  
				  doc.setCharacterAttributes(index, lex.lexeme[counter].length(), attribute1, true);  
				  break;
			  case 60:
				  StyleConstants.setForeground(attribute1, Color.yellow);  
				  doc.setCharacterAttributes(index, lex.lexeme[counter].length(), attribute1, true);  
				  break;
			  }
			  if (counter == arr_err[cur_err] && err_count!=0)
			  {
				  StyleConstants.setForeground(attribute1, Color.red);  
				  doc.setCharacterAttributes(index, lex.lexeme[counter].length(), attribute1, true);  
				  cur_err++;
			  }
		      index++;
		  } 
		  index = 0;
		  for (counter=0;counter<com_count;counter++)
		  {
		  index = text.indexOf("/*",index);
		  index2 = code.length();
		  index2 = text.indexOf("*/", index);
		  StyleConstants.setForeground(attribute1, Color.green);  
		  doc.setCharacterAttributes(index, index2-index, attribute1, true);  
		  index++;
		  }
		}  
	/**
	 * Create the frame.
	 */
	public Com() {
		setTitle("Syntax Analyser");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 668, 856);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(21, 532, 600, 232);
		contentPane.add(scrollPane_1);
		
		JTextPane textPane = new JTextPane();
		scrollPane_1.setViewportView(textPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 31, 461, 457);
		contentPane.add(scrollPane);
		
		JTextPane txtrinclude = new JTextPane();
		scrollPane.setViewportView(txtrinclude);
		
		JButton btnOpen = new JButton("Open file");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Open of = new Open();
				try{
					of.PickMe();
				}catch(Exception e1){
					e1.printStackTrace();
				}
				txtrinclude.setText(of.sb.toString());
				//code = txtrinclude.getText();
				//txtrinclude.setText(code);
			}
		});
		btnOpen.setBounds(502, 67, 117, 29);
		contentPane.add(btnOpen);
		
		JButton btnSave = new JButton("Save file");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fs = new JFileChooser();
				fs.setDialogTitle("Save a file");
				int result = fs.showSaveDialog(null);
				if(result == JFileChooser.APPROVE_OPTION){
					String content = txtrinclude.getText();
					File fi = fs.getSelectedFile();
					try{
						FileWriter fw = new FileWriter(fi.getPath());
						fw.write(content);
						fw.flush();
						fw.close();
					}catch(Exception e3){
						JOptionPane.showMessageDialog(null, e3.getMessage());
					}
				}
			}
		});
		btnSave.setBounds(502, 127, 117, 29);
		contentPane.add(btnSave);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Lexica lex = new Lexica();
				String lexeme = new String("");
				code = txtrinclude.getText();
				lex.get_lex(code);
				//lexeme = "";
				ct++;
				for (int counter = 0;counter<=lex.lex_num;counter++)
				{
					lexeme= lexeme + lex.lexeme[counter]+"\t\t"+lex.token[counter]+'\n';
				}
				//txtrinclude.setText(code);
				decorateKeyWords(txtrinclude);
				textPane.setText(lexeme);
				//System.out.println(stx.syntax(lex.lexeme, lex.token, lex.lex_num));
			}
		});
		Image img1 = new ImageIcon(this.getClass().getResource("/welcome03.png")).getImage();
		button.setIcon(new ImageIcon(img1));
		button.setBounds(517, 205, 80, 76);
		contentPane.add(button);
		
		JLabel lblController = new JLabel("Lexemes");
		lblController.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblController.setBounds(38, 492, 107, 26);
		contentPane.add(lblController);
		
		JLabel lblCode = new JLabel("Code");
		lblCode.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblCode.setBounds(38, 0, 85, 29);
		contentPane.add(lblCode);

		
	}
}
