import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Main {

    public static JSONObject readFile(String filename) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("input.json"));
         return (JSONObject) obj;
    }
    public static void main(String[] args) throws IOException {
        //Read input and does initialzation
        JSONObject offers;
        try {
            offers = readFile("input.json");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        System.out.println(offers);
        for (JSONObject i; i))
        {

        }
    }
}