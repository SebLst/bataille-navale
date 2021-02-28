package ensta;

import ensta.board.*;
import ensta.ships.*;
import ensta.input.*;

import java.io.Serializable;
import java.util.List;

public class Player {
    /*
     * ** Attributs
     */
    protected Board board;
    protected Board opponentBoard;
    protected int destroyedCount;
    protected AbstractShip[] ships;
    protected boolean lose;

    /*
     * ** Constructeur
     */
    public Player(Board board, Board opponentBoard, List<AbstractShip> ships) {
        this.board = board;
        this.ships = ships.toArray(new AbstractShip[0]);
        this.opponentBoard = opponentBoard;
    }

    /*
     * ** Méthodes
     */

    /**
     * Read keyboard input to get ships coordinates. Place ships on given
     * coodrinates.
     */
    public void putShips() {
        boolean done = false;
        int i = 0;

        do {
            AbstractShip s = ships[i];
            ShipState state = new ShipState(s);
            String msg = String.format("placement %d : %s(%d)", i + 1, s.getName(), s.getSize());
            System.out.println(msg);
            InputHelper.ShipInput res = InputHelper.readShipInput();

            switch (res.orientation) {
                case "n":
                    s.setOrientation(Orientation.NORTH);
                    break;

                case "s":
                    s.setOrientation(Orientation.SOUTH);
                    break;

                case "e":
                    s.setOrientation(Orientation.EAST);
                    break;

                case "w":
                    s.setOrientation(Orientation.WEST);
                    break;

                default:
                    break;
            }

            try {
                board.putShip(state, res.x, res.y);
            } catch (Exception e) {
                i--; // if the ship cannot be placed, we will ask the player to put it again
            }
            ++i;
            done = i == 5;

            board.printShipBoard();
        } while (!done);
    }

    public Hit sendHit(int[] coords) {
        Hit hit = null;
        InputHelper.CoordInput hitInput;
        do {
            System.out.println("où frapper?");
            hitInput = InputHelper.readCoordInput();
            hit = opponentBoard.sendHit(hitInput.x, hitInput.y);
        } while (hit == null);

        // TODO : Game expects sendHit to return BOTH hit result & hit coords.
        // return hit is obvious. But how to return coords at the same time ?
        coords[0] = hitInput.x;
        coords[1] = hitInput.y;
        return hit;
    }

    public AbstractShip[] getShips() {
        return ships;
    }

    public void setShips(AbstractShip[] ships) {
        this.ships = ships;
    }
}
