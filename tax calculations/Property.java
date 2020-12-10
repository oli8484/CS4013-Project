
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
        this.tax = 0;
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