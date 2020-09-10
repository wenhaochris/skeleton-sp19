import com.sun.source.tree.Tree;

import java.util.Iterator;
import java.util.Set;

public class BSTMap <Key extends  Comparable<Key>, Value> implements Map61B<Key, Value> {
    private Node root;
    private int size;

    public class Node {
        private Key key;
        private Value value;
        private Node left, right;


        public Node(Key k, Value v) {
            key = k;
            value = v;
        }
    }

    public BSTMap(){
        size = 0;
    }
    /** Removes all of the mappings from this map. */
    @Override
    public void clear(){
        root = null;
        size = 0;
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(Key k){
        return  get(k)  != null;
    }

    /** Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public Value get(Key k){
        return get(root, k);
    }

    private Value get(Node root, Key k){
        if(k == null){
            throw new IllegalArgumentException();
        }

        if(root == null){
            return null;
        }
        int cmp = k.compareTo(root.key);
        if (cmp < 0){
            return  get(root.left, k);
        }else if(cmp > 0){
            return get(root.right, k);
        }else{
               return root.value;
        }
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public  int size(){
        return size;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(Key k, Value v){
        root = put(k, v, root);
    }

    private Node put(Key k, Value v, Node root){
        if(k == null){
            throw new IllegalArgumentException();
        }

        if(root == null){
            size ++;
            return new Node(k, v);
        }

        int cmp = k.compareTo(root.key);

        if(cmp == 0){
            root.value = v;
        }else if(cmp < 0){
            root.left = put(k, v, root.left);
        }else if (cmp > 0){
            root.right = put(k,v,root.right);
        }
        return root;
    }
    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<Key> keySet(){
        throw new UnsupportedOperationException();
    }
    @Override
    public Value remove(Key k){
        throw new UnsupportedOperationException();
    }

    @Override
    public Value remove(Key k, Value v){
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator iterator(){
        throw new UnsupportedOperationException();
    }

    public void print(Node root){
        inOrderPrint(root);
    }

    public void inOrderPrint(Node root){
        if(root == null)
            return;
        inOrderPrint(root.left);
        System.out.println(root.key);
        inOrderPrint(root.right);
    }

}
