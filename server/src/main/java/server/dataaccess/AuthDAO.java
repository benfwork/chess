package server.dataaccess;

import model.AuthData;

import java.util.HashSet;

public class AuthDAO {
    private HashSet<AuthData> AuthInfo;

    public AuthDAO() {
    }

    public void setAuthInfo(HashSet<AuthData> authInfo) {
        AuthInfo = authInfo;
    }

    public void clearAuthData(){
        setAuthInfo(new HashSet<AuthData>());
    }
}
