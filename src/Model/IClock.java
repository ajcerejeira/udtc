package Model;

import java.time.LocalDateTime;
import java.time.ZoneId;

public interface IClock {
    String[] timeAt();
    LocalDateTime getLocalTimeAt(ZoneId id);
}
