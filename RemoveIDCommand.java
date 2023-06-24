import java.util.ArrayList;

public class RemoveIDCommand implements CommandRunner{
    public RemoveIDCommand(){}

    @Override
    public String execute(Command com, ArrayList<Person> list) {
        int id = 0;
        try {id = Integer.parseInt(com.getArg(0));}
        catch (Exception e){return "Incorrect parameter.";}
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getID() == id) {
                list.remove(i);
                return "Success!" + '\n';
            }
        }
        return "There's no element with this ID." + '\n';
    }
}

