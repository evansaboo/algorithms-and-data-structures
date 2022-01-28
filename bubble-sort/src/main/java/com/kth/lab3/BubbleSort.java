/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kth.lab3;

/**
 *
 * @author Evan
 */
public class BubbleSort {

    //linkHead always points on the top of the linked list
    LinkedNode linkHead;
    //Stores length of the linked list
    int size;
    //Counts amount of swapps made in bubble sort algorithm
    int swapps;

    BubbleSort() {
        linkHead = null;
    }

    public class LinkedNode {

        LinkedNode next;
        int value;

        LinkedNode(int value) {
            this.value = value;
        }
    }

    /**
     * Adds a new value in the linked list
     *
     * @param obj
     */
    public void add(int obj) {
        LinkedNode addLink = new LinkedNode(obj);
        addLink.next = linkHead;
        linkHead = addLink;
        size++;
    }

    public static void main(String[] args) {

        BubbleSort theLinkedList = new BubbleSort();

        theLinkedList.add(1);
        theLinkedList.add(5);
        theLinkedList.add(4);
        theLinkedList.add(3);
        theLinkedList.add(10);
        theLinkedList.add(2);
        theLinkedList.add(4);
        theLinkedList.add(3);
        theLinkedList.add(1);

        System.out.println("Inversion count: " + theLinkedList.InversionCount());
        theLinkedList.bSort();

        System.out.println("Bubble sort swapps: " + theLinkedList.swapps);

        for (int i = 0; i < theLinkedList.size; i++) {
            System.out.println(theLinkedList.linkHead.value);
            theLinkedList.linkHead = theLinkedList.linkHead.next;
        }
    }

    /**
     * Bubble sort algorithm
     */
    public void bSort() {
        LinkedNode linkedNode;

        boolean swapped = true;
        int r = this.size - 2;
        while (r >= 0 && swapped == true) {
            linkedNode = linkHead;
            swapped = false;
            for (int i = 0; i <= r; i++) {
                if (linkedNode.value > linkedNode.next.value) {
                    swapped = true;
                    swap(linkedNode, linkedNode.next);
                    swapps++;
                }
                linkedNode = linkedNode.next;
            }
            r -= 1;
        }
    }

    /**
     * Swaps object "i" and object "j" with eachother
     *
     * @param i
     * @param j
     */
    public void swap(LinkedNode i, LinkedNode j) {
        int val = i.value;
        i.value = j.value;
        j.value = val;
    }

    /**
     * Counts amount of swapps needed to get an ordererd list
     *
     * @return swapp count
     */
    public int InversionCount() {
        LinkedNode linkedNode;
        int swapp = 0;
        linkedNode = linkHead;
        LinkedNode inner;
        int length = this.size - 1;
        for (int i = 0; i < length; i++) {
            inner = linkedNode.next;
            for (int j = i; j < length; j++) {
                if (linkedNode.value > inner.value) {
                    swapp++;
                }
                inner = inner.next;
            }
            linkedNode = linkedNode.next;
        }
        return swapp;
    }
}
