package mx.ed.utez.api_efra.response;

import mx.ed.utez.api_efra.model.Asistencias;

import java.util.List;

public class AsistenciasResponse {
    private List<Asistencias> asistencias;
    public List<Asistencias> getAsistencias() {
        return asistencias;
    }
    public void setAsistencias(List<Asistencias> asistencias) {
        this.asistencias = asistencias;
    }
}
