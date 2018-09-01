import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        System.out.println("Digite o numero de vértices");
        Scanner ler = new Scanner(System.in);
        int ver = ler.nextInt();
        
        System.out.println("Digite o numero de Arestas");
        int are = ler.nextInt();
        
        
        System.out.println("o numero de vértices são "+ ver);
        System.out.println("o numero de Arestas são "+ are);
    }
    
}
