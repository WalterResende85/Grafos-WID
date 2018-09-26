
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        int m; // variavel de controle do  menu
        boolean tipo = true;
        boolean verificacao = true;
        ArrayList<Aresta> listaA = new ArrayList();
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

                //verificaçao do tipo de grafo, so acontece um vez na opção 1
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
                //fim da verificação
                System.out.println("");
                listaV.add(new Vertice());  //Cria o vertice

            }
            if (m == 2) {
                if (tipo) {
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
                if (!tipo) {     //grafo não orientado
                    System.out.println("");
                    System.out.println("Informe o primeiro vertice da aresta");
                    int a = ler.nextInt();
                    System.out.println("Informe o segundo vertice da aresta");
                    int b = ler.nextInt();
                    int x = 0;      //para descobrir o index do primeiro vertice da aresta
                    int y = 0;      //para descobrir o index do segundo vertice da aresta
                    for (int i = 0; i < listaV.size(); i++) {
                        if (listaV.get(i).getId() == a) {       //index do 1
                            x = i;
                        }
                        if (listaV.get(i).getId() == b) {       //index do 2
                            y = i;
                        }
                    }
                    System.out.println("Informe o peso da aresta");
                    int c = ler.nextInt();
                    listaA.add(new Aresta(a, b, c));
                    listaV.get(x).insereAdjacente(listaA.get(listaA.size() - 1));//pegar a ultima aresta adicionada pra poder colocar nos VERTICES
                    listaV.get(y).insereAdjacente(listaA.get(listaA.size() - 1));
                }
            }

            if (m == 3) {

                System.out.println("Informe o id do vertice a ser REMOVIDO: ");
                int a = ler.nextInt();

                for (int i = 0; i < listaV.size(); i++) {
                    if (tipo) {
                        listaV.get(i).removeArestaDosVertices(a);
                    }
                    if (!tipo) {
                        listaV.get(i).removeArestaDosVerticesNaoOrientado(a);
                    }
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
                int e = 0;
                if (!tipo) {  //se o grafo for não-direcional é preciso pegar o ultimo numero do id tambem
                    g++;
                    String index2 = a.substring(g);
                    e = Integer.parseInt(index2);   // e = utimo numero do id
                }

                int h = 0;      //para descobrir o index do vertice de numero que foi descoberto em cima(d)
                int j = 0;      //para descobrir o index do outro vertice que tem essa aresta em caso de grafo nao_ordenado
                for (int i = 0; i < listaV.size(); i++) {
                    if (listaV.get(i).getId() == d) { //se o id do vertice for igual ao numero de entrada(d) da aresta(a)
                        h = i;      //h = indice do vertice onde a aresta sera removida
                    }
                    if (tipo) {     //se o grafo for não-direcional é preciso retirar a aresta do vertice de id igual a (e)
                        if (listaV.get(i).getId() == e) { //se o id do vertice for igual ao numero de "saida"(e) da aresta (a)
                            j = i;      //h = indice do vertice onde a aresta sera removida
                        }
                    }

                    listaV.get(h).removeAresta(a);
                    /*remove a aresta do vertice de entrada em caso de ordenado
                            ou remove a aresta do primeiro vertice em caso de não-ordenado*/
                    if (!tipo) {
                        listaV.get(j).removeAresta(a);
                    }
                    //remove a aresta do segundo vertice em caso de não-ordenado
                }
            }
            if (m == 5) {
                boolean regular = true;
                boolean completo = true;
                int primeiro = listaV.get(0).mostraGrau();
                System.out.println("VALOR PRIMEIRO= " + primeiro);
                if (tipo) {
                    for (int i = 0; i < listaV.size(); i++) { // imprime as ligacoes ordenadas
                        System.out.print("Vertice " + listaV.get(i).getId() + " aponta para: ");
                        listaV.get(i).mostrarAdjacentes();
                        System.out.println("*");
                    }
                    int z = 0;        //Guardar o grau de saida
                    int q = 0;        //index
                    for (Vertice x : listaV) {      //mostra grau de entrada e saida do vertice

                        System.out.println("Grau de SAIDA do Vertice " + x.getId() + " igual a : " + x.getGrauSaida());
                        for (int i = 0; i < listaV.size(); i++) {
                            z += listaV.get(i).getGrauEntrada(q);
                        }
                        q++;
                        System.out.println("Grau de ENTRADA do Vertice " + x.getId() + " é igual a : " + z);
                        z = 0;
                    }
                }

                if (!tipo) {
                    for (int i = 0, j = 0; i < listaV.size(); i++) {        //mostra as conexoes
                        System.out.print("Vertice " + listaV.get(i).getId() + " conecta em: ");
                        listaV.get(i).mostrarConexao();
                        System.out.println("*");
                    }
                    for (Vertice x : listaV) {      //mostra ligaçoes dos vertices
                        System.out.println("Grau do vertice " + x.getId() + " é igual a: " + x.mostraGrau());
                        if (x.mostraGrau() != primeiro) {      //se o grau de algum vertice for diferente do primeiro entao o grafo não é regular
                            regular = false;
                        }
                        for (Vertice z : listaV) {      //verifica para todos os vertices x se eles se conectam com todos os vertices y
                            if (!x.ligaEmTodos(z)) {
                                completo = false;
                            }
                        }
                    }
                }

                int ordem = listaV.size();      //ordem do grafo igual ao numero de Vertices existentes
                System.out.println("Ordem do grafo: " + ordem);
                if (regular) {
                    System.out.println("GRAFO REGULAR");
                } else {
                    System.out.println("GRAFO NAO REGULAR");
                }

                if (completo) {
                    System.out.println("GRAFO COMPLETO");
                } else {
                    System.out.println("GRAFO NAO COMPLETO");
                }

            }

        } while (m != 0);

    }
}
