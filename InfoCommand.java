import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class InfoCommand{
    public InfoCommand(){}

    public String execute(Command com, ArrayList<Person> list, java.util.Date init) {
        String rep = "Type: ArrayList\n";
        SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        rep += "Creation date/time: " + form.format(init) + '\n';
        rep += "List length: " + list.size() + '\n';
        return rep;
    }
}
