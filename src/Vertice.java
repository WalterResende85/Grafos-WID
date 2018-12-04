
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Vertice implements Comparable<Vertice>,Serializable {

    private ArrayList<Aresta> listaArestaSai = new ArrayList();
    private ArrayList<Aresta> listaArestaChega = new ArrayList();
    public ArrayList<Aresta> TodasAsArestas = new ArrayList<>();               //Usar no prim
    private String nome;
    public int grau = 0;
    //Dijkstra
    private int distancia;
    public boolean visitado = false;
    private Vertice pai;
    private List<Vertice> vizinhos = new ArrayList<Vertice>();
    private int MenorCaminho;
    //Dijkstra
    //Malgrange
    //Malgrange


    public Vertice(String nome) {
        this.nome = nome;
    }

    public Vertice() {  //usado no Dijkstra
    }

    public void setVerticeSemAresta() {
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

    public void addArestaChega(Aresta a) {
        this.listaArestaChega.add(a);
    }

    public void removeAresta(Aresta v) {
        for (int i = 0; i < this.listaArestaChega.size(); i++) {
            if (this.listaArestaChega.get(i).equals(v)) {
                listaArestaChega.remove(i);
            }
        }
        for (int i = 0; i < this.listaArestaSai.size(); i++) {
            if (this.listaArestaSai.get(i).equals(v)) {
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

    public int menorCaminhoParaX(Vertice x) {
        int y = 0;
        for (int i = 0; i < this.listaArestaSai.size(); i++) {
            if (listaArestaSai.get(i).getDestino().equals(x)) {
                y = listaArestaSai.get(i).getPeso();
            }
        }
        for (int i = 0; i < this.listaArestaSai.size(); i++) {
            if (listaArestaSai.get(i).getDestino().equals(x)) {
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

    String criaStringNaoOrientado() {
        String lista = "";
        if (this.listaArestaSai.isEmpty()) {
            lista = this.nome + ";";
        } else {
            for (int i = 0; i < listaArestaSai.size(); i++) {
                lista = lista + " " + this.nome;
                lista = lista + " -- " + listaArestaSai.get(i).getDestino().getNome();
                lista = lista + " [label=\"" + listaArestaSai.get(i).getNome() + " = " + listaArestaSai.get(i).getPeso() + "\", fontcolor=darkgreen] ";
                lista = lista + " ;";
            }
        }
        return lista;
    }

    @Override
    public String toString() {
        return this.nome.toUpperCase();
    }

    //Dijkstra
    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public int getDistancia() {
        return this.distancia;
    }

    public boolean getVisita() {
        return visitado;
    }

    public void setVisita(boolean visitado) {
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

    public void addVizinho(Vertice vizinho) {
        if (!this.vizinhos.contains(vizinho)) {
            this.vizinhos.add(vizinho);
        }
    }

    public void removeVizinho(Vertice vizinho) {
        for (Vertice v : this.vizinhos) {
            if (v.equals(vizinho)) {
                this.vizinhos.remove(v);
                break;         //inseri o break por problema de concorencia(!)
            }
        }
    }

    public boolean verificaVizinho(Vertice vizinho) {
        for (Vertice v : vizinho.vizinhos) {
            if (v.equals(vizinho)) {
                return true;
            }
        }
        return false;
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

    void removeLoop() {
        for (Aresta a : this.listaArestaChega) {
            if (a.getA().equals(a.getB())) {
                this.listaArestaChega.remove(a);
                this.listaArestaSai.remove(a);
                break;
            }
        }
    }

    void removeParalelo() {
        if (this.grau >= 2) {

        }
    }

    public ArrayList<Aresta> todasAsArestas() {
        this.TodasAsArestas.addAll(listaArestaChega);
        this.TodasAsArestas.addAll(listaArestaSai);
        return TodasAsArestas;
    }
}
