import java.util.Comparator;

public class IntegerComparator implements Comparator<Integer> { //this is stupid
    @Override
    public int compare(Integer a, Integer b){
        return Integer.compare(a, b);
    }
}
