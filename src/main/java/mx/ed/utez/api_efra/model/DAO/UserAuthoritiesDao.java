package mx.ed.utez.api_efra.model.DAO;


import mx.ed.utez.api_efra.model.UserAuthorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserAuthoritiesDao extends JpaRepository<UserAuthorities, Long> {
    // Elimina los registros relacionados con un userId
    @Modifying
    @Query("DELETE FROM UserAuthorities ua WHERE ua.user.id = :userId")
    void deleteByUserId(@Param("userId") Long userId);
}