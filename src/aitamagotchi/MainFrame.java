package aitamagotchi;

/**
 *
 * @author Labalve
 */
//import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MainFrame extends JFrame {

    static private MainFrame instance;

    static Gotchi tama = Gotchi.getGotchi("Ziomek");

    static JLabel avatar = new JLabel();
    static JLabel generalStatusLabel = new JLabel();
    static JLabel hungerLabel = new JLabel();
    static JLabel lonelinessLabel = new JLabel();
    static JLabel dirtinessLabel = new JLabel();

    ImageIcon happyIcon;
    ImageIcon sadIcon;

    public static MainFrame getMainFrame() {
        if (instance == null) {
            instance = new MainFrame();
        }
        return instance;
    }

    private MainFrame() {
        super("AITamagotchi");
        setLocation(1000, 500);
        setSize(500, 250);
        setLayout(new GridLayout(3, 0));
        happyIcon = new ImageIcon();
        sadIcon = new ImageIcon();
        try {
            BufferedImage happyImage = ImageIO.read(new File("img/happy.jpg"));
            happyIcon.setImage(happyImage);
            BufferedImage sadImage = ImageIO.read(new File("img/sad.jpg"));
            sadIcon.setImage(sadImage);
        } catch (IOException e) {
            System.out.println("Błąd ładowania obrazka");
        }
        avatar.setIcon(happyIcon);
        add(avatar);
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

    void updateStats() {
        generalStatusLabel.setText(tama.currentGeneralStatus);
        hungerLabel.setText(tama.name + "'s hunger: " + String.valueOf(tama.hunger));
        lonelinessLabel.setText(tama.name + "'s loneliness: " + String.valueOf(tama.loneliness));
        dirtinessLabel.setText(tama.name + "'s dirtiness: " + String.valueOf(tama.dirtiness));
    }

    void setAvatar(String type) {
        switch (type) {
            case "happy":
                avatar.setIcon(happyIcon);
                break;
            case "sad":
                avatar.setIcon(sadIcon);
                break;
        }
    }
}
