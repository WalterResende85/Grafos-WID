public class Aresta {
    private String nome;
    private Vertice origem;
    private Vertice destino;
    private int peso;
    
    public Aresta(String nome) {
        this.nome = nome;
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
    }

    Aresta(Vertice origem, Vertice destino, int peso, String nome) {
        this.nome = nome;
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
    @Override
    public String toString() {
        return this.nome+"("+this.peso+")";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Aresta) {
            Aresta vRef = (Aresta) obj;
            if (this.getNome().equals(vRef.getNome())) {
                return true;
            }
        }
        return false;
    }
    public boolean equals(String a) {
        if (a instanceof String) {
            if (this.getNome().equalsIgnoreCase(a)) {
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