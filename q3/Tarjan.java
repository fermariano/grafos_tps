package q3;

import java.util.*;

import Grafo.Aresta;
import Grafo.Grafo;
import Grafo.Vertice;

public class Tarjan {

    // private int V; // Número de vértices
    // private List<Integer>[] adj; // Lista de adjacências
    private static int time = 0; // Tempo utilizado no algoritmo

    // // Construtor do grafo
    // @SuppressWarnings("unchecked")
    // Graph(int v) {
    // V = v;
    // adj = new ArrayList[v];
    // for (int i = 0; i < v; i++) {
    // adj[i] = new ArrayList<>();
    // }
    // }

    // // Método para adicionar aresta
    // void addEdge(int u, int v) {
    // adj[u].add(v);
    // adj[v].add(u); // Grafo não direcionado
    // }

    // // Método principal que encontra e imprime os pontos de articulação
    // public void tarjan() {
    // boolean[] visited = new boolean[V];
    // int[] disc = new int[V]; // Tempo de descoberta dos nós
    // int[] low = new int[V]; // Tempo mínimo de visita
    // int[] parent = new int[V];
    // boolean[] ap = new boolean[V]; // Para armazenar os pontos de articulação

    // // Inicializa os arrays
    // Arrays.fill(parent, -1); // Inicia todos os pais com -1
    // Arrays.fill(visited, false); // Nenhum nó foi visitado ainda

    // // Chama a função recursiva para encontrar os pontos de articulação para cada
    // vértice
    // for (int i = 0; i < V; i++) {
    // if (!visited[i]) {
    // tarjanUtil(i, visited, disc, low, parent, ap);
    // }
    // }

    // // Imprime os pontos de articulação
    // System.out.println("Pontos de articulação no grafo:");
    // for (int i = 0; i < V; i++) {
    // if (ap[i]) {
    // System.out.println(i);
    // }
    // }
    // }

    // // Função recursiva que faz a DFS e encontra os pontos de articulação
    // public void tarjanUtil(int u, boolean[] visited, int[] disc, int[] low, int[]
    // parent, boolean[] ap) {
    // int children = 0;
    // visited[u] = true;
    // disc[u] = low[u] = ++time; // Inicializa o tempo de descoberta

    // // Visita todos os vértices adjacentes
    // for (int v : adj[u]) {
    // if (!visited[v]) { // Se v não foi visitado
    // parent[v] = u;
    // children++;

    // // Chamada recursiva para v
    // tarjanUtil(v, visited, disc, low, parent, ap);

    // // Atualiza low[u] considerando a subárvore de v
    // low[u] = Math.min(low[u], low[v]);

    // // Se u não é raiz e low[v] >= disc[u], então u é ponto de articulação
    // if (parent[u] == -1 && children > 1) {
    // ap[u] = true;
    // }
    // if (parent[u] != -1 && low[v] >= disc[u]) {
    // ap[u] = true;
    // }
    // } else if (v != parent[u]) {
    // // Atualiza low[u] se v já foi visitado e não é o pai
    // low[u] = Math.min(low[u], disc[v]);
    // }
    // }
    // }

    // public void tarjanIterativo(Grafo gf) {
    // boolean[] visited = new boolean[gf.get_vertices_len()];
    // int[] disc = new int[gf.get_vertices_len()]; // Tempo de descoberta dos nós
    // int[] low = new int[gf.get_vertices_len()]; // Tempo mínimo de visita
    // int[] parent = new int[gf.get_vertices_len()];
    // boolean[] ap = new boolean[gf.get_vertices_len()]; // Para armazenar os
    // pontos de articulação
    // Stack<int[]> stack = new Stack<>(); // Pilha para simular a recursão
    // int children = 0;

    // Arrays.fill(parent, -1);
    // Arrays.fill(visited, false);

    // // Loop para verificar todos os nós, caso o grafo não seja conexo
    // for (int i = 0; i < gf.get_vertices_len(); i++) {
    // if (!visited[i]) {
    // // Simula a chamada recursiva
    // stack.push(new int[] {i, -1}); // Inicializando com o nó e o pai
    // (inicialmente -1)

    // while (!stack.isEmpty()) {
    // int[] info = stack.pop();
    // int u = info[0];
    // int p = info[1];

    // if (!visited[u]) {
    // visited[u] = true;
    // disc[u] = low[u] = ++time;

    // parent[u] = p; // Atualiza o pai do nó atual

    // children = 0; // Reinicia o contador de filhos do nó

    // for (int v : adj[u]) {
    // if (!visited[v]) {
    // stack.push(new int[]{v, u}); // Adiciona o filho à pilha
    // children++;
    // } else if (v != parent[u]) {
    // // Atualiza o valor mínimo (low) se o vértice já foi visitado
    // low[u] = Math.min(low[u], disc[v]);
    // }
    // }
    // } else {
    // if (p != -1) {
    // low[p] = Math.min(low[p], low[u]);

    // // Verifica se o nó é um ponto de articulação
    // if (parent[p] == -1 && children > 1) {
    // ap[p] = true;
    // }
    // if (parent[p] != -1 && low[u] >= disc[p]) {
    // ap[p] = true;
    // }
    // }
    // }
    // }
    // }
    // }

    // // Imprime os pontos de articulação
    // System.out.println("Pontos de articulação no grafo:");
    // for (int i = 0; i < gf.get_vertices_len(); i++) {
    // if (ap[i]) {
    // System.out.println(i);
    // }
    // }
    // }

    public static void tarjanIterativo(Grafo gf) {
        int V = gf.get_vertices_len(); // Número de vértices
        boolean[] visited = new boolean[V];
        int[] disc = new int[V]; // Tempo de descoberta dos nós
        int[] low = new int[V]; // Tempo mínimo de visita
        int[] parent = new int[V];
        boolean[] ap = new boolean[V]; // Para armazenar os pontos de articulação
        Stack<int[]> stack = new Stack<>(); // Pilha para simular a recursão
        int children = 0;

        Arrays.fill(parent, -1);
        Arrays.fill(visited, false);

        // Loop para verificar todos os nós, caso o grafo não seja conexo
        for (Vertice vertice : gf.getVertices()) {
            int i = vertice.getChave(); // Obtém a chave do vértice

            if (!visited[i]) {
                // Simula a chamada recursiva
                stack.push(new int[] { i, -1 }); // Inicializando com o nó e o pai (inicialmente -1)

                while (!stack.isEmpty()) {
                    int[] info = stack.pop();
                    int u = info[0]; // O vértice atual
                    int p = info[1]; // O pai do vértice

                    if (!visited[u]) {
                        visited[u] = true;
                        disc[u] = low[u] = ++time;

                        parent[u] = p; // Atualiza o pai do nó atual

                        children = 0; // Reinicia o contador de filhos do nó

                        // Percorre os vizinhos de u (os vértices conectados pelas arestas)
                        Vertice verticeAtual = gf.getVertice(u);
                        for (Aresta aresta : verticeAtual.getArestasList()) {
                            Vertice vizinho = aresta.get_vertice_incidente(verticeAtual);
                            int v = vizinho.getChave();

                            if (!visited[v]) {
                                stack.push(new int[] { v, u }); // Adiciona o filho à pilha
                                children++;
                            } else if (v != parent[u]) {
                                // Atualiza o valor mínimo (low) se o vértice já foi visitado
                                low[u] = Math.min(low[u], disc[v]);
                            }
                        }
                    } else {
                        if (p != -1) {
                            low[p] = Math.min(low[p], low[u]);

                            // Verifica se o nó é um ponto de articulação
                            if (parent[p] == -1 && children > 1) {
                                ap[p] = true;
                            }
                            if (parent[p] != -1 && low[u] >= disc[p]) {
                                ap[p] = true;
                            }
                        }
                    }
                }
            }
        }

        // Imprime os pontos de articulação
        System.out.println("Pontos de articulação no grafo:");
        for (int i = 0; i < gf.get_vertices_len(); i++) {
            if (ap[i]) {
                System.out.println("Joelho do tarjan: {" + i + "}");
            }
        }
    }

}
