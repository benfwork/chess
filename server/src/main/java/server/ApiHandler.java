package server;

import com.google.gson.Gson;
import io.javalin.http.Context;
import model.AuthData;
import model.UserData;
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
}