package ensta.ships;

import ensta.colorui.*;
import ensta.colorui.ColorUtil.Color;

public class ShipState {
    AbstractShip ship;
    boolean struck = false;

    public ShipState(AbstractShip ship) {
        this.ship = ship;
    }

    public void addStrike() {
        struck = true;
        ship.addStrike();
    }

    /**
     * Returns the state of the ship
     * 
     * @return true if the ship received at least one hit
     */
    public boolean isStruck() {
        return struck;
    }

    /**
     * Returns the state of the ship
     * 
     * @return true if the ship is destroyed
     */
    public boolean isSunk() {
        return ship.isSunk();
    }

    /**
     * Returns the ship's label (in red if stricken)
     */
    public String toString() {
        if (struck) {
            return ColorUtil.colorize(ship.getLabel(), Color.RED);
        } else {
            return ship.getLabel().toString();
        }
    }

    /**
     * Ship getter
     * 
     * @return Ship associated with the state
     */
    public AbstractShip getShip() {
        return ship;
    }
}
