package Game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;
    private Player player1;
    private Player player2;
    private Player player3;

    @BeforeEach
    void setUp() {
        game = new Game();
        player1 = new Player(1, "Alice", 10);
        player2 = new Player(2, "Bob", 20);
        player3 = new Player(3, "Charlie", 10);
        game.register(player1);
        game.register(player2);
        game.register(player3);
    }

    //  первый игрок сильнее
    @Test
    void shouldFirstPlayerStronger() {
        game.register(new Player(4, "Weak", 15));
        int actual = game.round("Bob", "Weak");
        int expected = 1;
        assertEquals(expected, actual);
    }

    //  второй игрок сильнее
    @Test
    void shouldSecondPlayerStronger() {
        int actual = game.round("Alice", "Bob");
        int expected = 2;
        assertEquals(expected, actual);
    }

    //  ничья (равная сила)
    @Test
    void shouldStrengthsEqual() {
        int actual = game.round("Alice", "Charlie");
        int expected = 0;
        assertEquals(expected, actual);
    }

    //  первый игрок не зарегистрирован
    @Test
    void shouldFirstPlayerNotRegistered() {
        assertThrows(NotRegisteredException.class,
                () -> game.round("Unknown", "Bob"));
    }

    //  второй игрок не зарегистрирован
    @Test
    void shouldSecondPlayerNotRegistered() {
        assertThrows(NotRegisteredException.class,
                () -> game.round("Alice", "Unknown"));
    }

    //  оба не зарегистрированы
    @Test
    void WhenBothPlayersNotRegistered() {
        assertThrows(NotRegisteredException.class,
                () -> game.round("Rocky", "Unknown"));
    }
}