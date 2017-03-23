package br.com.henriquerodrigues.palavra;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import java.awt.Label;
import java.awt.Button;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JSlider;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.Desktop;
import java.net.URI;
import java.io.IOException;


public class FullInterface {

	//base frame of the window
	public static JFrame frame;
	//all the booleans that will be represented as checkboxes
	public boolean verbose, cisNames, cisSurnames, engNames, engSurnames, generalPortuguese, generalEnglish;
	//Slider value for the english dictionary preseted for what we want at the start
	public int sliderValue = 100;
	/**
	 * Launch the application.
	 */
	public static void GUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FullInterface window = new FullInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize the Gui
	 */
	public FullInterface() {
		initialize();
	}

	/**
	 * Generate the components of the interface, using a definitive size and position in the frame.
	 * 
	 */
	private void initialize() { //a Method that create all the buttons, panels, and checklists, and the event listners and show then on the screen.
		//Variables
		verbose = true;
		
		/**
		 * 600x600 Frame
		 */
		frame = new JFrame("Palavra, a software that Parlava a Palavar of Palrava."); //A fun name for the window
		frame.setBounds(100, 100, 600, 600); //the size and position of the frame (positionX, positionY, width, height)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //what should happen when to user closes the app
		frame.getContentPane().setLayout(null);//This is here so I can set the window size myself

		
		/**
		 * Text Area
		 */
		
		JTextArea textArea = new JTextArea(); //The typing area for the user, I opted for textArea instead of TextField to handle more than one line of input
		textArea.setBounds(10, 78, 564, 173); //Set the size and position of the textArea
		textArea.addKeyListener(new KeyAdapter() {//See if user pressed F1 for help
			@Override//Necessary for some reason that escapes me
			public void keyPressed(KeyEvent arg0) { //when something is pressed
				if (arg0.getKeyCode() == KeyEvent.VK_F1) { //It was F1?
		             showHelpAndDocs();//Call method that show the help page in the default browser of the user
		        }
			}
		});
		//Make a scrollPane so the user can scroll around the textArea and make both bars Always visible because I like it more.
		JScrollPane scroll = new JScrollPane (textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(10, 78, 564, 173);
		frame.getContentPane().add(scroll);
		/**
		 * Tool Bar
		 */
		
		JToolBar toolBar = new JToolBar(); //make the toolbar to link for the documentation and verbose
		toolBar.setBounds(0, 0, 200, 23);
		frame.getContentPane().add(toolBar);
		Button button_1 = new Button("F1 - Help");
		toolBar.add(button_1);
		button_1.addActionListener(new ActionListener() { //When the button is clicked
			public void actionPerformed(ActionEvent arg0) {
				showHelpAndDocs();
				}
			});
		JCheckBox verboseCheckBox = new JCheckBox("Verbose");
		verboseCheckBox.setSelected(false); //Let the Verbose off by default
		toolBar.add(verboseCheckBox); //Add the checkbox to the bar
		

		
		/**
		 * Labels (The uneditable text fields, with its positions and add methods) 
		 */
		
		Label label = new Label("This is Palavra, a anagram/name generator. Select at least one dictionary on the right and then click on");
		label.setBounds(10, 22, 564, 23);
		frame.getContentPane().add(label);
		
		Label label2 = new Label("what process you want on the left. For more info try the Help and Documentation button.");
		label2.setBounds(10, 39, 564, 23);
		frame.getContentPane().add(label2);
		
		
		
		/**
		 * Checkboxes 
		 */	
		JCheckBox generalEnglishCheckBox = new JCheckBox("General English:"); //Text on the checkbox
		generalEnglishCheckBox.setBounds(375, 288, 199, 23); //The position and size
		frame.getContentPane().add(generalEnglishCheckBox); //add it to the frame
		generalEnglishCheckBox.setSelected(true); //Set selected by default
		
		JCheckBox generalPortugueseCheckBox = new JCheckBox("General Portuguese"); //Same as above
		generalPortugueseCheckBox.setBounds(375, 378, 199, 23);
		frame.getContentPane().add(generalPortugueseCheckBox);
		generalPortugueseCheckBox.setSelected(true);
		
		JCheckBox englishFNameCheckBox = new JCheckBox("English First Names"); //Same as above
		englishFNameCheckBox.setBounds(375, 412, 199, 23);
		frame.getContentPane().add(englishFNameCheckBox);
		englishFNameCheckBox.setSelected(true);

		
		JCheckBox englishSNameCheckBox = new JCheckBox("English Surnames"); //Same as above
		englishSNameCheckBox.setBounds(375, 446, 199, 23);
		frame.getContentPane().add(englishSNameCheckBox);
		englishSNameCheckBox.setSelected(true);
		
		JCheckBox russianFNameCheckBox = new JCheckBox("Russian First Names"); //Same as above
		russianFNameCheckBox.setBounds(375, 480, 199, 23);
		frame.getContentPane().add(russianFNameCheckBox);
		russianFNameCheckBox.setSelected(true);
		
		JCheckBox russianSNameCheckBox = new JCheckBox("Russian Surnames"); //Same as above
		russianSNameCheckBox.setBounds(375, 512, 199, 23);
		frame.getContentPane().add(russianSNameCheckBox);
		russianSNameCheckBox.setSelected(true);
		
		JCheckBox chckbxCopyResultTo = new JCheckBox("copy result to Clipboard"); //Same as above
		chckbxCopyResultTo.setBounds(10, 501, 187, 23);
		frame.getContentPane().add(chckbxCopyResultTo);
		
		/**
		 * Slider
		 */
		JSlider slider = new JSlider(); //The slider that select how strong you want your English Dictionary
		slider.setBounds(333, 318, 200, 16);
		frame.getContentPane().add(slider);
		slider.setValue(sliderValue); //Set the default value as the Value we selected in the first lines of the code
		
		/**
		 * Buttons 
		 */

		
		
		/**
		 * 
		 * 
		 * 
		 * THIS IS THE ASSIGMENT BUTTON, THE OTHERS ARE MADE AS A BONUS
		 * 
		 * 
		 * 
		 */
		//Assigment Test Button
		JButton assignmentTestBtn = new JButton("Assignment Test"); //The main assigment button
		//TODO:Figure something to break it even more in functions in other classes
		
		assignmentTestBtn.addActionListener(new ActionListener() { //When the button is clicked
			public void actionPerformed(ActionEvent arg0) {
				verbose = verboseCheckBox.isSelected(); //Get the status (is checked of not) of each checkbox
				cisNames = russianFNameCheckBox.isSelected();
				cisSurnames = russianSNameCheckBox.isSelected();
				engNames = englishFNameCheckBox.isSelected();
				engSurnames = englishSNameCheckBox.isSelected();
				generalPortuguese = generalPortugueseCheckBox.isSelected();
				generalEnglish = generalEnglishCheckBox.isSelected();
				sliderValue = slider.getValue(); //The value of the slider
				String uInput = textArea.getText();				
				if (uInput.hashCode() == 0) { //5 Hours to find this one out!! The blank user input was breaking everything
					JOptionPane.showMessageDialog(frame, "Unfortunatly you cheated and tryed to do your Assignment Test without any user Input, this app will now close. Call Again!");
					System.exit(0);
				}					
				JOptionPane.showMessageDialog(frame, "Depending on how many dictionaries you have "
						+ "selected, the size of the input and how powerful is your hardware this "
						+ "may take some time,\nAll dictionaries selected on a powerful computer "
						+ "takes 8 seconds to load.\n\n"
						+ " If you don't like the Anagrams result (too much made up words and typos)"
						+ " tweak the dictionaries.");
				boolean[] userSettings = {verbose, cisNames, cisSurnames, engNames, engSurnames, generalPortuguese, generalEnglish, chckbxCopyResultTo.isSelected()};
				Palavra.assigmentTest(userSettings, sliderValue, uInput);			
			}		
		});
		
		assignmentTestBtn.setBounds(10, 288, 233, 23);
		frame.getContentPane().add(assignmentTestBtn);
		
		/**
		 * 
		 * END OF ASSIGMENT BUTTON
		 * 
		 */
		
		//Random Name Button
		JButton randomNameBtn = new JButton("Generate Random Name");
		randomNameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verbose = verboseCheckBox.isSelected(); //Get the status (is checked of not) of each checkbox
				cisNames = russianFNameCheckBox.isSelected();
				cisSurnames = russianSNameCheckBox.isSelected();
				engNames = englishFNameCheckBox.isSelected();
				engSurnames = englishSNameCheckBox.isSelected();
				generalPortuguese = generalPortugueseCheckBox.isSelected();
				generalEnglish = generalEnglishCheckBox.isSelected();
				sliderValue = slider.getValue(); //The value of the slider
				String uInput = textArea.getText();								
				JOptionPane.showMessageDialog(frame, "Depending on how many dictionaries you have "
						+ "selected, the size of the input and how powerful is your hardware this "
						+ "may take some time,\nAll dictionaries selected on a powerful computer "
						+ "takes 8 seconds to load.\n\n"
						+ " If you don't like the Anagrams result (too much made up words and typos)"
						+ " tweak the dictionaries.");
				boolean[] userSettings = {verbose, cisNames, cisSurnames, engNames, engSurnames, generalPortuguese, generalEnglish, chckbxCopyResultTo.isSelected()};
				Palavra.generateRandomNames(userSettings, sliderValue, uInput);	
			}
		});
		randomNameBtn.setBounds(10, 323, 233, 23);
		frame.getContentPane().add(randomNameBtn);
		
		//Generate Anagram Button
		JButton genAnagramBtn = new JButton("Generate Anagram");
		genAnagramBtn.setBounds(10, 357, 233, 23);
		frame.getContentPane().add(genAnagramBtn);
		
		
		//
		JButton button_3 = new JButton("Generate Pangram");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verbose = verboseCheckBox.isSelected(); //Get the status (is checked of not) of each checkbox
				cisNames = russianFNameCheckBox.isSelected();
				cisSurnames = russianSNameCheckBox.isSelected();
				engNames = englishFNameCheckBox.isSelected();
				engSurnames = englishSNameCheckBox.isSelected();
				generalPortuguese = generalPortugueseCheckBox.isSelected();
				generalEnglish = generalEnglishCheckBox.isSelected();
				sliderValue = slider.getValue(); //The value of the slider
				String uInput = textArea.getText();								
				boolean[] userSettings = {verbose, cisNames, cisSurnames, engNames, engSurnames, generalPortuguese, generalEnglish, chckbxCopyResultTo.isSelected()};
				Palavra.generatePangram(userSettings, sliderValue, uInput);	
			}
		});
		button_3.setBounds(10, 391, 233, 23);
		frame.getContentPane().add(button_3);
		
		JButton button_4 = new JButton("Help and Documentation");
		button_4.setBounds(10, 425, 233, 23);
		frame.getContentPane().add(button_4);
		button_4.addActionListener(new ActionListener() { //When the button is clicked
			public void actionPerformed(ActionEvent arg0) {
				showHelpAndDocs();
				}
			});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 96, 233, 155);
		frame.getContentPane().add(scrollPane);
		
		JLabel lblDictionaries = new JLabel("Dictionaries");
		lblDictionaries.setBounds(406, 254, 187, 23);
		frame.getContentPane().add(lblDictionaries);
		
		JLabel label_1 = new JLabel("Available Processes");
		label_1.setBounds(81, 254, 187, 23);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("common words");
		label_2.setBounds(317, 333, 96, 23);
		frame.getContentPane().add(label_2);
		
		
		JLabel label_4 = new JLabel("uncommon words");
		label_4.setBounds(469, 333, 187, 23);
		frame.getContentPane().add(label_4);
		
		

		

	}
	public static void showMessage(String s){
		JOptionPane.showMessageDialog(frame, s);
	}
	public static void showHelpAndDocs(){
        String url = "http://henriquerodrigues.com/palavra/";

        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
            }catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("xdg-open " + url);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
	}
}
