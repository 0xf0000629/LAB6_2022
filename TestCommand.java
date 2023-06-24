import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestCommand {
    public TestCommand(){}
    public String execute(Command com, ArrayList<Person> list){
        String resp = "Command name: " + com.getName() + "\nArguments: ";
        for (int h = 0; h < com.argsSize()-1; h++){
            resp += com.getArg(h) + ", ";
        }
        resp += com.getArg(com.argsSize()-1) + "\n";
        return resp;
    }
}
