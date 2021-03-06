import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface IQueue {
  public void enqueue(Object item);
  public Object dequeue();
  public boolean isEmpty();
  public int size();
}

public class LinkedListQueue implements IQueue {
    public static class node{
        Object data ;
        node next;
        public node(Object elem , node nxt){
            data = elem;
            next = nxt;
        }
    }
    public node front;
    public static node rear;
    static int size;
    LinkedListQueue(){
        front = null;
        size = 0;
    }
    public void enqueue(Object item){ 
        node n = new node(item , null);
        if(size == 0){
            front = n ; // if the queue is empty set the node to be the front node 
        }
        else{ // else add the node to the first
            n.next = front ; 
            front = n ;
        }size++;
    }
    public Object dequeue(){ 
        node c = front ; //counter node to travese through the linked list
        Object tmp ;
        if ( size == 1 ){
           tmp = rear.data;
           front = null ;
        }else{
            while(c.next.next != null){
               c = c.next; 
            } // when the loop finishes you do the stuff as c would be the node before rear
            tmp = rear.data ; //assign
            c.next = null ; // remove
            size --;
        }
        return tmp; 
    }
    public boolean isEmpty(){
        return front == null ;
    }
    public int size(){
        return size ;
    }
    public void print(){
        node n = front;
        System.out.print("[");
        while(n != null){
            System.out.print(n.data);
            if(n.next != null){
                System.out.print(", ");
            }
            n = n.next;
        }
        System.out.print("]");
    }
    public static void main(String[] args) {
        //taking input array without the size 
        Scanner sc = new Scanner(System.in);
        String sin = sc.nextLine().replaceAll("\\[|\\]", "");
        String[] s = sin.split(", ");
        int[] arr = new int[s.length];
        if (s.length == 1 && s[0].isEmpty())
            arr = new int[]{};
        else {
            for(int i = 0; i < s.length; ++i)
               arr[i] = Integer.parseInt(s[i]);
        }
        LinkedListQueue MyQueue = new LinkedListQueue();
        //adding array elements to the stack 
        if(arr.length != 0 ){
            for(int counter = 0 ; counter < arr.length ; counter++){
                MyQueue.enqueue((Object) arr[arr.length - counter - 1]);
            }
        }
        if(! MyQueue.isEmpty()){
            rear = new node((Object) arr[arr.length - 1] , null);
        }
        String str = sc.nextLine();
        if(str.equals("enqueue")){
            int data = sc.nextInt();
            MyQueue.enqueue((Object) data);
            MyQueue.print();
        }else if(str.equals("dequeue")){
            try{
                MyQueue.dequeue();
            }catch(Exception EmptyQueueException){
                System.out.println("Error");
                System.exit(0);
            }
            MyQueue.print();
        }else if(str.equals("isEmpty")){
            boolean res = MyQueue.isEmpty();
            if(res){System.out.println("True");}
            else{System.out.println("False");}
        }else if(str.equals("size")){
            int si = MyQueue.size();
            System.out.println(si);
        }else{
            System.out.println("Error");
        }
    }
}
