public class SmartPlug extends SmartDevice{
    private static int defaultVolts = 220;
    public void setAmpereVal(double ampereVal) {
        if (ampereVal > 0)
            this.ampereVal = ampereVal;
        else {
            System.out.println("ERROR: Ampere value must be a positive number!");
            setIsErroneous(true);
        }
    }
    private double ampereVal;
    public SmartPlug(String name) {
        super(name);
    }

    public SmartPlug(String name, String initStatus) {
        super(name, initStatus);
    }

    public SmartPlug(
            String name, String initStatus, double ampereVal) {
        super(name, initStatus);
        setAmpereVal(ampereVal);
    }

    public void Switch(String status) {
    }
}
