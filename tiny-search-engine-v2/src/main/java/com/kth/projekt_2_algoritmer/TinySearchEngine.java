/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kth.projekt_2_algoritmer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import se.kth.id1020.TinySearchEngineBase;
import se.kth.id1020.util.*;

/**
 *
 * @author Evan
 */
public class TinySearchEngine implements TinySearchEngineBase {

    //Shashmap to store all words and their associated documents.
    HashMap<String, Set<Document>> wordsInDocs;

    public TinySearchEngine() {
        wordsInDocs = new HashMap<>();
    }

    @Override
    public void preInserts() {

    }

    @Override
    public void postInserts() {

    }

    /**
     * Function which inserts all words in a hashmap
     *
     * @param sentence Object which contains words in several sentence
     * @param attr attribute associated to the sentence
     */
    @Override
    public void insert(Sentence sentence, Attributes attr) {
        for (Word w : sentence.getWords()) {
            Document doc = attr.document;
            String words = w.word.toLowerCase();
            if (wordsInDocs.get(words) != null) {
                
                wordsInDocs.get(words).add(doc);
            } else {
                Set<Document> newDoc = new HashSet<>();
                newDoc.add(doc);
                wordsInDocs.put(words, newDoc);
            }
        }

    }
    /**
     * Search method which takes a string as query input and returns list of filtered documents depending on the user input prefix notation.
     * @param query input
     * @return list of distinct documents
     */
    @Override
    public List<Document> search(String query) {
        Set<Document> docs = new HashSet<>();
        Set<Document> docs1;
        String[] words = query.toLowerCase().split(" ");

        switch (words[0]) {
            case "+":
                docs = new HashSet<>(getDocs(words[1]));
                if (docs.isEmpty()) {
                    break;
                }
                words = Arrays.copyOfRange(words, 2, words.length);
                for (String word : words) {

                    docs1 = new HashSet<>(getDocs(word));

                    if (docs1.isEmpty()) {
                        docs.clear();
                        break;
                    }

                    for (Iterator<Document> it = docs.iterator(); it.hasNext();) {
                        Document aDocument = it.next();
                        if (!docs1.contains(aDocument)) {
                            it.remove();
                        }
                    }
                }
                break;
            case "-":
                docs = new HashSet<>(getDocs(words[1]));
                if (docs.isEmpty()) {
                    break;
                }
                words = Arrays.copyOfRange(words, 2, words.length);
                for (String word : words) {

                    docs1 = new HashSet<>(getDocs(word));

                    if (docs1.isEmpty()) {
                        continue;
                    }

                    for (Iterator<Document> it = docs.iterator(); it.hasNext();) {
                        Document aDocument = it.next();
                        if (docs1.contains(aDocument)) {
                            it.remove();
                        }
                    }
                }
                break;
            case "|":
                docs = noQuery(Arrays.copyOfRange(words, 1, words.length));
                break;
            default:
                if (words.length < 2) {
                    docs = noQuery(words);
                } else {
                    System.out.println("**Illegal query**");
                }
                break;
        }
        return new ArrayList(docs);
    }
    
    /**
     * Takes array of strings and returns all a list of documents associated to the given words in the array
     * @param words String array
     * @return list of documents
     */
    public Set<Document> noQuery(String[] words) {
        Set<Document> docs = new HashSet<>();
        for (String word : words) {
            Set<Document> tempList = getDocs(word);
            if (tempList == null) {
                continue;
            }

            for (Document obj : tempList) {
                docs.add(obj);
            }
        }
        
        return docs;
    }
    
    /**
     * Get all documents associated the String parameter and returns a list of documents from the global hashmap which everything is stored in.
     * @param s String input
     * @return list of documents
     */
    private Set<Document> getDocs(String s) {       
        return wordsInDocs.get(s);
    }
    
    /**
     * Transforms user input from prefix to infix form
     * @param query user input
     * @return String as infix form
     */
    @Override
    public String infix(String query) {
        String[] words = query.toLowerCase().split(" ");
        StringBuilder sb = new StringBuilder("Query(");

        if (words.length > 2) {
            for (int i = 1; i < words.length; i++) {
                sb.append(words[i]);
                if (i == words.length - 1) {
                    sb.append(")");
                } else {
                    sb.append(" ").append(words[0]).append(" ");
                }
            }
        } else {
            if (words.length > 1) {
                sb.append("NaN)");
            } else {
                sb.append(words[0]).append(")");
            }
        }
        return sb.toString();
    }

}
