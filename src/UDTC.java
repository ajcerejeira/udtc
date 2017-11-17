import business.*;

public class UDTC {
    public static void main(String[] args) throws InterruptedException {
        Chronometer c = new Chronometer();

        System.out.println("Start timer");
        c.start();
        Thread.sleep(1000);

        System.out.println("Pausa de 2 segundos");
        c.pause();
        Thread.sleep(2000);
        c.resume();

        System.out.println("Stop timer");
        c.stop();

        System.out.println("Tempo total: " + c.getElapsedTime());
    }
}
