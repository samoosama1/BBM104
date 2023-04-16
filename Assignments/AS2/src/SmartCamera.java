/**
 * Subclass of SmartDevice.
 */
public class SmartCamera extends SmartDevice {
    public void setMegabytePerMin(double megabytePerMin) {
        if (megabytePerMin > 0)
            this.megabytePerMin = megabytePerMin;
        else {
            System.out.println("Megabyte per minute value has to be non-negative!");
        }
    }

    private double megabytePerMin;
    public SmartCamera(String name) {
        super(name);
    }

    public SmartCamera(String name, double megabytePerMin) {
        super(name);
        setMegabytePerMin(megabytePerMin);
    }

    public SmartCamera(String name, String status, double megabytePerMin) {
        super(name, status);
        setMegabytePerMin(megabytePerMin);
    }

    public void Switch(String status) {

    }
}
