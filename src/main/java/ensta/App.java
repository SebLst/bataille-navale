package ensta;

public class App {
    public static void main(String[] args) {
        Game game = new Game();

        // mode 1 joueur
        // game.init();
        // game.run();

        // mode 2 joueurs
        game.initMulti();
        game.runMulti();
    }
}
