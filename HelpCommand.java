import java.util.ArrayList;

public class HelpCommand implements CommandRunner {
    public void HelpCommand(){}
    public String execute(Command com, ArrayList <Person> list){
        String rep = "";
        rep += "help: get a list of available commands.\n";
        rep += "info: get the list info (list type, initialization date, amount of people, etc.)\n";
        rep += "show: get the string representation of every person.\n";
        rep += "add <element>: add a person to the list (additional inputs will be requested).\n";
        rep += "update id <element>: update a person's record by their id (additional inputs will be requested).\n";
        rep += "remove_by_id <id>: remove a person's record by ID.\n";
        rep += "clear: clear the list.\n";
        rep += "save: save the list into the file, specified on load.\n";
        rep += "execute_script <file_name>: execute a script from a file.\n";
        rep += "exit: exit the program (without saving).\n";
        rep += "remove_last: remove the last person's record from the list.\n";
        rep += "shuffle: shuffle the records.\n";
        rep += "sort: sort the records by id.\n";
        rep += "remove_any_by_passport_i_d <passportID>: remove a person's record by a passport ID.\n";
        rep += "sum_of_height: get a sum of people's heights.\n";
        rep += "count_less_than_birthday <birthday>: get the amount of people with birthdays earlier than specified (in YYYY-MM-DD HH:MM:SS format).\n";
        return rep;
    }
}
