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
        ResponseEntity<UserResponseRest>  response = service.agregarUsuario(request);
        return response;
    }

    @GetMapping("/agencias/{id}")
    public ResponseEntity<UserResponseRest> buscarUsuarioPorId(@PathVariable Long id){
        ResponseEntity<UserResponseRest> response = service.buscarUsuarioPorId(id);
        return response;
    }
}
