public class Aresta {
    private Vertice origem;
    private Vertice destino;
    private String nome;
    public Aresta(Vertice origem, Vertice destino) {
        this.origem = origem;
        this.destino = destino;
        nome = ""+origem+"@"+destino+"";
        System.out.println("Aresta "+nome+" criada\n");
    }

    
    public Vertice getDestino() {
        return destino;
    }

    public void setDestino(Vertice destino) {
        this.destino = destino;
    }

    public Vertice getOrigem() {
        return origem;
    }

    public void setOrigem(Vertice origem) {
        this.origem = origem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
