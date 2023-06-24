import java.util.Comparator;

public class PersonComparator implements Comparator<Person> { //just a comparator thing
    @Override
    public int compare(Person a, Person b){
        return Integer.compare(a.getID(),b.getID());
    }
}
