package shipgame.ui;

import shipgame.domain.Listener;
import shipgame.domain.Player;
import shipgame.domain.impl.PlayerImpl;

public class GameUI {

    public void startGame() {
        Player[] players = setupGame();
        play(players[0], players[1]);
    }

    private Player[] setupGame() {
//        System.out.println("Player 1 => Enter your name:");
//        Player player1 = new PlayerImpl(Listener.listenForString());
        // DEV
        Player player1 = new PlayerImpl("Player 1");

//        System.out.println("Player 2 => Enter your name:");
//        Player player2 = new PlayerImpl(Listener.listenForString());
        // DEV
        Player player2 = new PlayerImpl("Player 2");

//        System.out.println("Choose field size! Minimum size: 5");
//        int size = new ListenerImpl().listenForInt(4);
        // DEV
        int size = 10;

        player1.createMap(size).setupShips();
        player2.createMap(size).setupShips();

        return new Player[]{player1, player2};
    }

    private void play(Player player1, Player player2) {
        round(player1, player2);

        round(player2, player1);

        if (player1.hasShips() && player2.hasShips()) {
            Listener.onRoundEnd();
            play(player1, player2);
        } else {
            endGame();
        }
    }

    private void round(Player player, Player attacked) {
        Listener.onNewRound(player);

        int x = Listener.listenForIntWithin(0, player.getMap().length + 1);
        int y = Listener.listenForIntWithin(0, player.getMap().length + 1);

        playerAttack(player, attacked, new int[]{x, y});

    }

    private void playerAttack(Player player, Player attacked, int[] coords){
        Listener.onAttack(player, attacked, coords);
        attacked.checkHit(new int[]{coords[0] - 1, coords[1] - 1});
        Listener.printMap(attacked.getMap());

    }

    private void endGame(Player player1, Player player2) {
        if (player1.hasShips()) System.out.println(player2 + " won!");
        else if(player2.hasShips()) System.out.println(player1 + " won!");
    }
}