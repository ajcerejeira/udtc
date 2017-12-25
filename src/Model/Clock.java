package Model;

import Utils.Static;

import java.time.*;

public class Clock implements IClock {
    @Override
    public String[] timeAt() {
        String[] countries = {"Portugal","Africa/Johannesburg","America/New_York","America/Los_Angeles","Asia/Bangkok","Asia/Seoul","Australia/Sydney","Canada/Central","Brazil/East","Poland"};
        String[] output = new String[10];
        int i=0;

        for(String s: countries){
            Instant now = Instant.now();
            ZoneId zid = ZoneId.of(s);
            ZoneOffset currentOffsetForMyZone = zid.getRules().getOffset(now);

            Duration duration = Duration.ofSeconds(currentOffsetForMyZone.getTotalSeconds());
            long hours = duration.toHours();
            int minutes = (int) ((duration.getSeconds() % (60 * 60)) / 60);

            LocalDateTime arrival = LocalDateTime.now().plusHours(hours).plusMinutes(minutes);
            output[i] = "Current time at " + s + " is " + arrival.toString().substring(0,arrival.toString().indexOf(".")).replace("T"," ");
            i++;
        }
        return output;
    }

    @Override
    public LocalDateTime getLocalTimeAt(ZoneId id) {
        Instant now = Instant.now();
        ZoneOffset currentOffsetForMyZone = id.getRules().getOffset(now);

        Duration duration = Duration.ofSeconds(currentOffsetForMyZone.getTotalSeconds());
        long hours = duration.toHours();
        int minutes = (int) ((duration.getSeconds() % (60 * 60)) / 60);

        return LocalDateTime.now().plusHours(hours).plusMinutes(minutes);
    }
}
