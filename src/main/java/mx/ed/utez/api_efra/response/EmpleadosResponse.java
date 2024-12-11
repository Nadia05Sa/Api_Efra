package mx.ed.utez.api_efra.response;

import mx.ed.utez.api_efra.model.Empleados;

import java.util.List;

public class EmpleadosResponse {
    private List<Empleados> empleados;
    public List<Empleados> getEmpleados() {
        return empleados;
    }
    public void setEmpleados(List<Empleados> empleados) {
        this.empleados = empleados;
    }
}
