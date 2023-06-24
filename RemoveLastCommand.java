import java.util.ArrayList;

public class RemoveLastCommand implements CommandRunner{
    public RemoveLastCommand(){}

    @Override
    public String execute(Command com, ArrayList<Person> list) {
        if (list.size() > 0) {
            list.remove(list.size()-1);
            return "Success!" + '\n';
        }
        else
            return "The list is empty." + '\n';
    }
}