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
public class LinkedList {
    private Node head;
    
    public LinkedList(){}
    
    public boolean isEmpty(){
        if(head == null){
            return true;
        }else{
            return false;
        }
    }
    
    public void add(int obj){
        if(isEmpty()){
            head = new Node(obj);
        }else{
            Node current = head;
            while(current.getNext() != null){
                current = current.getNext();
            }
            current.setNext(new Node(obj));
        }
    }
    
    public Integer size(){
        Integer size = 0;
        Node current = head;
        while(current != null){
            size++;
            current = current.getNext();
        }
        return size;
    }
    public int getElementAt(int index){
        // head is a node
        Node reference = head;
        for(int i = 0; i < index; i++){
            reference = reference.getNext();        
        }
        return reference.getValue();
    }
public void addAt(int data, int index) {
		Node crunchifyTemp = new Node(data);
		Node crunchifyCurrent = head;
 
		// Let's check for NPE before iterate over crunchifyCurrent
		if (crunchifyCurrent != null) {
			// crawl to the requested index or the last element in the list, whichever comes first
			for (int i = 0; i < index && crunchifyCurrent.getNext() != null; i++) {
				crunchifyCurrent = crunchifyCurrent.getNext();
			}
		}
 
		// set the new node's next-node reference to this node's next-node reference
		crunchifyTemp.setNext(crunchifyCurrent.getNext());
 
 
	}
    
    public class Node{
        private int value;
        private Node next;
        
        public Node(int element){
            value = element;
        }
        
        public int getValue(){
            return value;
        }
        
        public void setNext(Node node){
            next = node;
        }
        
        public Node getNext(){
            return next;
        }
    }
}
