import java.awt.Color;

import javax.swing.JFrame;

public class Breakout extends JFrame{
	
	static final long serialVersionUID = 1L;
	
	private BreakoutPanel panel;
	
	public Breakout() {
		
		//Here you need to set various visualization parameters
		
		// 1) TODO: Set the size of the screen (use Settings.WINDOW_WIDTH/HEIGHT)
		setSize(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
		
		// 2) TODO: Set the title. You should be able to do this easily
		setTitle(Settings.WINDOW_NAME);
		// TODO: Set the background colour to white
		setBackground(Color.WHITE);  //See the Settings .java to see what colour parameter to use
		
		// TODO: Set resizable to false
		setResizable(false);
		
		//For closing action
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//This is the key statement. BreakoutPanel class is repsonsilbe for setting up
		//all the game parametrs including bricks, ball, paddle and the panel itself. 
		//Remember that panel object of class BreakoutPanel is actually a JPanel and implelemnts
		//action listener. So it contains all the visual information for a panel to be displayed.
		//Hence before displaying the panel using setVisible(true), we need to add the current 
		//active panel. That is why we need to move the setVisible to the end after the add(panel)
		
        panel = new BreakoutPanel(this);
        add(panel);
       
        // TODO: Set visible to true
        setVisible(true);
	}

	public static void main(String[] args) {
									 //=============================================================
		new Breakout();	             //This is the main starting point of the game. When you create
		                             //an instance of the Breakout() object, the Breackout() constructor is called
		                             //with all the game related processing steps.
									 //=============================================================
	}

}
