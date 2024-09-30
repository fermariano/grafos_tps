
import Grafo.GeradorDeGrafos;
import Grafo.Grafo;
import java.util.ArrayList;
import java.util.List;




public class App {
    public static void main(String[] args) {

        List<Grafo> grafos = new ArrayList<>();

        grafos.add(GeradorDeGrafos.gerarGrafoAleatorioConexo(100, null)); 
        grafos.add(GeradorDeGrafos.gerarGrafoAleatorioConexo(1_000, null));
        grafos.add(GeradorDeGrafos.gerarGrafoAleatorioConexo(10_000, null));
        grafos.add(GeradorDeGrafos.gerarGrafoAleatorioConexo(100_000, null));


        
        // Importa a questão 1

        // Importa a questão 2

        // Importa a questão 3
    }
}
