package mx.ed.utez.api_efra.response;

import mx.ed.utez.api_efra.model.UserAuthorities;

import java.util.List;

public class UserAuthoritiesResponse {
    private List<UserAuthorities> userAuthorities;
    public List<UserAuthorities> getUserAuthorities() {
        return userAuthorities;
    }
    public void setUserAuthorities(List<UserAuthorities> userAuthorities) {
        this.userAuthorities = userAuthorities;
    }
}
