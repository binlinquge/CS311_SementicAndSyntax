import java.util.Scanner;

import javax.activation.FileTypeMap;
import javax.swing.JFileChooser;
import java.lang.*;

public class Open {
	JFileChooser fileChooser = new JFileChooser();
	StringBuilder sb = new StringBuilder();
	
	public void PickMe() throws Exception{
		if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
			fileChooser.setDialogTitle("Select A File");
			
			java.io.File file = fileChooser.getSelectedFile();
			Scanner input = new Scanner(file);
			while(input.hasNext()){
				sb.append(input.nextLine());
				sb.append("\n");
			}
			input.close();
		}
		else{
			sb.append("No file was selected");
		}
	}
	
}
