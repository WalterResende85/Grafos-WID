
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import org.apache.commons.lang3.SerializationUtils;

public class Kruskal {

    public void AlgoritmoKruskal(Grafo grafo) throws IOException {
        Grafo g = SerializationUtils.clone(grafo);
        Grafo grafoKruskal = new Grafo();
        Collections.sort(g.getListaAresta(), new Ordenador());            //Ordenar A pelos valores de peso
        ArrayList<Aresta> s = g.getListaAresta();

        while (!s.isEmpty()) {
            Aresta a = s.get(0);
            s.remove(0);
            if (grafoKruskal.grafo.contains(a.getA()) && grafoKruskal.grafo.contains(a.getB())) {
                //contem os dois descarta
            }
            if (!grafoKruskal.grafo.contains(a.getA()) && !grafoKruskal.grafo.contains(a.getB())) {
                //nao contem os dois adiciona
                grafoKruskal.grafo.add(a.getA());
                grafoKruskal.grafo.add(a.getB());
                grafoKruskal.addAresta(a);
            }
            if (!grafoKruskal.grafo.contains(a.getA()) && grafoKruskal.grafo.contains(a.getB())) {
                grafoKruskal.grafo.add(a.getA());
                grafoKruskal.addAresta(a);
            }
            if (grafoKruskal.grafo.contains(a.getA()) && !grafoKruskal.grafo.contains(a.getB())) {
                grafoKruskal.grafo.add(a.getB());
                grafoKruskal.addAresta(a);
            }        
        }
        Impressao i = new Impressao();
        i.ImprimeKruskal(grafoKruskal,"Kruskal");
    }

}
