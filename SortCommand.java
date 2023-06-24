import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortCommand implements CommandRunner{
    public SortCommand(){}

    public String execute(Command com, ArrayList<Person> list) {
        if (list.size() == 0) return "The list is empty.\n";
        list.sort(Comparator.comparingInt(s -> s.getID()));
        return "Success!\n";
    }
}