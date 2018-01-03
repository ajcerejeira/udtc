package Model;

import java.time.Duration;

/**
 *
 * A chronometer only has four possible actions: start, stop, pause, and resume.
 * This interface is abstract enough so that its implementations can vary in design.
 *
 * @author Octavio Maia
 */
public interface IChronometer {
    /**
     * Starts the chronometer.
     */
    void start();

    /**
     * Stops the chronometer, and returns the time the chronometer has been running.
     *
     * @return the time the chronometer has benn running
     */
    Duration stop();

    /**
     * Pauses the chronometer, and returns the time the chronometer has been running.
     *
     * @return the time the chronometer has been running
     */
    Duration pause();

    /**
     *
     */
    void resume();
}