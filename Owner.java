
/**
 * Owner of the property
 *
 * @author (Oliver Nagy)
 * @version (1)
 */
import java.util.ArrayList;
public class Owner extends User {

    private SerialisationSurrogate properties; // ListOfProperties 
    public Owner(String name, String password) {
        super(name, password);
        properties = new SerialisationSurrogate();
    }

    public void addPropertyToSystem(Property p) {
        properties.addProperty(p);
    }

    public ArrayList<Property> getOwnedProperties() {
        return properties.getPropertiesByOwner(getName(), getPassword());
    }
}