package Model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Appointment {
    private LocalDateTime date;
    private String text;

    public Appointment() {
        this.date = LocalDateTime.now();
        this.text = "";
    }

    public Appointment(LocalDateTime date, String text) {
        this.date = date;
        this.text = text;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return Objects.equals(date, that.date) &&
                Objects.equals(text, that.text);
    }

    @Override
    public String toString() {
        // TODO format date to a more legible format
        return "[" + this.date + "] " + this.text;
    }
}
