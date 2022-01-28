/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kth.seminarie1.algoritmer;

/**
 *Error input checking 
 * @author Evan
 */
public abstract class ErrorPascal implements Pascal{
    /**
     * Checks binum inputs
     * @param n row
     * @param k column 
     */
    public void binumInputCheck(int n, int k){
       if( k > n || n < 0 || k < 0){
           throw new IllegalArgumentException(
           "Invalid input");
       }      
    }
    /**
     * Checks user input
     * @param n amount of levels that should be printed
     */
    public void printInputCheck(int n){
       if(n < 0){
           throw new IllegalArgumentException(
           "Your value can not be less than 0");
       } 
    }
}
