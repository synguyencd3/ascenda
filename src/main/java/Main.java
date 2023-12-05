import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;



public class Main {

    public static JSONObject readFile(String filename) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("input.json"));
         return (JSONObject) obj;
    }
    public static void main(String[] args) throws IOException {
        //Read input and does initialzation
        JSONObject input;
        try {
            input = readFile("input.json");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        JSONArray offers = (JSONArray) input.get("offers");
        List<Offer> OffersList = new ArrayList<Offer>();
        for (int i=0;i<offers.size();i++)
        {
            OffersList.add( new Offer((JSONObject) offers.get(i)) );
        }
    }
}