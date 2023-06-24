import java.util.ArrayList;

public class ClearCommand implements CommandRunner{
    public ClearCommand(){}

    @Override
    public String execute(Command com, ArrayList<Person> list) {
        if (list.size() > 0) {
            list.clear();
            return "Success!" + '\n';
        }
        else
            return "The list is empty." + '\n';
    }
}