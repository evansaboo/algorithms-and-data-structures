/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kth.projekt_1_algoritmer;

import java.util.List;
import se.kth.id1020.TinySearchEngineBase;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import se.kth.id1020.util.Word;

/**
 *
 * @author Evan
 */
public class TinySearchEngine implements TinySearchEngineBase {

    Index array = new Index();
    Search search = new Search(array);

    @Override
    public void insert(Word word, Attributes atrbts) {
        array.insertWord(word, atrbts);
    }

    @Override
    public List<Document> search(String string) {

        return search.query(string);
    }

}
