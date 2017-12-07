import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.ProgressBar;

public class Main {

	protected Shell shlEnkryptore;
	private Text encodetxt;
	private Text result;
	private Text decodetxt;
	private Text key;
	private Text gkey;
	private Text charset;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Main window = new Main();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlEnkryptore.open();
		shlEnkryptore.layout();
		while (!shlEnkryptore.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlEnkryptore = new Shell();
		shlEnkryptore.setSize(450, 290);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		shlEnkryptore.setText("built on " + dateFormat.format(date));
		
		encodetxt = new Text(shlEnkryptore, SWT.BORDER);
		encodetxt.setBounds(10, 36, 414, 21);
		
		Label lblTextToEncode = new Label(shlEnkryptore, SWT.NONE);
		lblTextToEncode.setBounds(10, 15, 87, 15);
		lblTextToEncode.setText("Text to encode");
		
		result = new Text(shlEnkryptore, SWT.BORDER);
		result.setText("Result Goes here");
		result.setEditable(false);
		result.setBounds(10, 208, 414, 21);
		
		Label lblEncoded = new Label(shlEnkryptore, SWT.NONE);
		lblEncoded.setBounds(10, 187, 49, 15);
		lblEncoded.setText("Result");
		
		Label lblNewLabel = new Label(shlEnkryptore, SWT.NONE);
		lblNewLabel.setBounds(10, 62, 87, 15);
		lblNewLabel.setText("Text to decode");
		
		decodetxt = new Text(shlEnkryptore, SWT.BORDER);
		decodetxt.setToolTipText("Insert encoded text to decode");
		decodetxt.setBounds(10, 81, 414, 21);
		
		Button encodebtn = new Button(shlEnkryptore, SWT.NONE);
		encodebtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(key.getText().isEmpty()) {
					
				}else {
					if(key.getText().length() == 27) {
						String en = encode(encodetxt.getText(), key.getText());
						result.setText(en);

					}else {
						JOptionPane.showMessageDialog(null, "key MUST be 27 characters long");

					}
					
				}
			}
		});
		encodebtn.setBounds(10, 156, 75, 25);
		encodebtn.setText("Encode text");
		
		Button decodebtn = new Button(shlEnkryptore, SWT.NONE);
		decodebtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
if(key.getText().isEmpty()) {
					
				}else {
					if(key.getText().length() == 27) {
						String de = decode(decodetxt.getText(), key.getText());
						result.setText(de);

					}else {
						JOptionPane.showMessageDialog(null, "key MUST be 27 characters long");

					}
					
				}
				
			}
		});
		decodebtn.setBounds(92, 156, 75, 25);
		decodebtn.setText("Decode Text");
		
		Label lblKey = new Label(shlEnkryptore, SWT.NONE);
		lblKey.setBounds(10, 108, 55, 15);
		lblKey.setText("Key");
		
		key = new Text(shlEnkryptore, SWT.BORDER);
		key.setBounds(9, 129, 158, 21);
		
		Button btnGenerateKey = new Button(shlEnkryptore, SWT.NONE);
		btnGenerateKey.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(charset.getText().isEmpty()) {
					
				}else {
					String s = shuffle(charset.getText());
					gkey.setText(s);
				}
			}
		});
		btnGenerateKey.setBounds(337, 108, 87, 25);
		btnGenerateKey.setText("Generate key");
		
		gkey = new Text(shlEnkryptore, SWT.BORDER);
		gkey.setText("Generated key");
		gkey.setEditable(false);
		gkey.setBounds(191, 181, 233, 21);
		
		charset = new Text(shlEnkryptore, SWT.BORDER);
		charset.setText("%&\u00F1(){}[]^|-_+=.,<>/!@#$~`\u00A7");
		charset.setBounds(191, 154, 233, 21);
		
		Label lblCharset = new Label(shlEnkryptore, SWT.NONE);
		lblCharset.setBounds(191, 132, 75, 15);
		lblCharset.setText("Character Set");
		
		Label lblKeyGeneration = new Label(shlEnkryptore, SWT.NONE);
		lblKeyGeneration.setBounds(191, 108, 96, 15);
		lblKeyGeneration.setText("KEY GENERATION");
		
		Label label = new Label(shlEnkryptore, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(183, 108, 2, 94);
		
		Label label_1 = new Label(shlEnkryptore, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setBounds(191, 129, 140, 2);

	}
	public static String shuffle(String input){
        List<Character> characters = new ArrayList<Character>();
        for(char c:input.toCharArray()){
            characters.add(c);
        }
        StringBuilder output = new StringBuilder(input.length());
        while(characters.size()!=0){
            int randPicker = (int)(Math.random()*characters.size());
            output.append(characters.remove(randPicker));
        }
        return output.toString();
    }

    public static String encode(String n, String c){
      //CHATSET:
      //%&ñ(){}[]^|-_+=.,<>/!@#$~`§
    n = n.replace("a", chars(c, 0));
    n = n.replace("b", chars(c, 1));
    n = n.replace("c", chars(c, 2));
    n = n.replace("d", chars(c, 3));
    n = n.replace("e", chars(c, 4));
    n = n.replace("f", chars(c, 5));
    n = n.replace("g", chars(c, 6));
    n = n.replace("h", chars(c, 7));
    n = n.replace("i", chars(c, 8));
    n = n.replace("j", chars(c, 9));
    n = n.replace("k", chars(c, 10));
    n = n.replace("l", chars(c, 11));
    n = n.replace("m", chars(c, 12));
    n = n.replace("n", chars(c, 13));
    n = n.replace("o", chars(c, 14));
    n = n.replace("p", chars(c, 15));
    n = n.replace("q", chars(c, 16));
    n = n.replace("r", chars(c, 17));
    n = n.replace("s", chars(c, 18));
    n = n.replace("t", chars(c, 19));
    n = n.replace("u", chars(c, 20));
    n = n.replace("v", chars(c, 21));
    n = n.replace("w", chars(c, 22));
    n = n.replace("x", chars(c, 23));
    n = n.replace("y", chars(c, 24));
    n = n.replace("z", chars(c, 25));
    n = n.replace(" ", chars(c, 26));
    return n;
  }
  public static String chars(String a, int pos){
String a_letter = Character.toString(a.charAt(pos));
return a_letter; // Prints f
  }
  public static String decode(String n, String c){
    
    n = n.replace(chars(c, 0), "a");
    n = n.replace(chars(c, 1), "b");
    n = n.replace(chars(c, 2), "c");
    n = n.replace(chars(c, 3), "d");
    n = n.replace(chars(c, 4), "e");
    n = n.replace(chars(c, 5), "f");
    n = n.replace(chars(c, 6), "g");
    n = n.replace(chars(c, 7), "h");
    n = n.replace(chars(c, 8), "i");
    n = n.replace(chars(c, 9), "j");
    n = n.replace(chars(c, 10), "k");
    n = n.replace(chars(c, 11), "l");
    n = n.replace(chars(c, 12), "m");
    n = n.replace(chars(c, 13), "n");
    n = n.replace(chars(c, 14), "o");
    n = n.replace(chars(c, 15), "p");
    n = n.replace(chars(c, 16), "q");
    n = n.replace(chars(c, 17), "r");
    n = n.replace(chars(c, 18), "s");
    n = n.replace(chars(c, 19), "t");
    n = n.replace(chars(c, 20), "u");
    n = n.replace(chars(c, 21), "v");
    n = n.replace(chars(c, 22), "w");
    n = n.replace(chars(c, 23), "x");
    n = n.replace(chars(c, 24), "y");
    n = n.replace(chars(c, 25), "z");
    n = n.replace(chars(c, 26), " ");
    return n;
  }
}
