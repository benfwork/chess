package server;

import com.google.gson.Gson;
import io.javalin.http.Context;
import model.UserData;
import server.dataaccess.UserDAO;
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

    public void clear(Context ctx) throws Exception{
        clearService.clearDB();

        ctx.status(200)
                .contentType("application/json")
                .result("{}");
    }

    public void register(Context ctx) throws Exception {
        UserData registerRequest = gson.fromJson(ctx.body(), UserData.class);

        var result = authService.register(registerRequest);


    }
}