
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Grafo implements Serializable {

    public ArrayList<Vertice> grafo = new ArrayList<>();            //lista de todos os Vertice do grafo
    public ArrayList<Aresta> ListaAresta = new ArrayList<>();

    public void setVertices(List<Vertice> vertices) {
        this.grafo.addAll(vertices);
    }

    public void addVertice(Vertice novoVertice) {
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

    public void removeVertice(Vertice x) {
        for (Vertice v : grafo) {    //remove esse vertice da vizinhança dos outros
            v.removeVizinho(x);
        }
        ArrayList<Aresta> ArestasParaRemocao = new ArrayList<>();
        for (Aresta a : ListaAresta) {
            if (a.getA().equals(x) || a.getB().equals(x)) {
                ArestasParaRemocao.add(a);
            }
        }
        for (Aresta a : ArestasParaRemocao) {
            ListaAresta.remove(a);
            a.getA().removeAresta(a);
            a.getB().removeAresta(a);
        }
        this.grafo.remove(x);
    }

    void removeAresta(Aresta a) {
        a.getA().removeAresta(a);
        a.getB().removeAresta(a);
        ListaAresta.remove(a);
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

    public void setGrauDosVertices() {
        zeraGrauDosVertices();
        for (Aresta a : ListaAresta) {
            a.getA().grau++;
            a.getB().grau++;
        }
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

    public boolean contains(Vertice v) {
        for (int i = 0; i < this.grafo.size(); i++) {
            if (grafo.get(i).equals(v)) {
                return true;
            }
        }
        return false;
    }

    public void zerarVisitas() {
        for (int i = 0; i < this.grafo.size(); i++) {
            this.grafo.get(i).setVisita(false);
        }
    }

    void removeVerticeGrauDois(Vertice v) {
        v.todasAsArestas();
        List vizinhos = v.getVizinhos();
        if (vizinhos.size() == 1) {
            //
        } else if (vizinhos.size() == 2) {
            Vertice a = (Vertice) vizinhos.get(0);
            Vertice b = (Vertice) vizinhos.get(1);
            this.addAresta(new Aresta(a, b, 0));
        }
        this.removeVertice(v);
    }

    void addListaArestas(ArrayList<Aresta> T) {
        this.ListaAresta.addAll(T);
    }

    void removeLoop() {
        for (Vertice v : grafo) {
            v.removeLoop();
        }
    }

    boolean verificaGrau2() {
        this.setGrauDosVertices();
        for (Vertice v : grafo) {
            if (v.grau == 2) {
                return true;
            }
        }
        return false;
    }

    boolean contemVisitado() {
        for (Vertice v : grafo) {
            if (v.visitado) {
                return true;
            }
        }
        return false;
    }

    void removeParalelo() {
        zerarVisitasArestas();
        for (Aresta a : ListaAresta) {
            for (Aresta b : ListaAresta) {
                if (a.getA().equals(b.getA()) && a.getB().equals(b.getB()) && !a.equals(b) && !a.visitado) { // arestas diferentes com mesmos Vertices
                    b.visitado = true;
                }
                if (a.getA().equals(b.getB()) && a.getB().equals(b.getA()) && !a.equals(b) && !a.visitado) { // arestas diferentes com mesmos Vertices
                    b.visitado = true;
                }
            }
        }
        int i = ListaAresta.size() - 1;
        while (this.contemVisitadoAresta()) {
            if (i < 0) {
                i = ListaAresta.size()-1;
            }
            if (this.ListaAresta.get(i).visitado) {
                removeAresta(ListaAresta.get(i));
                i--;
            } else {
                i--;
            }
        }
    }

    public void zerarVisitasArestas() {
        for (Aresta a : ListaAresta) {
            a.visitado = false;
        }
    }

    private boolean contemVisitadoAresta() {
        for (Aresta a : ListaAresta) {
            if (a.visitado) {
                return true;
            }
        }
        return false;
    }

    private void zeraGrauDosVertices() {
        for (Vertice vertice : grafo) {
            vertice.grau = 0;
        }
    }

    boolean TodosSaoGrau2() {
        for (Vertice v : grafo) {
            if (v.grau != 2) {
                return false;
            }
        }
        return true;
    }

    void zeraGrafo() {
        this.ListaAresta.clear();
        this.grafo.clear();
    }
}
