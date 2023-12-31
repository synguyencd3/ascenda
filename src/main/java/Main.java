import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {

    static final int Restaurant = 1;
    static final int Retail = 2;
    static final int Hotel = 3;
    static final int Activity = 4;

    public static JSONObject ReadFileInput(String filename) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(filename));
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

    public static Map<Long, Offer> filterOffer(JSONArray offers, LocalDate checkInAfter5Days)
    {
        Map<Long, Offer> map = new HashMap<Long, Offer>();
        for (int i=0;i<offers.size();i++)
        {
            Offer offer =  new Offer((JSONObject) offers.get(i));
            if (offer.getValidTo().isBefore(checkInAfter5Days) || offer.getCategory()==Hotel) continue;
            if (map.putIfAbsent(offer.getCategory(), offer)!=null)
            {
                Offer closetDistanceOffer = map.get(offer.getCategory());
                map.put(offer.getCategory(), offer.Compare(closetDistanceOffer));
            } 
        }
        return map;
    }

    public static void write(List<Offer> offerList)
    {
        JSONObject output = new JSONObject();
        JSONArray arr = new JSONArray();
        for (int i=0;i<=1;i++)
        {
            try {
                Offer offer = offerList.get(i);
                arr.add(offer.toJSON());
            }
            catch (Exception e)
            {
                continue;
            }
        }
        output.put("offers",arr);

        try {
         FileWriter file = new FileWriter("output.json");
         file.write(output.toJSONString());
         file.close();
      } catch (IOException e) {
         e.printStackTrace();
      }
    }

    public static void main(String[] args) {
        
        LocalDate date = ReadUserInput();
        LocalDate checkInAfter5Days = date.plusDays(5);
        //Read input file and do initialzation
        JSONObject input;
        try {
            input = ReadFileInput("input.json");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
             throw new RuntimeException(e);
        }
        // Process input
        JSONArray offers = (JSONArray) input.get("offers");
        Map<Long, Offer> map = filterOffer(offers, checkInAfter5Days);
        List<Offer> offerList = new ArrayList<Offer>(map.values());
        offerList.sort(new OfferComparator());
        
        //Write
        write(offerList);
    }
}