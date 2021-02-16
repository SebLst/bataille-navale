package ensta.ships;

public class BattleShip extends AbstractShip {

    public BattleShip(Orientation orientation) {
        super('B', "BattleShip", 4, orientation);
    }

    public BattleShip() {
        super('B', "BattleShip", 4);
    }

}
