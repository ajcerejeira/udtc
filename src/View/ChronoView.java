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
                new Option("Pause", this::pause),
                new Option("Stop", this::stop),
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

        Menu menu = new Menu(new Option[] {
                new Option("Resume", this::resume),
        });
    }

    private void resume() {
        chronometer.resume();
    }

    @Override
    public void run() {
        Menu menu = new Menu(new Option[] {
                new Option("Voltar", () -> System.exit(0)),
                new Option("Start", this::start),
        });
        menu.run();
    }
}
