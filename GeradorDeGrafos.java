
import Grafo.Grafo;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.*;

public class GeradorDeGrafos {

    public Grafo gerarGrafoAleatorio(int numeroDeVertices, Integer numeroDeArestas) {
        Grafo grafo = new Grafo();

        for (int i = 0; i < numeroDeVertices; i++) {
            grafo.adicionarVertice(i);
        }

        int maxArestasPossiveis = (numeroDeVertices * (numeroDeVertices - 1)) / 2;

        if (numeroDeArestas == null) {
            Random rand = new Random();
            int minAresta = numeroDeVertices - 1;
            numeroDeArestas = rand.nextInt(minAresta*10 - minAresta) + minAresta;
        }

        numeroDeArestas = Math.min(numeroDeArestas, maxArestasPossiveis);
        System.out.println("Gerando grafo com " + numeroDeVertices + " vértices e " + numeroDeArestas + " arestas");
        //densidade de vertice para aresta 
        System.out.println("Densidade: " + ((double) numeroDeArestas / (numeroDeVertices * (numeroDeVertices - 1))));

        // Usar um Set para garantir que não há duplicatas
        Set<String> arestasAdicionadas = new HashSet<>();
        Random rand = new Random();
        
        int count = 0;

        String nomeArquivo = "./arquivos/grafo.txt";
        int i = 1;

        // Verificar se o arquivo já existe e gerar um novo nome se necessário
        while (new File(nomeArquivo).exists()) {
            nomeArquivo = "./arquivos/grafo(" + i + ").txt";
            i++;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            while (count < numeroDeArestas) {
                int vertice1 = rand.nextInt(numeroDeVertices);
                int vertice2 = rand.nextInt(numeroDeVertices);
                // Evitar laços e duplicatas
                if (vertice1 != vertice2) {
                    int menor = Math.min(vertice1, vertice2);
                    int maior = Math.max(vertice1, vertice2);
                    String chaveAresta = menor + " " + maior;

                    // Apenas adicionar a aresta se ainda não foi adicionada
                    if (arestasAdicionadas.add(chaveAresta)) {
                        grafo.adicionarAresta(menor, maior);
                        writer.write(menor + " " + maior); // Escreve no formato "v w"
                        writer.newLine(); // Vai para a próxima linha
                        count++;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }

        // while (count < numeroDeArestas) {
        //     int vertice1 = rand.nextInt(numeroDeVertices);
        //     int vertice2 = rand.nextInt(numeroDeVertices);
        //     // Evitar laços e duplicatas
        //     if (vertice1 != vertice2) {
        //         int menor = Math.min(vertice1, vertice2);
        //         int maior = Math.max(vertice1, vertice2);
        //         String chaveAresta = menor + " " + maior;

        //         // Apenas adicionar a aresta se ainda não foi adicionada
        //         if (arestasAdicionadas.add(chaveAresta)) {
        //             grafo.adicionarAresta(menor, maior);
        //             count++;
        //         }
        //     }
        // }
        System.out.println("Arquivo criado com sucesso, versão (" + i + ")");

        return grafo;
    }

    // public static void main(String[] args) {
    //     long start = System.currentTimeMillis();
    //     long tempoFinal = System.currentTimeMillis() - start;
    //     System.out.println("grafo completo !! ebaaaa\nPronto em  " + tempoFinal / 1000 + "s");
    // }
}
