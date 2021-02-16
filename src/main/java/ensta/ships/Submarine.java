package ensta.ships;

public class Submarine extends AbstractShip {

    public Submarine(Orientation orientation) {
        super('S', "Submarine", 3, orientation);
    }

    public Submarine() {
        super('S', "Submarine", 3);
    }

}
