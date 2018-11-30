import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Orientado {

    public static void Orientado() throws IOException, CloneNotSupportedException {

        System.out.println("Orientado");

        int menu;
        Scanner ler = new Scanner(System.in);
        Grafo grafo = new Grafo();
        do {
            System.out.println("");
            System.out.println("----------------MENU----------------");
            System.out.println("        99- Criar Grafo para testes");
            System.out.println("        1- Criar Vertice ");
            System.out.println("        2- Criar Aresta ");
            System.out.println("        3- Remover Vertice");
            System.out.println("        4- Remover Aresta");
            System.out.println("        5- Informação");
            System.out.println("        6- Para criar a imagem");
            System.out.println("        7- Para usar o Algoritmo de dijkstra ");
            System.out.println("        8- Para usar o Algoritmo de Kruskal ");
            System.out.println("        X9- Para usar o Algoritmo de Prim");
            System.out.println("        X10- Para usar o algoritmo Malgrange");
            System.out.println("        11- Para usar o algoritmo de Busca em profundidade");
            System.out.println("        0- para SAIR ");
            System.out.println("-------------------------------------");
            menu = ler.nextInt();
            if (menu == 1) {
                System.out.println("Informe o nome do Vertice: ");
                String nome = ler.next();
                grafo.addVertice(new Vertice(nome));  //cria e adiciona vertice ao grafo
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
                    System.out.println("Informe o peso da aresta");
                    int pesoAresta = ler.nextInt();
                    grafo.addAresta(new Aresta(VerticeOrigem, VerticeDestino, pesoAresta));
                } else {
                    System.out.println("ARESTA NÂO FOI CRIADA!!!!!");
                }
            }
            if (menu == 3) {
                System.out.println("Informe o nome do vertice a ser REMOVIDO: ");
                String verticeRemocao = ler.next();
                Vertice v = grafo.getVertice(verticeRemocao);
                if (v != null) {
                    grafo.removeVertice(v);
                } else {
                    System.out.println("Vertice a ser removido não existe");
                }
            }
            if (menu == 4) {
                System.out.println("Informe o nome da aresta a ser REMOVIDO: ");
                String nomeAresta = ler.next();
                Aresta arestaRemocao = grafo.getArestaPeloNome(nomeAresta);
                grafo.removeAresta(arestaRemocao);
            }
            if (menu == 5) {
                InformaConexoes(grafo);
                InformaRegularidade(grafo);
                InformaCompleto(grafo);
            }
            if (menu == 6) {
                Impressao i = new Impressao();
                i.ImprimeGrafoOrientado(grafo, "grafoOrientado");
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
                Kruskal k = new Kruskal();
                k.AlgoritmoKruskal(grafo);
            }

            if (menu == 9) {
                //AlgoritmoPrim(grafo);
            }

            if (menu == 10) {
                Malgrange m = new Malgrange();
                m.AlgoritmoMalgrange(grafo);
            }

            if (menu == 11) {
                BuscaLargura b = new BuscaLargura(grafo);
                System.out.println("Defina um vertice Raiz: ");
                String x = ler.next();
                Vertice primeiro = grafo.getVertice(x);
                b.AlgoritmoLargura(primeiro);
            }

            if (menu == 99) {
                grafo = GrafoTeste();
            }
        } while (menu != 0);

    }

    private static void InformaConexoes(Grafo grafo) {
        for (int i = 0; i < grafo.getGrafo().size(); i++) {
            grafo.getGrafo().get(i).apontaPara();
        }
    }

    private static void InformaRegularidade(Grafo grafo) {
        boolean regular = true;
        grafo.setGrauDosVertices();
        for (Vertice v : grafo.grafo) {
            if (v.grau != grafo.grafo.get(0).grau) {
                regular = false;
            }
        }
        if (regular) {
            System.out.println("Grafo " + grafo.grafo.get(0).grau + "-Regular");
        } else {
            System.out.println("Grafo Nao Regular");
        }

    }

    private static void InformaCompleto(Grafo grafo) {

    }
    
    private static Grafo GrafoTeste() {
        Grafo grafo = new Grafo();
        System.out.println("      1-Grafo k5");
        System.out.println("      2-Grafo k{3,3}");
        System.out.println("      3-Grafo qualquer");
        System.out.println("      4-Grafo para Largura");
        Scanner ler = new Scanner(System.in);
        int menu = ler.nextInt();
        if (menu == 1) {
            grafo.addVertice(new Vertice("A"));
            grafo.addVertice(new Vertice("B"));
            grafo.addVertice(new Vertice("C"));
            grafo.addVertice(new Vertice("D"));
            grafo.addVertice(new Vertice("E"));

            Vertice A = grafo.getVertice("a");
            Vertice B = grafo.getVertice("b");
            Vertice C = grafo.getVertice("c");
            Vertice D = grafo.getVertice("d");
            Vertice E = grafo.getVertice("e");

            grafo.addAresta(new Aresta(A, B, 1));
            grafo.addAresta(new Aresta(A, C, 2));
            grafo.addAresta(new Aresta(A, D, 3));
            grafo.addAresta(new Aresta(A, E, 4));

            grafo.addAresta(new Aresta(B, C, 1));
            grafo.addAresta(new Aresta(B, D, 3));
            grafo.addAresta(new Aresta(B, E, 4));

            grafo.addAresta(new Aresta(C, D, 3));
            grafo.addAresta(new Aresta(C, E, 4));

            grafo.addAresta(new Aresta(D, E, 4));
        }
        if (menu == 3) {
            grafo.addVertice(new Vertice("1"));
            grafo.addVertice(new Vertice("2"));
            grafo.addVertice(new Vertice("3"));
            grafo.addVertice(new Vertice("4"));
            grafo.addVertice(new Vertice("5"));
            grafo.addVertice(new Vertice("6"));

            Vertice UM = grafo.getVertice("1");
            Vertice DOIS = grafo.getVertice("2");
            Vertice TRES = grafo.getVertice("3");
            Vertice QUATRO = grafo.getVertice("4");
            Vertice CINCO = grafo.getVertice("5");
            Vertice SEIS = grafo.getVertice("6");
            
            grafo.addAresta(new Aresta(CINCO, UM, 1 ));
            grafo.addAresta(new Aresta(CINCO, DOIS, 7 ));
            grafo.addAresta(new Aresta(CINCO, TRES, 3));
            grafo.addAresta(new Aresta(CINCO, QUATRO, 4));
            grafo.addAresta(new Aresta(CINCO, SEIS, 4));
            
            grafo.addAresta(new Aresta(SEIS, UM, 5));
            grafo.addAresta(new Aresta(DOIS, UM, 2));
            grafo.addAresta(new Aresta(DOIS, TRES, 3));
            grafo.addAresta(new Aresta(TRES, QUATRO, 9));
            
            
        }
        if (menu == 4) {
            grafo.addVertice(new Vertice("1"));
            grafo.addVertice(new Vertice("2"));
            grafo.addVertice(new Vertice("3"));
            grafo.addVertice(new Vertice("4"));
            grafo.addVertice(new Vertice("5"));
            grafo.addVertice(new Vertice("6"));
            grafo.addVertice(new Vertice("7"));
            grafo.addVertice(new Vertice("8"));

            Vertice UM = grafo.getVertice("1");
            Vertice DOIS = grafo.getVertice("2");
            Vertice TRES = grafo.getVertice("3");
            Vertice QUATRO = grafo.getVertice("4");
            Vertice CINCO = grafo.getVertice("5");
            Vertice SEIS = grafo.getVertice("6");
            Vertice SETE = grafo.getVertice("7");
            Vertice OITO = grafo.getVertice("8");

            grafo.addAresta(new Aresta(UM, DOIS, 2));
            grafo.addAresta(new Aresta(UM, TRES, 7));
            grafo.addAresta(new Aresta(DOIS, QUATRO, 3));
            grafo.addAresta(new Aresta(DOIS, CINCO, 4));
            grafo.addAresta(new Aresta(QUATRO, TRES, 3));
            grafo.addAresta(new Aresta(CINCO, QUATRO, 8));
            grafo.addAresta(new Aresta(QUATRO, SEIS, 9));
            grafo.addAresta(new Aresta(SEIS, SETE, 4));
            grafo.addAresta(new Aresta(SEIS, OITO, 5));
            grafo.addAresta(new Aresta(SETE, OITO, 2));
        }
        return grafo;
    }

}
