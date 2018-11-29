
import java.util.ArrayList;

public class Malgrange {

    ArrayList<ArrayList> malgrange = new ArrayList<>();

    public Malgrange() {
    }

    public void AlgoritmoMalgrange(Grafo grafo) {

        Vertice a = grafo.grafo.get(0);
        grafo.zerarVisitas();                           //so pra evitar erros de vertices ja visitados visto que ambos os metodos usam o mesmo atributo p verificacao
        a.ftd = grafo.FechoTransitivoDireto(a);         //preenche ftd de todos so V do grafo
        grafo.zerarVisitas();
        a.fti = grafo.FechoTransitivoInverso(a);        //preenche fti de todos so V do grafo
        grafo.zerarVisitas();
        ArrayList<Vertice> ift = grafo.grafo.get(0).intercecaoFechosTransitivos();
    
        for (int i = 0; i < grafo.grafo.size(); i++) {
            for (int j = 0; j < ift.size(); j++) {
                if (grafo.grafo.get(i) == ift.get(j)) {
                    grafo.grafo.get(i).setVisita(true);
                    break;
                }
            }
        }
        boolean verificacao = true;
        for (int i = 0; i < grafo.grafo.size(); i++) {
            if (!grafo.grafo.get(i).visitado) {
                verificacao = false;
            }
        }
        if (verificacao) {
            System.out.println("Grafo f-conexo");
        }
    }
}
