package View;

import Exceptions.InvalidInputException;

import static java.lang.System.out;

public class ChronoView implements View {
    private void stop() {
        out.println("Chronometer has stopped!");
    }

    private void start() {
        out.println("Chronometer has started!");
    }

    @Override
    public void run(Object o) {
        Menu menu = new Menu(new Option[] {
                new Option("Voltar", x -> System.exit(1)),
                new Option("Start", chronometer -> start()),
                new Option("Stop", chronometer -> stop()),
        });

        try {
            menu.run(o);
        } catch (InvalidInputException e) {
            System.out.println("A opção escolhida é inválida\n");
        }
    }
}
