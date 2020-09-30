import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTrieSet implements TrieSet61B {
    private Node root;

    private static class Node{
        private boolean isKey;
        private HashMap<Character, Node> map;

        public Node(boolean b){
            isKey = b;
            map = new HashMap<>();
        }
    }

    public MyTrieSet(){
        root = new Node(false);
    }

    /** Clears all items out of Trie */
    @Override
    public void clear(){
        root = new Node(false);
    }

    /** Returns true if the Trie contains KEY, false otherwise */
    @Override
    public boolean contains(String key){
        if(key == null || key.length() < 1){
            return false;
        }
        Node curr = root;
        for(int i = 0; i < key.length(); i ++){
            char c = key.charAt(i);
            if(!curr.map.containsKey(c)){
                return false;
            }else{
                curr = curr.map.get(c);
            }
        }
        return curr.isKey;
    }


    /** Inserts string KEY into Trie */
    @Override
    public void add(String key){
        if(key == null || key.length() < 1){
            return;
        }

        Node curr = root;
        for(int i = 0; i < key.length(); i++){
            char c = key.charAt(i);
            if(!curr.map.containsKey(c)){
                curr.map.put(c, new Node(false));
            }

            curr = curr.map.get(c);
        }
        curr.isKey = true;
    }

    /** Returns a list of all words that start with PREFIX */
    @Override
    public List<String> keysWithPrefix(String prefix){
        List<String> list = new ArrayList<>();
        Node curr = root;
        for(int i = 0; i < prefix.length();i++){
            char c = prefix.charAt(i);
            curr = curr.map.get(c);
        }
        prefixHelper(list, curr, prefix);
        return list;
    }

    private void prefixHelper(List<String> list, Node node, String str){
        if(node.isKey){
            list.add(str);
        }
        for(Map.Entry<Character,Node> entry : node.map.entrySet()){
            Character c = entry.getKey();
            Node nextNode = entry.getValue();
            prefixHelper(list, nextNode, str + c);
        }
    }

    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public String longestPrefixOf(String key){
        throw  new UnsupportedOperationException();
    }
    public static void main(String[] args) {
        MyTrieSet t = new MyTrieSet();
        t.add("hello");
        t.add("hi");
        t.add("help");
        t.add("zebra");
    }
}
