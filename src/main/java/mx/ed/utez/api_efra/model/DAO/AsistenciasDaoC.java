package mx.ed.utez.api_efra.model.DAO;

import mx.ed.utez.api_efra.model.Asistencias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AsistenciasDaoC extends JpaRepository<Asistencias, Long> {

    @Query("select a from Asistencias a where a.user = :nombre")
    public Asistencias findByNombre(@Param("nombre") String nombre);


    // Para actualizar la foto, no es necesario usar una consulta JPQL, solo se usa save o saveAndFlush
    public Asistencias save(Asistencias foto);
}