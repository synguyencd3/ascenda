import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Offer {
    private long id;
    private String title;
    private String description;
    private long category;
    private Merchant closetMerchant;
    private LocalDate validTo;

    public long getCategory() {
        return category;
    }

    public LocalDate getValidTo() {
        return validTo;
    }

    public double getClosetDistance()
    {
        return this.closetMerchant.getDistance();
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

    public Offer Compare(Offer offer)
    {
        if (this.closetMerchant.getDistance()<offer.closetMerchant.getDistance())
            return this;
        else 
            return offer;
    }

    public JSONObject toJSON()
    {
        JSONObject jo = new JSONObject();
        jo.put("id", this.id);
        jo.put("title", this.title);
        jo.put("description", this.description);
        jo.put("category", this.category);
        jo.put("merchants", this.closetMerchant.toJSON());
        jo.put("valid_to", this.validTo);
        return jo;
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
