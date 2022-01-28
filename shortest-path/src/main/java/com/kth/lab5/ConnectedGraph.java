/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kth.lab5;

import se.kth.id1020.Edge;
import se.kth.id1020.Graph;

/**
 *
 * @author Evan
 */
public class ConnectedGraph {

    Graph g;
    private final boolean[] visited;

    ConnectedGraph(Graph g) {
        this.g = g;
        visited = new boolean[this.g.numberOfVertices()];
    }

    /**
     * Checks amount of subgraps
     * @return amount of subgraphs
     */
    public int subGraphs() {
        int amount = 0;
        int i = 0;
        while (i < visited.length) {
            if (!visited[i]) {
                amount++;
                DFS(i);
            }
            i++;
        }

        if (amount > 1) {
            System.out.println("Graph g is not fully connected");
        } else {
            System.out.println("Graph g is fully connected");
        }
        return amount;
    }
    /**
     * Depth-first search algorithm  which chechks if the given graph is fully connected from given start to end of graph.
     * @param start starting node.
     */
    public void DFS(int start) {
        this.visited[start] = true;

        for (Edge edge : this.g.adj(start)) {
            if (!visited[edge.to]) {
                DFS(edge.to);
            }
        }
    }
}
