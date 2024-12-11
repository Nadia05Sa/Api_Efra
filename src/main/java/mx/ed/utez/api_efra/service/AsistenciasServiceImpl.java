package mx.ed.utez.api_efra.service;


import mx.ed.utez.api_efra.model.Asistencias;
import mx.ed.utez.api_efra.model.DAO.AsistenciasDao;
import mx.ed.utez.api_efra.response.AsistenciasResponseRest;
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
public class AsistenciasServiceImpl implements AsistenciasService {


    private static final Logger log = LoggerFactory.getLogger(AsistenciasServiceImpl.class);

    @Autowired
    private AsistenciasDao asistenciasDao;
    private Asistencias asistencias;


    @Override
    public List<Asistencias> getAsistencias(){

        return (List<Asistencias>) asistenciasDao.findAll();
    }

    @Override
    public Asistencias getAsistenciaById(Long id){
        Optional<Asistencias> asistencias = asistenciasDao.findById(id);
        return asistencias.orElseThrow(() -> new RuntimeException("Asistencia no encontrada"));
    }

    @Override
    public Asistencias saveAsistencia(Asistencias asistencias){

        return asistenciasDao.save(asistencias);
    }

    @Override
    public Asistencias updateAsistencia(Long id){
        Asistencias nuevaAsistencia = getAsistenciaById(id);
        nuevaAsistencia.setId(asistencias.getId());
        return asistenciasDao.save(nuevaAsistencia);
    }

    @Override
    public boolean deleteAsistencia(Long id){
        if(asistenciasDao.existsById(id)){
            asistenciasDao.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public ResponseEntity<AsistenciasResponseRest> agregarAsistencia(Asistencias asistencias) {
        log.info("Inicio metodo crear asistencia)");

        AsistenciasResponseRest response = new AsistenciasResponseRest();
        List<Asistencias> list = new ArrayList<>();

        try {

            Asistencias asistenciaGuardar = asistenciasDao.save(asistencias);

            if(asistenciaGuardar != null) {
                list.add(asistenciaGuardar);
                response.getAsistenciasResponse().setAsistencias(list);
                response.setMetada("Respuesta OK", "00", "Creacion exitosa");
            }else {
                log.info("Agencia no creada");
                response.setMetada("No Creada", "-1", "Agencia no creada");
                return new ResponseEntity<AsistenciasResponseRest>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            response.setMetada("Error", "-1", "Error al guardar la Agencia");
            log.error("Error al guardar la agencia: ",e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<AsistenciasResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<AsistenciasResponseRest>(response, HttpStatus.OK);
    }
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<AsistenciasResponseRest> obtenerAsistenciaPorId(Long id) {
        log.info("Inicio metodo obtenerAsistenciaPorId");

        AsistenciasResponseRest response = new AsistenciasResponseRest();

        try {
            Optional<Asistencias> asistencia = asistenciasDao.findById(id);
            if (asistencia.isPresent()) {
                response.getAsistenciasResponse().setAsistencias(List.of(asistencia.get()));
            } else {
                log.error("Asistencia no encontrada con id: {}", id);
                response.setMetada("Respuesta FALLIDA", "-1", "Asistencia no encontrada");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error al obtener asistencia por id", e);
            response.setMetada("Respuesta FALLIDA", "-1", "Error al obtener asistencia por id");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setMetada("Respuesta OK", "00", "Asistencia obtenida con éxito");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @Override
    @Transactional
    public ResponseEntity<AsistenciasResponseRest> actualizarAsistencia(Long id, Asistencias request) {
        log.info("Inicio metodo actualizarAsistencia");

        AsistenciasResponseRest response = new AsistenciasResponseRest();

        try {
            Optional<Asistencias> asistenciaExistente = asistenciasDao.findById(id);
            if (asistenciaExistente.isPresent()) {
                Asistencias asistencia = asistenciaExistente.get();
                // Actualiza los campos necesarios
                asistencia.setHoraEntrada(request.getHoraEntrada());
                asistencia.setHoraSalida(request.getHoraSalida());

                // Guardar asistencia actualizada
                asistenciasDao.save(asistencia);

                response.getAsistenciasResponse().setAsistencias(List.of(asistencia));
            } else {
                log.error("Asistencia no encontrada con id: {}", id);
                response.setMetada("Respuesta FALLIDA", "-1", "Asistencia no encontrada");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error al actualizar asistencia", e);
            response.setMetada("Respuesta FALLIDA", "-1", "Error al actualizar asistencia");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setMetada("Respuesta OK", "00", "Asistencia actualizada con éxito");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @Override
    @Transactional
    public ResponseEntity<AsistenciasResponseRest> eliminarAsistencia(Long id) {
        log.info("Inicio metodo eliminarAsistencia");

        AsistenciasResponseRest response = new AsistenciasResponseRest();

        try {
            Optional<Asistencias> asistenciaExistente = asistenciasDao.findById(id);
            if (asistenciaExistente.isPresent()) {
                asistenciasDao.deleteById(id);  // Elimina la asistencia

                response.setMetada("Respuesta OK", "00", "Asistencia eliminada con éxito");
            } else {
                log.error("Asistencia no encontrada con id: {}", id);
                response.setMetada("Respuesta FALLIDA", "-1", "Asistencia no encontrada");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error al eliminar asistencia", e);
            response.setMetada("Respuesta FALLIDA", "-1", "Error al eliminar asistencia");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
