
import java.util.Scanner;

public class NaoOrientado {

    public static void NaoOrientado() {
        System.out.println("Orientado");

        int menu;
        Scanner ler = new Scanner(System.in);
        Grafo grafo = new Grafo();
        do {
            System.out.println("");
            System.out.println("----------------MENU----------------");
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
                grafo.addVertice(new Vertice(nome));  //cria e adiciona vertice ao grafo
            }
            if (menu == 2) {
                System.out.println("");
                System.out.println("Informe vertice A");
                String nomeVerticeA = ler.next();
                Vertice VerticeA = grafo.getVertice(nomeVerticeA);
                System.out.println("Informe vertice B");
                String nomeVerticeB = ler.next();
                Vertice VerticeB = grafo.getVertice(nomeVerticeB);
                if (VerticeA != null || VerticeB != null) {
                    System.out.println("Informe o peso da aresta");
                    int pesoAresta = ler.nextInt();
                    grafo.addAresta(new Aresta(VerticeA, VerticeB, pesoAresta));
                    VerticeA.addAresta(grafo.ListaAresta.get(grafo.ListaAresta.size()-1));
                    VerticeB.addAresta(grafo.ListaAresta.get(grafo.ListaAresta.size()-1));
                } else {
                    System.out.println("ARESTA NÂO FOI CRIADA!!!!!");
                }
            }
        } while (menu != 0);
    }
}
