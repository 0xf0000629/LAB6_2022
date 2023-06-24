import javax.xml.crypto.Data;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.ArrayList;

public class DataManager {
    private ArrayList <Person> list;
    private ArrayList <String> rec;
    private java.util.Date init;
    public AskMore asktool;

    public DataManager(){
        Date date = new Date();
        init = date;
        list = new ArrayList<Person>();
        rec = new ArrayList<String>();
    }
    public DataManager get(){
        return this;
    }
    public void updateAsker(AskMore askMore){
        asktool = askMore;
    }
    private String toXML(){
        SimpleDateFormat formcd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String xmls = "<?xml version=" + '"' + "1.0" + '"' + " encoding=" + '"' + "UTF-8" + '"' + "?>\n";
        xmls += "<listData>\n";
        xmls += "\t<dateofCreation>" + formcd.format(init) + "</dateofCreation>\n";
        xmls += "\t<listSize>" + Integer.toString(list.size()) + "</listSize>\n";
        xmls += "</listData>\n";
        xmls += "<peopleList>\n";
        for (int i = 0; i<list.size();i++) xmls += list.get(i).getXML(1);
        xmls += "</peopleList>\n";
        return xmls;
    }
    private String save(String namefile){
        try {
            BufferedOutputStream buf = new BufferedOutputStream(new FileOutputStream(namefile));
            buf.write(toXML().getBytes());
            buf.close();
        }
        catch (Exception ex){
            return "Somehow the file is unavailable.\n";
        }
        File tem = new File("tmp.dat");
        if (namefile != "tmp.dat"){try{tem.delete();}
        catch (Exception e){return "Data saved, couldn't delete TEMP.\n";}}
        return "Data saved.";
    }
    public String restore(){
        String data = "";
        File tem = new File("tmp.dat");
        if (tem.exists()){
            try {
                Command comload = new Command("server", "load", Arrays.asList("tmp.dat"), "");
                new LoadDatabase().load(comload, list, init);
            }
            catch (Exception e){
                return "Couldn't restore the unsaved data.\n";
            }
            return "Data restored.";
        }
        else
            return "Nothing to restore.\n";
    }
    public String savelist(){
        String data = "";
        File tem = new File("tmp.dat");
        try {
            save("tmp.dat");
            return "";
        }
        catch (Exception e){
            return "Couldn't save changes into TEMP.\n";
        }
    }
    public String runcommand(Command com){
        try {Double.parseDouble(com.getName()); return "";} catch (Exception e) {}
        switch (com.getName()){
            case "test": return new TestCommand().execute(com, list);
            case "show": return new ShowCommand().execute(com, list);
            case "load": return new LoadDatabase().load(com, list, init);
            case "add","update": return new AddUpdater().execute(com, list, asktool);
            case "showall": return new ShowAllCommand().execute(com, list);
            case "help": return new HelpCommand().execute(com, list);
            case "clear": return new ClearCommand().execute(com, list);
            case "info": return new InfoCommand().execute(com, list, init);
            case "sort":return new SortCommand().execute(com, list);
            case "shuffle":return new ShuffleCommand().execute(com, list);
            case "remove_by_id":return new RemoveIDCommand().execute(com, list);
            case "remove_last":return new RemoveLastCommand().execute(com, list);
            case "remove_by_passport_id":return new RemovePassCommand().execute(com, list);
            case "sum_of_heights":return new SumHeightCommand().execute(com, list);
            case "save":{return save(com.getArg(0));}
            case "askme": return new TestAsker().execute(asktool);
            case "ping": return "pong\n";
            case "execute_script": return com.getArg(0);
        }
        return "Couldn't recognise the command: " + com.getName() + '\n';
    }
}
