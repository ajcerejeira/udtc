package View;

import Exceptions.InvalidInputException;
import Model.Chronometer;

import static java.lang.System.out;

public class ChronoView implements View {
    @Override
    public void run(Object o) {
        Menu menu = new Menu(new Option[] {
                new Option("Voltar", x -> System.exit(1)),
                new Option("Start", chronometer -> ((Chronometer) o).startChronometer()),
                new Option("Stop", chronometer -> ((Chronometer) o).stopChronometer())
        });

        try {
            menu.run(o);
        } catch (InvalidInputException e) {
            System.out.println("A opção escolhida é inválida\n");
        }
    }
}
