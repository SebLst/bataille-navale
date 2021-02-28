package ensta.ships;

public class Destroyer extends AbstractShip {

    /**
     * Parameterized constructor. The Destroyer is labelled 'D' and has a length of
     * 2.
     * 
     * @param orientation Ship's orientation
     */
    public Destroyer(Orientation orientation) {
        super('D', "Destroyer", 2, orientation);
    }

    /**
     * Default constructor. Sets orientation to EAST.
     */
    public Destroyer() {
        this(Orientation.EAST);
    }
}
