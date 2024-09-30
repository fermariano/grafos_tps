package Grafo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class GeradorDeGrafos {

    public static Grafo gerarGrafoAleatorioConexo(int numeroDeVertices, Integer numeroDeArestas) {
        Grafo grafo = new Grafo();

        // Adiciona todos os vértices ao grafo
        for (int i = 0; i < numeroDeVertices; i++) {
            grafo.adicionarVertice(i);
        }

        // Calcula o número máximo de arestas possíveis
        int maxArestasPossiveis = (numeroDeVertices * (numeroDeVertices - 1)) / 2;

        // Se o número de arestas não for especificado, define um valor aleatório
        if (numeroDeArestas == null) {
            Random rand = new Random();
            int minAresta = numeroDeVertices - 1;
            //no maximo quero 10 vezes o numero de vertices
            numeroDeArestas = rand.nextInt(minAresta * 10 - minAresta) + minAresta;
        }

        // Garante que o número de arestas não exceda o máximo possível
        numeroDeArestas = Math.min(numeroDeArestas, maxArestasPossiveis);
        System.out.println("Gerando grafo com " + numeroDeVertices + " vértices e " + numeroDeArestas + " arestas");
        // Densidade de vértice para aresta
        System.out.println("Densidade: " + ((double) numeroDeArestas / (numeroDeVertices * (numeroDeVertices - 1))));

        // Usar um Set para garantir que não há duplicatas
        Set<String> arestasAdicionadas = new HashSet<>();
        Random rand = new Random();

        int count = 0;

        String nomeArquivo = "./arquivos/grafo.txt";
        int i = 1;
        while (new File(nomeArquivo).exists()) {
            nomeArquivo = "./arquivos/grafo(" + i + ").txt";
            i++;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            List<Integer> vertices = new ArrayList<>();
            for (int v = 0; v < numeroDeVertices; v++) {
                vertices.add(v);
            }

            Collections.shuffle(vertices, rand);
            for (int v = 1; v < vertices.size(); v++) {
                int vertice1 = vertices.get(v);
                int vertice2 = vertices.get(rand.nextInt(v));
                adicionarArestaConexa(grafo, arestasAdicionadas, writer, vertice1, vertice2);
                count++;
            }

            while (count < numeroDeArestas) {
                int vertice1 = rand.nextInt(numeroDeVertices);
                int vertice2 = rand.nextInt(numeroDeVertices);
                if (vertice1 != vertice2) {
                    int menor = Math.min(vertice1, vertice2);
                    int maior = Math.max(vertice1, vertice2);
                    String chaveAresta = menor + " " + maior;

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

        System.out.println("Arquivo criado com sucesso, versão (" + i + ")");
        return grafo;
    }

    
    private static void adicionarArestaConexa(Grafo grafo, Set<String> arestasAdicionadas, BufferedWriter writer, int vertice1, int vertice2) throws IOException {
        int menor = Math.min(vertice1, vertice2);
        int maior = Math.max(vertice1, vertice2);
        String chaveAresta = menor + " " + maior;
        
        if (arestasAdicionadas.add(chaveAresta)) {
            grafo.adicionarAresta(menor, maior);
            writer.write(menor + " " + maior);
            writer.newLine();
        }
    }
}
