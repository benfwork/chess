package server.services;

import model.AuthData;
import model.UserData;
import server.dataaccess.AuthDAO;
import server.dataaccess.DataAccessException;
import server.dataaccess.UserDAO;

public class AuthService {
    private final UserDAO users;
    private final AuthDAO auth;

    public AuthService(UserDAO users, AuthDAO auth) {
        this.users = users;
        this.auth = auth;
    }

    public AuthData register(UserData request) throws Exception {
        if (request == null || request.username() == null | request.password() == null || request.email() == null){
            throw new Exception("Error: bad request");
        }

        if (users.getUser(request.username()) != null){
            throw new Exception("Error: Already taken");
        }

        return authData;
    }
}
