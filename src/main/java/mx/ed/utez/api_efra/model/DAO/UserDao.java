package mx.ed.utez.api_efra.model.DAO;


import mx.ed.utez.api_efra.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends CrudRepository<User, Long> {

    // Método para encontrar un usuario por su nombre de autoridad
    @Query("SELECT u FROM User u JOIN u.authorities a WHERE a.authority = :authority")
    Optional<User> findByAuthority(@Param("authority") String authority);

    // Método para encontrar un usuario por su nombre de usuario (username)
    Optional<User> findByUsername(String username);

}