import java.util.PriorityQueue;
import java.awt.FlowLayout; //import default layout managing(ordering)
import javax.swing.JFrame; // basic windows feature(title bar, minimize/maximize)
import javax.swing.JLabel; // ouput text+images on screen

public class Gui extends JFrame {
	 
	private JLabel item1;
	//constructor
	public Gui()	{
		super("The title bar"); //title for the window
		setLayout(new FlowLayout()); //default layout
		
		item1 = new JLabel("Add Text");
		item1.setToolTipText("Scrollbox options");
		add(item1); //adding item to the window
	}
}

//import javax.swing.JFrame;
//Gui example = new Gui();
//example.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//example.setSize();
//example.setVisible(true);


