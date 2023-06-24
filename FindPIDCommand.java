import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class FindPIDCommand implements CommandRunner {
    public FindPIDCommand(){}
    public String execute(Command com, ArrayList<Person>list){
        String pid = com.getArg(0);
        for (int h = 0; h < list.size(); h++){
            if (pid.contentEquals(list.get(h).getPID())) {
                List <String> arg = Collections.singletonList(""+list.get(h).getID());
                Command show = new Command("","", arg, "");
                return "Found a record: " + new ShowCommand().execute(show, list) + '\n';
            }
        }
        return "No people found with this passport ID.\n";
    }
}
