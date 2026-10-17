package server.dataaccess;

import model.GameData;

import java.util.ArrayList;

public class Games {
    private ArrayList<GameData> games;

    public Games(ArrayList<GameData> games) {
        this.games = games;
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
