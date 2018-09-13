
import java.util.ArrayList;

public class Vertice {

    private ArrayList<Aresta> listaAdjacente = new ArrayList();
    private int id;
    private static int w = 0;

    public Vertice() {
        id = this.w;
        w++;
        System.out.println("Vertice " + id + " criado\n");
    }

    boolean insereAdjacente(Aresta a) {
        listaAdjacente.add(a);
        return true;
    }

    void mostrarAdjacentes() {
        for (Aresta x : listaAdjacente) {
            System.out.print("" + x.getDestino() + "-->");
        }
    }

    public void removeVertice(int a) {
        for (int i = 0; i < listaAdjacente.size(); i++) {
            if (listaAdjacente.get(i).getDestino() == a) {
                listaAdjacente.remove(i);
            }
        }
    }

    public void removeAresta(String a) {
        for (int i = 0; i < listaAdjacente.size(); i++) {
            if (listaAdjacente.get(i).getNome() == a) {
                listaAdjacente.remove(i);
            }
        }
    }

    @Override
    public String toString() {
        return "" + this.id;
    }

    public ArrayList<Aresta> getListaAdjacente() {
        return listaAdjacente;
    }

    public void setListaAdjacente(ArrayList<Aresta> listaAdjacente) {
        this.listaAdjacente = listaAdjacente;
    }

    public int getId() {
        return id;
    }

    public void getId(int id) {
        this.id = id;
    }

}
