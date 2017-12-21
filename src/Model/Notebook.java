package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
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
}
