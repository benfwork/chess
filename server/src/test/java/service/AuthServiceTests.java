package service;

import model.AuthData;
import model.LoginRequest;
import model.UserData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.dataaccess.AuthDAO;
import server.dataaccess.UserDAO;
import server.services.AuthService;

import static org.junit.jupiter.api.Assertions.*;

public class AuthServiceTests {
    private UserDAO users;
    private AuthDAO auth;
    private AuthService service;

    @BeforeEach
    public void setUp() {
        users = new UserDAO();
        auth = new AuthDAO();
        service = new AuthService(users, auth);
    }

    @Test
    public void registerSuccessCreateUser() {
        UserData request = new UserData("bob", "password", "bob@example.com");
        AuthData result = service.register(request);

        assertEquals("bob", result.username());

        assertNotNull(result.authToken());
        assertFalse(result.authToken().isBlank());

        assertEquals(request, users.getUser("bob"));
        assertEquals(result, auth.getAuth(result.authToken()));
    }

    @Test
    public void loginSuccess(){
        LoginRequest request = new LoginRequest("bob", "password");
        AuthData result = service.login(request);

    }
}