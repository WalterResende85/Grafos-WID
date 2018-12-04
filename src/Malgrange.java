
import java.util.ArrayList;
import org.apache.commons.lang3.SerializationUtils;

public class Malgrange {

    public void AlgoritmoMalgrange(Grafo grafo) {
        Grafo g = SerializationUtils.clone(grafo);
        g.zerarVisitas();
        g.zerarVisitasArestas();

        for (int i = 0; i < g.grafo.size(); i++) {
            for (Aresta a : g.ListaAresta) {
                
            }
        }
    }


static void ftd(Vertice v) {
    }
}
