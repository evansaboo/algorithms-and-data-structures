/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kth.seminarie1.algoritmer;

/**
 * Class for recursive pascal and recursive methods
 * @author Evan
 */
public class RecursivePascal extends ErrorPascal {
    private boolean flip;
    private int [][] saveArr;
   
    /**
     * Constructor for recursive pascal
     * @param flip decides if the pascal triangle will print out reversed
     * @param key input for deciding how many levels should be printed out
     */
    public RecursivePascal(boolean flip, int key ){
        this.printInputCheck(key);
        this.flip = flip;
        this.saveArr = new int[key][];
        for(int i = 0; i < key; i++){
            this.saveArr[i] = new int[i/2+1];
        }      
    } 
    /**
     * Calculates a value by taking the two previous values in pascal triangle 
     * @param n row
     * @param k cloumn
     * @return 
     */
    @Override 
    public int binom(int n, int k){
         this.binumInputCheck(n, k);
        if ( n == 0 || k == 0 || n == k )
            return 1;
        
        if(k > n/2)
            k = n - k;   
        
        if(this.saveArr[n][k] != 0)
            return this.saveArr[n][k];
        
        this.saveArr[n][k] = binom(n-1, k-1) + binom(n-1, k);
        return this.saveArr[n][k];
    }
    
    /**
     * Prints out pascial traingle depending on how many levels the user wants
     * @param n decides amount of levels that should be printed out
     */
    @Override
    public void printPascal(int n){
        this.printInputCheck(n);
        if(this.saveArr.length == n) n--;

        if(n > 0 && !this.flip) printPascal(n-1);
        for (int j = 0; j <= n; j++)
            System.out.print(binom(n, j) + " ");
        System.out.println();
        if(n > 0 && this.flip) printPascal(n-1);
        }   
    }
