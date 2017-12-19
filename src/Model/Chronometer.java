package Model;

public class Chronometer implements IChronometer {
    @Override
    public void start() {
        System.out.println("teste");
    }

    public double getRuntime(){
        return 0.0;
    }

}
