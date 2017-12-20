package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Notebook implements INotebook {
    private Map<LocalDateTime, List<String>> appointments;

    public Notebook() {
        this.appointments = new TreeMap<>();
    }

    @Override
    public void addAppointment(LocalDateTime date, String text) {
        if (this.appointments.containsKey(date))  {
            this.appointments.get(date).add(text);
        } else {
            this.appointments.put(date, new ArrayList<>());
            this.appointments.get(date).add(text);
        }
    }

    @Override
    public void removeAppointment(LocalDateTime date, String text) {
        this.appointments.get(date).remove(text);
    }
}
