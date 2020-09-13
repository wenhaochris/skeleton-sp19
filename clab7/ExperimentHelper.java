/**
 * Created by hug.
 */
public class ExperimentHelper {

    /** Returns the internal path length for an optimum binary search tree of
     *  size N. Examples:
     *  N = 1, OIPL: 0
     *  N = 2, OIPL: 1
     *  N = 3, OIPL: 2
     *  N = 4, OIPL: 4
     *  N = 5, OIPL: 6
     *  N = 6, OIPL: 8
     *  N = 7, OIPL: 10
     *  N = 8, OIPL: 13
     */
    public static int optimalIPL(int N) {
//        brute-force  get optimalIPL
        int result = 0;
        int temp = N;
        int layer = 0;


        while(temp != 1){
            layer ++;
            temp /= 2;
        }

        for(int i = 0; i < layer; i++){
            result += (int)Math.pow(2, i) * i;
        }
        result += (N - (int) Math.pow(2, layer) + 1) * layer;
        return result;
//        recursion
//        if(N == 1 || N == 0){
//            return 0;
//        }
//        int plus = (int)log(2, N);
//        return plus + optimalIPL(N - 1);

    }

//
//    private static  double log(int base, int x){
//        return Math.log(x) / Math.log(base);
//    }

    /** Returns the average depth for nodes in an optimal BST of
     *  size N.
     *  Examples:
     *  N = 1, OAD: 0
     *  N = 5, OAD: 1.2
     *  N = 8, OAD: 1.625
     * @return
     */
    public static double optimalAverageDepth(int N) {
        return (double)optimalIPL(N) / (double) N;
    }

    public static void getInt(BST<Integer> T){
        int temp = RandomGenerator.getRandomInt(10000);
        while (true){
            if(!T.contains(temp)){
                T.add(temp);
                break;
            }else{
                temp = RandomGenerator.getRandomInt(10000);
            }
        }
    }
}
