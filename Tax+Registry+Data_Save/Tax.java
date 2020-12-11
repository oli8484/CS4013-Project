import java.util.ArrayList;

public class Tax {

    private double[] simulatedTaxRates = new double[4];
    private double[] simulatedLevyRates = new double[4];

    // Takes a property and returns the tax to be paid in the current year
    public static double getTaxForProperty(Property p) {
        return p.getTax();
    }

    // Takes a name of owner and returns an arraylist of properties owned
    public static ArrayList<Property> getPropertiesByOwner(String owner) {
        ArrayList<Property> x = new ArrayList<Property>();

        for (int i = 0; i < Registry.properties.size(); i++) {
            if (Registry.properties.get(i).getOwnerName().equalsIgnoreCase(owner)) {
                x.add(Registry.properties.get(i));
            }
        }
        return x;
    }

    public void setSimultedTaxRates(double a, double b, double c, double d) {
        simulatedTaxRates[0] = a;
        simulatedTaxRates[1] = b;
        simulatedTaxRates[2] = c;
        simulatedTaxRates[3] = d;
    }

    public void setSimulatedLevyRatesdouble(double a, double b, double c, double d) {
        simulatedLevyRates[0] = a;
        simulatedLevyRates[1] = b;
        simulatedLevyRates[2] = c;
        simulatedLevyRates[3] = d;
    }

    // Tax calculation based on simulated rates
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
