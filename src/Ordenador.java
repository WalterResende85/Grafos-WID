
import java.util.Comparator;

public class Ordenador implements Comparator<Aresta> {

    @Override
    public int compare(Aresta o1, Aresta o2) {
        if (o1.getPeso() < o2.getPeso()) {
            return -1;
        }
        if (o1.getPeso() > o2.getPeso()) {
            return 1;
        }
        return 0;
    }

}
