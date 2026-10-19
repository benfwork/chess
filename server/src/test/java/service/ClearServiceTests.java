package service;

import model.AuthData;
import model.UserData;
import org.junit.jupiter.api.Test;
import dataaccess.AuthDAO;
import dataaccess.GameDAO;
import dataaccess.UserDAO;

import static org.junit.jupiter.api.Assertions.*;

public class ClearServiceTests {
    @Test
    public void clearRemovesAllData() {
        AuthDAO auth = new AuthDAO();
        GameDAO games = new GameDAO();
        UserDAO users = new UserDAO();
        ClearService service = new ClearService(auth, games, users);

        users.createUser(new UserData("bob", "password", "bob@example.com"));
        auth.addAuth(new AuthData("bob-token", "bob"));
        games.createGame("Practice");

        service.clearDB();

        assertNull(users.getUser("bob"));
        assertNull(auth.getAuth("bob-token"));
        assertTrue(games.getGames().isEmpty());
    }
}
