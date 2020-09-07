package es.datastructur.synthesizer;
import java.util.Iterator;
import java.util.NoSuchElementException;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T>  {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        rb = (T[]) new Object[capacity];
        first = 0;
        fillCount = 0;
        last = 0;
    }

    @Override
    public int capacity() {
        return rb.length;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last.
        if(isFull()){
            throw new RuntimeException("Ring buffer overflow");
        }
        fillCount ++;
        rb[last] = x;
        last = (last + 1) % capacity();
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        if(isEmpty()){{
            throw new RuntimeException("Ring buffer underflow");
        }}
        fillCount --;
        T item = rb[first];
        rb[first] = null;
        first = (first + 1) % capacity();

        return item;

    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change.
        if(isEmpty()){
            throw new RuntimeException("Ring buffer underflow");
        }
        T item = rb[first];
        return item;
    }

    @Override
    public Iterator<T> iterator() {
        return new  MyIterator();
    }

    public class MyIterator<T> implements Iterator<T>{
        private  int index;
        private  int size;
        public  MyIterator(){
            index = first;
            size = fillCount;
        }
        public boolean hasNext(){
            return size != 0;
        }

        public T next(){
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            T temp = (T)rb[index];
            index ++;
            size --;
            if(index > rb.length - 1){
                index = 0;
            }
            return temp;
        }
    }
    @Override
    public boolean equals(Object o){
        ArrayRingBuffer temp = (ArrayRingBuffer) o;
        if(this.fillCount() != temp.fillCount()){
            return  false;
        }
        Iterator iter1 = this.iterator();
        Iterator iter2 = temp.iterator();
        while(iter1.hasNext()){
            if(!String.valueOf(iter1.next()).equals(String.valueOf(iter2.next()))){
                return false;
            }
        }
        return true;
    }

    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
}
    // TODO: Remove all comments that say TODO when you're done.
