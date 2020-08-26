import javax.management.ObjectName;

public class ArrayDeque <T> {
    private T[] items;
    private int  nextFirst;
    private int nextLast;
    private int size;


//    help function
    private int addOne(int n){
        return (n + 1)%items.length;
    }

    private int minusOne(int n){
        return (n - 1 + items.length) % items.length;
    }

    private boolean isFull(){
        return size == items.length;
    }

    private boolean isSparse(){
        return items.length >= 16 && size < (items.length / 4);
    }


    private void resize(int capacity){
        T[] cache =  (T[])new Object[capacity];
        int oldIndex = addOne(nextFirst);
        for(int i = 0; i < size; i++){
            cache[i] = items[oldIndex];
            oldIndex = addOne(oldIndex);
        }
        items = cache;
        nextLast = size;
        nextFirst = capacity - 1;
    }

    public ArrayDeque(){
        items = (T []) new Object[8];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }

    public ArrayDeque(ArrayDeque other){
        items = (T[]) new Object[other.items.length];
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        size = other.size;
        System.arraycopy(other.items, 0, items, 0, other.items.length);
    }


    public void addFirst(T item){
        if(isFull()){
            resize(items.length * 2);
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size ++;
    }

    public void addLast(T item){
        if(isFull()){
            resize(items.length * 2);
        }

        items[nextLast] = item;
        nextLast = addOne(nextLast);
        size ++;
    }

    public  boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        int index = addOne(nextFirst);
        for(int i = 0; i < size; i++){
            System.out.println(items[index] + " ");
            index = addOne(index);
        }
        System.out.println();
    }

    public T removeFirst(){
        if(isSparse()){
            resize(items.length / 2);
        }
        nextFirst = addOne(nextFirst);
        T removeItem  =  items[nextFirst];
        items[nextFirst] = null;
        size --;
//        if (!isEmpty()) {
//            size -= 1;
//        }
        return removeItem;
    }

    public T removeLast(){
        if(isSparse()){
            resize(items.length / 2);
        }
        nextLast = minusOne(nextLast);
        T removeItem =items[nextLast];
        items[nextLast] = null;
        size --;
        return  removeItem;
    }

    public T get(int index){
        if(index >= size){
            return null;
        }
        int start = addOne(nextFirst);
        return items[(start + index) % items.length];
    }








}
