/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kth.lab5;

import edu.princeton.cs.algs4.IndexMinPQ;
import java.util.ArrayList;
import se.kth.id1020.Edge;
import se.kth.id1020.Graph;

/**
 *
 * @author Evan
 */
public class ShortestPath {
    // Shortest distance from given start
    private final double[] distTo; 
    // Saves all edges to shotest path
    private final Edge[] edgeTo;
    private IndexMinPQ<Double> queue;    
    private Graph g;

    public ShortestPath(Graph g) {
        this.g = g;
        distTo = new double[g.numberOfVertices()];
        edgeTo = new Edge[g.numberOfVertices()];
    }

    public ArrayList<Edge> shortestPath(String start, String end, boolean weight) {
        int begin = getPos(start);
        int stop = getPos(end);

        for (int v = 0; v < g.numberOfVertices(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[begin] = 0.0;

        queue = new IndexMinPQ<>(g.numberOfVertices());
        
        queue.insert(begin, distTo[begin]);
        
        while (!queue.isEmpty()) {
            int v = queue.delMin();
            for (Edge e : g.adj(v)) {
                update(e, weight);
            }
        }
        return pathTo(stop);
    }
    
    /**
     * Goes to next connected vertex and saves total weight if its less than current save weight.  
     * @param e current edge
     * @param boolWeight if user wants calculate shortest path by taking edge-weights into acount or not
     */
    private void update(Edge e, boolean boolWeight) {
        double weight = boolWeight ? e.weight : 1;
        int v = e.from;
        int w = e.to;
        double next = distTo[v] + weight;
        if (distTo[w] > next) {
            distTo[w] = next;
            edgeTo[w] = e;
            if (queue.contains(w))  queue.decreaseKey(w, distTo[w]);
            else queue.insert(w, distTo[w]);
        }
    }
    
    /**
     * Checks if path is connected.
     * @param v current vertex position
     * @return true if path has been found else false
     */
    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }
    
    /**
     * Gets an arrayList of shortest path from start to end node as an edge list.
     * @param pos last pos
     * @return arrayList of  all edges.
     */
    public ArrayList<Edge> pathTo(int pos) {
        if (!hasPathTo(pos))
            return null;
        
        ArrayList<Edge> list = new ArrayList();
        for (Edge e = edgeTo[pos]; e != null; e = edgeTo[e.from])
            list.add(e);
        
        return list;
    }
    /**
     * gets total edge-weight after calculation is finished
     * @param posS
     * @return 
     */
    public double getTotalWeight(String posS){
        int pos = getPos(posS);
        return distTo[pos];
    }
    /**
     * Gets index of given string pos
     * @param s String input
     * @return index in Graph
     */
    public int getPos(String s) {
        for (int i = 0; i < g.numberOfVertices(); i++) {
            if (g.vertex(i).label.equals(s)) {
                return g.vertex(i).id;
            }
        }
        return -1;
    }

}
