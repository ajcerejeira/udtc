package Model;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.function.Function;

public class Travel {
    private String origin;
    private String destination;
    private Duration duration;
    private LocalDateTime departureDate;
    private double cost;

    public Travel() {
        this.origin = "";
        this.destination = "";
        this.duration = Duration.ZERO;
        this.departureDate = LocalDateTime.now();
        this.cost = 0.0;
    }

    public Travel(String origin, String destination, Duration duration, LocalDateTime departureDate, double cost) {
        this.origin = origin;
        this.destination = destination;
        this.duration = duration;
        this.departureDate = departureDate;
        this.cost = cost;
    }

    public Travel(Travel t){
        this.origin = t.getOrigin();
        this.destination = t.getDestination();
        this.duration = t.getDuration();
        this.departureDate = t.getDepartureDate();
        this.cost = t.getCost();
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public Duration getDuration() {
        return duration;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public ZonedDateTime getTimeAtArrival(){
        ZoneId leavingZone = ZoneId.of(origin);
        ZoneId arrivingZone = ZoneId.of(destination);

        LocalDateTime leaving = this.departureDate;
        ZonedDateTime departure = ZonedDateTime.of(leaving, leavingZone);

        long hours = this.duration.toHours();
        int minutes = (int) ((this.duration.getSeconds() % (60 * 60)) / 60);

        return departure.withZoneSameInstant(arrivingZone).plusHours(hours).plusMinutes(minutes);
    }

    public double getCost() {
        return cost;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        Function<Duration, String> durationToString = (d) -> {
            long hours = d.getSeconds() / 3600;
            long minutes = (d.getSeconds() % 3600) / 60;

            return String.format("%d:%02d", hours, minutes);
        };

        return "[" + this.departureDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")) + "] "
                + this.origin + " - " + this.destination + " "
                + "(" + durationToString.apply(this.duration) + ") " + this.cost + "€";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Travel travel = (Travel) o;
        return Double.compare(travel.cost, cost) == 0 &&
                Objects.equals(origin, travel.origin) &&
                Objects.equals(destination, travel.destination) &&
                Objects.equals(duration, travel.duration) &&
                Objects.equals(departureDate, travel.departureDate);
    }

    @Override
    public Travel clone(){
        return new Travel(this);
    }
}