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

    static JLabel generalStatusLabel = new JLabel();
    static JLabel hungerLabel = new JLabel();
    static JLabel lonelinessLabel = new JLabel();
    static JLabel dirtinessLabel = new JLabel();

    public MainFrame(Gotchi tama) {
        super("AITamagotchi");
        setLocation(1000, 500);
        setSize(500, 250);
        setLayout(new GridLayout(3, 0));

        JPanel imagePanel = new ImagePanel();
        add(imagePanel);
        add(generalStatusLabel);
        add(hungerLabel);
        add(lonelinessLabel);
        add(dirtinessLabel);

        JButton feedButton = new JButton("Feed");
        feedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                tama.beFed(50);
            }
        });
        add(feedButton);

        JButton interactButton = new JButton("Interact");
        interactButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                tama.beInteractedWith(50);
            }
        });
        add(interactButton);

        JButton cleanButton = new JButton("Clean");
        cleanButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                tama.beCleaned(50);
            }
        });
        add(cleanButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    void updateStats(Gotchi tama) {
        generalStatusLabel.setText(tama.currentGeneralStatus);
        hungerLabel.setText(tama.name + "'s hunger: " + String.valueOf(tama.hunger));
        lonelinessLabel.setText(tama.name + "'s loneliness: " + String.valueOf(tama.loneliness));
        dirtinessLabel.setText(tama.name + "'s dirtiness: " + String.valueOf(tama.dirtiness));
    }
}
