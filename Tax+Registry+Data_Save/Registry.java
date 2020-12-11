import java.util.ArrayList;
import java.util.Calendar;

public class Registry {

    public static Registry registry = new Registry();

    public static ArrayList<Property> properties;

    private Registry() {
        properties = new ArrayList<Property>();
        readCSV();
    }

    public void writeCSV() {
        SerialisationSurrogate.serialiseStorage(properties, (Calendar.getInstance().get(Calendar.YEAR) + ".csv"));
    }

    public void readCSV() {
        properties = SerialisationSurrogate.deserialiseStorage(Calendar.getInstance().get(Calendar.YEAR) + ".csv");
    }

    public ArrayList<Property> getPreviousYearData(double year) {
        return SerialisationSurrogate.deserialiseStorage(year + ".csv");
    }

    public static Registry getInstance() {
        return registry;
    }

    public void registerNewProperty(Property p) {
        properties.add(p);
    }
}