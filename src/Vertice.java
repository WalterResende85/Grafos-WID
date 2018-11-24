
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Vertice implements Comparable<Vertice> {

    private ArrayList<Aresta> listaArestaSai = new ArrayList();
    private ArrayList<Aresta> listaArestaChega = new ArrayList();
    public ArrayList<Aresta> arestaPrim = new ArrayList<>();               //Usar no prim
    private String nome;
    private int grau = 0;
    //Dijkstra
    private int distancia;
    private boolean visitado = false;
    private Vertice pai;
    private List<Vertice> vizinhos = new ArrayList<Vertice>();
    private int MenorCaminho;
    //Dijkstra

    public Vertice(String nome) {
        this.nome = nome;
    }

    public Vertice() {
    }

    public void setVerticeSemAresta(){
        this.listaArestaSai.clear();
    }
    
    public ArrayList<Aresta> getListaArestaSai() {
        return listaArestaSai;
    }

    public void setListaArestaSai(ArrayList<Aresta> listaArestaSai) {
        this.listaArestaSai = listaArestaSai;
    }

    public void addAresta(Aresta a) {
        this.listaArestaSai.add(a);
    }

    public void addArestaChega(Aresta a){
        this.listaArestaChega.add(a);
    }
    
    public void removeAresta(Aresta v) {
        for (int i = 0; i < this.listaArestaChega.size(); i++) {
            if(this.listaArestaChega.get(i).equals(v)){
                listaArestaChega.remove(i);
            }
        }
        for (int i = 0; i < this.listaArestaSai.size(); i++) {
            if(this.listaArestaSai.get(i).equals(v)){
                listaArestaSai.remove(i);
            }
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getGrau() {
        int x = 0;
        x = x + this.listaArestaSai.size();
        return x;
    }

    public int menorCaminhoParaX(Vertice x){
        int y = 0;
        for (int i = 0; i < this.listaArestaSai.size(); i++) {
           if(listaArestaSai.get(i).getDestino().equals(x)){
               y = listaArestaSai.get(i).getPeso();
           }
        }
        for (int i = 0; i < this.listaArestaSai.size(); i++) {
           if(listaArestaSai.get(i).getDestino().equals(x)){
               if (listaArestaSai.get(i).getPeso() < y) {
                   y = listaArestaSai.get(i).getPeso();
               }
           }
        }
        return y;
    }
    
    public int apontaParaOutroVertice(String vertice) {              //devolve quantas ligacoes esse vertice faz com o vertice passado
        int x = 0;
        for (int i = 0; i < this.listaArestaSai.size(); i++) {
            if (this.listaArestaSai.get(i).getDestino().getNome().equals(vertice)) {
                x++;
            }
        }
        return x;
    }

    public int apontaParaOutroVertice(Vertice vertice) {              //devolve quantas ligacoes esse vertice faz com o vertice passado
        int x = 0;
        for (int i = 0; i < this.listaArestaSai.size(); i++) {
            if (this.listaArestaSai.get(i).getDestino().getNome().equals(vertice)) {
                x++;
            }
        }
        return x;
    }

    public void apontaPara() {
        System.out.print("Vertice " + this.getNome() + " aponta para: ");
        for (int i = 0; i < this.listaArestaSai.size(); i++) {
            System.out.print(" -> " + this.listaArestaSai.get(i).getDestino());
        }
        if (!this.listaArestaSai.isEmpty()) {
            System.out.print(" ->*");
        } else if (true) {
            System.out.print("NINGUEM");
        }
        System.out.println("");

    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Vertice) {
            if (this.getNome() == ((Vertice) o).getNome()) {
                return true;
            }
        }
        if (o instanceof String) {
            if (this.getNome().equalsIgnoreCase((String) o)) {
                return true;
            }
        }
        return false;

    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.nome);
        return hash;
    }

    public String criaStringOrientado() {
        String lista = "";
        if (this.listaArestaSai.isEmpty()) {
            lista = this.nome + ";";
        } else {
            for (int i = 0; i < listaArestaSai.size(); i++) {
                lista = lista + " " + this.nome;
                lista = lista + " -> " + listaArestaSai.get(i).getDestino().getNome();
                lista = lista + " [label=\"" + listaArestaSai.get(i).getNome() + " = " + listaArestaSai.get(i).getPeso() + "\", fontcolor=darkgreen] ";
                lista = lista + " ;";
            }
        }
        return lista;
    }

    @Override
    public String toString() {
        return this.nome;
    }

    //Dijkstra
    public void setDistancia(int distancia) {

        this.distancia = distancia;
    }

    public int getDistancia() {

        return this.distancia;
    }

    public boolean verificarVisita() {
        return visitado;
    }

    public void visitar() {
        this.visitado = true;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    public void setPai(Vertice pai) {

        this.pai = pai;
    }

    public Vertice getPai() {

        return this.pai;
    }

    public void setVizinhos(List<Vertice> vizinhos) {

        this.vizinhos.addAll(vizinhos);

    }

    public List<Vertice> getVizinhos() {

        return this.vizinhos;
    }

    public int getMenorCaminho() {
        return MenorCaminho;
    }

    public void setMenorCaminho(int MenorCaminho) {
        this.MenorCaminho = MenorCaminho;
    }
    
    //Dijkstra
    @Override
    public int compareTo(Vertice o) {
        if (this.getDistancia() < o.getDistancia()) {
            return -1;
        } else if (this.getDistancia() == o.getDistancia()) {
            return 0;
        }
        return 1;
    }

    public ArrayList<Aresta> getListaArestaChega() {
        return listaArestaChega;
    }

    public void setListaArestaChega(ArrayList<Aresta> listaArestaChega) {
        this.listaArestaChega = listaArestaChega;
    }
    
    public void arestaPrim(){
        for (int i = 0; i < this.listaArestaChega.size(); i++) {
            this.arestaPrim.add(this.listaArestaChega.get(i));
        }
        for (int i = 0; i < this.listaArestaSai.size(); i++) {
            this.arestaPrim.add(this.listaArestaSai.get(i));
        }
        Ordenador X = new Ordenador();                          //Ordenar A pelos valores de peso
        Collections.sort(this.arestaPrim, X);
    }
}