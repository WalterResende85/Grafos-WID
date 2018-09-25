
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

    public void removeArestaDosVertices(int a) {
        for (int i = 0; i < listaAdjacente.size(); i++) {
            if (listaAdjacente.get(i).getDestino() == a) {
                listaAdjacente.remove(i);
            }
        }
    }
    
    public void removeArestaDosVerticesNaoOrientado(int a) {
        for (int i = 0; i < listaAdjacente.size(); i++) {
            if (listaAdjacente.get(i).getDestino() == a || listaAdjacente.get(i).getOrigem() == a) {
                listaAdjacente.remove(i);
            }
        }
    }

    public void removeAresta(String a) {
        for (int i = 0; i < listaAdjacente.size(); i++) {
            if (listaAdjacente.get(i).getNome().equalsIgnoreCase(a)) {
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
    
    public int getGrauSaida(){
        return this.listaAdjacente.size();
    }
    
    public int getGrauEntrada(int x){
        int z=0;
         for (Aresta i: listaAdjacente){
             if(i.getDestino() == x){
             z++;
             }
         }
         return z;
    }
}
