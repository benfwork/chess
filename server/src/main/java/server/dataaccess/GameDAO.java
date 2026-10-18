package server.dataaccess;

import chess.ChessGame;
import model.GameData;

import java.util.ArrayList;
import java.util.Random;

public class GameDAO {
    private ArrayList<GameData> games;

    public GameDAO() {
    }

    public ArrayList<GameData> getGames() {
        return games;
    }

    public static int generateGameId(){
        Random rand = new Random();
        return rand.nextInt(10000);
    }

    public GameData getGame(String gameName){
        for (var game : games){
            if (game.gameName().equals(gameName)){
                return game;
            }
        }
        return null;
    }

    public GameData getGameById(int gameId) {
        int index = getGameIdx(gameId);

        if (index == -1) {
            return null;
        }

        return games.get(index);
    }

    public int getGameIdx(int gameId){
        for (var game : games){
            if (game.gameID() == gameId){
                return games.indexOf(game);
            }
        }
        return -1;
    }

    public void updateGame(GameData updated) throws DataAccessException {
        int index = getGameIdx(updated.gameID());

        if (index == -1) {
            throw new DataAccessException("Cannot update game that does not exist");
        }

        games.set(index, updated);
    }

    public void createGame(String gameName){
        var newGame = new GameData(generateGameId(), null, null, gameName, new ChessGame());
        games.add(newGame);
    }

    public void setGames(ArrayList<GameData> games) {
        this.games = games;
    }

    public void clearGameData(){
        setGames(new ArrayList<GameData>());
    }
}
