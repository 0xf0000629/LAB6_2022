import java.util.ArrayList;

public class RemovePassCommand implements CommandRunner{
    public RemovePassCommand(){}

    @Override
    public String execute(Command com, ArrayList<Person> list) {
        String id = "";
        try {id = com.getArg(0);}
        catch (Exception e){return "Incorrect parameter.";}
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getPID() == id) {
                list.remove(i);
                return "Success!" + '\n';
            }
        }
        return "There's no element with this passport ID." + '\n';
    }
}

