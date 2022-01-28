/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kth.projekt_1_algoritmer;

import com.kth.projekt_1_algoritmer.Index.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import se.kth.id1020.util.Document;

/**
 *
 * @author Evan
 */
public class Search {

    Index global;

    public Search(Index i) {
        this.global = i;
    }

    /**
     * Search for a word in all documents.
     * @param input word
     * @return all documents which contains the word in List<Document> object type.
     */
    public List<Document> simpleSearch(String input) {
        List<Document> list = new ArrayList();
        int index = global.search(input);
        boolean exits = false;
        if (index < global.listOfLists.size()) {
            exits = global.listOfLists.get(index).get(0).words.word.toLowerCase().equals(input.toLowerCase());
        }

        if (exits) {
            for (Index.WordObject word : global.listOfLists.get(index)) {
                if (!list.contains(word.attributes.document)) {
                    list.add(word.attributes.document);
                }
            }
        }
        return list;
    }

    /**
     * Search for a word in all documents.
     * @param input word
     * @return all documents which contains the word in List<WordObject> object type .
     */
    private List<WordObject> wordSearch(String input) {
        List<Index.WordObject> list = new ArrayList();
        int index = global.search(input);
        boolean exits = false;
        if (index < global.listOfLists.size()) {
            exits = global.listOfLists.get(index).get(0).words.word.toLowerCase().equals(input.toLowerCase());
        }

        if (exits) {
            for (Index.WordObject word : global.listOfLists.get(index)) {
                list.add(word);
            }
        }
        return list;
    }
    
    /**
     * Main method which get a word or several words as input and returns all associated documents.
     * You can also add an attribute called "orderby" which orders the resultat by count, occurrence or popularity in ascending or descending order.
     * @param input word
     * @return all documents associated to the given words.
     */
    public List<Document> query(String input) {
        if(input.length() < 1) return null;
        List<Document> list = new ArrayList();
        List<Index.WordObject> wordList = new ArrayList();
        String[] words = input.toLowerCase().split(" ");
        
        String direction = "";
        String property = "";
        if (words.length > 3) {
            direction = words[words.length - 1];
            property = words[words.length - 2];
        }


        int reversed = 0;
        if (direction.equals("asc")) {
            reversed = 1;
        } else if (direction.equals("desc")) {
            reversed = -1;
        }
        
        if (contain(words, "orderby") && reversed != 0) {
            String[] s = Arrays.copyOfRange(words, 0, words.length - 3);
            switch (property) {
                case "popularity":
                    sortByPopularity(s, list, reversed);
                    break;
                case "occurrence":
                    sortByOccurrence(s, list, wordList, reversed);
                    break;
                case "count":
                    sortByCount(s, list, wordList, reversed);
                    break;
                default:
                    list = noQuery(words, list);
                    break;
            }
        } else {
            list = noQuery(words, list);
        }

        return list;
    }
    /**
     *A method to sort an arrayList by popularity found in Document object.
     * @param s User input devided into String array to check every word in the input
     * @param list Document arrayList to store all documents and return as an arrayList 
     * @param reversed Checks if user wants the list in acsending- or descending order
     */
    private void sortByPopularity(String[] s, List<Document> list, int reversed) {
        for (String word : s) {
            List<Document> tempList = simpleSearch(word);
            for (Document obj : tempList) {
                if (!list.contains(obj)) {
                    list.add(obj);
                }
            }
        }
        BubbleSort sort = new BubbleSort(new Comparator<Document>() {
            @Override
            public int compare(Document t, Document t1) {
                if (t.popularity < t1.popularity) {
                    return -1 * reversed;
                } else if (t.popularity > t1.popularity) {
                    return 1 * reversed;
                } else {
                    return 0;
                }
            }
        });
        sort.bubbleSort(list);
    }
    
    /**
     * A method to sort an arrayList by occurrence found in attributes object.
     * @param s User input devided into String array to check every word in the input
     * @param list Document arrayList to store all documents and return as an arrayList
     * @param wordList Used to get all documents
     * @param reversed Checks if user wants the list in acsending- or descending order
     */
    private void sortByOccurrence(String[] s, List<Document> list, List<WordObject> wordList, int reversed) {
        wordList = combAll(s, wordList);

        BubbleSort sort = new BubbleSort(new Comparator<WordObject>() {
            @Override
            public int compare(WordObject t, WordObject t1) {
                if (t.attributes.occurrence < t1.attributes.occurrence) {
                    return -1 * reversed;
                } else if (t.attributes.occurrence > t1.attributes.occurrence) {
                    return 1 * reversed;
                } else {
                    return 0;
                }
            }
        });
        sort.bubbleSort(wordList);
        for (WordObject obj : wordList) {
            if (!list.contains(obj.attributes.document)) {
                list.add(obj.attributes.document);
            }
        }
    }

    
    private void sortByCount(String[] s, List<Document> list, List<WordObject> wordList, int reversed) {
        wordList = combAll(s, wordList);
        List<Document> tempList = new ArrayList();
        List<countDoc> docList = new ArrayList();
        List<Document> otherList = new ArrayList();

        for (Index.WordObject l : wordList) {
            if (!tempList.contains(l.attributes.document)) {
                tempList.add(l.attributes.document);
            }
            otherList.add(l.attributes.document);
        }
        for (int i = 0; i < tempList.size(); i++) {
            countDoc a = new countDoc(Collections.frequency(otherList, tempList.get(i)), tempList.get(i));
            docList.add(a);
        }
        BubbleSort sort = new BubbleSort(new Comparator<countDoc>() {
            @Override
            public int compare(countDoc t, countDoc t1) {
                if (t.count < t1.count) {
                    return -1 * reversed;
                } else if (t.count > t1.count) {
                return 1 * reversed;
                } else {
                    return 0;
                }
            }
        });
        sort.bubbleSort(docList);

        for (countDoc l : docList) list.add(l.doc);
    }

    public List<Document> noQuery(String[] s, List<Document> list) {
        for (String word : s) {
            List<Document> tempList = simpleSearch(word);
            for (Document obj : tempList) {
                if (!list.contains(obj)) {
                    list.add(obj);
                }
            }
        }
        return list;
    }

    public boolean contain(String[] string, String subs) {
        return Arrays.asList(string).contains(subs.toLowerCase());
    }

    private List<WordObject> combAll(String[] s, List<WordObject> wordList) {
        for (String word : s) {
            List<Index.WordObject> tempList = wordSearch(word);
            for (Index.WordObject obj : tempList) {
                if(!wordList.contains(obj))
                    wordList.add(obj);
            }
        }
        return wordList;
    }

    class countDoc {

        int count;
        Document doc;

        countDoc(int count, Document doc) {
            this.count = count;
            this.doc = doc;
        }
    }
}
