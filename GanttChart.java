import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GanttChart extends JPanel {

   public static void main(String[] a) {
      JFrame f = new JFrame();
      f.setSize(400, 400);
      f.add(new GanttChart());
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setVisible(true);
   }
   public void paint(Graphics g) {
	   g.setColor(Color.GRAY);
	   g.fillRect (2, 5, 2500, 125);
   }
}