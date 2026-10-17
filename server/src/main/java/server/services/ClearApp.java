package server.services;
import server.dataaccess.Auth;
import server.dataaccess.Games;
import server.dataaccess.Users;

public class ClearApp {
    private final Auth auth;
    private final Games game;
    private final Users users;

    public ClearApp(Auth auth, Games game, Users users) {
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
