import business.*;

public class UDTC {
    public static void main(String[] args) throws InterruptedException {
        Chronometer c = new Chronometer();
        c.start();
        Thread.sleep(500);
        c.stop();
        System.out.println("tempo: " + c.getCurrentTime());
    }
}
