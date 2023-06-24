import java.util.ArrayList;

public class ShowAllCommand implements CommandRunner{
    public ShowAllCommand(){}

    @Override
    public String execute(Command com, ArrayList<Person> list) {
        if (list.size() == 0) return "The list is empty.\n";
        String every = "";
        for (int i = 0; i < list.size(); i++)
            every += list.get(i).getStringRep() + '\n';
        return every;
    }
}