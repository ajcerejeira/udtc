package Model;

import Utils.Parsers;

import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Notebook implements INotebook {
    private Set<Appointment> appointments;

    public Notebook() {
        this.appointments = new TreeSet<>();
    }

    @Override
    public void addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
    }

    @Override
    public void deleteAppointment(Appointment appointment) {
        this.appointments.remove(appointment);
    }

    @Override
    public List<Appointment> getAppointments() {
        return new ArrayList<>(this.appointments);
    }

    @Override
    public List<Appointment> getAppointments(LocalDateTime date) {
        return this.appointments
                .stream()
                .filter(appointment -> appointment.getDate().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public List<Appointment> getAppointments(LocalDateTime from, LocalDateTime to) {
        return this.appointments
                .stream()
                .filter(appointment -> appointment.getDate().isAfter(from) && appointment.getDate().isBefore(to))
                .collect(Collectors.toList());
    }

    @Override
    public int read(String s) {
        Pattern p = Pattern.compile("^\\[(.*?)\\] (.*?)$", Pattern.MULTILINE);
        Matcher m = p.matcher(s);
        int n = 0;

        while (m.find()) {
            LocalDateTime date = Parsers.parseDateTime(m.group(1)).orElse(LocalDateTime.now());
            String text = m.group(2);
            this.appointments.add(new Appointment(date, text));
            n++;
        }

        return n;
    }

    @Override
    public String toString() {
        StringBuilder build = new StringBuilder();

        for (Appointment appointment : this.appointments) {
            build.append(appointment.toString());
            build.append('\n');
        }

        return build.toString();
    }
}
