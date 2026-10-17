package server.dataaccess;

import model.AuthData;

import java.util.HashSet;

public class Auth {
    private HashSet<AuthData> AuthInfo;

    public Auth(HashSet<AuthData> authInfo) {
        AuthInfo = authInfo;
    }

    public void setAuthInfo(HashSet<AuthData> authInfo) {
        AuthInfo = authInfo;
    }

    public void ClearAuthInfo(){
        setAuthInfo(new HashSet<AuthData>());
    }
}
