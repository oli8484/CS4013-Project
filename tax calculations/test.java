import java.util.ArrayList;

public class test {
    // This is only a test class (do not use)
    public static void main(String[] args) {
        Property adc = new Property("Johnny", "somewhere", "numbers", 23242356, "City", true);
        Property cda = new Property("Jimmy", "nowhere", "digits", 273649, "Village", false);
        ArrayList<Property> arrayOne = new ArrayList<Property>();
        arrayOne.add(adc);
        arrayOne.add(cda);

        ArrayList<Property> arrayTwo = new ArrayList<Property>();

        SerialisationSurrogate.serialiseStorage(arrayOne);
        arrayTwo = SerialisationSurrogate.deserialiseStorage();

        System.out.println("------------------");
        System.out.println(arrayTwo.toString());

    }
}
