package mx.ed.utez.api_efra.service;


import mx.ed.utez.api_efra.model.Asistencias;
import mx.ed.utez.api_efra.model.DAO.AsistenciasDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsistenciasServiceImpl implements AsistenciasService {

    @Autowired
    private AsistenciasDao asistenciasDao;


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
        nuevaAsistencia.setId(Asistencias.getId());
        return asistenciasDao.save(nuevaAsistencia);
    }

    @Override
    public boolean deleteAsistencia(Long id){
        if(asistenciasDao.existsById(id)){
            asistenciasDao.deleteById(id);
            return true;
        }
        return false
    }
}
