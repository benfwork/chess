package server.dataaccess;

import model.UserData;

import java.util.ArrayList;

public class UserDAO {
    private ArrayList<UserData> users;

    public UserData getUser(String username) {
        for (var user : users){
            if (user.username().equals(username)){
                return user;
            }
        }
        return null;
    }

    public void setUsers(ArrayList<UserData> users) {
        this.users = users;
    }

    public void clearUserData(){
        setUsers(new ArrayList<UserData>());
    }
}
