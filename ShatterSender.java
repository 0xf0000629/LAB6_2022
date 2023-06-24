import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class ShatterSender { // receives commands from the ClientInterface and breaks them up
    // forms a command object and sends them to the DataManager on the server
    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    public String name = "";
    public ShatterSender(){};

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new ObjectOutputStream(clientSocket.getOutputStream());
        in = new ObjectInputStream(clientSocket.getInputStream());
    }
    public void stopConnection() throws IOException{
        in.close();
        out.close();
        clientSocket.close();
    }
    public String sendCommand(Command com) throws Exception{
        out.writeObject(com);
        String resp = String.class.cast(in.readObject());
        return resp;
    }
    public String speak(String data, String nfile){
        String command = "";
        String pars = "";
        String response = "";
        int sp1 = data.indexOf(' ');
        if (sp1 == -1) {
            if (data.length() == 0) {response = "No command was received.\n"; return response;}
            else {command = data;}
        } else {
            command = data.substring(0, sp1);
            pars = data.substring(sp1 + 1);
        }
        String[] splited = pars.split("\\s+");
        List<String> args = Arrays.asList(splited);
        Command comm = new Command(name, command, args, nfile);
        try {response = sendCommand(comm);}
        catch (IOException e){return "NOCON";}
        catch (ClassNotFoundException e){return "Couldn't understand the response.";}
        catch (Exception e){return "IDK what happened.";}
        return response;
    }
}
