package ensta.board;

import ensta.ships.*;

public interface IBoard {

    /**
     * Get the size of the grids contained in the Board
     * 
     * @return the size of the grids contained in the Board
     */
    int getSize();

    /**
     * Put the given ship at the given position
     * 
     * @param ship The ship to place on the board
     * @param x    Begins at 0!
     * @param y    Begins at 0!
     */
    void putShip(ShipState ship, int x, int y) throws Exception;

    /**
     * Get if a ship is placed at the given position
     * 
     * @param x Begins at 0!
     * @param y Begins at 0!
     * @return true if a ship is located at the given position
     */
    boolean hasShip(int x, int y);

    /**
     * Set the state of the hit at a given position
     * 
     * @param hit true if the hit must be set to successful
     * @param x   Begins at 0!
     * @param y   Begins at 0!
     */
    void setHit(boolean hit, int x, int y);

    /**
     * Get the state of a hit at the given position
     * 
     * @param x Begins at 0!
     * @param y Begins at 0!
     * @return true if the hit is successful
     */
    Boolean getHit(int x, int y);
}
