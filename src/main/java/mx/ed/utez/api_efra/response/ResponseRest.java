package mx.ed.utez.api_efra.response;

import java.util.ArrayList;
import java.util.HashMap;

public class ResponseRest {
    private ArrayList<HashMap<String, String>> metadata = new ArrayList<>();

    public ArrayList<HashMap<String, String>> getMetadata() {
        return metadata;
    }

    public void setMetada(String tipo, String codigo, String date) {
        HashMap<String, String> mapa = new HashMap<>();

        mapa.put("tipo", tipo);
        mapa.put("codigo", codigo);
        mapa.put("dato", date);

        metadata.add(mapa);
    }
}
