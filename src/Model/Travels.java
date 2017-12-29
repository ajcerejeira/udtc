package Model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Travels implements ITravels {
    private List<Travel> record;
    private int n;

    public Travels() {
        this.record = new ArrayList<>();
    }

    public Travels(List<Travel> record) {
        record.forEach(t -> this.record.add(t.clone()));
    }

    public Travels(Travels record){
        this.record = new ArrayList<>();
    }

    @Override
    public void addTravel(Travel t) {
        this.record.add(t.clone());
    }

    @Override
    public void removeTravel(int n) {
        this.record.remove(n);
    }

    @Override
    public List<Travel> getTravels(){
        return this.record.stream().map(Travel::clone).collect(Collectors.toList());
    }

    @Override
    public List<Travel> cheapestTravels(){
        return this.record.stream().map(Travel::clone).sorted(Comparator.comparingDouble(Travel::getCost)).collect(Collectors.toList());
    }

    @Override
    public List<Travel> mostExpensiveTravels(){
        return this.record.stream().map(Travel::clone).sorted(Comparator.comparingDouble(Travel::getCost).reversed()).collect(Collectors.toList());
    }

    @Override
    public List<Travel> shortestTravels() {
        return this.record.stream().map(Travel::clone).sorted(Comparator.comparing(Travel::getDuration)).collect(Collectors.toList());
    }

    @Override
    public List<Travel> longestTravels() {
        return this.record.stream().map(Travel::clone).sorted(Comparator.comparing(Travel::getDuration).reversed()).collect(Collectors.toList());
    }

    @Override
    public List<Travel> travelsBetweenDates(LocalDateTime d1, LocalDateTime d2) {
        return this.record.stream().filter(t -> t.getDepartureDate().isAfter(d1) && t.getDepartureDate().isBefore(d2)).map(Travel::clone).collect(Collectors.toList());
    }

    @Override
    public Duration timeUntilNextTravel() {
        return Duration.between(LocalDateTime.now(), getTravels().get(0).getDepartureDate());
    }

    @Override
    public Duration timeUntilLastTravel() {
        return Duration.between(LocalDateTime.now(), getTravels().get(getTravels().size()-1).getDepartureDate());
    }

    @Override
    public Travels clone() {
        return new Travels(this);
    }

    @Override
    public String toString() {
        return "Travels{" +
                "record=" + record +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Travels travels = (Travels) o;
        return Objects.equals(record, travels.record);
    }
}
