package mx.ed.utez.api_efra.response;

public class AuthorityResponseRest extends ResponseRest{
    private AuthorityResponse authorityResponse = new AuthorityResponse();
    public AuthorityResponse getAuthorityResponse() {
        return authorityResponse;
    }
    public void setAuthorityResponse(AuthorityResponse authorityResponse) {
        this.authorityResponse = authorityResponse;
    }
}
