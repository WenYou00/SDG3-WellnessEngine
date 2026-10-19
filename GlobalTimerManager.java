/*
 * Class: GlobalTimerManager
 * Description: - Countdown timer for quiz with callback when time expires.
 *              - Timer with ActionListener (overrides actionPerformed).
 *              - Constructor overloading, callback.
*/

// Import ActionEvent class for handling timer events
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

// Class declaration implementing ActionListener interface
public class GlobalTimerManager implements ActionListener {
    // Private variable to store total time limit in seconds
    private int timeLimitSeconds;
    // Private variable to track remaining time
    private int timeRemaining;
    // Private Timer object for countdown functionality
    private Timer timer;
    // Private flag to indicate if time has expired
    private boolean timeExpired;
    // Private Runnable callback to execute when time expires
    private Runnable onTimeExpiredCallback;

    // Constructor overloading example - first constructor with only time limit
    public GlobalTimerManager(int timeLimitSeconds) {
        // Call the main constructor with null callback
        this(timeLimitSeconds, null);
    }

    // Main constructor with time limit and callback parameter
    public GlobalTimerManager(int timeLimitSeconds, Runnable callback) {
        // Initialize time limit field
        this.timeLimitSeconds = timeLimitSeconds;
        // Set remaining time equal to time limit
        this.timeRemaining = timeLimitSeconds;
        // Store the callback function for later execution
        this.onTimeExpiredCallback = callback;
        // Initialize time expired flag to false
        this.timeExpired = false;
        // Create timer that fires every 1000ms (1 second) with this as listener
        this.timer = new Timer(1000, this);
    }

    // Method to start the countdown timer
    public void startTimer() {
        // Start the Swing timer
        timer.start();
    }

    // Method to stop the countdown timer
    public void stopTimer() {
        // Stop the Swing timer
        timer.stop();
    }

    // Method to reset timer to initial state
    public void resetTimer() {
        // Stop the timer if running
        stopTimer();
        // Reset remaining time to original time limit
        this.timeRemaining = timeLimitSeconds;
        // Reset time expired flag to false
        this.timeExpired = false;
    }

    // Getter method to retrieve remaining time
    public int getTimeRemaining() {
        // Return current remaining time in seconds
        return timeRemaining;
    }

    // Method to check if time has expired
    public boolean isTimeExpired() {
        // Return time expired flag status
        return timeExpired;
    }

    // Override actionPerformed method from ActionListener interface
    @Override
    public void actionPerformed(ActionEvent e) {
        // Check if there is still time remaining
        if (timeRemaining > 0) {
            // Decrease remaining time by 1 second
            timeRemaining--;
            // Print current remaining time to console
            System.out.println("Time remaining: " + timeRemaining + " seconds");
        // Execute this block when time reaches zero
        } else {
            // Stop the timer to prevent further events
            stopTimer();
            // Check if time expiration hasn't been processed yet
            if (!timeExpired) {
                // Set time expired flag to true
                timeExpired = true;
                // Print time's up message to console
                System.out.println("Time's up!");
                // Check if callback function exists
                if (onTimeExpiredCallback != null) {
                    // Execute the callback function when time expires
                    onTimeExpiredCallback.run();
                }
            }
        }
    }
}