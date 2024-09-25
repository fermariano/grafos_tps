# Arquivos relacionados aos estudos de Grafos
## Informações Gerais

Integrantes:
> - Arthur
> - Fernanda
> - Luis Phillip

## Enunciado
Um grafo conexo e sem articulações é chamado 2-conexo (ou biconexo) e apresenta propriedades interessantes, sendo que, em algumas áreas da Computação, se diz que tal grafo é “tolerante a falhas”. Um grafo com três ou mais vértices é biconexo se e somente se cada par de seus vértices estiver ligado por dois caminhos internamente disjuntos (ou seja, dois caminhos sem vértices internos em comum). Essa propriedade explica o nome “biconexo”.  <br>

Denominam-se componentes biconexos do grafo G aos subgrafos maximais de G que sejam biconexos em vértices, ou isomorfos a K2. Cada componente biconexo é também chamada bloco do grafo. Assim se G é biconexo então ele possui um único bloco, que coincide com o próprio G. Na Figura 1(a), os vértices 2, 3 e 4 são articulações (vértices que se forem removidos tornam o grafo desconexo ou trivial), enquanto que a aresta {2, 4} é uma ponte (aresta que se for removida tornam o grafo desconexo ou trivial). Seus componentes biconexos ou blocos estão indicadas na Figura 1(b).

![Figura 1: Exemplo de um grafo e seus blocos.](https://i.imgur.com/1oQ84Du.png)

Como o problema de se determinar os blocos existentes em um grafo apresenta várias aplicações, você irá desenvolver soluções para ele. Neste trabalho você deverá implementar três métodos para identificação de blocos: <br> <br>
i. um método que verifique a existência de dois caminhos internamente disjuntos (ou um ciclo) entre cada par de vértices do bloco;

ii. um método identifique articulações testando a conectividade após a remoção de cada vértice;

iii. o método proposto por Tarjan (1972).

Devem ser realizados experimentos que para avaliar o tempo médio gasto para as três estratégias aplicadas a grafos aleatórios contendo 100, 1.000, 10.000 e 100.000 vértices.

