import java.time.LocalDateTime;
import java.util.ArrayList;

public class CountLessBirthCommand implements CommandRunner{
    public CountLessBirthCommand(){}

    @Override
    public String execute(Command com, ArrayList<Person> list) {
        LocalDateTime bd = null;
        try {bd = LocalDateTime.parse(com.getArg(0));}
        catch (Exception e){return "Incorrect parameter.";}
        if (list.size() > 0) {
            int hei = 0;
            for (int h = 0; h < list.size(); h++){
                if (bd.compareTo(list.get(h).getBD()) < 0) hei++;
            }
            return "Amount of birthdays: " + hei + '\n';
        }
        else
            return "The list is empty." + '\n';
    }
}