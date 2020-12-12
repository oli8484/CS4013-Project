
/** 
 * @author Piotr Kramkowski
 */
import java.util.ArrayList;

public class Tax {

    private double[] simulatedTaxRates = new double[4];
    private double[] simulatedLevyRates = new double[5];

    /**
     * Takes a property and returns the tax to be paid in the current year
     * 
     * @param p Property to calculate tax on
     * @return Tax to be paid on the given property
     */
    public static double getTaxForProperty(Property p) {
        return p.getTax();
    }

    /**
     * Takes the name of a owner and returns an arraylist of properties owned by the
     * owner
     * 
     * @param owner Name of owner of properties
     * @return ArrayList<Property> of properties owned by the given owner
     */
    public static ArrayList<Property> getPropertiesByOwner(String owner) {
        ArrayList<Property> x = new ArrayList<Property>();

        for (int i = 0; i < Registry.properties.size(); i++) {
            if (Registry.properties.get(i).getOwnerName().equalsIgnoreCase(owner)) {
                x.add(Registry.properties.get(i));
            }
        }
        return x;
    }

    /**
     * Takes an eircode routing key and returns an arraylist of properties within
     * the area
     * 
     * @param eircode Eircode routing key
     * @return ArrayList<Property> of properties within the area of the given
     *         eircode routing key
     */
    public static ArrayList<Property> getPropertiesByEircodeRoutingKey(String eircode) {
        ArrayList<Property> x = new ArrayList<Property>();

        for (int i = 0; i < Registry.properties.size(); i++) {
            if (Registry.properties.get(i).getEircode().substring(0, 3).equals(eircode)) {
                x.add(Registry.properties.get(i));
            }
        }
        return x;
    }

    /**
     * Sets inputs as simulated tax rates
     * 
     * @param a Tax bracket Up to 150,000
     * @param b Tax bracket 150,000 - 400,000
     * @param c Tax bracket 400,001 - 650,000
     * @param d Tax bracket Above 650,000
     */
    public void setSimultedTaxRates(double a, double b, double c, double d) {
        simulatedTaxRates[0] = a;
        simulatedTaxRates[1] = b;
        simulatedTaxRates[2] = c;
        simulatedTaxRates[3] = d;
    }

    /**
     * Sets inputs as simulated levy rates
     * 
     * @param a Levy rate for "City"
     * @param b Levy rate for "Large town"
     * @param c Levy rate for "Small town"
     * @param d Levy rate for "Village"
     * @param e Levy rate for "Countryside"
     */
    public void setSimulatedLevyRates(double a, double b, double c, double d, double e) {
        simulatedLevyRates[0] = a;
        simulatedLevyRates[1] = b;
        simulatedLevyRates[2] = c;
        simulatedLevyRates[3] = d;
        simulatedLevyRates[4] = e;
    }

    /**
     * Tax calculation based on simulated rates
     * 
     * @param p Input property for tax calculations based on simulated rates and
     *          levies
     * @return Tax calculated based on on simulated rates and levies
     */
    public double calculateTaxBasedOnSimulatedRates(Property p) {
        double calculatedTax = 100;
        double remainder = p.getEstimatedMarketValue();
        int iteration = 0;
        for (; iteration < (p.getEstimatedMarketValue() / 150000 + 1) && iteration < 4; iteration++) {
            calculatedTax = calculatedTax + (150000 * simulatedTaxRates[iteration]);
            remainder = remainder - 150000;
        }
        calculatedTax = calculatedTax + (remainder / simulatedTaxRates[iteration - 1]);

        String[] places = { "City", "Large town", "Small town", "Village", "Countryside" };
        for (int i = 0; i < places.length; i++) {
            if (p.getLocationCategory().equalsIgnoreCase(places[i])) {
                calculatedTax = calculatedTax + (simulatedLevyRates[i]);
            }
        }
        if (p.getPrincipalPrivateResidence() == false) {
            calculatedTax = calculatedTax + 100;
        }
        return calculatedTax;
    }
}
