package server;

import com.google.gson.Gson;
import io.javalin.http.Context;
import model.*;
import server.dataaccess.DataAccessException;
import server.services.*;
import java.util.Map;

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

    public void clear(Context ctx) throws Exception{
        clearService.clearDB();

        ctx.status(200)
                .contentType("application/json")
                .result("{}");
    }

    public void register(Context ctx) throws Exception {
        UserData registerRequest = gson.fromJson(ctx.body(), UserData.class);

        AuthData result = authService.register(registerRequest);

        ctx.status(200)
                .contentType("application/json")
                .result(gson.toJson(result));
    }

    public void login(Context ctx) throws Exception{
        LoginRequest loginRequest = gson.fromJson(ctx.body(), LoginRequest.class);

        AuthData result = authService.login(loginRequest);

        ctx.status(200)
                .contentType("application/json")
                .result(gson.toJson(result));
    }

    public void logout(Context ctx) throws Exception{
        String authToken = ctx.header("authorization");
        authService.logout(authToken);

        ctx.status(200)
                .contentType("application/json")
                .result("{}");
    }

    public void createGame(Context ctx) {
        String authToken = ctx.header("authorization");
        CreateGameRequest request = gson.fromJson(ctx.body(), CreateGameRequest.class);
        String name = request.gameName();

        GameData gameData = gameService.createGame(authToken, name);
        var response = Map.of("gameID", gameData.gameID());

        ctx.status(200)
                .contentType("application/json")
                .result(gson.toJson(response));
    }

    public void joinGame(Context ctx) throws DataAccessException {
        String authToken = ctx.header("authorization");
        JoinGameRequest request = gson.fromJson(ctx.body(), JoinGameRequest.class);

        var playerColor = request.playerColor();
        var gameId = request.gameID();

        gameService.joinGame(authToken, gameId, playerColor);

        ctx.status(200)
                .contentType("application/json")
                .result("{}");
    }
}