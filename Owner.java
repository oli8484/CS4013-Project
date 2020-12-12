
/**
 * Owner of the property
 * 
 * @author (Oliver Nagy)
 * @version (1)
 */
import java.util.ArrayList;
public class Owner extends User {

    private ListOfProperties properties; // ListOfProperties 
    public Owner(String name, String password) {
        super(name, password);
        properties = new ListOfProperties();
    }

    public void addPropertyToSystem(Property p) {
        properties.addProperty(p);
    }

    public ArrayList<Property> getOwnedProperties() {
        return properties.getPropertiesByOwner(getName(), getPassword());
    }
}