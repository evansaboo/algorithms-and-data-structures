/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kth.lab4;

import edu.princeton.cs.introcs.In;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class Driver {

   public static void main(String[] args) {
      Tries trie = new Tries();
      
      URL url = ClassLoader.getSystemResource("kap1.txt");

      if (url != null) {
         System.out.println("Reading from: " + url);
      } else {
         System.out.println("Couldn't find file: kap1.txt");
      }

      In input = new In(url);

      while (!input.isEmpty()) {
         String line = input.readLine().trim();
         String[] words = line.split("(\\. )|:|,|;|!|\\?|( - )|--|(\' )| ");
         String lastOfLine = words[words.length - 1];

         if (lastOfLine.endsWith(".")) {
            words[words.length - 1] = lastOfLine.substring(0, lastOfLine.length() - 1);
         }
         for (String word : words) {
            String word2 = word.replaceAll("\"|\\(|\\)", "");

            if (word2.isEmpty()) {
               continue;
            }
            //System.out.println(word2);
            trie.put(word2.toLowerCase());
         }
      }
    //Perform analysis
    System.out.println("Total word occurances: " + trie.count(""));
    System.out.println("Total distinct words: " + trie.distinct(""));
  
   
   
   
   
    System.out.println("10 top occurances:");
    System.out.println(Arrays.toString(mFrequencies(trie, true)) + "\n");
    
        System.out.println(" 10 words with the lowest frequency:");
        System.out.println(Arrays.toString(mFrequencies(trie, false)) + "\n");
        
    System.out.println("Highest frequency wihth prefix length of 2:");
    
        System.out.println(highestFrequency(trie, 2) + "\n");

        System.out.println("Letter that the most different words start with:");
        System.out.println(topCommonLetter(trie) + "\n");
    }
    public static Entry[] mFrequencies (Tries trie, boolean inverter) {
        PriorityQueue<Entry<String, Integer>> pq = new PriorityQueue(new Comparator() {
            final int inverse = (inverter == true) ? 1 : -1;
            @Override
            public int compare(Object obj, Object obj2) {
                Entry<String, Integer> entry = 
                        (Entry<String, Integer>)obj;
                Entry<String, Integer> entry2 = 
                        (Entry<String, Integer>)obj2;
                
                if (entry.getValue() > entry2.getValue()) 
                    return -1 * inverse; 
                
                if (entry2.getValue() > entry.getValue())
                    return 1 * inverse; 
                return 0;
            }
        });

        Iterator<java.util.Map.Entry<String, Integer>> tIterator = trie.trieIterator("");
        
        while (tIterator.hasNext()) 
            pq.add(tIterator.next());

        Entry[] ent = new Entry[10];
        for (int i = 0; i < ent.length; i++)
            ent[i] = pq.poll();

        return ent;
    }
    public static String highestFrequency (Tries trie, int length) {
        Iterator<java.util.Map.Entry<String, Integer>> tIterator = trie.trieIterator("");

        String maxPrefix = null;
        int maxAmount = 0;

        String currPrefix = null;
        int currentAmount = 0;

        while (tIterator.hasNext()) {
            Entry<String, Integer> word = tIterator.next();

            if (word.getKey().length() >= length) {
                String comparePrefix = word.getKey().substring(0, length);
                if(!comparePrefix.equals(currPrefix)) {
                    if (currentAmount > maxAmount || maxPrefix == null) {
                        maxAmount = currentAmount;
                        maxPrefix = currPrefix;
                    }
                    currentAmount = 0;
                    currPrefix = comparePrefix;
                }
                currentAmount += word.getValue();
            }
        }

        if (currentAmount > maxAmount) {
            maxPrefix = currPrefix;
        }

        return maxPrefix;
    }

    public static String topCommonLetter (Tries trie) {
        Iterator<java.util.Map.Entry<String, Integer>> iterator = trie.trieIterator("");

        String maxPref = null;
        int maxCount = 0;

        String currPref = null;
        int currentCount = 0;

        while (iterator.hasNext()) {
            Entry<String, Integer> word = iterator.next();

            if (word.getKey().length() >= 1) {
                String comparePrefix = word.getKey().substring(0, 1);
                if(!comparePrefix.equals(currPref)) {
                    
                    if (currentCount > maxCount || maxPref == null) {
                        
                        maxCount = currentCount;
                        maxPref = currPref;
                        
                    }
                    currentCount = 0;
                    currPref = comparePrefix;
                }
                currentCount += 1;
            }
        }
        if (currentCount > maxCount) 
            maxPref = currPref;

        return maxPref;
    }
}
