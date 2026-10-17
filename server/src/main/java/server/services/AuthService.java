package server.services;

import server.dataaccess.AuthDAO;
import server.dataaccess.UserDAO;

public class AuthService {
    private final UserDAO users;
    private final AuthDAO auth;

    public AuthService(UserDAO users, AuthDAO auth) {
        this.users = users;
        this.auth = auth;
    }
}
