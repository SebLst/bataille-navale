package ensta.ships;

abstract class AbstractShip {
    protected Character label;
    protected String name;
    protected int size;
    protected Orientation orientation;

    public AbstractShip(Character label, String name, int size, Orientation orientation) {
        this.label = label;
        this.name = name;
        this.size = size;
        this.orientation = orientation;
    }

    /**
     * Label getter
     * 
     * @return Ship's label
     */
    public Character getLabel() {
        return label;
    }

    /**
     * Name getter
     * 
     * @return Ship's full name
     */
    public String getName() {
        return name;
    }

    /**
     * Ship size getter
     * 
     * @return Ship's size, between 2 and 5
     */
    public int getSize() {
        return size;
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
     * @param orientation NORTH, SOUTH, EAST or WEST
     */
    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }
}
