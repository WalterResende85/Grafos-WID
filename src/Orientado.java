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
                System.out.println("Informe o nome da aresta a ser REMOVIDO(EX: 1@1@2): ");
                String nomeAresta = ler.next();
                Aresta x = grafo.getArestaPeloNome(nomeAresta);
                Vertice v = x.getOrigem();
                v.removeAresta(x);
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

            if (menu == 99) {
                System.out.println("Existem 2 Grafos para testes, escolha um deles");
                int x = ler.nextInt();
                grafo = GrafoTeste(x);
            }
        } while (menu != 0);
    
    }
}
