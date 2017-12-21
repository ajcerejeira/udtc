package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Notebook implements INotebook {
    private List<Appointment> appointments;

    public Notebook() {
        this.appointments = new ArrayList<>();
    }


    @Override
    public void addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
    }

    @Override
    public void removeAppointment(int index) {
        this.appointments.remove(index);
    }

    @Override
    public void removeAppointment(Appointment appointment) {
        this.appointments.remove(appointment);
    }

    @Override
    public List<Appointment> getAppointments() {
        return this.appointments;
    }

    @Override
    public List<Appointment> getAppointments(LocalDateTime date) {
        return this.appointments
                .stream()
                .filter(appointment -> appointment.getDate().equals(date))
                .collect(Collectors.toList());
    }
}
