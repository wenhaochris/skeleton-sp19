import java.util.ArrayList;
import java.util.List;

/**
 * BnBSolver for the Bears and Beds problem. Each Bear can only be compared to Bed objects and each Bed
 * can only be compared to Bear objects. There is a one-to-one mapping between Bears and Beds, i.e.
 * each Bear has a unique size and has exactly one corresponding Bed with the same size.
 * Given a list of Bears and a list of Beds, create lists of the same Bears and Beds where the ith Bear is the same
 * size as the ith Bed.
 */
public class BnBSolver {
    private List<Bear> sortedBears;
    private List<Bed> sortedBeds;



    public BnBSolver(List<Bear> bears, List<Bed> beds) {
        // TODO: Fix me.
        Pair<List<Bear>, List<Bed>> sortedPair = quickSort(bears, beds);
        sortedBears = sortedPair.first();
        sortedBeds = sortedPair.second();
        
    }

    /**
     * Returns List of Bears such that the ith Bear is the same size as the ith Bed of solvedBeds().
     */
    public List<Bear> solvedBears() {
        // TODO: Fix me.
        return sortedBears;
    }

    /**
     * Returns List of Beds such that the ith Bear is the same size as the ith Bear of solvedBears().
     */
    public List<Bed> solvedBeds() {
        // TODO: Fix me.
        return sortedBeds;
    }

    private Bed getRandomBed(List<Bed> beds){
        int pivotIndex = (int)(Math.random() * beds.size());
        Bed pivot = null;
        for(Bed bed : beds){
            if(pivotIndex == 0){
                pivot = bed;
            }
            pivotIndex--;
        }
        return pivot;
    }

    private Pair<List<Bear>, List<Bed>> catenate(Pair<List<Bear>, List<Bed>> p1, Pair<List<Bear>, List<Bed>> p2){
        List<Bear> catenateBear = new ArrayList<>();
        List<Bed> catenateBed = new ArrayList<>();

        catenateBear.addAll(p1.first());
        catenateBear.addAll(p2.first());

        catenateBed.addAll(p1.second());
        catenateBed.addAll(p2.second());

        return new Pair<>(catenateBear, catenateBed);
    }

    private void partitionBear(List<Bear> unsortedBear, Bed pivot, List<Bear> less, List<Bear> equal, List<Bear> greater){
        int cmp;
        for(Bear bear : unsortedBear){
            cmp = bear.compareTo(pivot);
            if(cmp < 0){
                less.add(bear);
            }else if(cmp == 0){
                equal.add(bear);
            }else{
                greater.add(bear);
            }
        }
    }
    private void partitionBed(List<Bed> unsortedBed, Bear pivot, List<Bed> less, List<Bed> equal, List<Bed> greater){
        int cmp;
        for(Bed bed : unsortedBed){
            cmp = bed.compareTo(pivot);
            if(cmp < 0){
                less.add(bed);
            }else if(cmp == 0){
                equal.add(bed);
            }else{
                greater.add(bed);
            }
        }
    }

    private Pair<List<Bear>, List<Bed>> quickSort(List<Bear> bears, List<Bed> beds){
        if(bears.size() <= 1){
            return new Pair<>(bears, beds);
        }

        Bed bearPivot = getRandomBed(beds);
        List<Bear> bearLess = new ArrayList<>();
        List<Bear> bearEqual = new ArrayList<>();
        List<Bear> bearGreater = new ArrayList<>();

        Bear bedPivot;
        List<Bed> bedLess = new ArrayList<>();
        List<Bed> bedEqual = new ArrayList<>();
        List<Bed> bedGreater = new ArrayList<>();

        partitionBear(bears, bearPivot,bearLess,bearEqual,bearGreater);
        bedPivot = bearEqual.get(0);

        partitionBed(beds, bedPivot,bedLess,bedEqual, bedGreater);

        Pair<List<Bear>, List<Bed>> less = quickSort(bearLess, bedLess);
        Pair<List<Bear>, List<Bed>> equal = quickSort(bearEqual, bedEqual);
        Pair<List<Bear>, List<Bed>>  greater = quickSort(bearGreater, bedGreater);

        Pair<List<Bear>, List<Bed>> sortedPair;
        sortedPair = catenate(less, equal);
        sortedPair = catenate(equal, greater);
        return sortedPair;
    }


}
