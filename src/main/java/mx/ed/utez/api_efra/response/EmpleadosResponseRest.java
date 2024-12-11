package mx.ed.utez.api_efra.response;

public class EmpleadosResponseRest extends ResponseRest{
    private EmpleadosResponse empleadosResponse = new EmpleadosResponse();
    public EmpleadosResponse getEmpleadosResponse() {
        return empleadosResponse;
    }
    public void setEmpleadosResponse(EmpleadosResponse empleadosResponse) {
        this.empleadosResponse = empleadosResponse;
    }
}
