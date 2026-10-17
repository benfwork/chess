package server.dataaccess;

import model.UserData;

import java.util.ArrayList;

public class UserDAO {
    private ArrayList<UserData> users;

    public ArrayList<UserData> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<UserData> users) {
        this.users = users;
    }

    public void clearUserData(){
        setUsers(new ArrayList<UserData>());
    }
}
