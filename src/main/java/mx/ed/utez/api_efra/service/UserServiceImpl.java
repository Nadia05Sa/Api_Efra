package mx.ed.utez.api_efra.service;

import mx.ed.utez.api_efra.model.Asistencias;
import mx.ed.utez.api_efra.model.DAO.UserDao;
import mx.ed.utez.api_efra.model.Empleados;
import mx.ed.utez.api_efra.model.User;
import mx.ed.utez.api_efra.response.AsistenciasResponseRest;
import mx.ed.utez.api_efra.response.UserResponseRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService{

    private static final Logger log = LoggerFactory.getLogger(AsistenciasServiceImpl.class);

    @Autowired
    private UserDao userDao;
    private User user;

    @Override
    public List<User> getUsers(){
        return (List<User>)  userDao.findAll();
    }

    @Override
    public User getUserById(Long id){
        Optional<User> user = userDao.findById(id);
        return user.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public User saveUser(User user){
        return userDao.save(user);
    }

    @Override
    public User updateUser(Long id){
        User nuevoUser = getUserById(id);
        nuevoUser.setId(user.getId());
        return userDao.save(nuevoUser);
    }

    @Override
    public boolean deleteUser(Long id){
        if(userDao.existsById(id)){
            userDao.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public ResponseEntity<UserResponseRest> agregarUsuario(User user) {
        log.info("Inicio metodo crear Usuario)");

        UserResponseRest response = new UserResponseRest();
        List<User> list = new ArrayList<>();

        try {

            User usuarioGuardar = userDao.save(user);

            if(usuarioGuardar != null) {
                list.add(usuarioGuardar);
                response.getUserResponse().setUser(list);
                response.setMetada("Respuesta OK", "00", "Creacion exitosa");
            }else {
                log.info("Usuario no creado");
                response.setMetada("No Creado", "-1", "Usuario no creada");
                return new ResponseEntity<UserResponseRest>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            response.setMetada("Error", "-1", "Error al guardar el usuario");
            log.error("Error al guardar el usuario: ",e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<UserResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<UserResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<UserResponseRest> buscarUsuarioPorId(Long id) {
        log.info("Inicio metodo buscarPorId)");

        UserResponseRest response = new UserResponseRest();
        List<User> list = new ArrayList<>();

        try {
            Optional<User> usuario = userDao.findById(id);
            if (usuario.isPresent()) {
                list.add(usuario.get());
                response.getUserResponse().setUser(list);
            } else {
                log.error("Error en consultar usuario");
                response.setMetada("Respuesta FALLIDA", "-1", "Usuario no encontrado");
                return new ResponseEntity<UserResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error en consultar usuario");
            response.setMetada("Respuesta FALLIDA", "-1", "Error al consultar usuario");
            return new ResponseEntity<UserResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setMetada("Respuesta ok", "00", "Respuesta exitosa");
        return new ResponseEntity<UserResponseRest>(response, HttpStatus.OK); //devuelve 200
    }

    @Override
    @Transactional
    public ResponseEntity<UserResponseRest> actualizarUsuario(Long id, User request) {
        log.info("Inicio metodo actualizarUsuario");

        UserResponseRest response = new UserResponseRest();

        try {
            Optional<User> usuarioExistente = userDao.findById(id);
            if (usuarioExistente.isPresent()) {
                User usuario = usuarioExistente.get();
                // Actualiza los campos necesarios
                usuario.setUsername(request.getUsername());
                usuario.setPassword(request.getPassword());
                usuario.setEnabled(request.isEnabled());
                usuario.setCorreo(request.getCorreo());
                usuario.setNombre(request.getNombre());
                usuario.setApellido(request.getApellido());

                // Guardar usuario actualizado
                userDao.save(usuario);

                response.getUserResponse().setUser(List.of(usuario));
            } else {
                log.error("Usuario no encontrado con id: {}", id);
                response.setMetada("Respuesta FALLIDA", "-1", "Usuario no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error al actualizar usuario", e);
            response.setMetada("Respuesta FALLIDA", "-1", "Error al actualizar usuario");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setMetada("Respuesta OK", "00", "Usuario actualizado con éxito");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @Override
    @Transactional
    public ResponseEntity<UserResponseRest> eliminarUsuario(Long id) {
        log.info("Inicio metodo eliminarUsuario");

        UserResponseRest response = new UserResponseRest();

        try {
            Optional<User> usuarioExistente = userDao.findById(id);
            if (usuarioExistente.isPresent()) {
                userDao.deleteById(id);  // Elimina el usuario

                response.setMetada("Respuesta OK", "00", "Usuario eliminado con éxito");
            } else {
                log.error("Usuario no encontrado con id: {}", id);
                response.setMetada("Respuesta FALLIDA", "-1", "Usuario no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error al eliminar usuario", e);
            response.setMetada("Respuesta FALLIDA", "-1", "Error al eliminar usuario");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}