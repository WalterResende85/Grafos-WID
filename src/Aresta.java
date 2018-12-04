
import java.io.Serializable;

public class Aresta implements Serializable {

    static int a = 0;
    private String nome;
    private Vertice origem;
    private Vertice destino;
    private int peso;
    boolean orientado = true;
    boolean visitado = false;

    Aresta(Vertice origem, Vertice destino, int peso) {
        this.nome = ++a + "";  //nome gerado automaticamente a partir dos vertices
        this.origem = origem;
        origem.addVizinho(destino);
        origem.addAresta(this);
        this.destino = destino;
        destino.addArestaChega(this);
        destino.addVizinho(origem);
        this.peso = peso;
    }

    Aresta() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return this.nome + "(" + this.peso + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Aresta) {
            Aresta vRef = (Aresta) obj;
            if (this.getNome().equals(vRef.getNome())) {
                return true;
            }
        }
        if (obj instanceof String) {
            if (this.getNome().equalsIgnoreCase((String)obj)) {
                return true;
            }
        }
        return false;
    }

    public Vertice getOrigem() {
        return origem;
    }

    public Vertice getA() {
        return origem;
    }

    public Vertice getDestino() {
        return destino;
    }

    public Vertice getB() {
        return destino;
    }

    public int getPeso() {
        return peso;
    }
}
