import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class AddUpdater{
    public AddUpdater(){};
    public int nextfreeID(ArrayList<Person> list){
        ArrayList <Integer> ids = new ArrayList<Integer>();
        for (int i=0;i<list.size();i++){
            ids.add(list.get(i).getID());
        }
        ids.sort(new IntegerComparator());
        for (int i=0;i<ids.size();i++){
            if (i+1 != ids.get(i)){
                return i+1;
            }
        }
        return ids.size()+1;
    }
    public String execute(Command com, ArrayList <Person> list, AskMore asker){
        String nfile = com.getFile();
        String response = "";
        try {
            int id = 0, oops = 0;
            String errstr = "";
            if (com.getName().contentEquals("add"))
                if (list.size() > 0) {
                    id = nextfreeID(list);
                } else id = 1;
            else
                try {id = Integer.parseInt(com.getArg(0));}
                catch (Exception oof) {oops = 1;}
            int file = 0; if (!nfile.contentEquals("")) file = 1;
            System.out.printf("Requesting element...\n");
            String name = asker.askInput(nfile,"","Input name: ", 4, 0 + 2*file);
            name.replaceAll("\n",Character.toString((char)92) + 'n');
            name.replaceAll("\s",Character.toString((char)92) + 's');
            System.out.printf("Name processed (" + name + ")...\n");
            double cx = Double.parseDouble(asker.askInput(nfile,"","Input the X coordinate: ", 3, 0 + 2*file));
            int cy = Integer.parseInt(asker.askInput(nfile,"","Input the Y coordinate: ", 0, 0 + 2*file));
            System.out.printf("Coordinates processed (" + cx + "," + cy + ")...\n");
            // height is unnecessary and has to be >0 so ask until one of them is true
            float hi = Float.parseFloat(asker.askInput(nfile,"","Input the height: ", 2, 1 + file));
            while (!Float.isNaN(hi) && hi < 0) {
                String err = "";
                if (file > 0){errstr+=nfile + ": Height has to be more than 0 or none!\n";}
                else{err="Height has to be more than 0 or none!\n";}
                hi = Float.parseFloat(asker.askInput(nfile,err,"Input the height: ", 2, 1 + file));
            }
            System.out.printf("Height processed (" + hi + ")...\n");
            double lx = Double.parseDouble(asker.askInput(nfile,"","Input the X coordinate of the location: ", 3, 1 + file));
            long ly = Long.parseLong(asker.askInput(nfile,"","Input the Y coordinate of the location: ", 1, 1 + file));
            long lz = Long.parseLong(asker.askInput(nfile,"","Input the Z coordinate of the location: ", 1, 1 + file));
            System.out.printf("Location coordinates processed (" + lx + ',' + ly + ',' + lz + ")...\n");
            java.time.LocalDateTime bd =
                    LocalDateTime.parse(asker.askInput(nfile,"","Input date in YYYY-MM-DD format: ", 5, 0 + 2*file)
                            + "T" + asker.askInput(nfile,"","Input time in HH:MM:SS format: ", 6, 0 + 2*file));
            System.out.printf("BDate processed (" + bd + ")...\n");
            Color col = Color.BLUE; //this will be rewritten
            String colstr = asker.askInput(nfile,"","Input an eye color (red, green, yellow, blue or brown): ", 7, 0 + 2*file);
            if (colstr.toLowerCase().contentEquals("red"))col = Color.RED;
            if (colstr.toLowerCase().contentEquals("green"))col = Color.GREEN;
            if (colstr.toLowerCase().contentEquals("yellow"))col = Color.YELLOW;
            if (colstr.toLowerCase().contentEquals("brown"))col = Color.BROWN;
            if (colstr.toLowerCase().contentEquals("blue"))col = Color.BLUE;
            System.out.printf("Color processed (" + col + ")...\n");
            String passid = asker.askInput(nfile,"","Input the passport ID: ", 4, 0 + 2*file);
            while (true) {
                int ch = new FindPID().execute(passid, list);
                if (ch == id || ch == -1) break;
                else{
                    String err = "";
                    if (file == 0) err = "This passport ID is already in use!\n";
                    else  {errstr = nfile + ": This passport ID is already in use!\n"; oops = 1; break;}

                    passid = asker.askInput(nfile,err,"Input the passport ID: ", 4, 0 + 2*file);
                }
            }
            System.out.printf("Passport ID processed (" + passid + ")...\n");
            if (oops == 0) {
                if (com.getName().contentEquals("add"))
                    list.add(new Person(id, name, new Coordinates(cx, cy), hi, bd, passid, col, new Location(lx, ly, lz)));
                else {
                    for (int loc = 0; loc < list.size(); loc++) {
                        if (list.get(loc).getID() == id) {
                            list.set(loc, new Person(id, name, new Coordinates(cx, cy), hi, bd, passid, col, new Location(lx, ly, lz)));
                            break;
                        }
                    }
                }
            }
            response = "Element received.\n";
        }
        catch (IOException e){
            response = "Incorrect user id.\n";
        }
        catch (ClassNotFoundException e){
            response = "Class break.\n";
        }
        catch (Exception e){
            response = "Unknown error.\n";
        }
        return response;
    }
}
