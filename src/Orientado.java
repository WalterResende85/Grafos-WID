
import edu.ifet.grafos.graphview.GraphView;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.List;

public class Orientado {

    public static void Orientado() throws IOException, CloneNotSupportedException {

        System.out.println("Orientado");

        int menu;
        Scanner ler = new Scanner(System.in);
        Grafo grafo = new Grafo();
        do {
            System.out.println("");
            System.out.println("----------------MENU----------------");
            System.out.println("        99- Criar Grafo Completo para testes");
            System.out.println("        1- Criar Vertice ");
            System.out.println("        2- Criar Aresta ");
            System.out.println("        3- Remover Vertice");
            System.out.println("        4- Remover Aresta");
            System.out.println("        5- Informação");
            System.out.println("        6- Para criar a imagem");
            System.out.println("        7- Para usar o Algoritmo de dijkstra ");
            System.out.println("        8- Para usar o Algoritmo de Kruskal ");
            System.out.println("        9- Para usar o Algoritmo de Prim");
            System.out.println("        0- para SAIR ");
            System.out.println("-------------------------------------");
            menu = ler.nextInt();
            if (menu == 1) {
                System.out.println("Informe o nome do Vertice: ");
                String nome = ler.next();
                grafo.adicionarVertice(new Vertice(nome));  //cria e adiciona vertice ao grafo
            }

            if (menu == 2) {
                System.out.println("");
                System.out.println("Informe vertice origem");
                String nomeVerticeOrigem = ler.next();
                Vertice VerticeOrigem = grafo.getVertice(nomeVerticeOrigem);
                System.out.println("Informe vertice destino");
                String nomeVerticeDestino = ler.next();
                Vertice VerticeDestino = grafo.getVertice(nomeVerticeDestino);
                if (VerticeOrigem != null || VerticeDestino != null) {
                    System.out.println("Informe o nome da aresta");
                    String nomeAresta = ler.next();
                    System.out.println("Informe o peso da aresta");
                    int pesoAresta = ler.nextInt();
                    grafo.addAresta(new Aresta(VerticeOrigem, VerticeDestino, pesoAresta, nomeAresta));
                    grafo.getListaArestaChega().add(grafo.getListaAresta().get(grafo.getListaAresta().size() - 1));
                    VerticeOrigem.addAresta(grafo.getListaAresta().get(grafo.getListaAresta().size() - 1));
                    VerticeDestino.addArestaChega(grafo.getListaAresta().get(grafo.getListaAresta().size() - 1));
                } else {
                    System.out.println("ARESTA NÂO FOI CRIADA!!!!!");
                }
            }
            if (menu == 3) {
                System.out.println("Informe o nome do vertice a ser REMOVIDO: ");
                String verticeRemocao = ler.next();
                if (grafo.getVertice(verticeRemocao) != null) {
                    for (int i = 0; i < grafo.getListaAresta().size(); i++) {
                        if (grafo.getListaAresta().get(i).getDestino().equals(verticeRemocao)) {
                            grafo.getListaAresta().remove(i);
                        }
                    }
                    for (int i = 0; i < grafo.getListaArestaChega().size(); i++) {
                        if (grafo.getListaArestaChega().get(i).getDestino().equals(verticeRemocao)) {
                            grafo.getListaAresta().remove(i);
                        }
                    }
                    grafo.removeVertice(grafo.getVertice(verticeRemocao).getNome());
                } else {
                    System.out.println("Vertice a ser removido não existe");
                }
            }
            if (menu == 4) {
                System.out.println("Informe o nome da aresta a ser REMOVIDO: ");
                String nomeAresta = ler.next();
                Aresta x = grafo.getArestaPeloNome(nomeAresta);
                Vertice vo = x.getOrigem();
                Vertice vd = x.getDestino();
                vo.removeAresta(x);
                vd.removeAresta(x);
            }
            if (menu == 5) {
                InformaConexoes(grafo);
                InformaRegularidade(grafo);
                InformaCompleto(grafo);
            }
            if (menu == 6) {
                ImprimeGrafo(grafo, "grafo");
            }
            if (menu == 7) {
                System.out.println("Vertice de saida: ");
                String verticeSaida = ler.next();
                System.out.println("Vertice de chegada: ");
                String verticeChegada = ler.next();
                Dijkstra d = new Dijkstra();
                ArrayList<Vertice> l = d.encontrarMenorCaminhoDijkstra(grafo, grafo.getVertice(verticeSaida), grafo.getVertice(verticeChegada));
                int x = 0;
                System.out.println("");
                System.out.println("Menor Caminho");
                for (int i = 0; i < l.size(); i++) {
                    System.out.print(" - " + l.get(i).getNome());
                    x += l.get(i).getMenorCaminho();
                }
                System.out.println("");
                System.out.println("Peso do menor caminho: " + x);
            }

            if (menu == 8) {
                AlgoritmoKruskal(grafo);
            }

            if (menu == 9) {
                AlgoritmoPrim(grafo);
            }

            if (menu == 99) {
                System.out.println("Existem 2 Grafos para testes, escolha um deles");
                int x = ler.nextInt();
                grafo = GrafoTeste(x);
            }
        } while (menu != 0);

    }

    private static void InformaConexoes(Grafo grafo) {
        for (int i = 0; i < grafo.getGrafo().size(); i++) {
            grafo.getGrafo().get(i).apontaPara();
        }
    }

    private static void InformaRegularidade(Grafo grafo) {
        int grauPrimeiro = grafo.getGrauTotalDoVertice(0);
        boolean regular = true;
        for (int i = 0; i < grafo.getGrafo().size(); i++) {
            if (grafo.getGrauTotalDoVertice(i) != grauPrimeiro) {
                regular = false;
            }
        }
        if (regular) {
            System.out.println("Grafo " + grauPrimeiro + "-Regular");
        } else {
            System.out.println("Grafo Nao Regular");
        }

    }

    private static void InformaCompleto(Grafo grafo) {

    }

    private static void AlgoritmoKruskal(Grafo grafo) throws IOException, CloneNotSupportedException {
        Grafo g = grafo;
        Grafo grafoKruskal = new Grafo();
        Ordenador X = new Ordenador();                          //Ordenar A pelos valores de peso
        Collections.sort(g.getListaAresta(), X);            //Ordenar A pelos valores de peso

        ArrayList<Aresta> listaArestas = new ArrayList<>();
        ArrayList<Vertice> listaVertices = new ArrayList<>();

        listaArestas.add(g.getListaAresta().get(0));
        listaVertices.add(g.getListaAresta().get(0).getA());
        listaVertices.add(g.getListaAresta().get(0).getB());

        for (int i = 1; i < g.getListaAresta().size(); i++) {
            Aresta a = g.getListaAresta().get(i);
            if (listaVertices.contains(a.getA()) && listaVertices.contains(a.getB())) {
            }
            if (!listaVertices.contains(a.getA()) && listaVertices.contains(a.getB())) {
                listaArestas.add(g.getListaAresta().get(i));
                listaVertices.add(a.getA());
            }
            if (!listaVertices.contains(a.getB()) && listaVertices.contains(a.getA())) {
                listaArestas.add(g.getListaAresta().get(i));
                listaVertices.add(a.getB());
            }

        }
        for (int i = 0; i < listaVertices.size(); i++) {    //inserir os vertices no novo grafo
            listaVertices.get(i).setVerticeSemAresta();     //Apagar as antigas aresta
            grafoKruskal.adicionarVertice(listaVertices.get(i));
        }
        for (int i = 0; i < listaArestas.size(); i++) {
            grafoKruskal.getVertice(listaArestas.get(i).getOrigem()).addAresta(listaArestas.get(i));  //pega a aresta e insere no vertice certo
        }
        //grafo de kruskol completo a partir daqui 
        ImprimeGrafo(grafoKruskal, "grafoKruskal");

    }

    private static void AlgoritmoPrim(Grafo grafo) throws IOException {
        Grafo g = grafo;
        Ordenador X = new Ordenador();                          //Ordenar A pelos valores de peso
        Collections.sort(g.getListaAresta(), X);
        Vertice v = g.getGrafo().get(0);
        Grafo grafoPrim = new Grafo();

        ArrayList<Aresta> listaArestas = new ArrayList<>();
        ArrayList<Vertice> listaVertices = new ArrayList<>();

        listaVertices.add(v);
        for (int j = 0; j < g.getListaAresta().size(); j++) {
            for (int i = 0; i < g.getListaAresta().size(); i++) {
                Aresta a = g.getListaAresta().get(i);
                if (listaVertices.contains(a.getA()) && !listaVertices.contains(a.getB())) {
                    listaVertices.add(a.getB());
                    listaArestas.add(a);
                }
                if (!listaVertices.contains(a.getA()) && listaVertices.contains(a.getB())) {
                    listaVertices.add(a.getA());
                    listaArestas.add(a);
                }
            }
        }
        for (int i = 0; i < listaVertices.size(); i++) {    //inserir os vertices no novo grafo
            listaVertices.get(i).setVerticeSemAresta();     //Apagar as antigas aresta
            grafoPrim.adicionarVertice(listaVertices.get(i));
        }
        for (int i = 0; i < listaArestas.size(); i++) {
            grafoPrim.getVertice(listaArestas.get(i).getOrigem()).addAresta(listaArestas.get(i));  //pega a aresta e insere no vertice certo
        }
        //grafo de kruskol completo a partir daqui 
        ImprimeGrafo(grafoPrim, "grafoPrim");
    }

    private static void ImprimeGrafo(Grafo grafo, String arquivo) throws IOException {
        String g = "digraph graphname {";
        for (int i = 0; i < grafo.getGrafo().size(); i++) {
            String a = grafo.getGrafo().get(i).criaStringOrientado();
            g = g + a;
        }
        g = g + "}";

        FileWriter arq = new FileWriter(arquivo + ".txt");
        PrintWriter gravarArq = new PrintWriter(arq);
        gravarArq.printf(g);
        arq.close();
        System.out.println("Arquivo " + arquivo + ".txt gravado");

        // Criando um objeto da classe responsável por gerar verticeOrigem imagem do grafo
        GraphView gv = new GraphView();
        //Lendo verticeOrigem String 
        gv.readString(g);
        //Imprimindo verticeOrigem grafo em texto

        System.out.println(gv.getDotSource());
        //Gerando uma imagem com verticeB nome out.png 
        File ImagemGrafo = new File(arquivo + ".png");
        gv.writeGraphToFile(ImagemGrafo);
        System.out.println(arquivo + ".png FOI GRAVADO");
    }

    private static Grafo GrafoTeste(int x) {
        Grafo grafo = new Grafo();
        if (x == 1) {
            grafo.adicionarVertice(new Vertice("A"));
            grafo.adicionarVertice(new Vertice("B"));
            grafo.adicionarVertice(new Vertice("C"));
            grafo.adicionarVertice(new Vertice("D"));
            grafo.adicionarVertice(new Vertice("E"));
            grafo.adicionarVertice(new Vertice("F"));
            grafo.addAresta(new Aresta(grafo.getVertice("A"), grafo.getVertice("B"), 1, "a"));
            grafo.getVertice("A").addAresta(grafo.getListaAresta().get(grafo.getListaAresta().size() - 1));
            grafo.addAresta(new Aresta(grafo.getVertice("B"), grafo.getVertice("C"), 2, "b"));
            grafo.getVertice("B").addAresta(grafo.getListaAresta().get(grafo.getListaAresta().size() - 1));
            grafo.addAresta(new Aresta(grafo.getVertice("C"), grafo.getVertice("D"), 3, "c"));
            grafo.getVertice("C").addAresta(grafo.getListaAresta().get(grafo.getListaAresta().size() - 1));
            grafo.addAresta(new Aresta(grafo.getVertice("D"), grafo.getVertice("E"), 4, "d"));
            grafo.getVertice("D").addAresta(grafo.getListaAresta().get(grafo.getListaAresta().size() - 1));
            grafo.addAresta(new Aresta(grafo.getVertice("D"), grafo.getVertice("F"), 8, "e"));
            grafo.getVertice("D").addAresta(grafo.getListaAresta().get(grafo.getListaAresta().size() - 1));
            grafo.addAresta(new Aresta(grafo.getVertice("E"), grafo.getVertice("F"), 5, "f"));
            grafo.getVertice("E").addAresta(grafo.getListaAresta().get(grafo.getListaAresta().size() - 1));
            grafo.addAresta(new Aresta(grafo.getVertice("F"), grafo.getVertice("A"), 6, "g"));
            grafo.getVertice("F").addAresta(grafo.getListaAresta().get(grafo.getListaAresta().size() - 1));
        }
        if (x == 2) {

            grafo.adicionarVertice(new Vertice("A"));
            grafo.adicionarVertice(new Vertice("B"));
            grafo.adicionarVertice(new Vertice("C"));
            grafo.adicionarVertice(new Vertice("D"));
            grafo.adicionarVertice(new Vertice("E"));
            grafo.adicionarVertice(new Vertice("F"));
            grafo.adicionarVertice(new Vertice("G"));
            grafo.addAresta(new Aresta(grafo.getVertice("A"), grafo.getVertice("B"), 7, ""));
            grafo.getVertice("A").addAresta(grafo.getListaAresta().get(grafo.getListaAresta().size() - 1));
            grafo.addAresta(new Aresta(grafo.getVertice("A"), grafo.getVertice("D"), 5, ""));
            grafo.getVertice("A").addAresta(grafo.getListaAresta().get(grafo.getListaAresta().size() - 1));
            grafo.addAresta(new Aresta(grafo.getVertice("D"), grafo.getVertice("B"), 9, ""));
            grafo.getVertice("D").addAresta(grafo.getListaAresta().get(grafo.getListaAresta().size() - 1));
            grafo.addAresta(new Aresta(grafo.getVertice("D"), grafo.getVertice("E"), 15, ""));
            grafo.getVertice("D").addAresta(grafo.getListaAresta().get(grafo.getListaAresta().size() - 1));
            grafo.addAresta(new Aresta(grafo.getVertice("D"), grafo.getVertice("F"), 6, ""));
            grafo.getVertice("D").addAresta(grafo.getListaAresta().get(grafo.getListaAresta().size() - 1));
            grafo.addAresta(new Aresta(grafo.getVertice("B"), grafo.getVertice("C"), 13, ""));
            grafo.getVertice("B").addAresta(grafo.getListaAresta().get(grafo.getListaAresta().size() - 1));
            grafo.addAresta(new Aresta(grafo.getVertice("B"), grafo.getVertice("E"), 12, ""));
            grafo.getVertice("B").addAresta(grafo.getListaAresta().get(grafo.getListaAresta().size() - 1));
            grafo.addAresta(new Aresta(grafo.getVertice("E"), grafo.getVertice("C"), 4, ""));
            grafo.getVertice("E").addAresta(grafo.getListaAresta().get(grafo.getListaAresta().size() - 1));
            grafo.addAresta(new Aresta(grafo.getVertice("E"), grafo.getVertice("F"), 8, ""));
            grafo.getVertice("E").addAresta(grafo.getListaAresta().get(grafo.getListaAresta().size() - 1));
            grafo.addAresta(new Aresta(grafo.getVertice("E"), grafo.getVertice("G"), 10, ""));
            grafo.getVertice("E").addAresta(grafo.getListaAresta().get(grafo.getListaAresta().size() - 1));
            grafo.addAresta(new Aresta(grafo.getVertice("G"), grafo.getVertice("F"), 11, ""));
            grafo.getVertice("G").addAresta(grafo.getListaAresta().get(grafo.getListaAresta().size() - 1));
        }
        return grafo;
    }
}
