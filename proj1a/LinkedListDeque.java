import javax.swing.*;

public class LinkedListDeque<T> {
    public class IntNode{
        public T item;
        public IntNode prev;
        public IntNode next;

        public IntNode(T i, IntNode p, IntNode n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private IntNode sentinel;
    private int size;

    public LinkedListDeque(){
        sentinel = new IntNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public LinkedListDeque(LinkedListDeque other){
        sentinel = new IntNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;

        for(int i = 0; i < other.size; i++){
            addLast((T) other.get(i));
        }
    }

    public void addFirst(T item){
        sentinel.next = new IntNode(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size++;
    }

    public void addLast(T item){
        size++;
        sentinel.prev = new IntNode(item, sentinel, sentinel.prev);
        sentinel.prev.prev.next = sentinel.prev;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public  void printDeque(){
        IntNode p = sentinel.next;
        while(p.next.item != null){
            System.out.println(p.item);
            p = p.next;
        }
    }

    public  T removeFirst(){
            if(size != 0) {
                T ans = sentinel.next.item;
                sentinel.next = sentinel.next.next;
                sentinel.next.prev = sentinel;
                size--;
                return ans;
            }else{
                return null;
            }
    }

    public  T removeLast(){
        if(size != 0){
            T ans = sentinel.prev.item;
            size--;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            return  ans;
        }else{
            return null;
        }
    }

    public  T get(int index){
        if (size == 0){
            return null;
        }
        IntNode p = sentinel.next;
        while(p.next != null){
            if(index == 0){
                return p.item;
            }
            index--;
            p = p.next;
        }
        return null;
    }
//   help function to implement the recursive
    public  T getRecursive(int index, IntNode p){
        if(index == 0){
            return p.item;
        }else{
            return getRecursive(index-1, p.next);
        }
    }

//    recursive to get n-th item
    public T getRecursive(int index){
        if(index > size){
            return null;
        }
        return getRecursive(index, sentinel.next);
    }


}
