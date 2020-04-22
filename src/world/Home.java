package world;

import java.util.Arrays;
import java.util.Collection;

public class Home extends Building implements Residential {
    String occupant;

    public Home(String location) {
        super(location);
        this.occupant = "";
    }

    @Override
    public void moveIn(String occupant) {

    }

    @Override
    public void moveOut(String occupant) {

    }

    @Override
    public Collection<String> getOccupants() {
        return null;
    }

    /* YOUR CODE HERE */
}
