/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kth.projekt_1_algoritmer;

import se.kth.id1020.Driver;
import se.kth.id1020.TinySearchEngineBase;

/**
 *
 * @author Evan
 */
public class Main {
    public static void main(String[] args) throws Exception{
        TinySearchEngineBase searchEngine = new TinySearchEngine();
        Driver.run(searchEngine);
        
       
    }
}
