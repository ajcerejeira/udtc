package Model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public interface ITravels {
    void addTravel(Travel t);
    void removeTravel(int n);
    List<Travel> getTravels();
    List<Travel> cheapestTravels(int n);
    List<Travel> mostExpensiveTravels(int n);
    List<Travel> shortestTravels(int n);
    List<Travel> longestTravels(int n);
    List<Travel> travelsBetweenDates(LocalDateTime d1, LocalDateTime d2);
    Duration timeUntilNextTravel();
    Duration timeUntilLastTravel();
    Travels clone();
}