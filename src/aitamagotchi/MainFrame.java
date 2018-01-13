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

public class MainFrame extends JFrame {

    private static MainFrame instance;

    Gotchi tama = Gotchi.getGotchi("Ziomek");

    JLabel avatar = new JLabel();
    JLabel generalStatusLabel = new JLabel();
    JLabel hungerLabel = new JLabel();
    JLabel lonelinessLabel = new JLabel();
    JLabel dirtinessLabel = new JLabel();

    ImageIcon icon;

    String currentAvatar = "happy";

    Message currentMessage;

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
        setLayout(new GridLayout(2, 3));
        icon = new ImageIcon();
        try {
            BufferedImage image = ImageIO.read(new File("img/neutral.jpg"));
            icon.setImage(image);
        } catch (IOException e) {
            System.out.println("Błąd ładowania obrazka");
        }
        avatar.setIcon(icon);
        add(avatar);
        add(generalStatusLabel);
        add(new JLabel());
        
        //stats for testing
        /*add(hungerLabel);
        add(lonelinessLabel);
        add(dirtinessLabel);*/
        
        currentMessage = new Message("My name is " + tama.name + ". Are you my Creator?");
        generalStatusLabel.setText(currentMessage.getContent());
        
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
        if (currentMessage.isEnding()) {
            this.currentMessage = new Message();
            generalStatusLabel.setText(currentMessage.getContent());
        }
        //stats for testing
        /* hungerLabel.setText(tama.name + "'s hunger: " + String.valueOf(tama.hunger));
        lonelinessLabel.setText(tama.name + "'s loneliness: " + String.valueOf(tama.loneliness));
        dirtinessLabel.setText(tama.name + "'s dirtiness: " + String.valueOf(tama.dirtiness));*/
        if (!(tama.getGeneralStatus().equals(currentAvatar))) {
            setAvatar(tama.getGeneralStatus());
        }
    }

    void setAvatar(String type) {
        if (type.equals("")) {
            type = "neutral";
        }
        icon = new ImageIcon();
        try {
            BufferedImage image = ImageIO.read(new File("img/" + type + ".jpg"));
            icon.setImage(image);
        } catch (IOException e) {
            System.out.println("Błąd ładowania obrazka");
        }
        avatar.setIcon(icon);
        currentAvatar = type;
    }
}
