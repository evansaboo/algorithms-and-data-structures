/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kth.lab5;

/**
 *
 * @author Evan
 */
import se.kth.id1020.Graph;
import se.kth.id1020.DataSource;
import se.kth.id1020.Edge;

public class Paths {

    public static void main(String[] args) {
        Graph g = DataSource.load();
        ConnectedGraph graph = new ConnectedGraph(g);
        
        System.out.println("Amount of subgraphs: " +  graph.subGraphs());
        
        double pathLength = 0;
        int amountOfPaths = 0;
        ShortestPath sp = new ShortestPath(g);
        
        System.out.println();
        System.out.println("Shortest Path ignoring edge-weights:");
        for (Edge e : sp.shortestPath("Renyn", "Parses", false)) {
            amountOfPaths++;
            pathLength += e.weight;
            System.out.println(e + "   ");
        }
        System.out.println("Total path length: " + pathLength);
        System.out.println("Amount of paths: " + amountOfPaths);
        
        amountOfPaths = 0;
        System.out.println();
        System.out.println("Shortest Path taking edge-weights into account:");
        for (Edge e : sp.shortestPath("Renyn", "Parses", true)) {
            amountOfPaths++;
            System.out.println(e + "   ");
        }
        System.out.println("Total path length: " + sp.getTotalWeight("Parses"));
        
        System.out.println("Amount of paths: " + amountOfPaths);
        
    }
}

