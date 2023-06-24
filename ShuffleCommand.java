import java.util.ArrayList;

public class ShuffleCommand implements CommandRunner{
    public ShuffleCommand(){}

    public String execute(Command com, ArrayList<Person> list) {
        if (list.size() > 0) {
            java.util.Collections.shuffle(list);
            return "Success!" + '\n';
        }
        else
            return "The list is empty." + '\n';
    }
}