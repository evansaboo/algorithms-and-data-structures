/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kth.lab4;

import java.util.AbstractMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Evan
 */
class TrieNode {
    TrieNode[] nodeArray;
    int amount;
    char c;
    int countAllChilds;
    int nextNode;
    
    

    public TrieNode() {
        this.nodeArray = new TrieNode[26];
        this.nextNode = 0;
        this.amount = 0;
        this.c = 0;
    }
    
    public TrieNode(char character) {
        this.nextNode = 0;
        this.nodeArray = new TrieNode[26];
        this.amount = 0;
        this.c = character;
    }
    
    //gets the (n+1)'th child element in the node
    public TrieNode getNode (int n) {
        int amount = -1;
        for (TrieNode node : nodeArray) {
            if (node != null) 
                amount ++; 
            else 
                continue; 
            if (amount == n) 
                return node; 
        }
        return null;
    }
}
 
public class Tries {
    private final TrieNode top;
 
    public Tries() {
        top = new TrieNode();
    }
 
    // Inserts a word into the trie.
    public void put(String s) {
        TrieNode p = top;
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            int index = c-'a';
            if(index < 0 || index > 26)
                return;
            if(p.nodeArray[index]==null){
                TrieNode tempNode = new TrieNode(c);
                p.countAllChilds++;
                p.nodeArray[index]= tempNode;              
                p = tempNode;
                 
            } else 
                p = p.nodeArray[index];
        }
        p.amount++;
    }
 

    public int get(String word) {
        TrieNode p = nodeSearch(word);
        if(p == null)
            return 0;
        
        else if(p.amount > 0)
            return p.amount;
        
        return 0;
    }
 
 
    private TrieNode nodeSearch(String s){
        if ("".equals(s) || s == null)
            return top;
        TrieNode p = top;
        for(int i=0; i<s.length(); i++){
            char c= s.charAt(i);
            int index = c-'a';
        if(index < 0 || index > 26)
                return null;
            if(p.nodeArray[index]!=null){
                p = p.nodeArray[index];
            }else{
                return null;
            }
        }
        if(p==top)
            return top;
        return p;
    }
    
    public int count(String s){
        TrieNode p = nodeSearch(s);
        int count = 0;
        return countNode(p, count);
        
    }
    
    public int distinct(String s){
        TrieNode p = nodeSearch(s);
        int count = 0;  
        return distNode(p, count);
    }
    
    private int distNode(TrieNode p, int count){  
        if(p == null)
            return 0;
        for (TrieNode array : p.nodeArray) {
            if (array != null) {
                TrieNode temp = array;
                count = distNode(temp, count);       
            }
        }
        if(p.amount > 0)
            count += 1;
        
      return count;
    }
       
    private int countNode(TrieNode p, int count){
        if(p == null)
            return 0;
        for (TrieNode array : p.nodeArray) {
            if (array != null) {
                TrieNode temp = array;
                count = countNode(temp, count);          
            }
        }
        count += p.amount;
    return count;
    }
    /**
     * Goes through the trie
     * @param s
     * @return 
     */
    public Iterator<java.util.Map.Entry<String, Integer>> trieIterator (final String s) {
        //Return iterator
        return new Iterator<Map.Entry<String, Integer>>() {
            
            
            int amountWords; 
            //
            java.util.Stack<Integer> position;
            java.util.Stack<TrieNode> tries;
            TrieNode begin;
            // Reference to main class
            Tries tree;
            //Gets String s from iterator and appends it to stringbuilder
            //Iterator start from the last letter in Stringbuilder and gets all child nodes
            StringBuilder stringBuilder = new StringBuilder(s);
            
            {
                this.tries = new java.util.Stack<>();
                this.position = new java.util.Stack<>();
                this.tree = Tries.this;
                
                begin = Tries.this.nodeSearch(s);
                amountWords = Tries.this.distNode(begin, 0);
                
                if (stringBuilder.length() > 0)
                    stringBuilder.setLength(stringBuilder.length() - 1);

                if (begin.amount > 0 || begin.countAllChilds > 0) {
                    this.position.push(0);
                    this.tries.push(begin);
                }
            }
            //ewturns true if there are more than 0 words left in trie
            @Override
            public boolean hasNext() {
                return amountWords > 0;
            }
            
            @Override
            public Map.Entry<String, Integer> next() {
                
                int current = this.position.pop();
                
                TrieNode trieNode = this.tries.pop();
                
                int childrenLeft = trieNode.countAllChilds - current;
                
                boolean change = (current == 0);
                // Checks if we are visiting the nofor first time
                //If true, save node letter represntation in stringbuilder
                if (change) {
                    if (trieNode.c != 0) 
                        stringBuilder.append(trieNode.c);
                    //if not visitng for he first time, remove previous appended letter from the stringbuilder
                } else if (stringBuilder.length() > 0) 
                        stringBuilder.setLength(stringBuilder.length() - 1);
                
                if (childrenLeft > 0) {
                    this.position.push(current + 1);
                    this.tries.push(trieNode);
                    
                    this.position.push(0);
                    this.tries.push(trieNode.getNode(current));
                }
                //if visitng for thefirst time and occurences are larger than 0,
                //create a new entry with stringbuilder value and word count.
                if (change && trieNode.amount > 0) {
                    amountWords -= 1;
                    
                    return new AbstractMap.SimpleEntry(stringBuilder.toString(), trieNode.amount);
                }   else 
                    return this.next();
            }
        };
    }
}
