package ensta.ships;

public class Battleship extends AbstractShip {

    /**
     * Parameterized constructor. The battleship is labelled 'B' and has a length of
     * 4.
     * 
     * @param orientation Ship's orientation
     */
    public Battleship(Orientation orientation) {
        super('B', "Battleship", 4, orientation);
    }

    /**
     * Default constructor. Sets orientation to EAST.
     */
    public Battleship() {
        this(Orientation.EAST);
    }
}
