import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Person implements Serializable {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Float height; //Поле может быть null, Значение поля должно быть больше 0
    private java.time.LocalDateTime birthday; //Поле не может быть null
    private String passportID; //Значение этого поля должно быть уникальным, Строка не может быть пустой, Длина строки не должна быть больше 50, Поле не может быть null
    private Color eyeColor; //Поле не может быть null
    private Location location; //Поле может быть null

    public Person(int iid, String i_name, Coordinates c, Float i_height, java.time.LocalDateTime bd, String pass, Color col, Location l){
        id = iid;
        name = i_name;
        coordinates = c;
        Date date = new Date();
        creationDate = date;
        height = i_height;
        birthday = bd;
        passportID = pass;
        eyeColor = col;
        location = l;
    }
    public Person(int iid, String i_name, Coordinates c, java.util.Date createDate, Float i_height, java.time.LocalDateTime bd, String pass, Color col, Location l){
        id = iid;
        name = i_name;
        coordinates = c;
        creationDate = createDate;
        height = i_height;
        birthday = bd;
        passportID = pass;
        eyeColor = col;
        location = l;
    }

    public String getColorStr(){ //returns a string representation of the eye color
        switch (eyeColor){
            case RED: return "red";
            case BLUE: return "blue";
            case YELLOW: return "yellow";
            case GREEN: return "green";
            case BROWN: return "brown";
            default: return "none";
        }
    }

    public String getStringRep(){ //returns a string representation of a person's record
        String full = "";
        SimpleDateFormat formcd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        DateTimeFormatter formbd = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        full += Integer.toString(id) + ": " + name
                + "; Coordinates: " + Double.toString(coordinates.getX())
                + ", " + Integer.toString(coordinates.getY())
                + "; creation date/time: " + formcd.format(creationDate);

        if (height.isNaN()) full += "; height: null";
        else full += "; height: " + Float.toString(height);

        full += "; date of birth: " + birthday.format(formbd)
                + "; Passport ID: " + passportID
                + "; eye color: " + getColorStr();

        full += "; Location: ";

        if (Double.isNaN(location.getX())) full += " X - null";
        else full += "X - " + Double.toString(location.getX());

        if (location.getY() == Long.MIN_VALUE) full += ", Y - null";
        else full += "; Y - " + Long.toString(location.getY());

        if (location.getZ() == Long.MIN_VALUE) full += ", Z - null";
        else full += "; Z - " + Long.toString(location.getZ());

        return full;
    }

    public String getXML(int tabs){ //returns an XML representation of a person's record
        String full = "";
        SimpleDateFormat formcd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter formbd = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String tab = ""; while (tabs > 0) {tabs--;tab += '\t';}
        full += tab + "<person id=" + '"' + Integer.toString(id) + '"' + ">\n"
                + tab + "\t<name>" + name + "</name>\n"
                + tab + "\t<coordinates>" + Double.toString(coordinates.getX())
                + " " + Integer.toString(coordinates.getY()) + "</coordinates>\n"
                + tab + "\t<creationDate>" + formcd.format(creationDate) + "</creationDate>\n";

        if (height.isNaN()) full += "<height>null</height>\n";
        else full += tab + "\t<height>" + Float.toString(height) + "</height>\n";

        full += tab + "\t<dateofBirth>" + birthday.format(formbd) + "</dateofBirth>\n"
                + tab + "\t<passportID>" + passportID + "</passportID>\n"
                + tab + "\t<eyeColor>" + getColorStr() + "</eyeColor>\n";

        full += tab + "\t<location>\n";

        if (Double.isNaN(location.getX())) full += tab + "\t\t<locX>null</locX>\n";
        else full += tab + "\t\t<locX>" + Double.toString(location.getX()) + "</locX>\n";

        if (location.getY() == Long.MIN_VALUE) full += tab + "\t\t<locY>null</locY>\n";
        else full += tab + "\t\t<locY>" + Long.toString(location.getY()) + "</locY>\n";

        if (location.getZ() == Long.MIN_VALUE) full += tab + "\t\t<locZ>null</locZ>\n";
        else full += tab + "\t\t<locZ>" + Long.toString(location.getZ()) + "</locZ>\n";

        full += tab + "\t</location>\n";

        full += tab + "</person>\n";

        return full;
    }

    public int getID(){return id;} //returns an ID
    public String getPID(){return passportID;} //returns an passport ID
    public Float getH(){return height;} //returns the height
    public LocalDateTime getBD(){
        return birthday;
    } //returns the birthday
}