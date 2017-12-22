package Utils.UI;


public class Table implements Runnable {
    private Object[] rows;

    public Table(Object[] rows) {
        this.rows = rows.clone();
    }

    @Override
    public void run() {
        for (Object row : this.rows) {
            System.out.println(row);
        }
    }
}
