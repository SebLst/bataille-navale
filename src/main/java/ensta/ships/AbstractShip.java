package ensta.ships;

public abstract class AbstractShip {

    public enum Orientation {
        NORTH, SOUTH, EAST, WEST
    }

    protected final Character label; // isn't supposed to change
    protected final String name; // same here
    protected final int length;
    protected Orientation orientation;
    protected int strikeCount; // number of strikes taken

    /**
     * AbstractShip constructor
     * 
     * @param label       Ship's label
     * @param name        Ship's name
     * @param length      Ship's length
     * @param orientation Ship's orientation
     */
    protected AbstractShip(Character label, String name, int length, Orientation orientation) {
        this.label = label;
        this.name = name;
        this.length = length;
        this.orientation = orientation;
        this.strikeCount = 0;
    }

    /**
     * Ship's label getter
     * 
     * @return Ship's label
     */
    public Character getLabel() {
        return label;
    }

    /**
     * Ship's name getter
     * 
     * @return Ship's name
     */
    public String getName() {
        return name;
    }

    /**
     * Ship's length getter
     * 
     * @return Ship's length
     */
    public int getLength() {
        return length;
    }

    /**
     * Ship's orientation getter
     * 
     * @return Ship's orientation
     */
    public Orientation getOrientation() {
        return orientation;
    }

    /**
     * Ship's orientation setter
     * 
     * @param orientation Ship's orientation
     */
    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    /**
     * Increments the number of strikes taken by the ship
     */
    public void addStrike() {
        strikeCount++;
    }

    /**
     * Checks if the ship is sunk
     * 
     * @return State of the ship (true = sunk)
     */
    public boolean isSunk() {
        return strikeCount == length;
    }
}
