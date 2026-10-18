package server.services;

import model.AuthData;
import model.UserData;
import server.dataaccess.AuthDAO;
import server.dataaccess.DataAccessException;
import server.dataaccess.UserDAO;

import java.util.UUID;

public class AuthService {
    private final UserDAO users;
    private final AuthDAO auth;

    public AuthService(UserDAO users, AuthDAO auth) {
        this.users = users;
        this.auth = auth;
    }

    public static String generateToken(){
        return UUID.randomUUID().toString();
    }

    public AuthData register(UserData request) {
        if (request == null || request.username() == null || request.password() == null || request.email() == null){
            throw new BadRequestException("Error: bad request");
        }

        if (users.getUser(request.username()) != null){
            throw new AlreadyTakenException("Error: Already taken");
        }

        users.createUser(request);

        String authToken = generateToken();
        AuthData authData = new AuthData(authToken, request.username());

        auth.addAuth(authData);

        return authData;
    }
}
