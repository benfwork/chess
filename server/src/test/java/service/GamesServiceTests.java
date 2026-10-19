package service;

import chess.ChessGame;
import model.AuthData;
import model.GameData;
import model.GameResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.GameDAO;
import dataaccess.UserDAO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GamesServiceTests {
    private GameDAO games;
    private GamesService service;

    @BeforeEach
    public void setUp() {
        games = new GameDAO();
        AuthDAO auth = new AuthDAO();
        auth.addAuth(new AuthData("alice-token", "alice"));
        auth.addAuth(new AuthData("bob-token", "bob"));
        service = new GamesService(games, new UserDAO(), auth);
    }

    @Test
    public void createGameSuccess() {
        GameData game = service.createGame("alice-token", "Practice");
        assertEquals("Practice", game.gameName());
        assertEquals(game, games.getGameById(game.gameID()));
    }

    @Test
    public void joinGameSuccess() throws DataAccessException {
        GameData game = service.createGame("alice-token", "Practice");
        service.joinGame("alice-token", game.gameID(), ChessGame.TeamColor.WHITE);
        service.joinGame("bob-token", game.gameID(), ChessGame.TeamColor.BLACK);

        GameData updated = games.getGameById(game.gameID());
        assertEquals("alice", updated.whiteUsername());
        assertEquals("bob", updated.blackUsername());
    }

    @Test
    public void getGamesSuccess() {
        GameData game = service.createGame("alice-token", "Practice");
        var expected = new GameResponse(game.gameID(), null, null, "Practice");
        assertEquals(List.of(expected), service.getGames("alice-token"));
    }
}
