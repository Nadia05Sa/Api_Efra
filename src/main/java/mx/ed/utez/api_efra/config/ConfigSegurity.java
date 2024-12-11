package mx.ed.utez.api_efra.config;

import mx.ed.utez.api_efra.model.DAO.UserDao;
import mx.ed.utez.api_efra.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration // Indica que esta clase es una configuración de Spring.
public class ConfigSecurity {

    @Autowired
    @Lazy
    private JwtReqFilter jwtReqFilter; // Se inyecta un filtro que gestionará la validación de JWT.

    // Bean que define el servicio de detalles de usuario, en este caso se utiliza un JdbcUserDetailsManager
    // para obtener los usuarios de una base de datos.
    @Bean
    public UserDetailsService userDetailsService(UserDao userDao) {
        return username -> {
            Optional<User> userOptional = userDao.findByUsername(username);
            mx.ed.utez.api_efra.model.User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

            Set<GrantedAuthority> authorities = user.getAuthorities().stream()
                    .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                    .collect(Collectors.toSet());

            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configure -> configure
// Se configuran las reglas de autorización para las rutas según el método HTT
                                .requestMatchers(HttpMethod.POST, "/v1/authenticate").permitAll()
                )
// Configuración de CORS para permitir solicitudes desde un origen específico
                .cors(cors -> cors.configurationSource(request -> {
                    var config = new CorsConfiguration();
                    config.setAllowedOrigins(List.of("http://localhost:63342", "http://127.0.0.1:9090")); // Orígenes permitidos
                    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Métodos permitidos
                    config.setAllowCredentials(true); // Permite credenciales
                    config.setAllowedHeaders(List.of("Authorization", "Content-Type", "X-Requested-With")); // Encabezados permitidos
                    config.setExposedHeaders(List.of("Authorization")); // Encabezados expuestos al cliente
                    return config;
                }))
                // Deshabilitar CSRF (Cross-Site Request Forgery) porque la aplicación probablemente está utilizando JWT
                .csrf(csrf -> csrf.disable())
                // Configuración para que la aplicación no mantenga sesiones (stateless), ya que se usa JWT para autenticación
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Añadir el filtro JWT antes del filtro de autenticación por nombre de usuario y contraseña
                .addFilterBefore(jwtReqFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build(); // Construir la configuración de seguridad
    }

    // Bean que proporciona un AuthenticationManager para la autenticación de usuarios
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // Contraseñas sin encriptar
    }


}