package Model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public interface ITravels {
    void addTravel(Travel t);
    void removeTravel(int n);
    List<Travel> getTravels();
    List<Travel> cheapestTravels();
    List<Travel> mostExpensiveTravels();
    List<Travel> shortestTravels();
    List<Travel> longestTravels();
    List<Travel> travelsBetweenDates(LocalDateTime d1, LocalDateTime d2);
    Duration timeUntilNextTravel();
    Duration timeUntilLastTravel();
    Travels clone();
}