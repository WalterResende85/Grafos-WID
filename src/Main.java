
import java.util.Scanner;
import java.util.ArrayList;
import edu.ifet.grafos.graphview.GraphView;
import java.io.File;            //gravação
import java.io.FileWriter;      //gravação
import java.io.PrintWriter;     //gravação
import java.io.IOException;     //leitura e gravação
import java.io.BufferedReader;  //leitura
import java.io.FileReader;      //leitura

public class Main {

    public static void main(String[] args) throws IOException {

        //orientado so o vertice de origem recebe a aresta
        //nao orientado ambos os vertices recebem a aresta
        int m; // variavel de controle do  menu
        int k;//variavel de controle do tipo de grafo
        boolean tipo = true;
        boolean verificacao = true;
        ArrayList<Aresta> listaA = new ArrayList();
        ArrayList<Vertice> listaV = new ArrayList();
        Scanner ler = new Scanner(System.in);
        do { //verificaçao do tipo de grafo, so acontece um vez na opção 1
            System.out.println("        Digite:");
            System.out.println("        1-Para grafo Orientado");
            System.out.println("        2-Para grafo Não-orientado");
            System.out.println("        3-Para IMPRIMIR a partir de um txt");
            k = ler.nextInt();
            if (k == 1) {
                tipo = true;                  //se verdadeiro grafo ORIENTADO
                verificacao = false;          //true pra escolher o tipo e depois false pra nao entrar nesse sub menu mais
            }
            if (k == 2) {
                tipo = false;                 //se falso grafo NAO-ORIENTADO
                verificacao = false;
            }
            if (k == 3) {
                Scanner input = new Scanner(System.in);
                System.out.printf("Informe o nome de arquivo texto(EX: grafo.txt):\n");
                String grafo = input.nextLine();
                try {
                    FileReader arq = new FileReader(grafo);
                    BufferedReader lerArq = new BufferedReader(arq);

                    GraphView pl = new GraphView();
                    String grafoL = lerArq.readLine();
                    pl.readString(grafoL);
                    System.out.println(pl.getDotSource());
                    File grafoLeitura = new File("grafoLeitura.png");
                    pl.writeGraphToFile(grafoLeitura);
                    System.out.println("grafoLeitura.png FOI GRAVADO");

                    arq.close();
                } catch (IOException e) {
                    System.err.printf("Erro na abertura do arquivo: %s.\n",
                            e.getMessage());
                }

            }
        } while (k != 1 && k != 2);
        //fim da verificação}while()
        do {
            System.out.println("");
            System.out.println("----------------MENU----------------");
            System.out.println("        1- Criar Vertice ");
            System.out.println("        2- Criar Aresta ");
            System.out.println("        3- Remover Vertice");
            System.out.println("        4- Remover Aresta");
            System.out.println("        5- Informação");
            System.out.println("        6- Para criar a imagem e o arquivo txt");
            System.out.println("        0- para SAIR ");
            System.out.println("-------------------------------------");
            m = ler.nextInt();
            if (m == 1) {
                System.out.println("");
                listaV.add(new Vertice());  //Cria o vertice

            }
            if (m == 2) {
                if (tipo) {
                    System.out.println("");
                    System.out.println("Informe vertice origem");
                    int a = ler.nextInt();
                    int x = percorreGrafo(a, listaV);                    //metodo pra percorrer o grafo e reduzir/simplificar o codigo
                    System.out.println("Informe vertice destino");
                    int b = ler.nextInt();
                    int z = percorreGrafo(b, listaV);
                    printExisteVertice(x, 1, tipo);
                    printExisteVertice(z, 2, tipo);
                    if (x != -1 && z != -1) {
                        System.out.println("Informe o peso da aresta");
                        int c = ler.nextInt();
                        listaV.get(x).insereAdjacente(new Aresta(a, b, c));
                    } else {
                        System.out.println("ARESTA NÂO FOI CRIADA!!!!!");
                    }
                }
                if (!tipo) {     //grafo não orientado
                    System.out.println("");
                    System.out.println("Informe o primeiro vertice da aresta");
                    int a = ler.nextInt();
                    System.out.println("Informe o segundo vertice da aresta");
                    int b = ler.nextInt();
                    int x = percorreGrafo(a, listaV);     //para descobrir o index do primeiro vertice da aresta
                    int z = percorreGrafo(b, listaV);     //para descobrir o index do segundo vertice da aresta
                    printExisteVertice(x, 1, tipo);
                    printExisteVertice(z, 2, tipo);
                    if (x != -1 && z != -1) {
                        System.out.println("Informe o peso da aresta");
                        int c = ler.nextInt();
                        listaA.add(new Aresta(a, b, c));
                        listaV.get(x).insereAdjacente(listaA.get(listaA.size() - 1));//pegar a ultima aresta adicionada pra poder colocar nos VERTICES
                        listaV.get(z).insereAdjacente(listaA.get(listaA.size() - 1));
                    } else {
                        System.out.println("ARESTA NÂO FOI CRIADA!!!!!");
                    }
                }
            }

            if (m == 3) {
                System.out.println("Informe o id do vertice a ser REMOVIDO: ");
                int a = ler.nextInt();
                for (int i = 0; i < listaV.size(); i++) {               //REMOVE AS ARESTAS LIGADAS A ESSE VERTICE
                    if (tipo) {
                        listaV.get(i).removeArestaDosVertices(a);
                    }
                    if (!tipo) {
                        listaV.get(i).removeArestaDosVerticesNaoOrientado(a);
                    }
                }
                int x = percorreGrafo(a, listaV);      //para descobrir o index do vertice que sera removido
                if (x == -1) {
                    System.out.println("Vertice informado não existe");
                } else {
                    listaV.remove(x);
                    System.out.println("Vertice " + a + " REMOVIDO");
                }
            }

            if (m == 4) {

                System.out.println("Informe o nome da aresta a ser REMOVIDO(EX: 1@1@2): ");
                String a = ler.next();

                int f = a.indexOf("@") + 1;        //pegar o numero entre as arrobas ...
                int g = a.lastIndexOf("@");    //... pra definir em qual vertice essa aresta esta
                String index = a.substring(f, g);    // coloca esse numero em uma nova String
                //Transforma esse numero tipo String em um int

                int d = Integer.parseInt(index);    //d == vertice de entrada da aresta a ser removida
                int e = 0;                          //e== vertice de saida da aresta a ser removida

                if (!tipo) {  //se o grafo for não-direcional é preciso pegar o ultimo numero do id tambem
                    g++;
                    String index2 = a.substring(g);
                    e = Integer.parseInt(index2);   // e = utimo numero do id
                }

                int h = percorreGrafo(d, listaV);      //para descobrir o index do vertice de numero que foi descoberto em cima(d)
                int j = 0;
                if (!tipo) {
                    j = percorreGrafo(e, listaV);      //para descobrir o index do outro vertice que tem essa aresta em caso de grafo nao_ordenado
                }

                if (h != -1 && j != -1) {
                    listaV.get(h).removeAresta(a); //remove a aresta do vertice de entrada em caso de ordenado
                    //  ou remove a aresta do primeiro vertice em caso de não-ordenado                    
                }
                if (h != -1 && j != -1 && !tipo) {
                    listaV.get(j).removeAresta(a);         //remove a aresta do segundo vertice em caso de não-orientado
                }

////////FALTA CONCERTAR(MELHORAR) O CODIGO DO 5 EM DIANTE/////////////
                
            }
            if (m == 5) {
                boolean regular = true;
                boolean completo = true;

                int grauEntradaPrimeiro = 0;
                int grauSaidaPrimeiro = 0;
                int numAdjacenciasPrimeiro = 0;

                if (tipo) {

                    for (int i = 0; i < listaV.size(); i++) { // IMPRIME AS LIGACOES (NAO VERIFICA NADA)
                        System.out.print("Vertice " + listaV.get(i).getId() + " aponta para: ");
                        listaV.get(i).mostrarAdjacentes();
                        System.out.println("*");
                    }

                    int z = 0;        //Guardar o grau de saida
                    int p = 0;

                    for (Vertice x : listaV) {      //mostra grau de entrada e saida do vertice
                        System.out.println("Grau de SAIDA do Vertice " + x.getId() + " igual a : " + x.getGrauSaida());
                        for (int i = 0; i < listaV.size(); i++) {
                            z += listaV.get(i).VerificaSaida(x.getId());        //SOMA QUANTAS ARESTA CHEGA EM P (SAINDO DE TODOS OS VERTICES)
                        }
                        listaV.get(p).setGrauEntrada(z);                //VERTICE P TEM O GRAU DE ENTRADA IGUAL A Z
                        p++;                                            //PRA VERIFICAR O PROXIMO VERTICE
                        System.out.println("Grau de ENTRADA do Vertice " + x.getId() + " é igual a : " + z);
                        z = 0;
                    }

                    for (Vertice x : listaV) {
                        for (Vertice o : listaV) {
                            if (!x.ligacao(o.getId())) { //verifica se o x liga com o o
                                if (!o.ligacao(x.getId())) {  //se x nao liga com o verifica o contrario, pq ignora o sentido (a->b == b->a)
                                    completo = false;
                                }
                            }
                        }
                        x.setNumAdjacencia();               //set num de adjacencias de todos os vertices(desconsidera sentido) entrada + saida
                    }

                    grauEntradaPrimeiro = listaV.get(0).getGrauEntrada();       //set para comparacao de grafo regular
                    grauSaidaPrimeiro = listaV.get(0).getGrauSaida();           //set para comparacao de grafo regular
                    numAdjacenciasPrimeiro = listaV.get(0).getNumAdjacencia();  //set para comparacao de grafo regular

                    for (int i = 0; i < listaV.size(); i++) {
                        if (listaV.get(i).getGrauEntrada() != grauEntradaPrimeiro
                                || listaV.get(i).getGrauSaida() != grauSaidaPrimeiro
                                || listaV.get(i).getNumAdjacencia() != numAdjacenciasPrimeiro) {
                            //para ser regular os tres itens tem de ser igual para todos os vertices
                            regular = false;
                        }
                    }

                }
                int grauPrimeiro = listaV.get(0).mostraGrau();
                if (!tipo) {

                    for (int i = 0, j = 0; i < listaV.size(); i++) {        //IMPRIME AS CONEXOES (NAO VERIFICA NADA)
                        System.out.print("Vertice " + listaV.get(i).getId() + " conecta em: ");
                        listaV.get(i).mostrarConexao();
                        System.out.println("*");
                    }

                    for (Vertice x : listaV) {      //mostra ligaçoes dos vertices
                        System.out.println("Grau do vertice " + x.getId() + " é igual a: " + x.mostraGrau());
                        if (x.mostraGrau() != grauPrimeiro) {      //VERIFICA O GRAU DE TODOS OS VERTICES, SE TODOS IGUAIS==GRAFO REGULAR, SE DIFERENTES==GRAFO NAO REGULAR
                            regular = false;
                        }
                        for (Vertice z : listaV) {      //VERIFICA SE TODOS OS VERTICES CONETAM ENTRE SI, SE TODOS SE CONECTAM==GRAFO COMPLETO, SE TODOS NAO SE CONECTAM==GRAFO NAO COMPLETO
                            if (!x.ligaEmTodos(z)) {
                                completo = false;
                            }
                        }
                    }
                }
                int ordem = listaV.size();      //ordem do grafo igual ao numero de Vertices existentes
                System.out.println("Ordem do grafo: " + ordem);
                if (regular) {
                    System.out.println("GRAFO " + grauPrimeiro + "-REGULAR");
                } else {
                    System.out.println("GRAFO NAO REGULAR");
                }

                if (completo) {
                    System.out.println("GRAFO COMPLETO");
                } else {
                    System.out.println("GRAFO NAO COMPLETO");
                }

            }

            if (m == 6) {
                String g = "";
                if (tipo) {
                    g = "digraph graphname {";
                    for (int i = 0; i < listaV.size(); i++) {      //mostra grau de entrada e saida do vertice
                        String a = listaV.get(i).criaStringOrientado();
                        g = g + a;
                    }
                    g = g + "}";
                }

                if (!tipo) {
                    g = "graph graphname {";
                    for (int i = 0; i < listaV.size(); i++) {      //mostra grau de entrada e saida do vertice
                        String a = listaV.get(i).criaStringNaoOrientada();
                        g = g + a;
                    }
                    g = g + "}";
                }

                FileWriter arq = new FileWriter("grafo.txt");
                PrintWriter gravarArq = new PrintWriter(arq);
                gravarArq.printf(g);
                arq.close();
                System.out.println("Arquivo grafo.txt gravado");

                // Criando um objeto da classe responsável por gerar a imagem do grafo
                GraphView gv = new GraphView();
                //Lendo a String 
                gv.readString(g);
                //Imprimindo a grafo em texto

                System.out.println(gv.getDotSource());
                //Gerando uma imagem com o nome out.png 
                File grafo = new File("grafo.png");
                gv.writeGraphToFile(grafo);
                System.out.println("grafo.png FOI GRAVADO");

            }
        } while (m != 0);
    }

    public static int percorreGrafo(int a, ArrayList<Vertice> listaV) {
        int x = -1;      //para descobrir o index do vertice origem da aresta
        for (int i = 0; i < listaV.size(); i++) {
            if (listaV.get(i).getId() == a) {
                x = i;
            }
        }
        if (x == -1) {
            System.out.println("ERRO: VErtice ou aresta acessada não existe");
        }
        return x;
    }

    public static void printExisteVertice(int x, int index, boolean tipo) {
        if (tipo) {
            if (index == 1) {
                if (x == -1) {
                    System.out.println("Vertice de Origem não EXISTE ");
                }
            }
            if (index == 2) {
                if (x == -1) {
                    System.out.println("Vertice de Destino não EXISTE ");
                }
            }
        } else {
            if (index == 1) {
                if (x == -1) {
                    System.out.println("Primeiro Vertice não EXISTE ");
                }
            }
            if (index == 2) {
                if (x == -1) {
                    System.out.println("Segundo Vertice não EXISTE ");
                }
            }

        }
    }
}
