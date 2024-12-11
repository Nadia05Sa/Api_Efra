package mx.ed.utez.api_efra.response;

import mx.ed.utez.api_efra.model.Authority;

import java.util.List;

public class AuthorityResponse {
    private List<Authority> authority;
    public List<Authority> getAuthority() {
        return authority;
    }
    public void setAuthority(List<Authority> authority) {
        this.authority = authority;
    }
}
