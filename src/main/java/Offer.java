import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Offer {
    private long id;
    private String title;
    private String description;
    private long category;
    private Merchant merchant;
    private String valid_to;

    private Merchant getClosetMerchant(JSONArray merchants)
    {
         Merchant closetMerchant = new Merchant((JSONObject) merchants.get(0));
         int index=0;
         for (int i=1; i<merchants.size();i++ )
         {
            if ( (long)  )
         }
         return null;
    }

    Offer(JSONObject offer)
    {
        this.id = (long) offer.get("id");
        this.title = (String) offer.get("title");
        this.description = (String) offer.get("description");
        this.category = (long) offer.get("category");
        this.valid_to = (String) offer.get("valid_to");
        this.merchant = getClosetMerchant((JSONArray) offer.get("merchants"));
    }
}
