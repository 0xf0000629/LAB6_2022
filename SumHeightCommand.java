import java.util.ArrayList;

public class SumHeightCommand implements CommandRunner{
    public SumHeightCommand(){}

    public String execute(Command com, ArrayList<Person> list) {
        if (list.size() > 0) {
            return "Sum of heights: " + list.stream().mapToDouble(i -> i.getH()).sum() + '\n';
        }
        else
            return "The list is empty." + '\n';
    }
}