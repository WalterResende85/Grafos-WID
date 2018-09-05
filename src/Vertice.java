import java.util.ArrayList;

public class Vertice {

    ArrayList<Vertice> listaAdjacente = new ArrayList();
    int nome;
    static int w=0;
    public Vertice() {
        nome=w;
        w++;
        System.out.println("Vertice "+ nome +" criado\n");
    }

    boolean insereAdjacente(Vertice a) {
        listaAdjacente.add(a);
        return true;
    }

    public int getNome() {
        return nome;
    }
 
    

    void mostrarAdjacentes() {
        for (int i = 0; i < listaAdjacente.size(); i++) {
            System.out.print(listaAdjacente.get(i)+"-->");
        }
    }

    @Override
    public String toString() {
        return "" + this.nome;
    }

}
