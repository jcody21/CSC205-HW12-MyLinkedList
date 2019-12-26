/*
 * This application is a singly linked-list that is
 * the code base for a programming assignment.
 *
 * @creator John Cody
 * @created 02019.03.27
 */

class Node {

   // instance variables...
   private Object item;
   private Node next;

   // constructor... 
   public Node(Object x) { item = x; next = null; }

   // getter/setter methods...
   public Object getItem() { return item; }
   public Node getNext() { return next; }
   public void setItem(Object item) { this.item = item; }
   public void setNext(Node n) { next = n; }

   // return a String object representation of "this" Node object...
   public String toString() {
      return item + " (next is " + (next == null ? "" : "not ") + "null)";
   }

}

/*
 * class MyLinkedList is not a complete class (i.e. there are
 * missing methods). The class was primarily created to support
 * the Queue data structure. 
 */

public class MyLinkedList {

   private Node head = null;     // nth(1) is the head Node object
   private int size = 0;         // size == 0 implies empty linked-list

   // return the number of items in "this" linked-list...
   public int getSize() { return size; }

   // return the head Node for "this" linked-list...
   public Node getHead() { return head; }

   // set the size of "this" linked-list to zero...
   public void clear() { size = 0; head = null; }

   /* 
    * TBI (To Be Implemented) -- The remaining instance methods
    * need to be implemented as part of the #MyLinkedList assignment.
    */

   /**
    * TBI (To Be Implemented) 
    * Prints "this" linked-list starting at the Node object parameter.
    * The print format:  [ followed by a space followed the elements
    * separated by a space followed by ]  Example:  [ 0 1 2 3 4 1 ]
    *
    * @param Node to begin printing from
    * @return this MyLinkedList
    */
   public MyLinkedList print(Node n) {
       int i;
       Node a = head;
       System.out.print("[ ");
       for(i = 0; i < size; i++) {
           if(a == n) {
               break;
           }
           a = a.getNext();
       }
       for(; i < size; i++) {
           System.out.print(a.getItem() + " ");
           a = a.getNext();
       }
       System.out.println("]");
       return this;
   }

   /**
    * TBI (To Be Implemented)
    * Removes an item from this MyLinkedList object.
    *
    * @param item to remove from this MyLinkedList object
    * @return true if item removed; else return false
    */
   public boolean remove(Object item) {
       Node current = head;
       Node previous = current;
       int i = 0;
       while(current != null) {
           if(current.getItem().equals(item)) {
               if(i == 0) {
                   head = current.getNext();
                   size--;
                   return true;
               }
               if(i == size-1)
                   previous.setNext(null);
               else
                   previous.setNext(current.getNext());
               size--;
               return true;
           }
           previous = current;
           current = current.getNext();
           i++;
       }
       return false;
   }

   /**
    * TBI (To Be Implemented)
    * Inserts an item into this MyLinkList object.
    *
    * Insert examples:
    * [7 8 9] insert(5, 0) does nothing (return false)
    * [7 8 9] insert(5, 1) results in [5 7 8 9]
    * [7 8 9] insert(5, 2) results in [7 5 8 9]
    * [7 8 9] insert(5, 3) results in [7 8 5 9]
    * [7 8 9] insert(5, 4) does nothing (return false)
    *
    * @param item to insert into this MyLinkedList object
    * @param index where item is to be inserted
    * @return true if item inserted; else return false
    */
   public boolean insert(Object item, int at_i) {
       if(at_i == 0)
           return false;
       if(at_i > size)
           return false;
       
       Node insert = new Node(item);
       Node current = head;
       Node previous = current;
       for(int i = 1; i < size + 1; i++) {
           if(i == at_i) {
               if(i == 1) {
                   insert.setNext(head);
                   head = insert;
                   size++;
                   return true;
               }
               insert.setNext(current);
               previous.setNext(insert);
               size++;
               return true;
           }
           previous = current;
           current = current.getNext();
       }
       return false;
   }

   /**
    * TBI (To Be Implemented
    * Adds an item to to back of this MyLinkedList object.
    * 
    * Add Examples:
    * [7 8 9] add(5) results in [7 8 9 5]
    * [1 2 3] add(4) results in [1 2 3 4]
    * ["Phillip" "likes"] add("fish") results in
    * ["Phillip" "likes" "fish"]
    * 
    * @param item to add to the back of the list
    * @return this MyLinkedList
    */
   public MyLinkedList add(Object item) {
      size++;
      Node n = new Node(item);
      if (head == null) 
         head = n; 
      else {
         Node t = head;
         while (t.getNext() != null) 
            t = t.getNext();
         t.setNext(n);
      }
      return this;
   }

   /*
    * test class MyLinkedList...
    */
   public static void main(String[] argv) {

      MyLinkedList list = new MyLinkedList();
      Integer[] z = { new Integer(0), new Integer(1), new Integer(2),
                      new Integer(3), new Integer(4), new Integer(1), };
      for (int i = 0; i < z.length; i++)
         list.add(z[i]);
      System.out.println("test remove()...");
      System.out.print("linked-list: ");              
      list.print(list.head);
      remove(list, z[1]);
      remove(list, z[0]);
      remove(list, z[1]);
      remove(list, z[4]);
      remove(list, z[2]);
      remove(list, z[3]);
      remove(list, z[2]);

      System.out.println("\ntest insert()...");
      for (int i = 0; i < z.length; i++)
         list.add(z[i]);
      System.out.print("linked-list: ");              
      list.print(list.head);
      Integer rm0 = new Integer(9);
      Integer rm1 = new Integer(6);
      insert(list, rm0, 2);
      insert(list, new Integer(8), 0);
      insert(list, new Integer(5), 9);
      insert(list, rm1, list.getSize());
      //insert(list, rm1, 1);
      remove(list, rm0);
      remove(list, rm1);
   }

   static void remove(MyLinkedList mll, Object i) {
      char c = mll.remove(i) ? 'T' : 'F';
      System.out.print("remove(" + i + "): " + c + "; ");
      mll.print(mll.head);
   }

   static void insert(MyLinkedList mll, Object o, int i) {
      char c = mll.insert(o, i) ? 'T' : 'F';
      System.out.print("insert(" + o + ", " + i + "): " + c + "; ");
      mll.print(mll.head);
   }
}