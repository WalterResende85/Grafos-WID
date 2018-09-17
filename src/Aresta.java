public class Aresta {
    private int origem;
    private int destino;
    private String nome;    //numero da aresta+origem+destino
    private static int w = 1;
    
    public Aresta(int origem, int destino) {
        this.origem = origem;
        this.destino = destino;
        nome = w+"@"+origem+"@"+destino+"";
        w++;
        System.out.println("Aresta "+nome+" criada\n");
    }

    
    public int getDestino() {
        return destino;
    }

    public void setDestino(int destino) {
        this.destino = destino;
    }

    public int getOrigem() {
        return origem;
    }

    public void setOrigem(int origem) {
        this.origem = origem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "" + nome;
    }    
}
