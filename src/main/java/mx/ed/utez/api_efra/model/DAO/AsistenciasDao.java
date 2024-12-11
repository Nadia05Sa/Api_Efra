package mx.ed.utez.api_efra.model.DAO;

import mx.ed.utez.api_efra.model.Asistencias;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsistenciasDao extends CrudRepository<Asistencias,Long> {
        }