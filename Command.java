import java.io.Serializable;
import java.util.List;

public class Command implements Serializable {
    private String is;
    private String user;
    private List <String> args;
    private String file = "";
    public Command(String u, String name, List<String> arguments, String filename){
        user = u;
        is = name;
        args = arguments;
        file = filename;
    }
    public void setName(String name){is = name;}
    public int setArg(String argument, int id){
        try{args.add(id, argument);}
        catch(Exception e) {return -1;}
        return id;
    }
    public void setFile(String name){file = name;}
    public int argsSize(){return args.size();}
    public String getName(){return is;}
    public String getUser(){return user;}
    public String getFile(){return file;}
    public String getArg(int id){
        try {
            String argu = args.get(id);
            return argu;
        }
        catch (Exception e){return null;}
    }
}