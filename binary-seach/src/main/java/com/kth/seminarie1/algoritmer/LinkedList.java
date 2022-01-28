

package com.kth.seminarie1.algoritmer;

// Java program to reverse a linked list in groups of
// given size
class LinkedList
{
    Node head;  // head of list
  
    /* Linked list Node*/
    class Node
    {
        int data;
        Node next;
        Node(int d) {data = d; next = null; }
    }
 
 public Node fun2(Node head, int n) {
    if(head == null)
        return null;
 
    Node fast = head;
    Node slow = head;
 
    for(int i=0; i<n; i++){
        fast = fast.next;
    }
 
    if(fast == null){
        head = head.next;
        return head;
    }
 
    while(fast.next != null){
        fast = fast.next;
        slow = slow.next;
    }
 
    slow.next = slow.next.next;
 
    return head;
}                 
 
                    
    /* Utility functions */
 
    /* Inserts a new Node at front of the list. */
    public void push(int new_data)
    {
        /* 1 & 2: Allocate the Node &
                  Put in the data*/
        Node new_node = new Node(new_data);
  
        /* 3. Make next of new Node as head */
        new_node.next = head;
  
        /* 4. Move the head to point to new Node */
        head = new_node;
    }
 
    /* Function to print linked list */
    void printList()
    {
        Node temp = head;
        while (temp != null)
        {
           System.out.print(temp.data+" ");
           temp = temp.next;
        }  
        System.out.println();
    }
 
     /* Drier program to test above functions */
    public static void main(String args[])
    {
        LinkedList llist = new LinkedList();
         
        /* Constructed Linked List is 1->2->3->4->5->6->
           7->8->8->9->null */
        llist.push(9);
        llist.push(9);
        llist.push(9);
        llist.push(9);
        llist.push(9);
        llist.push(9);
        llist.push(9);
        llist.push(9);
        llist.push(8);
        llist.push(7);
        llist.push(6);
        llist.push(5);
        llist.push(4);
        llist.push(3);
        llist.push(2);
        llist.push(1);
         
        System.out.println("Given Linked List");
        llist.printList();
         
        llist.head = llist.fun2(llist.head, 8);
 
        System.out.println("Reversed list");
        llist.printList();
    }
} 