/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kth.projekt_1_algoritmer;

import java.util.*;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Word;
/**    
 *
 * @author Evan
 */
public class Index {
    ArrayList<ArrayList<WordObject>> listOfLists = new ArrayList();
    
    
    public void insertWord(Word word, Attributes attributes){
        WordObject thisWord = new WordObject(word, attributes);
        //Calls binary search method with String word from the object word
        int index = search(word.word);
        
        //checks if word is inside the array list
        boolean exits = false;
    
        if (index < this.listOfLists.size())
            exits = listOfLists.get(index).get(0).words.word.toLowerCase().equals(word.word.toLowerCase());
      
        //If the word exists in the arrayList, add it to the same object words
        //Else create a new object words and insert the given word in the arraylist
        if(exits) {
            this.listOfLists.get(index).add(thisWord);
        } else {
            ArrayList<WordObject> tempList = new ArrayList();
            tempList.add(thisWord);
            this.listOfLists.add(index, tempList);
        }   
    }

/**
 * Calls binary search method
 * @param key the given word
 * @return index position in the array
 */
public int search(String key){
    int lo = 0;
    int hi = this.listOfLists.size() - 1;
    return search(key.toLowerCase(), lo, hi);
}
    /**
     * Binary Search algorthim
     * @param key as String
     * @return position of given key in arraylist if found.
     */
    public int search(String key, int lo, int hi) {
        if (hi < lo) return lo;
        int mid = lo + (hi - lo) / 2;
        int cmp = key.compareTo(this.listOfLists.get(mid).get(0).words.word.toLowerCase());
        if      (cmp < 0) return search(key, lo, mid-1);
        else if (cmp > 0) return search(key, mid+1, hi);
        else              return mid;
    }
    
   
    /**
     * Object of words stored in the arrayList
     */
    class WordObject{
       Word words;
       Attributes attributes;
       
       WordObject(Word words, Attributes attributes){
           this.words = words;
           this.attributes = attributes;
       }
    }
}