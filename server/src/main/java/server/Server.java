package server;

import com.google.gson.Gson;
import io.javalin.*;
import io.javalin.http.Context;
import dataaccess.*;
import service.*;

import java.util.Map;

public class Server {

    private final Javalin javalin;

    public Server() {
        UserDAO users = new UserDAO();
        AuthDAO auth = new AuthDAO();
        GameDAO games = new GameDAO();

        AuthService authService = new AuthService(users, auth);
        ClearService clearService = new ClearService(auth, games, users);
        GamesService gameService = new GamesService(games, users, auth);

        ApiHandler handler = new ApiHandler(clearService, gameService, authService);


        javalin = Javalin.create(config -> config.staticFiles.add("web"))
                .delete("/db", handler::clear)
                .post("/user", handler::register)
                .post("/session", handler::login)
                .delete("/session", handler::logout)
                .post("/game", handler::createGame)
                .put("/game", handler::joinGame)
                .get("/game", handler::getGames)
                .exception(BadRequestException.class, (e, ctx) -> {
                    returnException(e, ctx, 400);
                })
                .exception(UnauthorizedException.class, (e, ctx) -> {
                    returnException(e, ctx, 401);
                })
                .exception(AlreadyTakenException.class, (e, ctx) -> {
                    returnException(e, ctx, 403);
                })
                .exception(Exception.class, (e, ctx) -> {
                    returnException(e, ctx, 500);
                });



    }

    public int run(int desiredPort) {
        javalin.start(desiredPort);
        return javalin.port();
    }

    private void returnException (Exception e, Context context, int status) {
        var body = new Gson().toJson(Map.of("message", String.format(e.getMessage()), "success", false));
        context.status(status);
        context.json(body);
    }

    public void stop() {
        javalin.stop();
    }
}
