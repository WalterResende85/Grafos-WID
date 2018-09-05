import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        int v;
        int x=0;
        ArrayList<Vertice> lista = new ArrayList();
        
        do{
        System.out.println("Digite 1 para cria Vertice ");

        System.out.println("Digite 2 para cria Aresta ");

        System.out.println("Digite 3 para mostrar o grafo ");

        Scanner ler = new Scanner(System.in);
        
        v = ler.nextInt();
        if (v == 1) {
            lista.add(new Vertice());

        }
        if (v == 2) {
            System.out.println("Informe vertice origem");
            int a = ler.nextInt();
            System.out.println("Informe vertive destino");
            int b = ler.nextInt();
            lista.get(a).insereAdjacente(b);

        }}while(v<3);
        
        if (v == 3) {
            for (int i = 0; i < lista.size(); i++) {
            //    lista.get(i).mostrarAdjacentes();
            System.out.print("Vertice "+i +" liga: ");
            lista.get(i).mostrarAdjacentes();
                
                System.out.println("");
            }            
        }
    }
}    

