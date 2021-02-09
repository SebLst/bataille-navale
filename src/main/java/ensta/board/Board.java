package ensta.board;

public class Board {
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
                hitBoard[i][j] = false;
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
     * Shows the ship board and the hit board in the terminal
     */
    public void print() {
        // Printing the ship board
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

        // printing the hit board
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
    }

    /**
     * Board name getter
     * 
     * @return Name of the board
     */
    public String getName() {
        return name;
    }
}
