
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.lang3.SerializationUtils;

public class Malgrange {

    public void AlgoritmoMalgrange(Grafo grafo) throws IOException {
        Impressao impressao = new Impressao();
        Grafo g = SerializationUtils.clone(grafo);
        Grafo f = SerializationUtils.clone(grafo);
        Grafo grafoMalgrange = new Grafo();
        g.zerarVisitas();
        g.zerarVisitasArestas();
        while (!g.grafo.isEmpty()) {
            g.zerarVisitas();
            ftd(g.grafo.get(0), g); //FTD E FTI do primeiro vertice
            fti(g.grafo.get(0), g);//FTD E FTI do primeiro vertice
            criaVerticeIntercecaoFtdFti(g, grafoMalgrange);
        }
        adicionaArestas(grafoMalgrange, f);
        impressao.ImprimeGrafoOrientado(grafoMalgrange, "Malgrange");
    }

    void ftd(Vertice v, Grafo g
    ) {
        v.ftd = true;
        for (Aresta a : v.getListaArestaSai()) {
            if (!a.getB().ftd) {
                ftd(a.getB(), g);
            }
        }
    }

    void fti(Vertice v, Grafo g) {
        v.fti = true;
        for (Aresta a : v.getListaArestaChega()) {
            if (!a.getA().fti) {
                fti(a.getA(), g);
            }
        }
    }

    private void criaVerticeIntercecaoFtdFti(Grafo g, Grafo m) {
        ArrayList<Vertice> x = new ArrayList<>();
        for (Vertice v : g.grafo) {
            if (v.ftd && v.fti) {
                x.add(v);
            }
        }
        String nomeVerticeMalgrange = "";
        for (Vertice vertice : x) {
            nomeVerticeMalgrange += vertice.getNome() + "";
            g.removeVertice(vertice);
        }
        m.addVertice(new Vertice(nomeVerticeMalgrange));
        m.grafo.get(m.grafo.size() - 1).conjuntoV = x;
    }

    private void adicionaArestas(Grafo grafoMalgrange, Grafo original) {
        for (Aresta aresta : original.ListaAresta) {
            original.zerarVisitas();
            grafoMalgrange.zerarVisitas();
            Aresta x = new Aresta();
            Vertice a = aresta.getA();
            Vertice b = aresta.getB();
            for (Vertice vertice : grafoMalgrange.grafo) {
                if (vertice.conjuntoV.contains(a) && !vertice.visitado) {
                    vertice.visitado = true;
                    x.origem = vertice;
                }
                if (vertice.conjuntoV.contains(b) && !vertice.visitado) {
                    vertice.visitado = true;
                    x.destino = vertice;
                }
            }
            if (x.destino != x.origem && x.destino != null && x.origem != null) {
                grafoMalgrange.addAresta(new Aresta(x.origem,x.destino,0));
            }
        }
    }
}
