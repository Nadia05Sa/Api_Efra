package mx.ed.utez.api_efra.contoller;

import mx.ed.utez.api_efra.model.Asistencias;
import mx.ed.utez.api_efra.response.AsistenciasResponse;
import mx.ed.utez.api_efra.response.AsistenciasResponseRest;
import mx.ed.utez.api_efra.service.AsistenciasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class AsistenciasRestController {

    @Autowired
    private AsistenciasService service;

    @PostMapping("/asistencia/agregar")
    public ResponseEntity<AsistenciasResponseRest> agregarAsistencia(@RequestBody Asistencias request){
        ResponseEntity<AsistenciasResponseRest>  response = service.agregarAsistencia(request);
        return response;
    }
}

