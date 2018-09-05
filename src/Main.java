
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        char v;
        
        ArrayList<Vertice> lista = new ArrayList();
        Scanner ler = new Scanner(System.in);
        do {

            System.out.println("Digite V para criar Vertice ");

            System.out.println("Digite A para criar Aresta ");

            System.out.println("Digite M para mostrar o grafo ");

            System.out.println("Digite S para SAIR ");

            v = ler.next().charAt(0);
            if (v == 'V' || v == 'v') {
                lista.add(new Vertice());

            }
            if (v == 'A' || v == 'a') {
                System.out.println("Informe vertice origem");
                int a = ler.nextInt();
                
                System.out.println("Informe vertive destino");
                int b = ler.nextInt();
                lista.get(a).insereAdjacente(lista.get(b));

            }

            if (v == 'm' || v == 'M') {
                for (int i = 0; i < lista.size(); i++) {
                    System.out.print("Vertice " + i + " liga: ");
                    lista.get(i).mostrarAdjacentes();

                    System.out.println("*");
                }
            }
        } while (v != 's' || v != 'S');
    }
}
