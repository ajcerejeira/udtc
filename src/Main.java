import Model.Chronometer;
import View.*;

public class Main {

    public static void main(String[] args) {
        Runnable v = new ChronoView(new Chronometer());
        v.run();
    }
}