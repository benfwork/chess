package service;

import model.AuthData;
import model.LoginRequest;
import model.UserData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.dataaccess.AuthDAO;
import server.dataaccess.UserDAO;
import server.services.AlreadyTakenException;
import server.services.AuthService;
import server.services.BadRequestException;
import server.services.UnauthorizedException;

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
    public void registerRejectsExistingUsername() {
        service.register(new UserData("alice", "password", "alice@example.com"));
        UserData duplicate = new UserData("alice", "different-password", "other@example.com");

        assertThrows(AlreadyTakenException.class, () -> service.register(duplicate));
    }

    @Test
    public void loginSuccess(){
        service.register(new UserData("bob", "password", "bob@example.com"));
        LoginRequest request = new LoginRequest("bob", "password");
        AuthData result = service.login(request);

        assertEquals(result, auth.getAuth(result.authToken()));
    }

    @Test
    public void loginWrongPassword(){
        service.register(new UserData("jimmy", "pwd", "j@gmail.com"));
        LoginRequest request = new LoginRequest("jimmy", "wrongpwd");

        assertThrows(UnauthorizedException.class, () -> service.login(request));
    }

    @Test
    public void loginNull() {
        assertThrows(BadRequestException.class,
                () -> service.login(null));

        assertThrows(BadRequestException.class,
                () -> service.login(new LoginRequest(null, "password")));

        assertThrows(BadRequestException.class,
                () -> service.login(new LoginRequest("bob", null)));
    }

    @Test
    public void registerNull() {
        assertThrows(BadRequestException.class, () -> service.register(null));

        assertThrows(BadRequestException.class, () -> service
                .register(new UserData(null, "password", "bob@example.com")));

        assertThrows(BadRequestException.class, () -> service.register(
                        new UserData("bob", null, "bob@example.com")));

        assertThrows(BadRequestException.class, () -> service.register(
                        new UserData("bob", "password", null)));
    }
}