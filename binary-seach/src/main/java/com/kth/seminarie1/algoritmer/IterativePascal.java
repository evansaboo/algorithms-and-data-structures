/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kth.seminarie1.algoritmer;

/**
 * Class for iterative pascal and iterative methods
 * @author Evan
 */
public class IterativePascal extends ErrorPascal {
    private boolean flip;
    private int [][] saveArr;
    
    /**
     * Constructor for iterative pascal
     * @param flip decides if the pascal triangle will print out reversed
     * @param key input for deciding how many levels should be printed out
     */
    public IterativePascal(boolean flip, int key ){
        this.printInputCheck(key);
        this.flip = flip;
        this.saveArr = new int[key][];
        for(int i = 1; i <= key; i++){
            this.saveArr[i-1] = new int[i];
        }      
    }
    /**
     * Calculates a value by taking the two previous values in the array
     * @param n row
     * @param k cloumn
     * @return 
     */
    @Override 
    public int binom(int n, int k){
        this.binumInputCheck(n, k);
        if ( n == 0 || k == 0 || n == k ){
            saveArr[n][k] = 1;
            return 1;
        }

        if(saveArr[n][k] != 0)
            return saveArr[n][k];
        
        saveArr[n][k] = saveArr[n-1][k-1] + saveArr[n-1][k];
        return saveArr[n][k];
    }
    
    /**
     * Prints out pascial traingle depending on how many levels the user wants
     * @param n decides amount of levels that should be printed out
     */
    @Override
    public void printPascal(int n){
        this.printInputCheck(n);
        if(!this.flip)
        for(int i = 0; i< n; i++){
            for (int j = 0; j <= i; j++)
                System.out.print(binom(i, j) + " ");
            
            System.out.println();    
        }
        else{
           
            for(int i = 0; i< n; i++){
                for (int j = 0; j <= i; j++)
                    binom(i, j);            
            }
            for(int i = n -1; i >= 0; i--){
                for (int j = 0; j <= i; j++)
                    System.out.print(saveArr[i][j] + " ");

                System.out.println();
            } 
        }
        }
  
      
    }
