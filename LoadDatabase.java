import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class LoadDatabase{
    public LoadDatabase(){}
    public String load(Command com, ArrayList<Person> list, java.util.Date init){
        BufferedReader re = null;
        list.clear();
        String namefile = com.getArg(0);
        try {
            try {
                re = new BufferedReader(new InputStreamReader(new FileInputStream(namefile)));
            }
            catch (Exception e){
                return "The load up file is inaccessible.\n";
            }

            String str = re.readLine(); str = re.readLine(); str = re.readLine();
            String crdate = str.substring(str.indexOf("<dateofCreation>") + 16, str.lastIndexOf("</dateofCreation>"));
            SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            init = form.parse(crdate);
            str = re.readLine();
            int len  = Integer.parseInt(str.substring(str.indexOf("<listSize>") + 10, str.lastIndexOf("</listSize>")));
            str = re.readLine();str = re.readLine();
            for (int i = 0; i < len; i++){
                str = re.readLine();
                int idd  = Integer.parseInt(str.substring(str.indexOf("<person id=" + '"') + 12, str.lastIndexOf('"' + ">")));
                str = re.readLine();

                String namei = str.substring(str.indexOf("<name>") + 6, str.lastIndexOf("</name>"));
                str = re.readLine();

                String coords  = str.substring(str.indexOf("<coordinates>") + 13, str.lastIndexOf("</coordinates>"));
                Double xx = Double.parseDouble(coords.substring(0, coords.indexOf(' ')));

                int yy = Integer.parseInt(coords.substring(coords.indexOf(' ')+1,coords.length()));

                str = re.readLine();
                String crdatei = str.substring(str.indexOf("<creationDate>") + 14, str.lastIndexOf("</creationDate>"));
                SimpleDateFormat form2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date creaDate = form2.parse(crdatei);
                str = re.readLine();

                Float hh = Float.parseFloat(str.substring(str.indexOf("<height>") + 8, str.lastIndexOf("</height>")));
                str = re.readLine();

                String bdate = str.substring(str.indexOf("<dateofBirth>") + 13, str.lastIndexOf("</dateofBirth>"));
                bdate = bdate.replace(' ', 'T');
                LocalDateTime bdDate = LocalDateTime.parse(bdate);
                str = re.readLine();

                String pid  = str.substring(str.indexOf("<passportID>") + 12, str.lastIndexOf("</passportID>"));
                str = re.readLine();

                String eyecol  = str.substring(str.indexOf("<eyeColor>") + 10, str.lastIndexOf("</eyeColor>"));
                Color coli = Color.BLUE;
                if (eyecol.toLowerCase().contentEquals("red"))coli = Color.RED;
                if (eyecol.toLowerCase().contentEquals("green"))coli = Color.GREEN;
                if (eyecol.toLowerCase().contentEquals("yellow"))coli = Color.YELLOW;
                if (eyecol.toLowerCase().contentEquals("brown"))coli = Color.BROWN;
                if (eyecol.toLowerCase().contentEquals("blue"))coli = Color.BLUE;
                str = re.readLine(); str = re.readLine();

                double lx = Double.parseDouble(str.substring(str.indexOf("<locX>") + 6, str.lastIndexOf("</locX>")));
                str = re.readLine();
                Long ly = Long.parseLong(str.substring(str.indexOf("<locY>") + 6, str.lastIndexOf("</locY>")));
                str = re.readLine();
                Long lz = Long.parseLong(str.substring(str.indexOf("<locZ>") + 6, str.lastIndexOf("</locZ>")));
                str = re.readLine();str = re.readLine();

                list.add(new Person(idd, namei, new Coordinates(xx, yy), creaDate,  hh, bdDate, pid, coli, new Location(lx, ly, lz)));
            }
            re.close();
        }
        catch (Exception e){
            try {re.close();}
            catch (Exception e2){}
            return "The load up file contains errors.\n";
        }
        return "Loaded up successfully!\n";
    }
}
