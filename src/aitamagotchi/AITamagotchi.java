package aitamagotchi;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Labalve
 */
public class AITamagotchi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Gotchi tama = Gotchi.getGotchi("Tama");
        MainFrame mainFrame = MainFrame.getMainFrame();

        Runnable tamaLive = new Runnable() {
            public void run() {
                tama.live();
            }
        };
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
// faster rate for test
//executor.scheduleAtFixedRate(tamaLive, 0, 500, TimeUnit.MILLISECONDS);
        executor.scheduleAtFixedRate(tamaLive, 0, 1, TimeUnit.SECONDS);

        Runnable interfaceRefresh = new Runnable() {
            public void run() {
                mainFrame.updateStats();
            }
        };
        ScheduledExecutorService interfaceExecutor = Executors.newScheduledThreadPool(2);
        interfaceExecutor.scheduleAtFixedRate(interfaceRefresh, 0, 500, TimeUnit.MILLISECONDS);
    }

}
