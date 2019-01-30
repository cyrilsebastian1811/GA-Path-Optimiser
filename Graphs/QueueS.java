package Graphs;

public class QueueS<T>{
    private Node head;
    private Node tail;
    private int size=0;

    private class Node{
        T data;
        Node next;
        Node(T data,Node x){
            this.data=data;
            this.next=x;
        }
    }

    public void enqueue(T data){
        Node x;
        x=new Node(data,null);
        if(head==null) {
            head=x;
        }else{
            tail.next=x;
        }
        tail=x;
        size++;
    }

    public T dequeue(){
        if(head==null) {try {
            throw new Exception("no more elements in the queue");
        } catch (Exception e) {
            System.out.println("No More Elements in the Queue: "+e);
            return null;
        }
        }
        T data=head.data;
        head=head.next;
        size--;
        return data;
    }

//    public int size(){
//        return size;
//    }

    public boolean isEmpty(){
        return size==0;
    }
}
