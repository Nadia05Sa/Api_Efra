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
        log.info("Inicio metodo crear Agencia)");

        AsistenciasResponseRest response = new AsistenciasResponseRest();
        List<Asistencias> list = new ArrayList<>();

        try {

            Asistencias agenciaGuardar = asistenciasDao.save(asistencias);

            if(agenciaGuardar != null) {
                list.add(agenciaGuardar);
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


}
