package data;

//import lombok.Value;

//@Value
//public class AuthInfo {
//    String login;
//    String password;
//}

public class AuthInfo {
    private final String login;
    private final String password;

    public AuthInfo(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
