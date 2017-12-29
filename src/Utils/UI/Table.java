package Utils.UI;

import java.util.List;
import java.util.function.Function;

public class Table<T> implements Runnable {
    private List<T> rows;
    private Function<T, String> show;


    public Table(List<T> rows, Function<T, String> show) {
        this.rows = rows;
        this.show = show;
        System.out.println(this.rows.size());
    }

    @Override
    public void run() {
        System.out.println();

        for (T row : this.rows) {
            System.out.println(this.show.apply(row));
        }
    }
}
