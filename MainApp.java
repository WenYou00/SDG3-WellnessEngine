/*
 * Class: MainApp
 * Description: - Application entry point. Launches WelcomeScreen. 
 *              - The main bootstrapper class launching the desktop frame environment.
*/

import javax.swing.SwingUtilities;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WelcomeScreen();
            }
        });
    }
}