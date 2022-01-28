/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kth.seminarie1.algoritmer;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.Stopwatch;

/**
 *
 * @author Evan
 */
public class Driver {
        public static void main(String[] args) {
           int key = StdIn.readInt();
           //int key = 1000;
            IterativePascal itrPascal = new IterativePascal(false, key);
            RecursivePascal recPascal = new RecursivePascal(false, key); 
            IterativePascal itrPascalrev = new IterativePascal(true, key);
            RecursivePascal recPascalrev = new RecursivePascal(true, key); 

           Stopwatch itrStopwatch = new Stopwatch();
           itrPascal.printPascal(key);
           double itrSW = itrStopwatch.elapsedTime();
           
           Stopwatch recStopwatch = new Stopwatch();
           recPascal.printPascal(key);
           double recSW = recStopwatch.elapsedTime();
           
           Stopwatch itrRevStopwatch = new Stopwatch();
           itrPascalrev.printPascal(key);
           double itrSWrev = itrRevStopwatch.elapsedTime();
           
           Stopwatch recRevStopwatch = new Stopwatch();
           recPascalrev.printPascal(key);
           double recSWrev = recRevStopwatch.elapsedTime();
           
           System.out.println("---------------");
           System.out.println("Iterative Time: " + itrSW);
           System.out.println("Recursive Time: " + recSW);
           System.out.println("Iterative Reversed Time: " + itrSWrev);
           System.out.println("Recursive Reversed Time: " + recSWrev);
           System.out.println("---------------");
           System.out.println(recPascal.binom(0, 0));
           System.out.println(recPascal.binom(5, 4));
           System.out.println(recPascal.binom(6, 1));
           System.out.println(recPascal.binom(15, 14));
           System.out.println(recPascal.binom(19, 9));
           
           System.out.println(itrPascal.binom(0, 0));
           System.out.println(itrPascal.binom(5, 4));
           System.out.println(itrPascal.binom(6, 1));
           System.out.println(itrPascal.binom(15, 15));
           System.out.println(itrPascal.binom(19, 9));
    }
    
}
