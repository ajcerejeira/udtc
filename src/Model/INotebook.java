package Model;

import java.time.LocalDateTime;
import java.util.List;

public interface INotebook {
    void addAppointment(Appointment appointment);
    void deleteAppointment(Appointment appointment);

    List<Appointment> getAppointments();
    List<Appointment> getAppointments(LocalDateTime date);
}
