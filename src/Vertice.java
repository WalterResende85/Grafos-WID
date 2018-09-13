
import java.util.ArrayList;

public class Vertice {

    private ArrayList<Aresta> listaAdjacente = new ArrayList();
    private int nome;
    private static int w = 0;

    public Vertice() {
        nome = w;
        w++;
        System.out.println("Vertice " + nome + " criado\n");
    }

    boolean insereAdjacente(Aresta a) {
        listaAdjacente.add(a);
        return true;
    }

    void mostrarAdjacentes() {
        String z; //Controlar a impressão
        for (int i = 0; i < listaAdjacente.size(); i++) {
            z = mostrarAdjacente(listaAdjacente.get(i));
            System.out.print(z + "-->");
        }
    }

    @Override
    public String toString() {
        return "" + this.nome;
    }

    public String mostrarAdjacente(Aresta v) //para a impressão
    {
        return ""+v.getDestino();
    }

    public ArrayList<Aresta> getListaAdjacente() {
        return listaAdjacente;
    }

    public void setListaAdjacente(ArrayList<Aresta> listaAdjacente) {
        this.listaAdjacente = listaAdjacente;
    }

    public int getNome() {
        return nome;
    }

    public void setNome(int nome) {
        this.nome = nome;
    }

}
