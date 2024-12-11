package mx.ed.utez.api_efra.response;

public class AsistenciasResponseRest extends ResponseRest{
    private AsistenciasResponse asistenciasResponse = new AsistenciasResponse();
    public AsistenciasResponse getAsistenciasResponse() {
        return asistenciasResponse;
    }
    public void setAsistenciasResponse(AsistenciasResponse asistenciasResponse) {
        this.asistenciasResponse = asistenciasResponse;
    }
}
