package q3;

import java.util.*;

class Graph {
    private int V; // Número de vértices
    private List<Integer>[] adj; // Lista de adjacências
    private int time = 0; // Tempo utilizado no algoritmo

    // Construtor do grafo
    @SuppressWarnings("unchecked")
    Graph(int v) {
        V = v;
        adj = new ArrayList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    // Método para adicionar aresta
    void addEdge(int u, int v) {
        adj[u].add(v);
        adj[v].add(u); // Grafo não direcionado
    }

    // Método principal que encontra e imprime os pontos de articulação
    void tarjan() {
        boolean[] visited = new boolean[V];
        int[] disc = new int[V]; // Tempo de descoberta dos nós
        int[] low = new int[V];  // Tempo mínimo de visita
        int[] parent = new int[V];
        boolean[] ap = new boolean[V]; // Para armazenar os pontos de articulação

        // Inicializa os arrays
        Arrays.fill(parent, -1); // Inicia todos os pais com -1
        Arrays.fill(visited, false); // Nenhum nó foi visitado ainda

        // Chama a função recursiva para encontrar os pontos de articulação para cada vértice
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                tarjanUtil(i, visited, disc, low, parent, ap);
            }
        }

        // Imprime os pontos de articulação
        System.out.println("Pontos de articulação no grafo:");
        for (int i = 0; i < V; i++) {
            if (ap[i]) {
                System.out.println(i);
            }
        }
    }

    // Função recursiva que faz a DFS e encontra os pontos de articulação
    private void tarjanUtil(int u, boolean[] visited, int[] disc, int[] low, int[] parent, boolean[] ap) {
        int children = 0;
        visited[u] = true;
        disc[u] = low[u] = ++time; // Inicializa o tempo de descoberta

        // Visita todos os vértices adjacentes
        for (int v : adj[u]) {
            if (!visited[v]) { // Se v não foi visitado
                parent[v] = u;
                children++;

                // Chamada recursiva para v
                tarjanUtil(v, visited, disc, low, parent, ap);

                // Atualiza low[u] considerando a subárvore de v
                low[u] = Math.min(low[u], low[v]);

                // Se u não é raiz e low[v] >= disc[u], então u é ponto de articulação
                if (parent[u] == -1 && children > 1) {
                    ap[u] = true;
                }
                if (parent[u] != -1 && low[v] >= disc[u]) {
                    ap[u] = true;
                }
            } else if (v != parent[u]) {
                // Atualiza low[u] se v já foi visitado e não é o pai
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }

    public static void main(String[] args) {
        // Exemplo de uso
        Graph g = new Graph(5);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(3, 4);

        g.tarjan();
    }
}
