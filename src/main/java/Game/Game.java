package Game;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Game {
    Map<String, Player> players = new HashMap<>();

    public void register(Player player) {
        players.put(player.getName(), player);
    }

    public int round (String playerName1, String playerName2) {
        Player player1 = findByName(playerName1);
        Player player2 = findByName(playerName2);

        if (player1.getStrength() > player2.getStrength()) {
            return 1;
        }
        if (player2.getStrength() > player1.getStrength()) {
            return 2;
        }
        return 0;
    }

    private Player findByName(String name) {
        Player player = players.get(name);
        if (player == null) {
            throw new NotRegisteredException("Игрок с именем " + name + " не зарегистрирован");
        }
        return player;
    }

}
