package server;

import io.javalin.*;

public class Server {

    private final Javalin javalin;

    public Server() {
        javalin = Javalin.create(config -> config.staticFiles.add("web"));

        // Register your endpoints and exception handlers here.

        // clear db
        javalin.delete("db", ctx -> {
            System.out.println(ctx.body());

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
