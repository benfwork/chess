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

    public AuthData getAuth(String authToken){
        for (var auth : authInfo){
            if (auth.authToken().equals(authToken)){
                return auth;
            }
        }
        return null;
    }

    public void revokeAuth(String authToken){
        authInfo.removeIf(auth -> auth.authToken().equals(authToken));
    }

    public void setAuthInfo(HashSet<AuthData> authInfo) {
        this.authInfo = authInfo;
    }

    public void clearAuthData(){
        setAuthInfo(new HashSet<AuthData>());
    }
}
