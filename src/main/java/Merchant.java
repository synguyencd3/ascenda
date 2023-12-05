import org.json.simple.JSONObject;

public class Merchant {
    private long id;
    private String name;
    public double distance;

    Merchant (JSONObject merchant)
    {
        this.id = (long) merchant.get("id");
        this.name = (String) merchant.get("name");
        this.distance = (double) merchant.get("distance");
    }
}
