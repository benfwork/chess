package server.services;
import server.dataaccess.AuthDAO;
import server.dataaccess.GameDAO;
import server.dataaccess.UserDAO;

public class ClearService {
    private final AuthDAO auth;
    private final GameDAO game;
    private final UserDAO users;

    public ClearService(AuthDAO auth, GameDAO game, UserDAO users) {
        this.auth = auth;
        this.game = game;
        this.users = users;
    }


    public void clearDB(){
        //
        auth.clearAuthData();
        game.clearGameData();
        users.clearUserData();
    }
}
