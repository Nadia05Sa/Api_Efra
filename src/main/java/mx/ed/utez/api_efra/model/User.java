package mx.ed.utez.api_efra.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;  // El correo del usuario

    @Column(nullable = false)
    private String password;  // Contraseña cifrada

    @Column(nullable = false)
    private boolean enabled;  // Indica si el usuario está habilitado


    private String correo;
    private String Nombre;
    private String Apellido;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_authorities", // Tabla intermedia
            joinColumns = @JoinColumn(name = "user_id"), // Clave foránea hacia 'users'
            inverseJoinColumns = @JoinColumn(name = "authority_id") // Clave foránea hacia 'authorities'
    )
    private Set<Authority> authorities = new HashSet<>();  // Conjunto de roles asociados al usuario

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }
}