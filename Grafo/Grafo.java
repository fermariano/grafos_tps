package Grafo;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

// Classe Grafo
public class Grafo {

    private Map<Integer, Vertice> vertices_map;
    int vertice_len = 0;
    int aresta_len = 0;

    public Grafo() {
        vertices_map = new HashMap<>();
    }

    public int get_vertices_len(){
        return this.vertice_len;
    }
    public int get_arestas_len(){
        return this.aresta_len;
    }

    public void adicionarVertice(int chave) {
        if (!vertices_map.containsKey(chave)) {
            vertices_map.put(chave, new Vertice(chave));
            vertice_len++;
        }
    }

    public void adicionarAresta(int chave1, int chave2) {
        Vertice vertice1 = vertices_map.get(chave1);
        Vertice vertice2 = vertices_map.get(chave2);
        if (vertice1 == null) {
            adicionarVertice(chave1);
            vertice1 = vertices_map.get(chave1);
        }
        if (vertice2 == null) {
            adicionarVertice(chave2);
            vertice2 = vertices_map.get(chave2);
        }
        aresta_len++;
        Aresta aresta = new Aresta(vertice1, vertice2);
        // Adiciona a aresta em ambos os vértices
        vertice1.adicionarAresta(aresta);
        vertice2.adicionarAresta(aresta);
    }

    public void removeVertice(Vertice v) {

        if (vertices_map.get(v.getChave()) == null) {
            throw new IllegalArgumentException("Vertice não existe");
        }
        List<Aresta> arestas = v.getArestasList();
        for (Aresta aresta : arestas) {
            Vertice w = aresta.get_vertice_incidente(v);
            w.removerAresta(v);
            this.aresta_len--;
        }
        this.vertice_len--;
        this.vertices_map.remove(v.getChave());
    }

    public void readicione_vertice_removido(Vertice v) {
        List<Aresta> vizinhos = v.getArestasList();

        vertices_map.put(v.getChave(), v);

        for (Aresta vizinha : vizinhos) {
            Vertice w = vizinha.get_vertice_incidente(v);
            adicionarAresta(v.getChave(), w.getChave());
        }
        this.vertice_len++;
        this.vertices_map.put(v.getChave(), v);
    }

    public void removeAresta(Vertice v, Vertice w) {
        if (v.contemArestaCom(w)) {
            v.removerAresta(w);
            w.removerAresta(v);
            aresta_len--;
        }
    }

    public Vertice getVertice(int chave) {
        return vertices_map.get(chave);
    }

    public List<Vertice> getVertices() {
        ArrayList<Vertice> vertices = new ArrayList<>();
        for (Vertice vertice : vertices_map.values()) {
            vertices.add(vertice);
        }
        return vertices;

    }

    public Aresta get_aresta_v_w(int chave1, int chave2) {
        Vertice vertice1 = vertices_map.get(chave1);
        Vertice vertice2 = vertices_map.get(chave2);
        if (vertice1 == null || vertice2 == null) {
            return null;
        }
        if (vertice1.contemArestaCom(vertice2)) { // ordem não importa
            return vertice1.getArestas().get(chave1 + " " + chave2);
        } else {
            return null;
        }
    }

    public boolean isConnex() {
        HashSet<Vertice> visitados = new HashSet<>();
        HashSet<Aresta> arestas_marcadas = new HashSet<>();
        Deque<Vertice> pilha = new ArrayDeque<>();
        /*
         * Mapa de vertices visitados e arestas
         * estou marcando aresta pois no método iterativo eu posso revisitalas caso não
         * haja marcação
         */
        /*
         * pilha usada para gerenciar a busca em profundidade
         */

        Vertice start = this.vertices_map.values().iterator().next();
        pilha.push(start);
        while (!pilha.isEmpty()) {
            Vertice v = pilha.peek(); // pega o topo da pilha
            if (!visitados.contains(v)) { // marca visitado
                visitados.add(v);
            }
            boolean descobertos = false;
       List<Aresta> vizinhanca = v.getArestasList(); // pega vizinhança
            for (Aresta vizinha : vizinhanca) {

                if (arestas_marcadas.contains(vizinha)) { // se a aresta ja foi marcada é tchau
                    continue;
                }
                arestas_marcadas.add(vizinha);

                Vertice w = vizinha.get_vertice_incidente(v); // pega o vertice incidente
                if (!visitados.contains(w)) { // se não foi visitado ainda, paro a busca nesse e vou para o w
                    pilha.push(w);
                    descobertos = true; // marco que descobriu alguem para eu poder voltar em v posteriormente
                    break;
                }
            }
            
            if (!descobertos) {
                // so popo se o vertice não descobriu ninguem
                pilha.pop();
            }
        }

        if(visitados.size() == this.vertice_len){
            return true;
        }
        return false;
    }
}
