package aitamagotchi;

import java.awt.EventQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.management.timer.Timer;

/**
 *
 * @author Labalve
 */
public class AITamagotchi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Gotchi tama = new Gotchi("Ziomek");
        MainFrame mainFrame = new MainFrame(tama);

        Runnable tamaLive = new Runnable() {
            public void run() {
                tama.live();
                mainFrame.updateStats(tama);
            }
        };
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(tamaLive, 0, 1, TimeUnit.SECONDS);
    }

}
