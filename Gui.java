import java.awt.Color;
import java.awt.GridLayout; //import default layout managing(ordering)
import javax.swing.JFrame; // basic windows feature(title bar, minimize/maximize)
import javax.swing.JLabel; // outputs text+images on screen
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField; 
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.color.*;

public class Gui extends JFrame { 
	CommandLineInterface cmd = new CommandLineInterface();
	 public static void main(String[]args){
		Gui example = new Gui();
		example.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		example.setSize(1000, 700);
		example.setTitle("Operating System Scheduler Simulator");
		example.setVisible(true);
	 }
		private JPanel pcb = new JPanel();
		private JPanel gnatt = new JPanel();
		private JPanel stats = new JPanel();
		private JPanel output = new JPanel();
		
	public JPanel input = new JPanel(new GridLayout(2,1));
	public JLabel txl = new JLabel("output");
	public JTextArea txtfd = new JTextArea();
	
	//text from text area and set it to the output
	private void setInputPanel(){
		 txtfd.addKeyListener(new java.awt.event.KeyAdapter() {
	            public void keyPressed(java.awt.event.KeyEvent evt) {
	                txtfd(evt);
	            }
	            private void txtfd(KeyEvent evt) {
	                int keyCode = evt.getKeyCode();
	                 if (keyCode == 10) {
	                	 txl.setText(cmd.executeCmd(txtfd.getText()));	//user input and set result to output label                }
	            }
	            }}
	            );
		 
		 	txtfd.setBackground(Color.black);
	        txtfd.setForeground(Color.white);
	        txtfd.setCaretColor(Color.white);
		input.add(txtfd);
		input.add(txl);
	}
	
	public JLabel lab = new JLabel("pcb");
	public JLabel gt = new JLabel("gnatt");
	public JLabel st = new JLabel("stats");
	
	public Gui()	{
		super("The title bar"); //title for the window
		setLayout(new GridLayout(3,1)); //default layout
		
		setInputPanel();
		add(input); //adding item to the window
		
		pcb.add(lab);
		gnatt.add(gt);
		stats.add(st);
		
		add(pcb);
		add(gnatt);
		add(stats);
		
//		txtfd.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent ae){
//			txl.setText(cmd.executeCmd(txtfd.getText()));
//			}
//		} );
	}
}

