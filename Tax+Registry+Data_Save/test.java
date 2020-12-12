
/** 
 * @author Piotr Kramkowski
 */
import java.util.ArrayList;

public class test {
    // This is only a test class (do not use)
    public static void main(String[] args) {

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