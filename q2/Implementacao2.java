package q2;

import Grafo.Grafo;
import Grafo.Vertice;
import java.util.List;

public class Implementacao2 {

    public static boolean TemArticulacao(Grafo g) {
        List<Vertice> vertices = g.getVertices();
        boolean temArt = false;
        for (Vertice v : vertices) {
            g.removeVertice(v);
           // System.out.println("removendo vertice de chave = " + v.getChave());
            if (!g.isConnex()) {
                System.out.println("Articulação v = {" + v.getChave() + "}");
                temArt = true;
            }
            g.readicione_vertice_removido(v);
           // System.out.println("g tem " +  g.get_vertices_len() +"vertivces e " + g.get_arestas_len() + "arestas");
        }
        return temArt;
    }

}
