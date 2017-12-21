package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Travels implements ITravels {
    private List<Travel> record;

    public Travels() {
        this.record = new ArrayList<>();
    }

    public Travels(List<Travel> record) {
        record.forEach(t -> this.record.add(t.clone()));
    }

    public Travels(Travels record){
        this.record = new ArrayList<Travel>();
    }

    public List<Travel> getTravels(){
        return this.record.stream().map(Travel::clone).collect(Collectors.toList());
    }

    @Override
    public Travels clone() {
        return new Travels(this);
    }

}
