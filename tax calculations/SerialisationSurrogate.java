import java.util.ArrayList;
import java.io.*;

public class SerialisationSurrogate {
    public static final String file = "out.csv";

    /**
     * Takes an ArrayList<Property> and saves to csv
     * 
     * @param array ArrayList to save.
     */
    public static void serialiseStorage(ArrayList<Property> array) {
        try {
            // Create the BufferedWriter.
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));

            // Write each Property as a separate line in the csv file
            for (Property s : array) {
                writeLine(bw, s);
            }
            bw.flush();
            bw.close();

        } catch (IOException i) {
            System.out.println(i.toString());
        }
    }

    // Helper for serialiseStorage()
    private static void writeLine(BufferedWriter bw, Property line) throws IOException {
        write(bw, line.getOwnerName());
        write(bw, line.getAddress());
        write(bw, line.getEircode());
        write(bw, line.getEstimatedMarketValue());
        write(bw, line.getLocationCategory());
        writeLast(bw, line.getPrincipalPrivateResidence());

        bw.newLine();
    }

    // Helper for serialiseStorage()
    private static void write(BufferedWriter bw, Object write) throws IOException {

        // Changable seperator character
        final String separator = ",";

        StringBuffer tempString = new StringBuffer();

        tempString.append(write);
        tempString.append(separator);
        tempString.append(" ");

        bw.write(tempString.toString(), 0, tempString.toString().length());
    }

    // Helper for serialiseStorage()
    private static void writeLast(BufferedWriter bw, Object write) throws IOException {

        StringBuffer gary = new StringBuffer();

        gary.append(write);

        bw.write(gary.toString(), 0, gary.toString().length());
    }

    /**
     * Read csv file and save to ArrayList
     * 
     * @return ArrayList<Property>.
     */
    public static ArrayList<Property> deserialiseStorage() {
        ArrayList<Property> x = new ArrayList<Property>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            BufferedReader fr = new BufferedReader(new FileReader(file));

            int lines = 0;
            while (fr.readLine() != null)
                lines++;
            fr.close();

            // Add a new Property to the ArrayList for each line that we have in the file
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
    private static Property read(String line) {
        String[] splitLine = line.split(", ", 0);
        Property s = new Property(splitLine[0], splitLine[1], splitLine[2], Double.parseDouble(splitLine[3]),
                splitLine[4], Boolean.parseBoolean(splitLine[5]));

        s.setTax(Tax.calculateTax(s));
        return s;
    }
}
