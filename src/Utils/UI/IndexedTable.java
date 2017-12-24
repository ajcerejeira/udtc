package Utils.UI;

public class IndexedTable implements Runnable{
    private Object[] rows;
    private int n;

    public IndexedTable(int n, Object[] rows) {
        this.n = n;
        this.rows = rows.clone();
    }

    @Override
    public void run() {
        for (int i=0; i<=this.n ; i++) {
            int index=i+1;
            System.out.println("[" + index + "] " + this.rows[i]);
        }
    }
}

