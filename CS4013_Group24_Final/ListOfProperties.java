/**
 * Class for ListOfProperty
 *
 * @author (Robert Mooney)
 * @version (1)
 */
import java.util.ArrayList;

public class ListOfProperties {
	
	private static ArrayList<String[]> propertyList;
	
	public static void main(String[] args) {
		propertyList = new ArrayList<String[]>();
		
		String[] pro1 = { "John Doe", "21 West Lane", "A64 F3S4", "250000.50", "Village", "false" };
		String[] pro2 = { "Alan Smith", "12 Red Avenue", "K84 J2H1", "300000", "City", "false" };
		String[] pro3 = { "Vince Hogan", "7 Green", "G34 S2A1", "260000.75", "City", "true" };
		propertyList.add(pro1);
		propertyList.add(pro2);
		propertyList.add(pro3);
		
		csvhandler.serialiseStorage(propertyList, "List_Of_Properties.csv");
	}
	
}
