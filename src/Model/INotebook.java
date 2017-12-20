package Model;

import java.time.LocalDateTime;

public interface INotebook {
    void addAppointment(LocalDateTime date, String text);
    void removeAppointment(LocalDateTime date, String text);
}
