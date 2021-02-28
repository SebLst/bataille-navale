package ensta.ships;

public class Submarine extends AbstractShip {

    /**
     * Parameterized constructor. The Submarine is labelled 'S' and has a length of
     * 3.
     * 
     * @param orientation Ship's orientation
     */
    public Submarine(Orientation orientation) {
        super('S', "Submarine", 3, orientation);
    }

    /**
     * Default constructor. Sets orientation to EAST.
     */
    public Submarine() {
        this(Orientation.EAST);
    }
}
