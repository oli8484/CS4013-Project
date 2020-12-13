/**
 * Class for ListOfOwners
 *
 * @author (Robert Mooney)
 * @version (1)
 */
import java.util.ArrayList;

public class ListOfOwners {
	
	private static ArrayList<String[]> ownerList;
	
	public static void main(String[] args) {
		ownerList = new ArrayList<String[]>();
		
		String[] o1 = { "John Doe", "123456789" };
		String[] o2 = { "Alan Smith", "qwertyuiop" };
		String[] o3 = { "Vince Hogan", "password123" };
		ownerList.add(o1);
		ownerList.add(o2);
		ownerList.add(o3);
		
		csvhandler.serialiseStorage(ownerList, "List_Of_Owners");
		
	}
	
	public void addOwner(String[] o) {
		ownerList.add(o);
	}
	
	public ArrayList<String[]> getOwners() {
		return ownerList;
	}
	
}