package q1;

import Grafo.Grafo;
import Grafo.Vertice;
import java.util.List;

public class Implementacao1 {
    
    
    
    public static boolean grafo_biconnex(Grafo g){
        List<Vertice> vertices = g.getVertices();
        for (Vertice v : vertices) {
            for(Vertice w:vertices){
                if(v.getChave() != w.getChave()){
                    if(!g.findCycleBetween(v, w)){
                        return false;
                    }
                }
            }
        }
        return true;
    }



    


}
