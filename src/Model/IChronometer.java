package Model;

import java.time.Duration;

/**
 * This interface sets the core functionality of a simple chronometer. It has only four possible actions: start, stop,
 * pause and resume.
 *
 * This interface is abstract enough so that its implementations can vary in design.
 *
 * One simple example of its usage could be like this:
 * <pre>
 *     <code>
 *         public static void run(IChronometer chronometer) {
 *             chronometer.start();
 *             System.out.println("Start");
 *
 *             Duration d = chronometer.stop();
 *             System.out.println("Stop. Elapsed time:" + d);
 *         }
 *     </code>
 * </pre>
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
     * Resumes the chronometer.
     */
    void resume();
}