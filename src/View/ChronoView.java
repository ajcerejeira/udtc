package View;

import Model.Chronometer;

public class ChronoView implements Runnable {
    private Chronometer chronometer;

    public ChronoView() {
        this.chronometer = new Chronometer();
    }

    public ChronoView(Chronometer chronometer) {
        this.chronometer = chronometer;
    }

    private void start() {
        System.out.println("Iniciando o cronómetro");
        chronometer.start();

        Menu menu = new Menu(new Option[] {
                new Option(1,"Pause", this::pause),
                new Option(2,"Stop", this::stop),
        });

        menu.run();
    }

    private void stop() {
        chronometer.stop();
        System.out.println(chronometer.getRuntime());
        this.run();
    }

    private void pause() {
        chronometer.pause();
    }

    private void resume() {
        chronometer.resume();
    }

    @Override
    public void run() {
        Menu menu = new Menu(new Option[] {
                new Option(1,"Start", this::start),
                new Option(0,"Voltar", () -> System.exit(0)),

        });
        menu.run();
    }
}
