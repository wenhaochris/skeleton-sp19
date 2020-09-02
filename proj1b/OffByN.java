public class OffByN implements CharacterComparator{
    private int n;
    public OffByN(int m){
        n = m;
    }

    @Override
    public boolean equalChars(char x, char y) {
        if(x - y == n || y - x == n){
            return true;
        }
        return false;
    }
}
