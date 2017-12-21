package Model;

import java.time.LocalDateTime;
import java.util.List;

public interface INotebook {
    void addAppointment(Appointment appointment);

    void removeAppointment(int index);
    void removeAppointment(Appointment appointment);

    List<Appointment> getAppointments();
    List<Appointment> getAppointments(LocalDateTime date);
}
