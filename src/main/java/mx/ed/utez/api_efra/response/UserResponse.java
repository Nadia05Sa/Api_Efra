package mx.ed.utez.api_efra.response;

import mx.ed.utez.api_efra.model.User;

import java.util.List;

public class UserResponse {
    private List<User> asistencias;
    public List<User> getAsistencias() {
        return asistencias;
    }
    public void setAsistencias(List<User> user) {
        this.asistencias = user;
    }
}
