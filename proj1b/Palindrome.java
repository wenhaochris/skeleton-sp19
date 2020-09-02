public class Palindrome {
    public  Deque<Character> wordToDeque(String word){
        Deque<Character> res = new LinkedListDeque<>();
        for(int i = 0; i < word.length(); i++){
            res.addLast(word.charAt(i));
        }
        return res;
    }

    public boolean isPalindrome(String word){
        if(word.length() == 1){
            return true;
        }
        int i = 0, j = word.length() - 1;
        while(i < j){
            if(word.charAt(i) != word.charAt(j)){
                return false;
            }else{
                i++;
                j--;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        int i = 0, j = word.length() - 1;
        while ( i < j){
            if(!cc.equalChars(word.charAt(i), word.charAt(j))){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
