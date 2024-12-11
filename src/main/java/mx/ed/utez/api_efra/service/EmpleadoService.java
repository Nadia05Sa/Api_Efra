package mx.ed.utez.api_efra.service;

import mx.ed.utez.api_efra.model.Empleados;

import java.util.List;

public interface EmpleadoService {
    List<Empleados> getEmpleados();
    Empleados getEmpleadoById(Long id);
    Empleados saveEmpleado(Empleados empleados);
    Empleados updateEmpleado(Long id);
    boolean deleteEmpleado(Long id);
}
