// @author Oliver Nagy
public class Eircode_RoutingKey {

    private String eircode;

    public Eircode_RoutingKey()
    {
     eircode = ""; 
    }
    public boolean fromString(String eircodeString) {
        String noSpaces = eircodeString.toUpperCase().replace(" ", "");
        if (noSpaces.length() != 7) {
            return false;
        }
        this.eircode =noSpaces;
        return true;
    }

    public String getEircode() {
        return eircode;
    }

    public String getRoutingKey() {
        return eircode.substring(0, 3);
    }

    public String getUniqueId() {
        return eircode.substring(3, 7);
    }
}