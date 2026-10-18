package server.services;

import chess.ChessGame;
import model.AuthData;
import model.GameData;
import model.GameResponse;
import org.jetbrains.annotations.NotNull;
import server.dataaccess.AuthDAO;
import server.dataaccess.DataAccessException;
import server.dataaccess.GameDAO;
import server.dataaccess.UserDAO;

import java.util.ArrayList;
import java.util.HashSet;

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

    public void joinGame(String authToken, int gameId, ChessGame.TeamColor playerColor) throws DataAccessException {
        AuthData user = auth.getAuth(authToken);
        if (user == null) {
            throw new UnauthorizedException("Error: unauthorized");
        }

        if (playerColor == null) {
            throw new BadRequestException("Error: bad request");
        }

        GameData currentGame = games.getGameById(gameId);
        if (currentGame == null) {
            throw new BadRequestException("Error: bad request");
        }

        GameData updated = getUpdated(playerColor, currentGame, user);

        games.updateGame(updated);
    }

    @NotNull
    private static GameData getUpdated(ChessGame.TeamColor playerColor, GameData current, AuthData user) {
        boolean isWhite = playerColor == ChessGame.TeamColor.WHITE;
        String occupantUsername = isWhite ? current.whiteUsername() : current.blackUsername();

        if (occupantUsername != null) {
            throw new AlreadyTakenException("Error: already taken");
        }

        return new GameData(
                current.gameID(),
                isWhite ? user.username() : current.whiteUsername(),
                isWhite ? current.blackUsername() : user.username(),
                current.gameName(),
                current.game()
        );
    }

    public ArrayList<GameResponse> getGames(String authToken){
        if (!isLoggedIn(authToken)){
            throw new UnauthorizedException("Error: unauthorized");
        }
        var currGames = games.getGames();
        var gameResponse = new ArrayList<GameResponse>();

        for (var game : currGames){
            GameResponse newResp = new GameResponse(
                    game.gameID(),
                    game.whiteUsername(),
                    game.blackUsername(),
                    game.gameName()
            );
            gameResponse.add(newResp);
        }

        return gameResponse;
    }

}
