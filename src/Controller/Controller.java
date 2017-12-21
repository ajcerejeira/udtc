package Controller;

import Model.Chronometer;
import View.ChronoView;

import java.util.Scanner;
import static java.lang.System.out;

public class Controller implements Runnable{

    @Override
    public void run(){
        Scanner s = new Scanner(System.in);

        switch (s.nextInt()){
            case 1:
                ChronoView c =  new ChronoView(new Chronometer());
                c.run();
                break;
            case 2:
                break;
            default:
                break;
        }
    }
}
