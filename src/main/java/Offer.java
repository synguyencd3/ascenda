import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Offer {
    private long id;
    private String title;
    private String description;
    private long category;
    private Merchant closetMerchant;
    private LocalDate validTo;

    public LocalDate getValidTo() {
        return validTo;
    }

    private Merchant getClosetMerchant(JSONArray merchants)
    {
         Merchant closetMerchant = new Merchant((JSONObject) merchants.get(0));

         for (int i=1; i<merchants.size();i++ )
         {
            JSONObject merchant = (JSONObject) merchants.get(i);
            if ((double) merchant.get("distance") < closetMerchant.getDistance())
            {
                closetMerchant = new Merchant((JSONObject) merchants.get(i));
            }
         }
         return closetMerchant;
    }

    Offer(JSONObject offer)
    {
        this.id = (long) offer.get("id");
        this.title = (String) offer.get("title");
        this.description = (String) offer.get("description");
        this.category = (long) offer.get("category");
        this.validTo = LocalDate.parse((String) offer.get("valid_to"), DateTimeFormatter.ISO_DATE);
        this.closetMerchant = getClosetMerchant((JSONArray) offer.get("merchants"));
    }
}
