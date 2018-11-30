
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Prim {

public void AlgoritmoPrim(Grafo grafo) throws IOException {
        Grafo g = grafo;
        Ordenador X = new Ordenador();                          //Ordenar A pelos valores de peso
        Collections.sort(g.getListaAresta(), X);
        Vertice v = g.getGrafo().get(0);
        Grafo grafoPrim = new Grafo();

        ArrayList<Aresta> listaArestas = new ArrayList<>();
        ArrayList<Vertice> listaVertices = new ArrayList<>();

        listaVertices.add(v);
        for (int j = 0; j < g.getListaAresta().size(); j++) {
            for (int i = 0; i < g.getListaAresta().size(); i++) {
                Aresta a = g.getListaAresta().get(i);
                if (listaVertices.contains(a.getA()) && !listaVertices.contains(a.getB())) {
                    listaVertices.add(a.getB());
                    listaArestas.add(a);
                }
                if (!listaVertices.contains(a.getA()) && listaVertices.contains(a.getB())) {
                    listaVertices.add(a.getA());
                    listaArestas.add(a);
                }
            }
        }
        for (int i = 0; i < listaVertices.size(); i++) {    //inserir os vertices no novo grafo
            listaVertices.get(i).setVerticeSemAresta();     //Apagar as antigas aresta
            grafoPrim.addVertice(listaVertices.get(i));
        }
        for (int i = 0; i < listaArestas.size(); i++) {
            grafoPrim.getVertice(listaArestas.get(i).getOrigem()).addAresta(listaArestas.get(i));  //pega a aresta e insere no vertice certo
        }
        //grafo de kruskol completo a partir daqui 
        Impressao i = new Impressao();
        i.ImprimeGrafoOrientado(grafoPrim, "grafoPrim");
    }    
}
