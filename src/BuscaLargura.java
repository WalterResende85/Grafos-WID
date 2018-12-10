
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BuscaLargura {

    Grafo grafo;
    Grafo grafoLargura = new Grafo();
    ArrayList<Vertice> filaVertice = new ArrayList<>();

    public BuscaLargura(Grafo grafo) throws IOException {
        grafo.zerarVisitas();
    }

    void AlgoritmoLargura(Vertice v) throws IOException {
        v.setVisita(true);
        
        grafoLargura.addVertice(verticeClone(v));
        
        filaVertice.add(v);
        while (!filaVertice.isEmpty()) {
            Vertice i = filaVertice.get(0);
            filaVertice.remove(0);
            for (Vertice z: i.getVizinhos()) {
                if (!z.visitado) {
                    z.visitado = true;
                    grafoLargura.addVertice(verticeClone(z));
                    grafoLargura.addAresta(new Aresta(i,z,0));
                    filaVertice.add(z);
                }
            }
        }
        Impressao i = new Impressao();
        i.ImprimeArvoreLargura(grafoLargura ,"Largura");
    }
    
    Vertice verticeClone(Vertice v){
    Vertice x = new Vertice(v.getNome());
    return x;
    }
}
