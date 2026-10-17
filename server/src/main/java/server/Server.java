package server;

import io.javalin.*;
import server.dataaccess.AuthDAO;
import server.dataaccess.GameDAO;
import server.dataaccess.UserDAO;
import server.services.AuthService;
import server.services.ClearService;
import server.services.GamesService;

public class Server {

    private final Javalin javalin;

    public Server() {
        UserDAO users = new UserDAO();
        AuthDAO auth = new AuthDAO();
        GameDAO games = new GameDAO();

        AuthService authService = new AuthService(users, auth);
        ClearService clearService = new ClearService(auth, games, users);
        GamesService gameService = new GamesService(games, users);

        ApiHandler handler = new ApiHandler(clearService, gameService, authService);


        javalin = Javalin.create(config -> config.staticFiles.add("web"));
        // clear db
        javalin.delete("/db", handler::clear);
        javalin.post("/user", handler::register);

    }

    public int run(int desiredPort) {
        javalin.start(desiredPort);
        return javalin.port();
    }

    public void stop() {
        javalin.stop();
    }
}
