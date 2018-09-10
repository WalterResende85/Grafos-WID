
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        int m; // variavel de controle do menu
        int r = 0; // variavel para saber o indice da aresta criada
        
        ArrayList<Vertice> listaV = new ArrayList();
        ArrayList<Aresta> listaA = new ArrayList();
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
            if (m== 1) {
                listaV.add(new Vertice());

            }
            if (m == 2) {
                
                System.out.println("Informe vertice origem");
                int a = ler.nextInt();
                
                System.out.println("Informe vertive destino");
                int b = ler.nextInt();
                
                listaA.add(new Aresta(listaV.get(a),listaV.get(b)));
                for (int i = 0; i < listaA.size(); i++) {
                    if(listaA.get(i).getNome() == a+"@"+b){
                        r=i;
                    } 
                }
                listaV.get(a).insereAdjacente(listaA.get(r));

            }

            if (m == 3) {
                
            }
            
            if (m == 4) {
                
            }
            
            
            if (m == 5) {
                for (int i = 0; i < listaV.size(); i++) {
                    System.out.print("Vertice " + i + " liga: ");
                    listaV.get(i).mostrarAdjacentes();
                    System.out.println("*");
                }
            }
        } while (m != 0);
    }
}
