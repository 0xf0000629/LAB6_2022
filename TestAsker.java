import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestAsker {
    public TestAsker(){}
    public String execute(AskMore asker){
        String resp = "Your reply: ";
        try {
            resp += asker.askInput("","", "Say hi!", 4, 0) + "\n";
        }
        catch (IOException e){
            resp = "ERRPARSE";
        }
        catch (ClassNotFoundException e){
            resp = "Class breaks.";
        }
        catch (Exception e){
            resp = e.toString();
        }
        return resp;
    }
}

