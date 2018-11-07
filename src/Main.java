
import java.util.Scanner;
import java.util.ArrayList;
import edu.ifet.grafos.graphview.GraphView;
import java.io.File;            //gravação
import java.io.FileWriter;      //gravação
import java.io.PrintWriter;     //gravação
import java.io.IOException;     //leitura verticeRemocaoChegada gravação
import java.io.BufferedReader;  //leitura
import java.io.FileReader;      //leitura

public class Main {

    public static void main(String[] args) throws IOException {

        /*Orientação do que esta feito
        > Criar Vertice ok
        > Criar Aresta ok
        > Remover vertice ok
        > remover Aresta ok
        > Gerar informações sobre o grafo ok
        > imprimir grafo em txt ok
        > implementar algoritimos finais (tentativas em andamento)
        > implementação do grafo planar para o seminario (tentaiva em andamento)
        
        
        */
        
        
        
        
        //orientado so verticeB verticeRemocao de origem recebe verticeOrigem aresta
        //nao orientado ambos os vertices recebem verticeOrigem aresta
        int menu; // variavel de controle do  menu
        Boolean orientado = null;       
        ArrayList<Aresta> listaA = new ArrayList();
        ArrayList<Vertice> listaV = new ArrayList();
        Scanner ler = new Scanner(System.in);
        
        
        do { //verificaçao do orientado de grafo, so acontece um vez na opção 1
            System.out.println("        Digite:");
            System.out.println("        1-Para grafo Orientado");
            System.out.println("        2-Para grafo Não-orientado");
            System.out.println("        3-Para IMPRIMIR a partir de um txt");
            menu = ler.nextInt();
            if (menu == 1) {
                orientado = true;                  //se verdadeiro grafo ORIENTADO
                
            }
            if (menu == 2) {
                orientado = false;                 //se falso grafo NAO-ORIENTADO
                
            }
            if (menu == 3) {
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
        }while (orientado == null); // Caso verticeOrigem opção seja verticeOrigem 3 ele permanece no while para escolher verticeOrigem continuação do menu orientado ou nao orientado
       
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
            menu = ler.nextInt();
            if (menu == 1) {
                System.out.println("");
                listaV.add(new Vertice());  //Cria verticeB verticeRemocao

            }
            if (menu == 2) {
                if (orientado) {
                    System.out.println("");
                    System.out.println("Informe vertice origem");
                    int verticeOrigem = ler.nextInt();
                    int idVerticeOrigem = percorreGrafo(verticeOrigem, listaV);                    //metodo pra percorrer verticeB grafo verticeRemocaoChegada reduzir/simplificar verticeB codigo
                    System.out.println("Informe vertice destino");
                    int verticeDestino = ler.nextInt();
                    int idVerticeDestino = percorreGrafo(verticeDestino, listaV);
                    printExisteVertice(idVerticeOrigem, 1, orientado);
                    printExisteVertice(idVerticeDestino, 2, orientado);
                    if (idVerticeOrigem != -1 && idVerticeDestino != -1) {
                        System.out.println("Informe o peso da aresta");
                        int pesoAresta = ler.nextInt();
                        listaV.get(idVerticeOrigem).insereAdjacente(new Aresta(verticeOrigem, verticeDestino, pesoAresta));
                    } else {
                        System.out.println("ARESTA NÂO FOI CRIADA!!!!!");
                    }
                }
                if (!orientado) {     //grafo não orientado
                    System.out.println("");
                    System.out.println("Informe o primeiro vertice da aresta");
                    int vertice1 = ler.nextInt();
                    System.out.println("Informe o segundo vertice da aresta");
                    int vertice2 = ler.nextInt();
                    int idVertice1 = percorreGrafo(vertice1, listaV);     //para descobrir verticeB index do primeiro verticeRemocao da aresta
                    int idVertice2 = percorreGrafo(vertice2, listaV);     //para descobrir verticeB index do segundo verticeRemocao da aresta
                    printExisteVertice(idVertice1, 1, orientado);
                    printExisteVertice(idVertice2, 2, orientado);
                    if (idVertice1 != -1 && idVertice2 != -1) {
                        System.out.println("Informe o peso da aresta");
                        int pesoAresta = ler.nextInt();
                        listaA.add(new Aresta(vertice1, vertice2, pesoAresta));
                        listaV.get(idVertice1).insereAdjacente(listaA.get(listaA.size() - 1));//pegar verticeOrigem ultima aresta adicionada pra poder colocar nos VERTICES
                        listaV.get(idVertice2).insereAdjacente(listaA.get(listaA.size() - 1));
                    } else {
                        System.out.println("ARESTA NÂO FOI CRIADA!!!!!");
                    }
                }
            }

            if (menu == 3) {
                System.out.println("Informe o id do vertice a ser REMOVIDO: ");
                int verticeRemocao = ler.nextInt();
                for (int i = 0; i < listaV.size(); i++) {               //REMOVE AS ARESTAS LIGADAS A ESSE VERTICE
                    if (orientado) {
                        listaV.get(i).removeArestaDosVertices(verticeRemocao);
                    }
                    if (!orientado) {
                        listaV.get(i).removeArestaDosVerticesNaoOrientado(verticeRemocao);
                    }
                }
                int idVerticeRemocao = percorreGrafo(verticeRemocao, listaV);      //para descobrir verticeB index do verticeRemocao que sera removido
                if (idVerticeRemocao == -1) {
                    System.out.println("Vertice informado não existe");
                } else {
                    listaV.remove(idVerticeRemocao);
                    System.out.println("Vertice " + verticeRemocao + " REMOVIDO");
                }
            }

            if (menu == 4) {

                System.out.println("Informe o nome da aresta a ser REMOVIDO(EX: 1@1@2): ");
                String nomeAresta = ler.next();

                int f = nomeAresta.indexOf("@") + 1;        //pegar verticeB numero entre as arrobas ...
                int g = nomeAresta.lastIndexOf("@");    //... pra definir em qual verticeRemocao essa aresta esta
                String index = nomeAresta.substring(f, g);    // coloca esse numero em uma nova String
                //Transforma esse numero orientado String em um int

                int verticeRemocaoSaida = Integer.parseInt(index);    //d == verticeRemocao de entrada da aresta verticeOrigem ser removida
                int verticeRemocaoChegada = 0;                          //e== verticeRemocao de saida da aresta verticeOrigem ser removida

                if (!orientado) {  //se verticeB grafo for não-direcional é preciso pegar verticeB ultimo numero do id tambem
                    g++;
                    String index2 = nomeAresta.substring(g);
                    verticeRemocaoChegada = Integer.parseInt(index2);   // verticeRemocaoChegada = utimo numero do id
                }

                int idVerticeRemocaoSaida = percorreGrafo(verticeRemocaoSaida, listaV);      //para descobrir verticeB index do verticeRemocao de numero que foi descoberto em cima(verticeRemocaoSaida)
                int idVerticeRemocaoChegada = 0;
                if (!orientado) {
                    idVerticeRemocaoChegada = percorreGrafo(verticeRemocaoChegada, listaV);      //para descobrir verticeB index do outro verticeRemocao que tem essa aresta em caso de grafo nao_ordenado
                }

                if (idVerticeRemocaoSaida != -1 && idVerticeRemocaoChegada != -1) {
                    listaV.get(idVerticeRemocaoSaida).removeAresta(nomeAresta); //remove verticeOrigem aresta do verticeRemocao de entrada em caso de ordenado
                    //  ou remove verticeOrigem aresta do primeiro verticeRemocao em caso de não-ordenado                    
                }
                if (idVerticeRemocaoSaida != -1 && idVerticeRemocaoChegada != -1 && !orientado) {
                    listaV.get(idVerticeRemocaoChegada).removeAresta(nomeAresta);         //remove verticeOrigem aresta do segundo verticeRemocao em caso de não-orientado
                }
                
            }
            if (menu == 5) {
                boolean regular = true;         
                boolean completo = true;        //Todos o vertices tem ligaçao direta entre si

                int grauEntradaPrimeiro = 0;
                int grauSaidaPrimeiro = 0;
                int numAdjacenciasPrimeiro = 0;

                if (orientado) {
                    for (int i = 0; i < listaV.size(); i++) { // IMPRIME AS LIGACOES (NAO VERIFICA NADA)
                        System.out.print("Vertice " + listaV.get(i).getId() + " aponta para: ");
                        listaV.get(i).mostrarAdjacentes();
                        System.out.println("*");
                    }
                    int grauEntradaAuxiliar = 0;        //Guardar verticeB grau de saida
                    int p = 0;

                    for (Vertice vertice : listaV) {      //mostra grau de entrada do verticeRemocaoChegada e grau de saida do verticeRemocao
                        System.out.println("Grau de SAIDA do Vertice " + vertice.getId() + " igual a : " + vertice.getGrauSaida());
                        for (int i = 0; i < listaV.size(); i++) {
                            grauEntradaAuxiliar += listaV.get(i).VerificaSaida(vertice.getId());        //SOMA QUANTAS ARESTA CHEGA EM P (SAINDO DE TODOS OS VERTICES)
                                                                                                        //vertice.VerificaSaida(x) retorna n de ligacoes que verice tem com x
                        }
                        listaV.get(p).setGrauEntrada(grauEntradaAuxiliar);                //VERTICE P TEM O GRAU DE ENTRADA IGUAL A grauEntradaAuxiliar
                        p++;                                                              //PRA VERIFICAR O PROXIMO VERTICE
                        System.out.println("Grau de ENTRADA do Vertice " + vertice.getId() + " é igual a : " + grauEntradaAuxiliar);
                        grauEntradaAuxiliar = 0;
                    }

                    for (Vertice verticeA : listaV) {
                        for (Vertice verticeB : listaV) {
                            if (!verticeA.ligacao(verticeB.getId())) {      //verifica se o idVerticeOrigem liga com o verticeB
                                if (!verticeB.ligacao(verticeA.getId())) {  //se idVerticeOrigem nao liga com verticeB verifica o contrario, pq ignora 0 sentido (verticeOrigem->verticeDestino == verticeDestino->verticeOrigem)
                                    completo = false;
                                }
                            }
                        }
                        verticeA.setNumAdjacencia();               //set num de adjacencias de todos os vertices(desconsidera sentido) entrada + saida
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
                int grauPrimeiro = listaV.get(0).mostraGrau();              //mostraGrau retorna o grau do vertice(grau = entrada + saida)
                if (!orientado) {
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
                    
                System.out.println("Ordem do grafo: " + listaV.size());  //ordem do grafo igual ao numero de Vertices existentes
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

            if (menu == 6) {
                String g = "";
                if (orientado) {
                    g = "digraph graphname {";
                    for (int i = 0; i < listaV.size(); i++) {      //mostra grau de entrada verticeRemocaoChegada saida do verticeRemocao
                        String a = listaV.get(i).criaStringOrientado();
                        g = g + a;
                    }
                    g = g + "}";
                }

                if (!orientado) {
                    g = "graph graphname {";
                    for (int i = 0; i < listaV.size(); i++) {      //mostra grau de entrada verticeRemocaoChegada saida do verticeRemocao
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

                // Criando um objeto da classe responsável por gerar verticeOrigem imagem do grafo
                GraphView gv = new GraphView();
                //Lendo verticeOrigem String 
                gv.readString(g);
                //Imprimindo verticeOrigem grafo em texto

                System.out.println(gv.getDotSource());
                //Gerando uma imagem com verticeB nome out.png 
                File grafo = new File("grafo.png");
                gv.writeGraphToFile(grafo);
                System.out.println("grafo.png FOI GRAVADO");

            }
        } while (menu != 0);
    }

    public static int percorreGrafo(int a, ArrayList<Vertice> listaV) {
        int x = -1;      //para descobrir verticeB index do verticeRemocao origem da aresta
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