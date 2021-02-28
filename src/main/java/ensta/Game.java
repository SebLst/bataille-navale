package ensta;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import ensta.ships.*;

public class Game {

    /*
     * *** Constante
     */
    public static final File SAVE_FILE = new File("savegame.dat");

    /*
     * *** Attributs
     */
    private Player player1;
    private Player player2;
    private Scanner sin;

    /*
     * *** Constructeurs
     */
    public Game() {
    }

    public Game init() {
        // if (!loadSave()) {
        // init attributes
        sin = new Scanner(System.in);
        System.out.println("Entre ton nom:");
        String userName = sin.nextLine(); // read user name

        // board init
        System.out.println("Entre la taille du plateau (min 10, max 26)");
        int boardSize = sin.nextInt();
        if (boardSize < 10) {
            boardSize = 10;
        } else if (boardSize > 26) {
            boardSize = 26;
        }
        Board b1, b2;
        b1 = new Board(userName, boardSize);
        b2 = new Board("Adversaire", boardSize);

        // players init
        List<AbstractShip> player1Ships = createDefaultShips();
        List<AbstractShip> player2Ships = createDefaultShips();

        this.player1 = new Player(b1, b2, player1Ships);
        this.player2 = new AIPlayer(b2, b1, player2Ships);

        b1.print();
        // place player ships
        player1.putShips();
        player2.putShips();
        // }
        return this;
    }

    public Game initMulti() {
        // init attributes
        sin = new Scanner(System.in);
        System.out.println("Nom du premier joueur:");
        String userName1 = sin.nextLine(); // read user name
        System.out.println("Nom du deuxième joueur:");
        String userName2 = sin.nextLine(); // read user name

        // board init
        System.out.println("Entre la taille du plateau (min 10, max 26)");
        int boardSize = sin.nextInt();
        if (boardSize < 10) {
            boardSize = 10;
        } else if (boardSize > 26) {
            boardSize = 26;
        }
        Board b1, b2;
        b1 = new Board(userName1, boardSize);
        b2 = new Board(userName2, boardSize);

        // players init
        List<AbstractShip> player1Ships = createDefaultShips();
        List<AbstractShip> player2Ships = createDefaultShips();

        this.player1 = new Player(b1, b2, player1Ships);
        this.player2 = new Player(b2, b1, player2Ships);

        // place ships
        System.out.println("A " + userName1 + " de placer ses bateaux");
        b1.print();
        player1.putShips();

        System.out.println("A " + userName2 + " de placer ses bateaux");
        b2.print();
        player2.putShips();

        return this;
    }

    /*
     * *** Méthodes
     */
    public void run() {
        int[] coords = new int[2];
        Board b1 = player1.board;
        Board b2 = player2.board;
        Hit hit;

        // main loop
        b1.print();
        boolean done = false;
        do {
            if (!done) {
                hit = player1.sendHit(coords);
                b1.print();
                System.out.println(makeHitMessage(false /* outgoing hit */, coords, hit));
                done = updateScore();
            }

            if (!done) {
                hit = player2.sendHit(coords);
                b1.print();
                System.out.println(makeHitMessage(true /* incoming hit */, coords, hit));
                done = updateScore();
            }

        } while (!done);

        SAVE_FILE.delete();
        System.out.println(String.format("joueur %d gagne", player1.lose ? 2 : 1));
        sin.close();
    }

    public void runMulti() {
        int[] coords = new int[2];
        Board b1 = player1.board;
        Board b2 = player2.board;
        Hit hit;

        // main loop
        boolean done = false;
        do {
            if (!done) {
                System.out.println("C'est au joueur 1 de tirer !");
                b1.print();
                hit = player1.sendHit(coords);
                b1.print();
                System.out.println(makeHitMessage(false /* outgoing hit */, coords, hit));
                done = updateScore();
            }

            if (!done) {
                System.out.println("C'est au joueur 2 de tirer !");
                b2.print();
                hit = player2.sendHit(coords);
                b2.print();
                System.out.println(makeHitMessage(true /* incoming hit */, coords, hit));
                done = updateScore();
            }

        } while (!done);

        SAVE_FILE.delete();
        System.out.println(String.format("joueur %d gagne", player1.lose ? 2 : 1));
        sin.close();
    }

    // private void save() {
    // try {
    // // TODO bonus 2 : uncomment
    // // if (!SAVE_FILE.exists()) {
    // // SAVE_FILE.getAbsoluteFile().getParentFile().mkdirs();
    // // }

    // // TODO bonus 2 : serialize players

    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // }

    // private boolean loadSave() {
    // if (SAVE_FILE.exists()) {
    // try {
    // // TODO bonus 2 : deserialize players

    // return true;
    // } catch (IOException | ClassNotFoundException e) {
    // e.printStackTrace();
    // }
    // }
    // return false;
    // }

    private boolean updateScore() {
        for (Player player : new Player[] { player1, player2 }) {
            int destroyed = 0;
            for (AbstractShip ship : player.getShips()) {
                if (ship.isSunk()) {
                    destroyed++;
                }
            }

            player.destroyedCount = destroyed;
            player.lose = destroyed == player.getShips().length;
            if (player.lose) {
                return true;
            }
        }
        return false;
    }

    private String makeHitMessage(boolean incoming, int[] coords, Hit hit) {
        String msg;
        ColorUtil.Color color = ColorUtil.Color.RESET;
        switch (hit) {
            case MISS:
                msg = hit.toString();
                break;
            case STRIKE:
                msg = hit.toString();
                color = ColorUtil.Color.RED;
                break;
            default:
                msg = hit.toString() + " coulé";
                color = ColorUtil.Color.RED;
        }
        msg = String.format("%s Frappe en %c%d : %s", incoming ? "<=" : "=>", ((char) ('A' + coords[0])),
                (coords[1] + 1), msg);
        return ColorUtil.colorize(msg, color);
    }

    private static List<AbstractShip> createDefaultShips() {
        return Arrays.asList(new AbstractShip[] { new Destroyer(), new Submarine(), new Submarine(), new Battleship(),
                new Carrier() });
    }

    public static void main(String args[]) {
        new Game().init().run();
    }
}
