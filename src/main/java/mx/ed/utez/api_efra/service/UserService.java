package mx.ed.utez.api_efra.service;

import mx.ed.utez.api_efra.model.Asistencias;
import mx.ed.utez.api_efra.model.User;
import mx.ed.utez.api_efra.response.AsistenciasResponseRest;
import mx.ed.utez.api_efra.response.UserResponseRest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    User getUserById(Long id);
    User saveUser(User user);
    User updateUser(Long id);
    boolean deleteUser(Long id);

    public ResponseEntity<UserResponseRest> agregarUsuario(User user);
    public ResponseEntity<UserResponseRest> buscarUsuarioPorId(Long id);
}
