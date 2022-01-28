/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kth.projekt_1_algoritmer;

import java.util.Comparator;
import java.util.List;

/**
 *Bubble sort algorithm in generic form
 * @author Evan
 */
public class BubbleSort <T> {
    //Comparator is comparison function that lets you control you compare method to be able to pass different kind of objects.
    Comparator<T> comparator;
    public BubbleSort(Comparator<T> comp) {
        this.comparator = comp;
    }
    public void bubbleSort (List<T> list) {
        boolean swaps = true;
        int length = list.size() - 2;
        while (swaps && length >= 0) {
            swaps = false;
            for (int i = 0; i <= length; i++) {
                int comparison = this.comparator.compare(list.get(i), list.get(i + 1));
                if (comparison > 0) {
                    T temp = list.get(i);
                    list.set(i, list.get(i+1));
                    list.set(i + 1, temp);
                    swaps = true;
                }
            }
            length--;
        }
    }
}
