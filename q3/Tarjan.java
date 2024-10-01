package q3;

import Grafo.Aresta;
import Grafo.Grafo;
import Grafo.Vertice;
import java.util.*;

public class Tarjan {

    private static int time = 0; // Tempo utilizado no algoritmo

    // Classe interna para representar o estado de cada nó na pilha
    private static class Frame {
        int u;           // Vértice atual
        int parent;      // Pai do vértice
        int nextNeighbor; // Próximo vizinho a processar
        int children;    // Número de filhos

        Frame(int u, int parent) {
            this.u = u;
            this.parent = parent;
            this.nextNeighbor = 0;
            this.children = 0;
        }
    }

    public static void tarjanIterativo(Grafo gf) {
        int V = gf.get_vertices_len(); // Número de vértices
        boolean[] visited = new boolean[V];
        int[] disc = new int[V]; // Tempo de descoberta dos nós
        int[] low = new int[V];  // Tempo mínimo de visita
        int[] parent = new int[V];
        boolean[] ap = new boolean[V]; // Para armazenar os pontos de articulação
        Stack<Frame> stack = new Stack<>(); // Pilha para simular a recursão

        Arrays.fill(parent, -1);
        Arrays.fill(visited, false);

        // Loop para verificar todos os nós, caso o grafo não seja conexo
        for (Vertice vertice : gf.getVertices()) {
            int i = vertice.getChave(); // Obtém a chave do vértice

            if (!visited[i]) {
                // Inicializa o quadro para o nó raiz
                Frame root = new Frame(i, -1);
                stack.push(root);

                while (!stack.isEmpty()) {
                    Frame current = stack.peek();

                    if (!visited[current.u]) {
                        visited[current.u] = true;
                        disc[current.u] = low[current.u] = ++time;
                    }

                    Vertice verticeAtual = gf.getVertice(current.u);
                    List<Aresta> arestas = verticeAtual.getArestasList();

                    if (current.nextNeighbor < arestas.size()) {
                        // Processa o próximo vizinho
                        Aresta aresta = arestas.get(current.nextNeighbor);
                        current.nextNeighbor++;
                        Vertice vizinho = aresta.get_vertice_incidente(verticeAtual);
                        int v = vizinho.getChave();

                        if (!visited[v]) {
                            // Incrementa o número de filhos do nó atual
                            current.children++;
                            parent[v] = current.u;
                            // Empilha o vizinho para processamento
                            stack.push(new Frame(v, current.u));
                        } else if (v != current.parent) {
                            // Atualiza o low value se o vizinho já foi visitado e não é o pai
                            low[current.u] = Math.min(low[current.u], disc[v]);
                        }
                    } else {
                        // Todos os vizinhos foram processados, realiza o backtracking
                        stack.pop();

                        if (current.parent != -1) {
                            low[current.parent] = Math.min(low[current.parent], low[current.u]);

                            // Verifica se o nó pai é um ponto de articulação
                            if (low[current.u] >= disc[current.parent]) {
                                ap[current.parent] = true;
                            }
                        } else {
                            // Se o nó é raiz, verifica se tem mais de um filho
                            if (current.children > 1) {
                                ap[current.u] = true;
                            }
                        }
                    }
                }
            }
        }

        // Imprime os pontos de articulação
        System.out.println("Pontos de articulação no grafo:");
        for (int i = 0; i < V; i++) {
            if (ap[i]) {
                System.out.println("Articulação: {" + i + "}");
            }
        }
    }

}
