package aitamagotchi;

/**
 *
 * @author Labalve
 */
//import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MainFrame extends JFrame {
        
    static JLabel textOutputLabel = new JLabel();//TextOutputPanel();

    public MainFrame(Gotchi tama) {
        super("AITamagotchi");
        setLocation(1000, 500);
        setSize(500, 250);
        setLayout(new GridLayout(3, 0));

        JPanel imagePanel = new ImagePanel();
        add(imagePanel);
        String string = "test";
//       textOutputPanel.printOutput = "AAA";
        add(textOutputLabel);
//        textOutputPanel.grInterface.drawString("AAAAA", 15, 15);

        JButton feedButton = new JButton("Feed");
        feedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                tama.beFed(50);
            }
        });
        add(feedButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    void updateStats(Gotchi tama) {
        textOutputLabel.setText(String.valueOf(tama.hunger));
        
    }
}
