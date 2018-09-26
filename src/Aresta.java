
public class Aresta {

    private int origem;
    private int destino;
    private String nome;    //numero da aresta@origem@destino
    private static int w = 1;
    private int peso;

    public Aresta(int origem, int destino, int peso) {
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
        nome = w + "@" + origem + "@" + destino + "";
        w++;
        System.out.println("Aresta " + nome + " criada\n");
    }

    public boolean conectaAoVertice(int x) {
        if (this.destino == x || this.origem == x) {
            return true;
        }
        return false;
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

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "" + nome;
    }
}
