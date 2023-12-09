import org.json.simple.JSONObject;

public class Merchant {
    private long id;
    private String name;
    private double distance;

    public double getDistance() {
        return distance;
    }

    Merchant (JSONObject merchant)
    {
        this.id = (long) merchant.get("id");
        this.name = (String) merchant.get("name");
        this.distance = (double) merchant.get("distance");
    }

    public JSONObject toJSON()
    {
        JSONObject jo = new JSONObject();
        jo.put("id", this.id);
        jo.put("name", this.name);
        jo.put("distance", this.distance);
        return jo;
    }
}
