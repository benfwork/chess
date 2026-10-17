package server.services;
import server.dataaccess.Auth;
import server.dataaccess.Game;
import server.dataaccess.User;

public class ClearApp {
    private final Auth auth;
    private final Game game;
    private final User user;

    public ClearApp(Auth auth, Game game, User user) {
        this.auth = auth;
        this.game = game;
        this.user = user;
    }


    public void ClearDB(){
        //
        auth.ClearAuthInfo();
    }
}
