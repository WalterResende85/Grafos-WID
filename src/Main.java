
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        int m; // variavel de controle do menu

        ArrayList<Vertice> listaV = new ArrayList();
        Scanner ler = new Scanner(System.in);
        do {
            System.out.println("----------------MENU----------------");
            System.out.println("        1- Criar Vertice ");
            System.out.println("        2- Criar Aresta ");
            System.out.println("        3- Remover Vertice");
            System.out.println("        4- Remover Aresta");
            System.out.println("        5- Informação");
            System.out.println("        0- para SAIR ");
            System.out.println("-------------------------------------");

            m = ler.nextInt();
            if (m == 1) {

                System.out.println("");
                listaV.add(new Vertice());  //Cria o vertice

            }
            if (m == 2) {
                System.out.println("");
                System.out.println("Informe vertice origem");
                int a = ler.nextInt();
                int x = 0;      //para descobrir o index do vertice origem da aresta
                for (int i = 0; i < listaV.size(); i++) {
                    if (listaV.get(i).getId() == a) {
                        x = i;
                    }
                }

                System.out.println("Informe vertice destino");
                int b = ler.nextInt();
                listaV.get(x).insereAdjacente(new Aresta(a, b));
            }

            if (m == 3) {
                System.out.println("Informe o id do vertice a ser REMOVIDO: ");
                int a = ler.nextInt();

                for (int i = 0; i < listaV.size(); i++) {
                    listaV.get(i).removeVertice(a);
                }
                int x = 0;      //para descobrir o index do vertice que sera removido
                for (int i = 0; i < listaV.size(); i++) {
                    if (listaV.get(i).getId() == a) {
                        x = i;
                    }
                }
                listaV.remove(x);
                System.out.println("Vertice " + a + " REMOVIDO");
            }

            if (m == 4) {
                System.out.println("Informe o nome da aresta a ser REMOVIDO(EX: 1@1@2): ");
                String a = ler.next();
                int f = a.indexOf("@") + 1;        //pegar o numero entre as arrobas ...
                int g = a.lastIndexOf("@");    //... pra definir em qual vertice essa aresta esta
                String index = a.substring(f, g);    // coloca esse numero em uma nova String
                int d = Integer.parseInt(index);    //Transforma esse numero tipo String em um int

                int h = 0;      //para descobrir o index do vertice de numero que foi descoberto em cima(d)
                for (int i = 0; i < listaV.size(); i++) {
                    if (listaV.get(i).getId() == d) {
                        h = i;      //h = indice do vertice onde a aresta sera removida
                    }
                }

                listaV.get(h).removeAresta(a);
                System.out.println("Aresta " + a + " REMOVIDO");

            }

            if (m == 5) {
                System.out.println("");
                for (int i = 0; i < listaV.size(); i++) {
                    System.out.print("Vertice " + listaV.get(i).getId() + " liga em: ");
                    listaV.get(i).mostrarAdjacentes();
                    System.out.println("*");
                }

            }
        } while (m != 0);
    }
}
