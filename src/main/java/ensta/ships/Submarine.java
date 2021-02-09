package ensta.ships;

public class Submarine extends AbstractShip {

    public Submarine(Character label, String name, int size, Orientation orientation) {
        super('S', "Submarine", 3, orientation);
    }

}
