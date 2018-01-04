package Model;

import Utils.Parsers;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Travels implements ITravels {
    private List<Travel> record;

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
    public void removeTravel(Travel t) {
        this.record.remove(t);
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
    public int read(String s) {
        Pattern p = Pattern.compile("^\\[(.*?)\\] (.*?) - (.*?) \\((.*?)\\) (.*?)€$", Pattern.MULTILINE);
        Matcher m = p.matcher(s);
        int n = 0;

        try {
            while (m.find()) {
                LocalDateTime date = Parsers.parseDateTime(m.group(1)).orElse(LocalDateTime.now());
                String from = m.group(2);
                String to = m.group(3);
                Duration duration = Parsers.parseDuration(m.group(4)).orElse(Duration.ZERO);
                System.out.println(m.group(5));
                double cost = Parsers.parseDouble(m.group(5)).orElse(0.0);

                this.record.add(new Travel(from, to, duration, date, cost));
                n++;
            }
        } catch (Exception e) {
            n = -1;
        }

        return n;
    }

    @Override
    public Travels clone() {
        return new Travels(this);
    }

    @Override
    public String toString() {
        StringBuilder build = new StringBuilder();

        for (Travel travel : this.record) {
            build.append(travel.toString());
            build.append('\n');
        }

        return build.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Travels travels = (Travels) o;
        return Objects.equals(record, travels.record);
    }
}
