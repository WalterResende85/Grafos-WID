import java.util.ArrayList;
import java.util.List;

public class Grafo {

    public ArrayList<Vertice> grafo = new ArrayList<>();            //lista de todos os Vertice do grafo
    public ArrayList<Aresta> ListaAresta = new ArrayList<>();       
    public ArrayList<Aresta> ListaArestaChega = new ArrayList<>();

    public void setVertices(List<Vertice> vertices) {
        this.grafo.addAll(vertices);
    }
    
    public void adicionarVertice(Vertice novoVertice) {
        this.grafo.add(novoVertice);
    }

    public List<Vertice> getGrafo() {
        return this.grafo;
    }

    public void insereVertice(Vertice v) {
        this.grafo.add(v);
    }

    public Vertice getVertice(Vertice v) {
        return grafo.get(grafo.lastIndexOf(v));
    }

    public void removeVertice(String v) {
        for (int i = 0; i < grafo.size(); i++) {
            if (grafo.get(i).equals(v)) {
                grafo.remove(i);
            }
        }
    }

    public Vertice getVertice(String nome) {
        for (int i = 0; i < this.grafo.size(); i++) {
            if (this.grafo.get(i).getNome().equalsIgnoreCase(nome)) {
                return grafo.get(i);
            }
        }
        return null;
    }

    public Vertice getVertice(int i) {
        return this.grafo.get(i);
    }

    public ArrayList<Aresta> getListaAresta() {
        return this.ListaAresta;
    }

    public void addAresta(Aresta a) {
        this.ListaAresta.add(a);
    }

    public Aresta getArestaPeloNome(String a) {
        for (int i = 0; i < ListaAresta.size(); i++) {
            if (ListaAresta.get(i).equals(a)) {
                return ListaAresta.get(i);
            }
        }
        return null;
    }

    public int getGrauTotalDoVertice(int i) {
        int x = 0;
        x += this.grafo.get(i).getGrau();
        x += this.numeroLigacoesDeXparaY(this.grafo.get(i));
        return x;
    }

    public int numeroLigacoesDeXparaY(String v) {
        int x = 0;
        for (int i = 0; i < this.grafo.size(); i++) {
            if (!this.grafo.get(i).equals(v)) {
                x += this.grafo.get(i).apontaParaOutroVertice(v);
            }
        }
        return x;
    }

    public int numeroLigacoesDeXparaY(Vertice v) {
        int x = 0;
        for (int i = 0; i < this.grafo.size(); i++) {
            if (!this.grafo.get(i).equals(v)) {
                x += this.grafo.get(i).apontaParaOutroVertice(v.getNome());
            }
        }
        return x;
    }

    public ArrayList<Aresta> getListaArestaChega() {
        return ListaArestaChega;
    }

    public void setListaArestaChega(ArrayList<Aresta> ListaArestaChega) {
        this.ListaArestaChega = ListaArestaChega;
    }
    
    public boolean contains(Vertice v){
        for (int i = 0; i < this.grafo.size();i++) {
            if (grafo.get(i).equals(v)) {
                return true;
            }
        }
        return false;
    }    
}