package View;

import Exceptions.InvalidInputException;
import Model.Chronometer;

import static java.lang.System.out;

public class ChronoView implements View {
    @Override
    public void run(Object o) {
        Menu menu = new Menu(new Option[] {
                new Option<Void>("Voltar", x -> System.exit(1)),
                new Option<Chronometer>("Start", Chronometer::start),
                new Option<Chronometer>("Stop", Chronometer::stop),
                new Option<Chronometer>("Pause", Chronometer::pause),
                new Option<Chronometer>("Resume", Chronometer::resume)
        });
        menu.run(o);
    }
}
