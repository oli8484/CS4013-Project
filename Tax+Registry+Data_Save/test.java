import java.util.ArrayList;

public class test {
    // This is only a test class (do not use)
    public static void main(String[] args) {
        Property adc = new Property("Johnny", "somewhere", "numbers", 23242356, "City", true);
        Property cda = new Property("Jimmy", "nowhere", "digits", 273649, "Village", false);
        ArrayList<Property> arrayOne = new ArrayList<Property>();
        arrayOne.add(adc);
        arrayOne.add(cda);

        Property bc = new Property("1", "2", "3", 23242356, "City", true);
        Property cb = new Property("1", "4", "5", 273649, "Village", false);
        ArrayList<Property> arrayTwo = new ArrayList<Property>();
        arrayTwo.add(bc);
        arrayTwo.add(cb);

        SerialisationSurrogate.serialiseStorage(arrayTwo, "2020.csv");

        System.out.println("------------------");

        // This reads the current year's CSV file to stored data that can be worked on
        Registry.getInstance().readCSV();

        // This is how you register a new property!!!
        Registry.getInstance()
                .registerNewProperty(new Property("OwnerName", "Address", "Eircode", 123456, "LocationCategory", true));
        Registry.getInstance().registerNewProperty(
                new Property("OwnerName1", "Address1", "Eircode1", 123456, "LocationCategory1", true));

        // This writes stored data to CSV file
        Registry.getInstance().writeCSV();
    }
}