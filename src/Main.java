import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        int m; // variavel de controle do  menu
        boolean tipo = true;
        boolean verificacao = true;

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
                if (verificacao) {
                    System.out.println("        1-Para grafo Orientado");
                    System.out.println("        2-Para grafo Não-orientado");
                    int a = ler.nextInt();
                    if (a == 1) {
                        tipo = true;                  //se verdadeiro grafo ORIENTADO
                        verificacao = false;          //true pra escolher o tipo e depois false pra nao entrar nesse sub menu mais
                    } else {
                        tipo = false;                 //se falso grafo NAO-ORIENTADO
                        verificacao = false;
                    }

                }

                System.out.println("");
                listaV.add(new Vertice());  //Cria o vertice

            }
            if (m == 2) {
                if (tipo) {     //grafo orientado

                    if (verificacao) {
                        System.out.println("        1-Para grafo Orientado");
                        System.out.println("        2-Para grafo Não-orientado");
                        int a = ler.nextInt();
                        if (a == 1) {
                            tipo = true;
                            verificacao = false;
                        } else {
                            tipo = false;
                            verificacao = false;
                        }

                    }
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
                    System.out.println("Informe o peso da aresta");
                    int c = ler.nextInt();
                    listaV.get(x).insereAdjacente(new Aresta(a, b, c));
                }
                if(!tipo){     //grafo não orientado
                
                }
            }

            if (m == 3) {
                if (tipo) {
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
            }

            if (m == 4) {
                if (tipo) {
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
                }
            }

            if (m == 5) {
                boolean regular = true;
                int z = 0;        //Guardar o grau de saida
                int q = 0;        //index
                System.out.println("");
                for (int i = 0; i < listaV.size(); i++) {
                    System.out.print("Vertice " + listaV.get(i).getId() + " liga em: ");
                    listaV.get(i).mostrarAdjacentes();
                    System.out.println("*");
                }
                int grau = listaV.size();
                System.out.println("Ordem do grafo: " + grau);

                for (Vertice x : listaV) {
                    System.out.println("Grau de SAIDA do Vertice " + x.getId() + " igual a : " + x.getGrauSaida());
                    for (int i = 0; i < listaV.size(); i++) {
                        z += listaV.get(i).getGrauEntrada(q);
                    }
                    q++;
                    System.out.println("Grau de ENTRADA do Vertice " + x.getId() + " igual a : " + z);
                    z = 0;
                }

            }
        } while (m != 0);
    }
}