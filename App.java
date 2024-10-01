import Grafo.GeradorDeGrafos;
import Grafo.Grafo;
import q3.Tarjan;

public class App {
    public static void main(String[] args) {

        Grafo grafo = GeradorDeGrafos.gerarGrafoAleatorioConexo(10000, 5000);
    
        
        // Importa a questão 1

        // Importa a questão 2
        System.out.println("grafo tem joelho = " + q2.Implementacao2.TemArticulacao(grafo));

        // Importa a questão 3
        Tarjan.tarjanIterativo(grafo);
    }
}
