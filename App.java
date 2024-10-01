import Grafo.GeradorDeGrafos;
import Grafo.Grafo;
import q3.Tarjan;

public class App {
    public static void main(String[] args) {

        Grafo grafo = GeradorDeGrafos.gerarGrafoAleatorioConexo(100, 99);
    
        
        // Importa a questão 1
        System.out.println("grafo é biconexo = " + q1.Implementacao1.grafo_biconnex(grafo));
        // Importa a questão 2
        System.out.println("grafo é biconexo por joelho = " + !q2.Implementacao2.TemArticulacao(grafo));

        // Importa a questão 3
        Tarjan.tarjanIterativo(grafo);
    }
}
