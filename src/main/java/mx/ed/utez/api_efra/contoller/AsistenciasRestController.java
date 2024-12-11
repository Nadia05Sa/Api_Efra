package mx.ed.utez.api_efra.contoller;

import mx.ed.utez.api_efra.model.Asistencias;
import mx.ed.utez.api_efra.response.AsistenciasResponseRest;
import mx.ed.utez.api_efra.service.AsistenciasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class AsistenciasRestController {

    @Autowired
    private AsistenciasService service;

    @PostMapping("/asistencia/agregar")
    public ResponseEntity<AsistenciasResponseRest> agregarAsistencia(@RequestBody Asistencias request){
        ResponseEntity<AsistenciasResponseRest> response = service.agregarAsistencia(request);
        return response;
    }

    @GetMapping("/asistencia/{id}")
    public ResponseEntity<AsistenciasResponseRest> obtenerAsistenciaPorId(@PathVariable Long id){
        ResponseEntity<AsistenciasResponseRest> response = service.obtenerAsistenciaPorId(id);
        return response;
    }

    @PutMapping("/asistencia/actualizar/{id}")
    public ResponseEntity<AsistenciasResponseRest> actualizarAsistencia(@PathVariable Long id, @RequestBody Asistencias request){
        ResponseEntity<AsistenciasResponseRest> response = service.actualizarAsistencia(id, request);
        return response;
    }

    @DeleteMapping("/asistencia/eliminar/{id}")
    public ResponseEntity<AsistenciasResponseRest> eliminarAsistencia(@PathVariable Long id){
        ResponseEntity<AsistenciasResponseRest> response = service.eliminarAsistencia(id);
        return response;
    }

}

