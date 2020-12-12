/** 
 * @author Piotr Kramkowski
 */
import java.util.ArrayList;
import java.util.Calendar;

public class Registry {

    public static Registry registry = new Registry();

    Property empty = new Property("", "", "", 0, "", false);

    // Temporaty storage of current year's CSV file in memory
    public static ArrayList<Property> properties;

    // Methods used to register a new property
    private Registry() {
        properties = new ArrayList<Property>();
        readCSV();
    }

    public static Registry getInstance() {
        return registry;
    }

    public void registerNewProperty(Property p) {
        properties.add(p);
    }

    // Write temporary memory storage of properties into current year's CSV
    public void writeCSV() {
        SerialisationSurrogate.serialiseStorage(properties, (Calendar.getInstance().get(Calendar.YEAR) + ".csv"));
    }

    // Copy CSV file for current year into memory
    public void readCSV() {
        properties = SerialisationSurrogate.deserialiseStorage(Calendar.getInstance().get(Calendar.YEAR) + ".csv");
    }

    /**
     * Read specified year's CSV file and return an ArrayList of properties
     * 
     * @param year Name of file to read from (files are named by years)
     * @return ArrayList of properties for specified year
     */
    public ArrayList<Property> getPreviousYearData(double year) {
        return SerialisationSurrogate.deserialiseStorage(year + ".csv");
    }

    // copies data from previous year's CSV to current
    public void copyPreviousYearToCurrent() {
        ArrayList<Property> temp = new ArrayList<Property>();
        temp = SerialisationSurrogate.deserialiseStorage((Calendar.getInstance().get(Calendar.YEAR) - 1) + ".csv");
        SerialisationSurrogate.serialiseStorage(temp, (Calendar.getInstance().get(Calendar.YEAR) + ".csv"));
    }

    /**
     * Look for a property that contains the input address If returns a property
     * with empty fields, no match was found
     * 
     * @param address Address to scan for
     * @return property object with provided address
     */
    public Property getPropertyByAddress(String address) {
        for (int i = 0; i < properties.size(); i++) {
            if (properties.get(i).getAddress().equalsIgnoreCase(address)) {
                return properties.get(i);
            }
        }
        return empty;
    }
}