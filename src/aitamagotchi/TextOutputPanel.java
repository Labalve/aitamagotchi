package aitamagotchi;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Labalve
 */
public class TextOutputPanel extends JPanel {
    
    //public Graphics grInterface;
    String printOutput; 
    
    @Override
    protected void paintComponent(Graphics gr){
        super.paintComponent(gr);
        gr.drawString(printOutput, 15, 15);
    }
    
}
