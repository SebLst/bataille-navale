package ensta.ships;

public class AircraftCarrier extends AbstractShip {

    public AircraftCarrier(Orientation orientation) {
        super('C', "Aircraft Carrier", 5, orientation);
    }

    public AircraftCarrier() {
        super('C', "Aircraft Carrier", 5);
    }

}
