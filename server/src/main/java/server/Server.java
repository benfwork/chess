package server;

import io.javalin.*;
import server.dataaccess.AuthDAO;
import server.dataaccess.GameDAO;
import server.dataaccess.UserDAO;
import services.ClearApp;

public class Server {

    private final Javalin javalin;

    public Server() {
        UserDAO users = new UserDAO();
        AuthDAO auth = new AuthDAO();
        GameDAO games = new GameDAO();

        javalin = Javalin.create(config -> config.staticFiles.add("web"));

        // Register your endpoints and exception handlers here.

        // clear db
        javalin.delete("db", ctx -> {
            ClearApp.ClearDB();
        });

        // register new user
        javalin.post("user", ctx -> {

        });

        // login
        javalin.post("session", ctx -> {

        });

        // logout
        javalin.delete("session", ctx -> {

        });

        // list games
        javalin.get("game", ctx -> {

        });

        // create game
        javalin.post("game", ctx -> {

        });

        // logout
        javalin.put("game", ctx -> {

        });

    }

    public int run(int desiredPort) {
        javalin.start(desiredPort);
        return javalin.port();
    }

    public void stop() {
        javalin.stop();
    }
}
