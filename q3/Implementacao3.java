package q3;

import Grafo.GeradorDeGrafos;
import Grafo.Grafo;

public class Implementacao3 {
    public static void main(String[] args) {
        Grafo grafo = GeradorDeGrafos.gerarGrafoAleatorioConexo(1_000, 5_000);
        Tarjan.tarjanIterativo(grafo);
    }
}
