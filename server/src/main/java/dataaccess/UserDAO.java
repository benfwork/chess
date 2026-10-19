package dataaccess;

import model.UserData;

import java.util.HashSet;

public class UserDAO {
    private HashSet<UserData> users;

    public UserDAO(){
        this.users = new HashSet<UserData>();
    }

    public UserData getUser(String username) {
        for (var user : users){
            if (user.username().equals(username)){
                return user;
            }
        }
        return null;
    }

    public void createUser(UserData userData){
        users.add(userData);
    }

    public void setUsers(HashSet<UserData> users) {
        this.users = users;
    }

    public void clearUserData(){
        setUsers(new HashSet<UserData>());
    }
}
