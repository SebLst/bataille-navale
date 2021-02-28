package ensta;

import java.io.Serializable;
import java.util.List;

import ensta.ships.*;
import ensta.ships.AbstractShip.Orientation;

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
            String msg = String.format("placer %d : %s(%d)", i + 1, s.getName(), s.getLength());
            System.out.println(msg);
            InputHelper.ShipInput res = InputHelper.readShipInput();
            Orientation orientation = Orientation.EAST;
            switch (res.orientation) {
                case "n":
                    orientation = Orientation.NORTH;
                    break;

                case "e":
                    orientation = Orientation.EAST;
                    break;

                case "s":
                    orientation = Orientation.SOUTH;
                    break;

                case "w":
                    orientation = Orientation.WEST;
                    break;

                default:
                    break;
            }
            s.setOrientation(orientation);

            // if the ship fits the board
            if (board.isFree(s, res.x, res.y)) {
                board.putShip(s, res.x, res.y); // put it
            } else {
                i--; // else we try again
            }

            ++i;
            done = i == ships.length;

            board.printShips();
        } while (!done);
    }

    /**
     * Reads player input to shot on the opponent board.
     * 
     * @param coords coordinates of the strike
     * @return Status of the hit
     */
    public Hit sendHit(int[] coords) {
        boolean done;
        Hit hit = null;

        do {
            System.out.println("où frapper?");
            InputHelper.CoordInput hitInput = InputHelper.readCoordInput();
            coords[0] = hitInput.x;
            coords[1] = hitInput.y;

            if (coords[0] < 0 || coords[0] >= board.getSize() || coords[1] < 0 || coords[1] >= board.getSize()) {
                System.out.println("Illegal shot. Try again.");
                done = false; // if the player shoots outside the boundaries of the board
            } else if (board.strikes[coords[0]][coords[1]] != null) {
                System.out.println("You already shot there! Shot elsewhere!");
                done = false; // if the player already shot here
            } else {
                System.out.println("Fire!");
                done = true;
            }
        } while (!done);

        hit = opponentBoard.sendHit(coords[0], coords[1]); // send strike to opponent board
        board.setHit(hit != Hit.MISS, coords[0], coords[1]); // set the strike on the player board (hit or miss)
        return hit;
    }

    public AbstractShip[] getShips() {
        return ships;
    }

    public void setShips(AbstractShip[] ships) {
        this.ships = ships;
    }
}
