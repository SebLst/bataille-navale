package ensta.ships;

public class Carrier extends AbstractShip {

    /**
     * Parameterized constructor. The Aircraft Carrier is labelled 'C' and has a
     * length of 5.
     * 
     * @param orientation Ship's orientation
     */
    public Carrier(Orientation orientation) {
        super('C', "Aircraft Carrier", 5, orientation);
    }

    /**
     * Default constructor. Sets orientation to EAST.
     */
    public Carrier() {
        this(Orientation.EAST);
    }
}
