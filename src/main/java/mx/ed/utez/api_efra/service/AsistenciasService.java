package mx.ed.utez.api_efra.service;

import mx.ed.utez.api_efra.model.Asistencias;
import mx.ed.utez.api_efra.response.AsistenciasResponseRest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AsistenciasService {
    List<Asistencias>  getAsistencias();
    Asistencias getAsistenciaById(Long id);
    Asistencias saveAsistencia(Asistencias asistencias);
    Asistencias updateAsistencia(Long id);
    boolean deleteAsistencia(Long id);

    public ResponseEntity<AsistenciasResponseRest> agregarAsistencia(Asistencias asistencias);

}
