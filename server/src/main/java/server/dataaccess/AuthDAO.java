package server.dataaccess;

import model.AuthData;

import java.util.HashSet;

public class AuthDAO {
    private HashSet<AuthData> authInfo;

    public AuthDAO() {
    }

    public void addAuth(AuthData authData){
        authInfo.add(authData);
    }

    public void setAuthInfo(HashSet<AuthData> authInfo) {
        this.authInfo = authInfo;
    }

    public void clearAuthData(){
        setAuthInfo(new HashSet<AuthData>());
    }
}
