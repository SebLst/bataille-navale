package ensta.board;

import ensta.ships.AbstractShip;
import ensta.ships.ShipState;
import ensta.colorui.*;
import ensta.colorui.ColorUtil.Color;

public class Board implements IBoard {
    protected String name;
    protected int boardSize;
    protected ShipState[][] shipBoard;
    protected Boolean[][] hitBoard;

    /**
     * 
     * @param name      Board name
     * @param boardSize Board size, if < 10, is set to 10, if > 26, is set to 26
     */
    public Board(String name, int boardSize) {
        this.name = name;

        if (boardSize < 10) {
            System.out.println("Board is too small! Board size set to 10.");
            boardSize = 10;
        } else if (boardSize > 26) {
            System.out.println("Board is too big! Board size set to 26.");
            boardSize = 26;
        }

        this.boardSize = boardSize;
        this.shipBoard = new ShipState[boardSize][boardSize];
        this.hitBoard = new Boolean[boardSize][boardSize];

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                shipBoard[i][j] = null;
            }
        }
    }

    /**
     * Constructs a 10x10 board
     * 
     * @param name Board name
     */
    public Board(String name) {
        this(name, 10);
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
     * Prints the ship board in the terminal
     */
    public void printShipBoard() {
        System.out.println("Ships :");
        for (int j = 0; j < boardSize + 1; j++) {
            for (int i = 0; i < boardSize + 1; i++) {

                // top left white space
                if (i == 0 && j == 0) {
                    space(boardSize / 10 + 2);
                } else if (i != 0 && j == 0) { // first row
                    System.out.print(Character.toString(64 + i)); // 65 is ascii for A
                    space(1);
                } else if (i == 0 && j != 0) { // first column
                    System.out.print(j);
                    space(boardSize / 10 + 1 - j / 10);
                } else { // the board itself
                    if (shipBoard[i - 1][j - 1] == null) {
                        System.out.print(".");
                    } else {
                        System.out.print(shipBoard[i - 1][j - 1].toString());
                    }
                    space(1);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Prints the hit board in the terminal
     */
    public void printHitBoard() {
        System.out.println("Hits :");
        for (int j = 0; j < boardSize + 1; j++) {
            for (int i = 0; i < boardSize + 1; i++) {

                if (i == 0 && j == 0) {
                    space(boardSize / 10 + 2);
                } else if (i != 0 && j == 0) {
                    System.out.print(Character.toString(64 + i));
                    space(1);
                } else if (i == 0 && j != 0) {
                    System.out.print(j);
                    space(boardSize / 10 + 1 - j / 10);
                } else {
                    if (hitBoard[i - 1][j - 1] == null) {
                        System.out.print(".");
                    } else if (hitBoard[i - 1][j - 1]) {
                        System.out.print(ColorUtil.colorize("X", Color.RED)); // hit
                    } else if (!hitBoard[i - 1][j - 1]) {
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
     * Shows the ship board and the hit board in the terminal
     */
    public void print() {
        printShipBoard();
        printHitBoard();
    }

    /**
     * Board name getter
     * 
     * @return Name of the board
     */
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return boardSize;
    }

    /**
     * Tests if a ship can fit in the board
     * 
     * @param ship The ship tested
     * @param x    X position, begins at 0
     * @param y    y position, begins at 0
     * @return false is the ship doesn't fit, true if it fits
     */
    private boolean shipFits(AbstractShip ship, int x, int y) {
        for (int i = 0; i < ship.getSize(); i++) {
            if (hasShip(x, y)) {
                return false;
            }
            switch (ship.getOrientation()) {
                case NORTH:
                    y--; // y = 0 is the top of the board
                    break;

                case SOUTH:
                    y++;
                    break;

                case EAST:
                    x++;
                    break;

                case WEST:
                    x--;
                    break;

                default:
                    break;
            }
        }
        if (x < 0 || x >= boardSize || y < 0 || y >= boardSize) {
            System.out.println("Ship doesn't fit");
            return false;
        }
        return true;
    }

    @Override
    public void putShip(ShipState ship, int x, int y) throws Exception {
        if (!shipFits(ship.getShip(), x, y)) {
            throw new Exception(ship.getShip().getName() + " doesn't fit in the board");
        }

        shipBoard[x][y] = ship;
        for (int i = 0; i < ship.getShip().getSize() - 1; i++) {
            switch (ship.getShip().getOrientation()) {
                case NORTH:
                    y--; // y = 0 is the top of the board
                    break;

                case SOUTH:
                    y++;
                    break;

                case EAST:
                    x++;
                    break;

                case WEST:
                    x--;
                    break;

                default:
                    break;
            }
            shipBoard[x][y] = ship;
        }

    }

    @Override
    public boolean hasShip(int x, int y) {
        // TODO error handling
        if (shipBoard[x][y] == null) {
            return false;
        }
        return true;
    }

    @Override
    public void setHit(boolean hit, int x, int y) {
        // TODO error handling
        if (hasShip(x, y) || hit) {
            hitBoard[x][y] = true;
        } else {
            hitBoard[x][y] = false;
        }
    }

    @Override
    public Boolean getHit(int x, int y) {
        // TODO error handling
        return hitBoard[x][y];
    }
}
