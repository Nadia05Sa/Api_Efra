package mx.ed.utez.api_efra.contoller;

import mx.ed.utez.api_efra.model.User;
import mx.ed.utez.api_efra.response.UserResponseRest;
import mx.ed.utez.api_efra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class UserRestController {
    @Autowired
    private UserService service;

    @PostMapping("/usuarios/agregar")
    public ResponseEntity<UserResponseRest> agregarUsuario(@RequestBody User request){
        ResponseEntity<UserResponseRest> response = service.agregarUsuario(request);
        return response;
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<UserResponseRest> buscarUsuarioPorId(@PathVariable Long id){
        ResponseEntity<UserResponseRest> response = service.buscarUsuarioPorId(id);
        return response;
    }

    @PutMapping("/usuarios/actualizar/{id}")
    public ResponseEntity<UserResponseRest> actualizarUsuario(@PathVariable Long id, @RequestBody User request){
        ResponseEntity<UserResponseRest> response = service.actualizarUsuario(id, request);
        return response;
    }

    @DeleteMapping("/usuarios/eliminar/{id}")
    public ResponseEntity<UserResponseRest> eliminarUsuario(@PathVariable Long id){
        ResponseEntity<UserResponseRest> response = service.eliminarUsuario(id);
        return response;
    }
}
