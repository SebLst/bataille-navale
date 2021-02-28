package ensta.ships;

import ensta.ColorUtil;
import ensta.ColorUtil.Color;

public class ShipState {

    protected AbstractShip ship;
    private boolean struck = false;

    /**
     * Parameterized constructor.
     * 
     * @param ship Reference ship for the state
     */
    public ShipState(AbstractShip ship) {
        this.ship = ship;
    }

    /**
     * Marks the ship has damaged
     */
    public void addStrike() {
        if (!struck) { // the ship cannot take multiple damage from the same place
            ship.addStrike();
            struck = true;
        }
    }

    /**
     * Returns the state of the ship
     * 
     * @return True if the ship is struck
     */
    public boolean isStruck() {
        return struck;
    }

    /**
     * Ship getter
     * 
     * @return Ship concerned by the state
     */
    public AbstractShip getShip() {
        return ship;
    }

    public String toString() {
        if (struck) {
            return ColorUtil.colorize(ship.getLabel(), Color.RED);
        } else {
            return ColorUtil.colorize(ship.getLabel(), Color.WHITE);
        }
    }
}
