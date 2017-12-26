package Utils;

import java.util.Optional;

public class NumParser {
    public static Optional<Integer> parseInt(String s) {
        Integer i;

        try {
            i = Integer.valueOf(s);
        } catch (Exception e) {
            i = null;
        }

        return Optional.ofNullable(i);
    }

    public static Optional<Double> parseDouble(String s) {
        Double d;

        try {
            d = Double.valueOf(s);
        } catch (Exception e) {
            d = null;
        }

        return Optional.ofNullable(d);
    }
}
