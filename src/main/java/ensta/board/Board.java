package ensta.board;

import ensta.ships.AbstractShip;

public class Board implements IBoard {
    protected String name;
    protected int boardSize;
    protected Character[][] shipBoard;
    protected boolean[][] hitBoard;

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
        this.shipBoard = new Character[boardSize][boardSize];
        this.hitBoard = new boolean[boardSize][boardSize];

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                shipBoard[i][j] = '.';
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
    private void printShipBoard() {
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
                    System.out.print(shipBoard[i - 1][j - 1]);
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
    private void printHitBoard() {
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
                    if (hitBoard[i - 1][j - 1]) {
                        System.out.print("X"); // hit
                    } else {
                        System.out.print("."); // miss
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
     * @return
     */
    private boolean shipFits(AbstractShip ship, int x, int y) {
        switch (ship.getOrientation()) {
            case NORTH:
                y = y - ship.getSize(); // y = 0 is the top of the board
                break;

            case SOUTH:
                y = y + ship.getSize();
                break;

            case EAST:
                x = x + ship.getSize();
                break;

            case WEST:
                x = x - ship.getSize();
                break;

            default:
                break;
        }
        if (x < 0 || x >= boardSize || y < 0 || y >= boardSize) {
            return false;
        }
        return true;
    }

    @Override
    public void putShip(AbstractShip ship, int x, int y) throws Exception {
        if (!shipFits(ship, x, y)) {
            throw new Exception(ship.getName() + " doesn't fit in the board");
        }

        shipBoard[x][y] = ship.getLabel();
        for (int i = 0; i < ship.getSize() - 1; i++) {
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
            shipBoard[x][y] = ship.getLabel();
        }

    }

    @Override
    public boolean hasShip(int x, int y) {
        // TODO error handling
        if (shipBoard[x][y] == '.') {
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
