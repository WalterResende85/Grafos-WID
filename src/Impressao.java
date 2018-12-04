
import edu.ifet.grafos.graphview.GraphView;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Impressao {

    public void ImprimeGrafoOrientado(Grafo grafo, String arquivo) throws IOException {
        String g = "digraph graphname {";
        for (Vertice v : grafo.grafo) {
            String a = v.criaStringOrientado();
            g = g + a;
        }
        g = g + "}";

        FileWriter arq = new FileWriter(arquivo + ".txt");
        PrintWriter gravarArq = new PrintWriter(arq);
        gravarArq.printf(g);
        arq.close();
        System.out.println("Arquivo " + arquivo + ".txt gravado");

        // Criando um objeto da classe responsável por gerar verticeOrigem imagem do grafo
        GraphView gv = new GraphView();
        //Lendo verticeOrigem String 
        gv.readString(g);
        //Imprimindo verticeOrigem grafo em texto

        System.out.println(gv.getDotSource());
        //Gerando uma imagem com verticeB nome out.png 
        File ImagemGrafo = new File(arquivo + ".png");
        gv.writeGraphToFile(ImagemGrafo);
        System.out.println(arquivo + ".png FOI GRAVADO");
    }

    public void ImprimeArvoreLargura(Grafo grafo, String arquivo) throws IOException {
        String g = "graph graphname {rankdir=LR;";
        for (Aresta a : grafo.ListaAresta) {
            String p = a.getA() + "--" + a.getB() + ";";
            g = g + p;
        }
        g = g + "}";

        FileWriter arq = new FileWriter(arquivo + ".txt");
        PrintWriter gravarArq = new PrintWriter(arq);
        gravarArq.printf(g);
        arq.close();
        System.out.println("Arquivo " + arquivo + ".txt gravado");

        // Criando um objeto da classe responsável por gerar verticeOrigem imagem do grafo
        GraphView gv = new GraphView();
        //Lendo verticeOrigem String 
        gv.readString(g);
        //Imprimindo verticeOrigem grafo em texto

        System.out.println(gv.getDotSource());
        //Gerando uma imagem com verticeB nome out.png 
        File ImagemGrafo = new File(arquivo + ".png");
        gv.writeGraphToFile(ImagemGrafo);
        System.out.println(arquivo + ".png FOI GRAVADO");

    }

    void ImprimeKruskal(Grafo grafo, String arquivo) throws IOException {
        String g = "graph graphname {rankdir=LR;";
        for (Aresta a : grafo.ListaAresta) {
            String p = a.getA() + "--" + a.getB() + ";";
            g = g + p;
        }
        g = g + "}";

        FileWriter arq = new FileWriter(arquivo + ".txt");
        PrintWriter gravarArq = new PrintWriter(arq);
        gravarArq.printf(g);
        arq.close();
        System.out.println("Arquivo " + arquivo + ".txt gravado");

        // Criando um objeto da classe responsável por gerar verticeOrigem imagem do grafo
        GraphView gv = new GraphView();
        //Lendo verticeOrigem String 
        gv.readString(g);
        //Imprimindo verticeOrigem grafo em texto

        System.out.println(gv.getDotSource());
        //Gerando uma imagem com verticeB nome out.png 
        File ImagemGrafo = new File(arquivo + ".png");
        gv.writeGraphToFile(ImagemGrafo);
        System.out.println(arquivo + ".png FOI GRAVADO");

    }

    void ImprimeGrafoNaoOrientado(Grafo grafo, String arquivo) throws IOException {
        String g = "graph graphname {";
        for (Vertice v : grafo.grafo) {
            String a = v.criaStringNaoOrientado();
            g = g + a;
        }
        g = g + "}";

        FileWriter arq = new FileWriter(arquivo + ".txt");
        PrintWriter gravarArq = new PrintWriter(arq);
        gravarArq.printf(g);
        arq.close();
        System.out.println("Arquivo " + arquivo + ".txt gravado");

        // Criando um objeto da classe responsável por gerar verticeOrigem imagem do grafo
        GraphView gv = new GraphView();
        //Lendo verticeOrigem String 
        gv.readString(g);
        //Imprimindo verticeOrigem grafo em texto

        System.out.println(gv.getDotSource());
        //Gerando uma imagem com verticeB nome out.png 
        File ImagemGrafo = new File(arquivo + ".png");
        gv.writeGraphToFile(ImagemGrafo);
        System.out.println(arquivo + ".png FOI GRAVADO");
    }

    void ImprimePrim(Grafo grafo, String arquivo) throws IOException {
        String g = "graph graphname {";
        for (Aresta a : grafo.ListaAresta) {
            String p = a.getA() + "--" + a.getB() + ";";
            g = g + p;
        }
        g = g + "}";

        FileWriter arq = new FileWriter(arquivo + ".txt");
        PrintWriter gravarArq = new PrintWriter(arq);
        gravarArq.printf(g);
        arq.close();
        System.out.println("Arquivo " + arquivo + ".txt gravado");

        // Criando um objeto da classe responsável por gerar verticeOrigem imagem do grafo
        GraphView gv = new GraphView();
        //Lendo verticeOrigem String 
        gv.readString(g);
        //Imprimindo verticeOrigem grafo em texto

        System.out.println(gv.getDotSource());
        //Gerando uma imagem com verticeB nome out.png 
        File ImagemGrafo = new File(arquivo + ".png");
        gv.writeGraphToFile(ImagemGrafo);
        System.out.println(arquivo + ".png FOI GRAVADO");

    }
}
