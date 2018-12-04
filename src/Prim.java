
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import org.apache.commons.lang3.SerializationUtils;

public class Prim {

    public void AlgoritmoPrim(Grafo grafo) throws IOException {
        Grafo g = SerializationUtils.clone(grafo);
        g.zerarVisitas();
        ArrayList<Aresta> T = new ArrayList<>();    //conjunto de arestas da árvore geradora mínima
        ArrayList<Vertice> V = new ArrayList<>();   // conjunto de vértice do grafo original
        V.addAll(g.grafo);
        ArrayList<Vertice> B = new ArrayList<>();   // conjunto de vértice da árvore geradora mínima
        
        //
        Integer i = Integer.MAX_VALUE;//infinito
        Aresta x = new Aresta();
        //
        B.add(V.get(0));                  // Um vértice de G 
        
        while(B.size() != V.size())       //enquanto B não contém todos os vértices faça
        {
            for (Vertice v : B) {
                for (Aresta a : v.todasAsArestas()) {
                    if (a.getPeso()<i && a.visitado == false) {
                        x = a;
                        i = a.getPeso();
                        a.visitado = true;
                    }
                }
            }
            i = Integer.MAX_VALUE;
            for (Vertice v : B) {
                if (x.getA().equals(v)) {
                    B.add(x.getB());
                    T.add(x);
                    break;
                }
                if (x.getB().equals(v)) {
                    B.add(x.getA());
                    T.add(x);
                    break;
                }
            }
            //x== aresta de menor peso dentre os vertice introduzidos
        }
        Grafo grafoPrim = new Grafo();
        grafoPrim.addListaArestas(T);
        Impressao imprime = new Impressao();
        imprime.ImprimePrim(grafoPrim, "Prim");
        //i.ImprimeGrafoOrientado(grafoPrim,"grafoPrim");
    }
}
