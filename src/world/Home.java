package world;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class Home extends Building implements Residential {
    String occupant;

    public Home(String location) {
        super(location);
        this.occupant = "";
    }

    @Override
    public void moveIn(String occupant) {
        if (this.occupant == "") {
            this.occupant = occupant;
        }
    }

    @Override
    public void moveOut(String occupant) {
        if (this.occupant == occupant) {
            this.occupant = "";
        }
    }

    @Override
    public Collection<String> getOccupants() {
        Collection<String> occupantList = new ArrayList<>();
        if (this.occupant != "") {
            occupantList.add(this.occupant);
        }
        return occupantList;
    }
}
