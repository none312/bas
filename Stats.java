import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
public class Stats{
public static void main(String[] args){
        JFrame f = new JFrame();
        f.setLayout(new BorderLayout());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JProgressBar bar = new JProgressBar(0,100);
        f.add(bar, BorderLayout.SOUTH);
        f.setSize(64, 64);
        f.pack();
        f.setVisible(true);

        for(int i = bar.getMinimum(); i <= bar.getMaximum(); i++){
            final int percent = i ;
            SwingUtilities.invokeLater(new Runnable(){
                @Override
                public void run() {
                    bar.setValue(percent);
                }
            });
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
}
}