package mx.ed.utez.api_efra.model.DAO;

import mx.ed.utez.api_efra.model.Empleados;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadosDao extends CrudRepository<Empleados,Long> {
        }