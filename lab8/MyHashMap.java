import java.security.Key;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyHashMap <K, V> implements Map61B<K, V> {
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;
    private int size;
    private int threshold;
    private BucketEntity<K,V>[] buckets;

    private class BucketEntity<K, V>{
        private K key;
        private V value;
        private BucketEntity<K, V> next;
        private int hasCode;

        public BucketEntity(int hasCode,  K k, V v, BucketEntity n){
            key = k;
            value = v;
            next = n;
            this.hasCode =hasCode;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public BucketEntity<K, V> getNext() {
            return next;
        }

        public int getHasCode() {
            return hasCode;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public void setNext(BucketEntity<K, V> next) {
            this.next = next;
        }

        public void setHasCode(int hasCode) {
            this.hasCode = hasCode;
        }
    }
    public MyHashMap(){
        buckets = new BucketEntity[INITIAL_CAPACITY];
        threshold = (int)(INITIAL_CAPACITY * LOAD_FACTOR);
        size = 0;
    }
    public MyHashMap(int INITIAL_CAPACITY){
        buckets = new BucketEntity[INITIAL_CAPACITY];
        threshold = (int)(INITIAL_CAPACITY * LOAD_FACTOR);
        size = 0;
    }

    public MyHashMap(int INITIAL_CAPACITY, double LOAD_FACTOR){
        buckets = new BucketEntity[INITIAL_CAPACITY];
        threshold = (int)(INITIAL_CAPACITY * LOAD_FACTOR);
        size = 0;
    }


    /** Removes all of the mappings from this map. */
    @Override
    public void  clear(){
        buckets = new BucketEntity[buckets.length];
        size = 0;
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key){
        if(key == null){
            throw new IllegalArgumentException();
        }
        return get(key) != null;
    }

    /** rewrite the hasCode for this class*/
    public int hash(K key, int length){
        if(key == null){
            throw new IllegalArgumentException();
        }
//        It returns an integer equal to k, but with the top bit set to zero.
        return (key.hashCode() & 0x7fffffff)% length;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key){
        if(key == null){
            throw new IllegalArgumentException();
        }
        BucketEntity<K,V> entity = get(hash(key, buckets.length), key);
        if(entity != null){
            return entity.value;
        }else{
            return null;
        }
    }

    private BucketEntity<K,V> get(int hasCode, K key){
        BucketEntity<K, V> entity = buckets[hasCode];
        while(entity != null){
            if(entity.getHasCode() == hasCode && entity.getKey().equals(key)){
                return entity;
            }
            entity = entity.getNext();
        }
        return null;
    }
    /** Returns the number of key-value mappings in this map. */
    public int size(){
        return size;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    @Override
    public void put(K key, V value){
        int hasCode = hash(key, buckets.length);
        BucketEntity<K, V> entity = buckets[hasCode];
        while (entity != null){
            if(entity.getHasCode() == hasCode && entity.getKey().equals(key)){
                entity.setValue(value);
            }
            entity = entity.getNext();
        }

        put(hasCode, key, value);
    }

    private void put(int hasCode, K key, V value){
        BucketEntity<K, V> entity = new BucketEntity<>(hasCode, key, value, buckets[hasCode]);
        buckets[hasCode] = entity;
        size++;
        if(size > threshold){
            resize(buckets.length * 2);
        }
    }

    private void resize(int capacity){
        BucketEntity<K, V>[] newBuckets = new BucketEntity[capacity];
        for(int i = 0; i < buckets.length; i ++){
            BucketEntity<K, V> entity = buckets[i];
            while(entity != null){
                BucketEntity<K, V> oldNext = entity.getNext();
                int newHasCode = hash(entity.key, newBuckets.length);
                BucketEntity<K, V> newEntity =new BucketEntity<>(newHasCode, entity.key, entity.value, newBuckets[newHasCode]);
//                entity.setHasCode(newHasCode);
//                entity.setNext(newBuckets[newHasCode]);
                newBuckets[newHasCode] = newEntity;
                entity = oldNext;
            }
        }
        buckets = newBuckets;
        threshold = (int)(buckets.length * LOAD_FACTOR);
    }



    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet(){
        Set<K> allKeys = new HashSet<>();
        for(int i = 0; i < buckets.length; i++){
            BucketEntity<K,V> entity = buckets[i];
            while (entity != null){
                allKeys.add(entity.getKey());
                entity = entity.getNext();
            }
        }
        return allKeys;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public V  remove(K key){
        if(key == null){
            throw new IllegalArgumentException();
        }
        int hasCode = hash(key, buckets.length);
        return  remove(hasCode, key);
    }

    private V remove(int hasCode, K key){
        BucketEntity<K,V> entity = buckets[hasCode];
        BucketEntity<K,V> nextEntity = entity.getNext();
        if(entity.getKey().equals(key)){
            V toRemove = entity.getValue();
            size--;
           buckets[hasCode] = nextEntity;
           return toRemove;
        }else{
            while(!nextEntity.getKey().equals(key)){
                entity = entity.getNext();
                nextEntity = nextEntity.getNext();
            }
            V toRemove = nextEntity.getValue();
            entity.setNext(nextEntity.getNext());
            size--;
            return  toRemove;
        }
    }


    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key, V value){
        if(key == null){
            throw new IllegalArgumentException();
        }
        int hasCode = hash(key, buckets.length);
        return  remove(hasCode, key, value);
    }

    private V remove(int hasCode, K key, V value){
        BucketEntity<K,V> entity = buckets[hasCode];
        BucketEntity<K,V> nextEntity = entity.getNext();
        if(entity.getKey().equals(key) && entity.getValue().equals(value)){
            V toRemove = entity.getValue();
            buckets[hasCode] = nextEntity;
            size--;
            return toRemove;
        }else{
            while (!nextEntity.getKey().equals(key)){
                entity = nextEntity;
                nextEntity= nextEntity.getNext();
            }
            if (nextEntity.getValue().equals(value)){
                V toRemove = nextEntity.getValue();
                entity.setNext(nextEntity.getNext());
                size--;
                return toRemove;
            }
        }
        return null;
    }

}


