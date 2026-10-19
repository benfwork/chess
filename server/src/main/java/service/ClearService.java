package service;
import dataaccess.AuthDAO;
import dataaccess.GameDAO;
import dataaccess.UserDAO;

public class ClearService {
    private final AuthDAO auth;
    private final GameDAO games;
    private final UserDAO users;

    public ClearService(AuthDAO auth, GameDAO game, UserDAO users) {
        this.auth = auth;
        this.games = game;
        this.users = users;
    }


    public void clearDB(){
        //
        auth.clearAuthData();
        games.clearGameData();
        users.clearUserData();
    }
}
