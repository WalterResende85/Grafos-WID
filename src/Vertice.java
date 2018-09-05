import java.util.ArrayList;

public class Vertice {

    ArrayList listaAdjacente = new ArrayList();
    int nome;
    static int w=0;
    public Vertice() {
        nome=w;
        w++;
        System.out.println("Vertice "+nome+" criado\n");
    }

    boolean insereAdjacente(int a) {
        listaAdjacente.add(a);
        return true;
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
