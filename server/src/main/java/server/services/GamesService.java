package server.services;

import model.AuthData;
import model.GameData;
import server.dataaccess.AuthDAO;
import server.dataaccess.GameDAO;
import server.dataaccess.UserDAO;

public class GamesService {
    private final GameDAO games;
    private final UserDAO users;
    private final AuthDAO auth;

    public GamesService(GameDAO games, UserDAO users, AuthDAO auth) {
        this.games = games;
        this.users = users;
        this.auth = auth;
    }

    public boolean isLoggedIn(String authToken){
        AuthData authData = auth.getAuth(authToken);
        return (authData != null);
    }

    public GameData createGame(String authToken, String gameName){
        if (!isLoggedIn(authToken)){
            throw new UnauthorizedException("Error: unauthorized");
        }

        if (gameName == null || games.getGame(gameName) != null){
            throw new BadRequestException("Error: bad request");
        }

        games.createGame(gameName);

        return games.getGame(gameName);
    }

}
