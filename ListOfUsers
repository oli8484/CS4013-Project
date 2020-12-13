/**
 * Class for ListOfUsers
 *
 * @author (Robert Mooney)
 * @version (1)
 */
import java.util.ArrayList;

public class ListOfUsers {
	
	private static ArrayList<String[]> userList;
	
	public static void main(String[] args) {
		userList = new ArrayList<String[]>();
		
		String[] u1 = { "John Doe", "123456789" };
		String[] u2 = { "Alan Smith", "qwertyuiop" };
		String[] u3 = { "Vince Hogan", "password123" };
		String[] u4 = { "Adam Walker", "zxcvbnm" };
		userList.add(u1);
		userList.add(u2);
		userList.add(u3);
		userList.add(u4);
		
		csvhandler.serialiseStorage(userList, "List_Of_Users.csv");
	}
	
	public void addUser(String[] o) {
		userList.add(o);
	}
	
	public ArrayList<String[]> getOwners() {
		return userList;
	}
	
}
