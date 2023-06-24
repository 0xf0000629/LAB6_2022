import java.util.ArrayList;

public class ShowCommand implements CommandRunner{
    public ShowCommand(){}

    @Override
    public String execute(Command com, ArrayList<Person> list) {
        try{
            int id = 0;
            try {id = Integer.parseInt(com.getArg(0));}
            catch (Exception e){return "Incorrect parameter.\n";}
            return list.get(id).getStringRep()+'\n';
        }
        catch(Exception e){return "Failed to locate the record.\n";}
    }
}
