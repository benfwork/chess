package server;

import com.google.gson.Gson;
import server.services.AuthService;
import server.services.ClearService;
import server.services.GamesService;

public class ApiHandler {
    private final ClearService clearService;
    private final GamesService gameService;
    private final AuthService authService;
    private final Gson gson;

    public ApiHandler(ClearService clearService, GamesService gameService, AuthService authService) {
        this.authService = authService;
        this.gameService = gameService;
        this.clearService = clearService;
        this.gson = new Gson();
    }
}