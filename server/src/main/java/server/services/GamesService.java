package server.services;

import server.dataaccess.GameDAO;
import server.dataaccess.UserDAO;

public class GamesService {
    private final GameDAO games;
    private final UserDAO users;

    public GamesService(GameDAO games, UserDAO users) {
        this.games = games;
        this.users = users;
    }



}
