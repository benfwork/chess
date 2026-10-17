package server.dataaccess;

import model.GameData;

import java.util.ArrayList;

public class GameDAO {
    private ArrayList<GameData> games;

    public GameDAO() {
    }

    public ArrayList<GameData> getGames() {
        return games;
    }

    public void setGames(ArrayList<GameData> games) {
        this.games = games;
    }

    public void clearGameData(){
        setGames(new ArrayList<GameData>());
    }
}
