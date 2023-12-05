import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;



public class Main {

    public static JSONObject ReadFile(String filename) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("input.json"));
         return (JSONObject) obj;
    }

    public static LocalDate ReadUserInput(){

        Scanner scanner = new Scanner(System.in);
        LocalDate date;
        while (true)
        {
            try {
                System.out.print("Enter a date (yyyy-mm-dd): ");
                String dateString = scanner.nextLine();
                date = LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE);
                System.out.println("Input Date: " + date);
                break;
            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter a date in the format yyyy-mm-dd.");
            }
        }
        scanner.close();
        return date;
    }

    public static void main(String[] args) throws IOException {
        
        LocalDate date = ReadUserInput();
        LocalDate checkInAfter5Days = date.plusDays(5);
        //Read input file and do initialzation
        JSONObject input;
        try {
            input = ReadFile("input.json");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        // Process input
        JSONArray offers = (JSONArray) input.get("offers");
        List<Offer> OffersList = new ArrayList<Offer>();
        for (int i=0;i<offers.size();i++)
        {
            Offer offer =  new Offer((JSONObject) offers.get(i));
            if (offer.getValidTo().isBefore(checkInAfter5Days)) continue;
            OffersList.add(offer);
        }



    }
}