/**
 * @author Piotr Kramkowski
 */
class Property {
    private String ownerName;
    private String address;
    private String eircode;
    private double estimatedMarketValue;
    private String locationCategory;
    private boolean principalPrivateResidence;
    private double tax;

    // Get and Set methods
    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String name) {
        this.ownerName = name;
    }

    public boolean getPrincipalPrivateResidence() {
        return principalPrivateResidence;
    }

    public void setPrincipalPrivateResidence(boolean principalPrivateResidence) {
        this.principalPrivateResidence = principalPrivateResidence;
    }

    public String getLocationCategory() {
        return locationCategory;
    }

    public void setLocationCategory(String locationCategory) {
        this.locationCategory = locationCategory;
    }

    public double getEstimatedMarketValue() {
        return estimatedMarketValue;
    }

    public void setEstimatedMarketValue(double estimatedMarketValue) {
        this.estimatedMarketValue = estimatedMarketValue;
    }

    public String getEircode() {
        return eircode;
    }

    public void setEircode(String eircode) {
        this.eircode = eircode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Constructor
    public Property(String ownerName, String address, String eircode, double estimatedMarketValue,
            String locationCategory, boolean principalPrivateResidence) {
        this.ownerName = ownerName;
        this.address = address;
        this.eircode = eircode;
        this.estimatedMarketValue = estimatedMarketValue;
        this.locationCategory = locationCategory;
        this.principalPrivateResidence = principalPrivateResidence;
        this.tax = calculateTax(estimatedMarketValue, locationCategory, principalPrivateResidence);
    }

    /**
     * Tax calculation without yearly compounding
     * 
     * @param estimatedMarketValue
     * @param locationCategory
     * @param principalPrivateResidence
     * @return Tax calculated based on given data
     */
    public double calculateTax(double estimatedMarketValue, String locationCategory,
            boolean principalPrivateResidence) {
        double calculatedTax = 100;
        double[] taxPercentages = { 0, 0.01, 0.02, 0.04 };
        double remainder = estimatedMarketValue;
        int iteration = 0;
        for (; iteration < (estimatedMarketValue / 150000 + 1) && iteration < 4; iteration++) {
            calculatedTax = calculatedTax + (150000 * taxPercentages[iteration]);
            remainder = remainder - 150000;
        }
        calculatedTax = calculatedTax + (remainder / taxPercentages[iteration - 1]);

        String[] places = { "City", "Large town", "Small town", "Village", "Countryside" };
        int[] placeTax = { 100, 80, 60, 50, 25 };
        for (int i = 0; i < places.length; i++) {
            if (locationCategory.equalsIgnoreCase(places[i])) {
                calculatedTax = calculatedTax + (placeTax[i]);
            }
        }
        if (principalPrivateResidence == false) {
            calculatedTax = calculatedTax + 100;
        }
        return calculatedTax;
    }

    // For testing
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("ownerName: " + ownerName + " ");
        sb.append("address: " + address + " ");
        sb.append("eircode: " + eircode + " ");
        sb.append("estimatedMarketValue: " + estimatedMarketValue + " ");
        sb.append("locationCategory: " + locationCategory + " ");
        sb.append("principalPrivateResidence: " + principalPrivateResidence + " ");

        return sb.toString();
    }

}