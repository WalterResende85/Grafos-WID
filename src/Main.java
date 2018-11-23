
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        int menu;
        Scanner ler = new Scanner(System.in);
        do { //verificaçao do orientado de grafo, so acontece um vez na opção 1
            System.out.println("        Digite:");
            System.out.println("        1-Para grafo Orientado");
            System.out.println("        2-Para grafo Não-orientado");
            menu = ler.nextInt();
            if (menu == 1) {
                Orientado.Orientado();
            }
            if (menu == 2) {
               NaoOrientado.NaoOrientado();
            }
        } while (menu != 1 && menu != 2);
    }
}
