public class Eircode_RoutingKey {

    private String eircode;

    public Eircode_RoutingKey()
    {
     eircode = ""; 
    }
    public boolean fromString(String eircodeString) {
        String eircodeNoSpaces = eircodeString.toUpperCase().replace(" ", "");
        if (eircodeNoSpaces.length() != 7) {
            return false;
        }
        this.eircode = eircodeNoSpaces;
        return true;
    }

    public String geteircode() {
        return eircode;
    }

    public String getRoutingKey() {
        return eircode.substring(0, 3);
    }

    public String getUniqueId() {
        return eircode.substring(3, 7);
    }
}