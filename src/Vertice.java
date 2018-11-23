
import java.util.ArrayList;
import java.util.Objects;

public class Vertice {

    private ArrayList<Aresta> listaAdjacente = new ArrayList();
    private int id;
    private static int w = 0;
    private int grauEntrada;
    private int grauSaida;
    private int numAdjacencia;
    private String nome;

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

    
    public int VerificaSaida(int x) {//orientado
        int z = 0;
        for (Aresta i : listaAdjacente) {
            if (i.getDestino() == x) {
                z++;
            }
        }
        return z;
    }

    public int mostraGrau() { //para grafo não orientado
        return this.listaAdjacente.size();
    }

    public void mostrarConexao() {
        /*
        for (Aresta x : listaAdjacente) {
            System.out.print("" + x.getDestino() + "-->");
        }
         */
        for (int i = 0; i < listaAdjacente.size(); i++) {
            if (listaAdjacente.get(i).getOrigem() == this.id) {
                System.out.print(listaAdjacente.get(i).getDestino() + "-->");
            } else {
                System.out.print(listaAdjacente.get(i).getOrigem() + "-->");
            }
        }
    }

    public boolean ligaEmTodos(Vertice x) { //nao-orientado
        boolean completo = false;
        for (int i = 0; i < listaAdjacente.size(); i++) {
            if (listaAdjacente.get(i).getDestino() == x.id || listaAdjacente.get(i).getOrigem() == x.id) {
                completo = true;
            }
        }

        return completo;
    }

    public boolean ligacao(int x) {
        if (x == this.id) {
            //se verdadeiro o id passado pertence a esse vertice
            return true;
        }
        for (int i = 0; i < listaAdjacente.size(); i++) {
            if (listaAdjacente.get(i).getDestino() == x || listaAdjacente.get(i).getOrigem() == x) {
                //se verdadeiro o id do vertice passado conecta chegando ou saindo com esse
                return true;
            }
        }
        return false;
    }

    public String criaStringOrientado() {
        String lista = "";
        if (listaAdjacente.isEmpty()) {
            lista = this.id + ";";
        } else {
            for (int i = 0; i < listaAdjacente.size(); i++) {
                lista = lista + "" + this.id;
                lista = lista + " -> " + listaAdjacente.get(i).getDestino();
                lista = lista + "[label=\"" + listaAdjacente.get(i).getPeso() + "\", fontcolor=darkgreen]";
                lista = lista + ";";
            }
        }
        return lista;
    }

   public String criaStringNaoOrientada() {
        String lista = "";
        for (int i = 0; i < listaAdjacente.size(); i++) {
            if (listaAdjacente.get(i).getOrigem() == this.id) {
                lista = lista + "" + this.id;
                lista = lista + " -- " + listaAdjacente.get(i).getDestino();
                lista = lista + "[label=\"" + listaAdjacente.get(i).getPeso() + "\", fontcolor=red]";
                lista = lista + ";";
            }
        }
        System.out.println("Lista "+this.id+" = "+lista);
        return lista;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
    public int getGrauEntrada() {
        return grauEntrada;
    }

    public void setGrauEntrada(int grauEntrada) {
        this.grauEntrada = grauEntrada;
    }

    public void setNumAdjacencia() {
        this.numAdjacencia = this.grauEntrada + this.getGrauSaida();
    }

    public int getNumAdjacencia() {
        return numAdjacencia;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGrauSaida() { //orientado (o numero de aresta representa o numero de saidas desse vertice)
        return this.listaAdjacente.size();
    }
     public ArrayList<Aresta> getListaAdjacente() {
        return listaAdjacente;
    }

    public void setListaAdjacente(ArrayList<Aresta> listaAdjacente) {
        this.listaAdjacente = listaAdjacente;
    }
    @Override
    public String toString() {
        return "" + this.id;
    }
}
