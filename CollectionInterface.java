import java.io.*;
import java.util.ArrayList;
import java.net.*;
import java.util.Arrays;

/*
if (inp.contentEquals("save")) inp += ' ' + savefile;
                String outp = rec.speak(inp, name);
                if (inp.contains("execute_script")) {
                    if (!outp.contentEquals("-"))
                        outp = fileread(rec, outp, args);
                    else
                        outp = "No file name was provided.";
                }
                if (outp == "over") {
                    return "over";
                } else if (outp == "ERRPARSE") return "The script " + name + " contains errors.\n";
                else System.out.printf(outp);
*/

public class CollectionInterface {
    public static ShatterSender cons = null;
    public static ArrayList<String> running = new ArrayList<>();
    public static String fileread(ShatterSender rec, String name, String[] args) throws IOException { //reads the commands form the specified file, can call itself if files reference other files
        boolean ex = false;
        int addup = 0;
        String inp = "", username = args[0], ip = args[1]; int port = Integer.parseInt(args[2]);
        BufferedReader re = null;
        try {
            re = new BufferedReader(new InputStreamReader(new FileInputStream(name)));
            if(running.indexOf(name) != -1) {return "Script " + name + " is already being executed.\n";}
            else running.add(name);
        }
        catch (Exception e){
            return "The file " + name + " doesn't exist.\n";
        }
        try {
            inp = re.readLine();
            while (inp != null) {
                if (inp.contentEquals("add") || inp.contains("update")) addup = 1;
                String outp = "";
                if (addup < 2) {
                    try {
                        rec.startConnection(ip, port);
                    } catch (Exception e) {
                        int pong = ping_cycle(rec, ip, port, re);
                        if (pong == 1) break;
                        else continue;
                    }
                    if (addup == 1)
                        addup = 2;
                }
                outp = rec.speak(inp, name);
                if (outp.contentEquals("Element received.\n")) {addup = 0; continue;}
                //else
                //if (addup==2 && !outp.contentEquals("")) {addup = 0; continue;}
                if (outp.contentEquals("NOCON")){
                    int pong = ping_cycle(rec, ip, port, re);
                    if (pong == 1) break;
                    else continue;
                }
                if (inp.contains("execute_script")) {
                    if (!outp.contentEquals("-"))
                        outp = fileread(rec, outp, args);
                    else
                        outp = "No file name was provided.\n";
                }
                if (inp.contentEquals("exit")){System.out.printf("Exiting...\n"); rec.stopConnection(); return "exit\n";}
                else if (outp.contentEquals("ERRPARSE")) System.out.printf("The script " + name + " contains errors.\n");
                else System.out.printf(outp);
                //System.out.printf("file addup: " + String.valueOf(addup)+'\n');
                inp = re.readLine();
            }
            re.close();
        }
        catch (Exception e) {
            ex = true;
            return "The file " + name + " is not accessible.\n";
        }
        if (!ex) {
            running.remove(name);
            return "Script " + name + " executed successfully!\n";
        }
        else {return "Couldn't execute script " + name + "...\n";}
    }
    public static int ping_cycle(ShatterSender rec, String ip, int port, BufferedReader reader){
        String inp = "y";
        int fail = 0;

        while (true) {
            try {System.out.printf("No connection. Try and restore? (Y/N)\n"); inp = reader.readLine();}
            catch (Exception e){System.out.printf("Can't read the user reply.\n");}
            if (inp.contentEquals("y")) {
                try {rec.stopConnection();} catch (Exception e2) {}
                try {rec.startConnection(ip, port);}catch (Exception e){
                    fail = 1;
                }
                String pong = rec.speak("ping","");
                if (pong.contentEquals("pong\n")) {fail = 0;break;}
            } else {fail = 1;break;}
            if (fail == 0){break;}
        }
        return fail;
    }
    public static void main(String[] args) throws IOException { //reads the commands from the terminal
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inp = "", ip = args[0]; int port = Integer.parseInt(args[1]);
        String exec = "";
        String username = "", password = ""; boolean log = false;
        for (int i = 0; i < args.length; i++){
            if (args[i].contentEquals("-exec") && i+1 < args.length)
                exec = args[i+1];
            if (args[i].contentEquals("-login") && i+2 < args.length) {
                username = args[i+1];
                password = args[i+2]; log = true;
            }
        }

        cons = new ShatterSender();
        int addup = 0;
        int loggedin = 0;
        while (true) {
            if (!exec.contentEquals("")) {inp = "execute_script " + exec; exec = "";}
            else inp = reader.readLine();
            if (inp.contentEquals("add") || inp.contains("update")) addup = 1;
            if (inp.contentEquals("save")) inp += ' ' + args[0];
            String outp = "";
            if (addup < 2) {
                try {
                    cons.startConnection(ip, port);
                } catch (Exception e) {
                    int pong = ping_cycle(cons, ip, port, reader);
                    if (pong == 1) break;
                    else {System.out.printf("Reconnected!\n"); continue;}
                }
                if (addup == 1)
                addup = 2;
            }
            if (loggedin == 1 || inp.startsWith("exit") || inp.startsWith("help") || inp.startsWith("login") || inp.startsWith("register"))
                outp = cons.speak(inp, "");
            else{
                System.out.printf("You are not logged in!\n");
                continue;
            }
            if (outp.contains("Added element")) {addup = 0;}
            if (outp.contains("Updated element")) {addup = 0;}

            if (outp.contains("Logged in!")) {loggedin = 1; cons.name = inp.split("\\s+")[1]; continue;}
            if (outp.contains("Registered!")) {loggedin = 1; cons.name = inp.split("\\s+")[1]; continue;}

            if (outp.contains("Error:") && addup == 2) {addup = 0;}
            if (outp.contentEquals("NOCON")){
                int pong = ping_cycle(cons, ip, port, reader);
                if (pong == 1) break;
                else continue;
            }
            String filearg[] = {username, args[0], args[1]};
            if (inp.contains("execute_script")) {
                if (!outp.contentEquals("-"))
                    outp = fileread(cons, outp, filearg);
                else
                    outp = "No file name was provided.\n";
            }
            if (inp.contentEquals("exit")){System.out.printf("Exiting..."); cons.stopConnection(); break;}
            else System.out.printf(outp);
            //System.out.printf("normal addup: " + String.valueOf(addup)+'\n');
        }
    }
}