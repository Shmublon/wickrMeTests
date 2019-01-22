package beans;

import com.fasterxml.jackson.annotation.JsonGetter;

public class Account {
    private String username;
    private String password;

    @JsonGetter("login")
    public String getUsername() {
        return username;
    }

    @JsonGetter("password")
    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "account: { username:" + getUsername() + ", password: " + getPassword() + "}";
    }
}
