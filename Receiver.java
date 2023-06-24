import javax.xml.crypto.Data;
import java.net.*;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Receiver {
    public static ServerSocket serverSocket;
    public static Socket clientSocket;
    public static ObjectOutputStream out;
    public static ObjectInputStream in;

    public static void start(int port) throws IOException{
        serverSocket = new ServerSocket(port);
    }
    public static int connect(){
        try{
            clientSocket = serverSocket.accept();
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
        }catch (Exception e){return 1;}
        return 0;
    }

    public static void stop() throws IOException{
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }
    public static void main(String[] args) {
        Receiver server = new Receiver();
        DataManager mom = new DataManager();
        System.out.printf(mom.runcommand(new Command("server", "load", Arrays.asList("dtb.xml"), "")));
        System.out.printf(mom.restore());
        try {
            server.start(Integer.parseInt(args[0]));
        }
        catch (Exception e){
            try {
                server.start(Integer.parseInt(args[0]));
            }
            catch (Exception e2) {
                System.out.printf("Can't start the server.\n");
                return;
            }
        }
        while (true){ //listening
            try {
                Command com = null;
                try{com = Command.class.cast(in.readObject());}
                catch (Exception e){out.writeObject("That was not a command.\n");}
                String user = com.getUser();
                System.out.printf("User " + user + " sent a command.\n");
                mom.updateAsker(new AskMore(in, out));
                String resp = mom.runcommand(com);
                out.writeObject(resp);
                System.out.printf("Responded to " + user + ".\n");
                System.out.printf(mom.savelist());
                while (connect() == 1){}
            }
            catch (Exception e){
                while (connect() == 1){}
            }
        }
    }
}
