import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;

public class AskMore {
    public ObjectOutputStream out;
    public ObjectInputStream in;
    public AskMore(ObjectInputStream sin, ObjectOutputStream sout){
        in = sin; out = sout;
    }
    public String askInput(String fname, String ermsg, String que, int type, int idc) throws Exception { //this method is used by the speak method for asking for additional inputs
        String inp = "";
        String inv = "";
        while (true) {
            boolean corr = false;
            if (!que.contentEquals("") && idc < 2)
                out.writeObject(ermsg+inv+que);
            else
                out.writeObject("");
            int c = 0;
            Object obj = null;
            while(c==0){
                try {obj = in.readObject(); c=1;}
                catch (Exception e){return e.toString();}
            }
            inp = Command.class.cast(obj).getName();
            switch (type) {
                case 0: try {Integer.parseInt(inp);} catch (Exception e){if (inp.contentEquals("") && idc >= 1) break; corr = true;} break;
                case 1: try {Long.parseLong(inp);} catch (Exception e){if (inp.contentEquals("") && idc >= 1) break; corr = true;} break;
                case 2: try {Float.parseFloat(inp);} catch (Exception e){if (inp.contentEquals("") && idc >= 1) break; corr = true;} break;
                case 3: try {Double.parseDouble(inp);} catch (Exception e){if (inp.contentEquals("") && idc >= 1) break; corr = true;} break;
                case 4: if (inp.length() == 0){if (inp.contentEquals("") && idc >= 1) break; corr = true;} break;
                case 5: try {
                    LocalDate.parse(inp);} catch (Exception e){if (inp.contentEquals("") && idc >= 1) break; corr = true;} break;
                case 6: try {
                    LocalTime.parse(inp);} catch (Exception e){if (inp.contentEquals("") && idc >= 1) break; corr = true;} break;
                case 7: if (!inp.toLowerCase().contentEquals("red") &&
                        !inp.toLowerCase().contentEquals("blue") &&
                        !inp.toLowerCase().contentEquals("green") &&
                        !inp.toLowerCase().contentEquals("yellow") &&
                        !inp.toLowerCase().contentEquals("brown")
                ) {if (inp.contentEquals("") && idc >= 1) break; corr = true;} break;
            } //parsing the input depending on the input type, looping asking again if an exception is caught
            if (!corr) break; // breaking the loop if no exception is caught
            else
            if (idc < 2 && !(inp.contentEquals("") && idc == 1))
                inv = "Invalid input.\n";
            else
                inv = fname + ": Invalid input.\n";
            ermsg = "";
        }
        if (inp.contentEquals("")) {
            switch (type){ //in case the value has to be null, set it to the smallest possible
                case 0: inp = Integer.toString(Integer.MIN_VALUE); break;
                case 1: inp = Long.toString(Long.MIN_VALUE); break;
                case 2,3: inp = "NaN"; break;
                case 5: inp = "1970-01-01"; break;
                case 6: inp = "00:00:00"; break;
                case 7: inp = "blue"; break; // yeah idk
            }
        }
        return inp;
    }
}
