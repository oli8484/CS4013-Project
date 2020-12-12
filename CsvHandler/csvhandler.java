import java.util.ArrayList;
import java.io.*;

public class csvhandler {
    public static String folderPath = "csvhandler/";

    // Get and Set methods for folder path
    public static void setFolderPath(String folderPathNew) {
        folderPath = folderPathNew;
    }

    public static String getFolderPath() {
        return folderPath;
    }

    /**
     * Takes an ArrayList<String[]> and saves to given csv file
     * 
     * @param array    ArrayList to save
     * @param fileName Name of file to store to
     */
    public static void serialiseStorage(ArrayList<String[]> array, String fileName) {
        try {
            // Create the BufferedWriter
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(folderPath + fileName)));

            // Write each Property as a separate line in the csv file
            for (String[] s : array) {
                writeLine(bw, s);
            }
            bw.flush();
            bw.close();

        } catch (IOException i) {
            System.out.println(i.toString());
        }
    }

    // Helper for serialiseStorage()
    private static void writeLine(BufferedWriter bw, String[] line) throws IOException {

        for (int i = 0; i < line.length - 1; i++) {
            write(bw, line[i]);
        }
        writeLast(bw, line[line.length - 1]);
        bw.newLine();
    }

    // Helper for serialiseStorage()
    private static void write(BufferedWriter bw, String write) throws IOException {

        // Changable seperator character
        final String separator = ",";

        StringBuffer tempString = new StringBuffer();

        tempString.append(write);
        tempString.append(separator);
        tempString.append(" ");

        bw.write(tempString.toString(), 0, tempString.toString().length());
    }

    // Helper for serialiseStorage()
    private static void writeLast(BufferedWriter bw, String write) throws IOException {

        StringBuffer gary = new StringBuffer();

        gary.append(write);

        bw.write(gary.toString(), 0, gary.toString().length());
    }

    /**
     * Read csv file and save to ArrayList
     * 
     * @return ArrayList<String[]>
     */
    public static ArrayList<String[]> deserialiseStorage(String fileName) {
        ArrayList<String[]> x = new ArrayList<String[]>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(folderPath + fileName));
            BufferedReader fr = new BufferedReader(new FileReader(folderPath + fileName));

            int lines = 0;
            while (fr.readLine() != null)
                lines++;
            fr.close();

            // Add a new String[] to the ArrayList for each line that is in the CSV file
            for (int i = 0; i < lines; i++) {
                x.add(read(br.readLine()));
            }
            br.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        return x;
    }

    // Helper for deserialiseStorage()
    private static String[] read(String line) {
        String[] splitLine = line.split(", ", 0);
        String[] s = new String[splitLine.length];
        for (int i = 0; i < s.length; i++) {
            s[i] = splitLine[i];
        }
        return s;
    }
}
