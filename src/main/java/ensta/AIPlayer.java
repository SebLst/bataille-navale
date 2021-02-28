package ensta;

import java.io.Serializable;
import java.util.List;

import ensta.board.*;
import ensta.ships.*;

public class AIPlayer extends Player {
    /*
     * ** Attribut
     */
    private BattleShipsAI ai;
    private List<AbstractShip> ships;

    /*
     * ** Constructeur
     */
    public AIPlayer(Board ownBoard, Board opponentBoard, List<AbstractShip> ships) {
        super(ownBoard, opponentBoard, ships);
        this.ships = ships;
        ai = new BattleShipsAI(ownBoard, opponentBoard);
    }

    @Override
    public void putShips() {
        ai.putShips(ships);
    }

    @Override
    public Hit sendHit(int[] coords) {
        return ai.sendHit(coords);
    }
}
