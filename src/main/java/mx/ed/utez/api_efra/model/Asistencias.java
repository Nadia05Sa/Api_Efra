package mx.ed.utez.api_efra.model;
import java.io.Serializable;
import java.time.LocalTime;

import jakarta.persistence.*;
import jakarta.persistence.Id;  // Solo esta línea es necesaria

@Entity
@Table(name = "Asistencias")
public class Asistencias implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalTime horaEntrada;
    private LocalTime horaSalida;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empleados_id")
    private Empleados empleados;


    public Empleados getEmpleados() {
        return empleados;
    }

    public User getUser() {
        return user;
    }

    public void setEmpleados(Empleados empleados) {
        this.empleados = empleados;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Asistencias() {
   }

    public Asistencias(LocalTime horaEntrada, LocalTime horaSalida) {
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }


}
