package Utils;

import java.time.format.DateTimeFormatter;
import java.util.function.Predicate;

public class Static {
    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static<T> Predicate<T> not(Predicate<T> p) {
        return t -> !p.test(t);
    }
}
