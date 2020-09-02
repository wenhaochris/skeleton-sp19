import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    OffByN offBy5 = new OffByN(5);
    @Test
    public void testOffByN(){
        offBy5.equalChars('a', 'f');  // true
        offBy5.equalChars('f', 'a');  // true
        offBy5.equalChars('f', 'h');  // false
    }
}
