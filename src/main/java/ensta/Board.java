package ensta;

import ensta.ColorUtil.Color;
import ensta.ships.AbstractShip;
import ensta.ships.ShipState;

public class Board implements IBoard {

    protected final String name;
    protected final int size;
    protected ShipState[][] ships;
    protected Boolean[][] strikes;

    /**
     * Board constructor
     * 
     * @param name Board name
     * @param size Board size
     */
    public Board(String name, int size) {
        this.name = name;
        this.size = size;
        this.ships = new ShipState[size][size];
        this.strikes = new Boolean[size][size];
    }

    /**
     * Board constructor. Sets the size to 10.
     * 
     * @param name Board name
     */
    public Board(String name) {
        this(name, 10);
    }

    /**
     * Board name getter
     * 
     * @return Board name
     */
    public String getName() {
        return name;
    }

    /**
     * Board size getter
     * 
     * @return Board size
     */
    public int getSize() {
        return size;
    }

    /**
     * Creates a number of spaces equal to size (used for padding)
     * 
     * @param size Number of spaces
     */
    private void space(int size) {
        for (int i = 0; i < size; i++) {
            System.out.print(" ");
        }
    }

    /**
     * Prints the ships in the terminal
     */
    public void printShips() {
        System.out.println(name + "'s ships :");
        for (int j = 0; j < size + 1; j++) {
            for (int i = 0; i < size + 1; i++) {

                // top left white space
                if (i == 0 && j == 0) {
                    space(size / 10 + 2);
                } else if (i != 0 && j == 0) { // first row
                    System.out.print(Character.toString(64 + i)); // 65 is ascii for A
                    space(1);
                } else if (i == 0 && j != 0) { // first column
                    System.out.print(j);
                    space(3 - String.valueOf(j).length());
                } else { // the board itself
                    if (ships[i - 1][j - 1] == null) {
                        System.out.print(".");
                    } else {
                        System.out.print(ships[i - 1][j - 1].toString());
                    }
                    space(1);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Prints the strikes in the terminal
     */
    public void printStrikes() {
        System.out.println(name + "'s strikes :");
        for (int j = 0; j < size + 1; j++) {
            for (int i = 0; i < size + 1; i++) {

                if (i == 0 && j == 0) {
                    space(size / 10 + 2);
                } else if (i != 0 && j == 0) {
                    System.out.print(Character.toString(64 + i));
                    space(1);
                } else if (i == 0 && j != 0) {
                    System.out.print(j);
                    space(3 - String.valueOf(j).length());
                } else {
                    if (strikes[i - 1][j - 1] == null) {
                        System.out.print("."); // no strike
                    } else if (strikes[i - 1][j - 1]) {
                        System.out.print(ColorUtil.colorize("X", Color.RED)); // hit
                    } else if (!strikes[i - 1][j - 1]) {
                        System.out.print(ColorUtil.colorize("X", Color.WHITE)); // miss
                    }
                    space(1);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Shows the ships and the strikes in the terminal
     */
    public void print() {
        printShips();
        printStrikes();
    }

    /**
     * Checks if a ship can be placed
     * 
     * @param ship Ship to be placed
     * @param x    X coordinate (begins at 0)
     * @param y    Y coordinate (begins at 0)
     */
    public boolean isFree(AbstractShip ship, int x, int y) {
        for (int i = 0; i < ship.getLength(); i++) {
            if (x < 0 || y < 0 || x >= size || y >= size) {
                return false;
            } else if (ships[x][y] != null) {
                return false;
            }
            switch (ship.getOrientation()) {
                case EAST:
                    x++;
                    break;

                case WEST:
                    x--;
                    break;

                case NORTH:
                    y--;
                    break;

                case SOUTH:
                    y++;
                    break;

                default:
                    break;
            }
        }
        return true;
    }

    @Override
    public void putShip(AbstractShip ship, int x, int y) {
        for (int i = 0; i < ship.getLength(); i++) {
            ships[x][y] = new ShipState(ship);

            switch (ship.getOrientation()) {
                case EAST:
                    x++;
                    break;

                case WEST:
                    x--;
                    break;

                case NORTH:
                    y--;
                    break;

                case SOUTH:
                    y++;
                    break;

                default:
                    break;
            }
        }

    }

    @Override
    public boolean hasShip(int x, int y) {
        if (ships[x][y] == null) {
            return false;
        }
        return true;
    }

    @Override
    public void setHit(boolean hit, int x, int y) {
        strikes[x][y] = hit;
    }

    @Override
    public Boolean getHit(int x, int y) {
        return strikes[x][y];
    }

    @Override
    public Hit sendHit(int x, int y) {
        if (ships[x][y] == null) {
            return Hit.fromInt(-1); // miss
        } else {
            ships[x][y].addStrike(); // hit
            if (ships[x][y].getShip().isSunk()) { // if the ship sunks
                System.out.println(ships[x][y].getShip().getName() + " coulé");
                return Hit.fromInt(ships[x][y].getShip().getLength()); // return the ship destroyed
            }
            return Hit.fromInt(-2);
        }
    }

}
