package Model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * The Travel interface is responsible for all functions related to traveling queries.
 * This interface is abstract enough so that its implementations can vary in design.
 *
 * @author Octavio Maia
 */
public interface ITravels {
    /**
     * Adds a valid travel.
     *
     * @param t Travel to be added.
     */
    void addTravel(Travel t);

    /**
     * Removes a given travel.
     *
     * @param t Travel to be removed.
     */
    void removeTravel(Travel t);

    /**
     * List all currently present Travels.
     *
     * @return List containing all the Travels.
     */
    List<Travel> getTravels();

    /**
     * List the cheapest Travel by ascending order.
     *
     * @return List containing the cheapest Travels.
     */
    List<Travel> cheapestTravels();

    /**
     * List the cheapest Travel by descending order.
     *
     * @return List containing the most expensive Travels.
     */
    List<Travel> mostExpensiveTravels();

    /**
     * List the shortest Travel by ascending order.
     *
     * @return List containing the shortest Travels.
     */
    List<Travel> shortestTravels();

    /**
     * List the longest Travel by descending order.
     *
     * @return List containing the longest Travels.
     */
    List<Travel> longestTravels();

    /**
     * Lists the Travels between two LocalDateTime.
     *
     * @param d1 Starting LocalDateTime.
     * @param d2 Ending LocalDateTime.
     * @return List containing all the Travels between the two given LocalDateTime.
     */
    List<Travel> travelsBetweenDates(LocalDateTime d1, LocalDateTime d2);

    /**
     * Calculates the Duration until the next Travel.
     *
     * @return Duration until the next Travel.
     */
    Duration timeUntilNextTravel();

    /**
     * Calculates the Duration until the last Travel.
     *
     * @return Duration until the last Travel.
     */
    Duration timeUntilLastTravel();

    /**
     * Parses the given string, adding the textual represented travels.
     *
     * @param s String received via the input.
     * @return Integer representing the number of travels read.
     */
    int read(String s);

    /**
     * Transforms the Travel into a String.
     *
     * @return Travel converted to a String format easier to read.
     */
    String toString();

    /**
     * Clones all given Travels.
     *
     * @return Clone of all Travels.
     */
    Travels clone();
}