package mx.ed.utez.api_efra.model.DAO;

import mx.ed.utez.api_efra.model.Authority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityDao extends CrudRepository<Authority, Long> {
    // Método para buscar un rol por su nombre
    Authority findByAuthority(String authority);
}