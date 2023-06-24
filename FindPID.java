import java.util.ArrayList;

public class FindPID{
    public FindPID(){}
    public int execute(String pid, ArrayList<Person>list){
        for (int h = 0; h < list.size(); h++){
            if (pid.contentEquals(list.get(h).getPID())) {return list.get(h).getID();}
        }
        return -1;
    }
}
