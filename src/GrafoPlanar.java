
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.lang3.SerializationUtils;

public class GrafoPlanar {

    public void Planaridade(Grafo grafo) throws IOException {
        Grafo g = SerializationUtils.clone(grafo);
        int x = 1;
        Impressao impressao = new Impressao();

        if (g.ListaAresta.size() <= (3 * g.grafo.size()) - 6) { //corolario 1
            if (g.ListaAresta.size() <= 7 && g.grafo.size() <= 5) {
                //Algoritmo de redução
                //Passo 1 não precisa ser feito(todos os componentes já determinados)
                g.removeLoop();    //Passo 2
                while (g.verificaGrau2()) {//para repetir os passos 3 e 4
                    //passo 3
                    for (Vertice v : g.grafo) {
                        if (v.grau == 2) {
                            v.setVisita(true);
                        }
                    }
                    int i = 0;
                    if (!g.TodosSaoGrau2()) {    //se todos os vertice são grau dois já dá pra saber que o grafo reduzido final tera 2 v e uma aresta
                        while (g.contemVisitado()) {
                            if (g.grafo.get(i).visitado) {
                                g.removeVerticeGrauDois(g.grafo.get(i));
                            }
                            if (i == g.grafo.size() - 1) {
                                i = 0;
                            } else {
                                i++;
                            }
                        }
                        //passo 3
                        impressao.ImprimeGrafoNaoOrientado(g, "Planar" + x++);
                        //passo 4
                        g.removeParalelo();
                        //passo 4
                        impressao.ImprimeGrafoNaoOrientado(g, "Planar" + x++);
                        g.zerarVisitas();
                        g.zerarVisitasArestas();
                    } else {//sai do algoritmo de redução e monta o grafo como ele iria ficar
                        g.zeraGrafo();
                        g.addVertice(new Vertice("A"));
                        g.addVertice(new Vertice("B"));
                        Vertice A = g.getVertice("A");
                        Vertice B = g.getVertice("B");
                        g.addAresta(new Aresta(A, B, 1));
                    }
                }
            }
            if (g.ListaAresta.size() == 1) {
                System.out.println("Grafo com pelo menos 5 vértices 7 arestas");
                System.out.println("Teve como resutado final UMA ARESTA por isso: É PLANAR");
            }else{
                if (g.grafo.size() == 4 && InformaCompleto(g)) {
                    System.out.println("Grafo com pelo menos 5 vértices 7 arestas");
                    System.out.println("Teve como resultado final UM GRAFO COMPLETO COM 4 VÉRTICES Por isso: É PLANAR");
                }else{
                    System.out.println("Algoritmo de redução INCONCLUSIVO");
                }
            }
        } else {
            System.out.println("Grafo não é PLANAR pois não passa pelo teste: A<= 3 * V - 6 ");
        }
        impressao.ImprimeGrafoNaoOrientado(g, "Planar");
    }

    public static boolean InformaCompleto(Grafo grafo) {
        for (Vertice v : grafo.grafo) {
            for (Vertice x : grafo.grafo) {
                if (!v.equals(x)) {
                    if (v.verificaVizinho(x)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
