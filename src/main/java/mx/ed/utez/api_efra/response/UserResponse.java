package mx.ed.utez.api_efra.response;

import mx.ed.utez.api_efra.model.User;

import java.util.List;

public class UserResponse {
    private List<User> user;
    public List<User> getUser() {
        return user;
    }
    public void setUser(List<User> user) {
        this.user = user;
    }
}
