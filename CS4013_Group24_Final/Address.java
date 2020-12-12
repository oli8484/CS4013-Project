
// @author Oliver Nagy
public class Address {

    private String firstLine;
    private String secondLine;
    private String city;
    private String county;
    private String country;
    private String locationType;
    private Eircode_RoutingKey eircode;

    public Address(String firstLine, String secondLine, String city, String county,
                   String country, String locationType, Eircode_RoutingKey eircode){

        this.eircode = eircode;
        this.firstLine = firstLine;
        this.secondLine = secondLine;
        this.city = city;
        this.county = county;
        this.country = country;
        this.locationType = locationType;
    }

    public Eircode_RoutingKey getEircode() {
        return eircode;
    }

    public String getFirstLine() {
        return firstLine;
    }

    public String getSecondLine() {
        return secondLine;
    }

    public String getCity() {
        return city;
    }

    public String getCounty() {
        return county;
    }

    public String getCountry() {
        return country;
    }

    public String getLocationType(){
        return locationType;
    }

}