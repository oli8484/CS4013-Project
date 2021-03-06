import java.util.ArrayList;

public class testing {
    static ArrayList<String[]> array = new ArrayList<String[]>();
    static ArrayList<String[]> array2 = new ArrayList<String[]>();

    public static void main(String[] args) {

        // This loads the csv file "testing" from the default file path into an
        // arraylist array2
        array2 = csvhandler.deserialiseStorage("testing");

        for (int i = 0; i < array2.size(); i++) {
            String[] x = array2.get(i);
            for (int j = 0; j < x.length; j++) {
                System.out.print(array2.get(i)[j]);
            }
            System.out.println("");
        }
    }

    public static void test(String[] args) {

        String[] temp1 = { "gg", "wp", "ez" };
        String[] temp2 = { "a", "b", "c" };
        array.add(temp1);
        array.add(temp2);

        // This saves the arraylist "array" into csv called testing in the default file
        // path
        csvhandler.serialiseStorage(array, "testing");
    }
}