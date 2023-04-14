public class SmartLamp extends SmartDevice{
    public int getKelvinVal() {
        return kelvinVal;
    }

    public void setKelvinVal(int kelvinVal) {
        if (kelvinVal >= 0 && kelvinVal <= 2000)
            this.kelvinVal = kelvinVal;
        else {
            System.out.println("ERROR: Kelvin value must be in range of 2000K-6500K!");
            setIsErroneous(true);
        }

    }

    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    private int kelvinVal = 4000;
    private int brightness = 100;
    public SmartLamp(String name) {
        super(name);
    }

    public SmartLamp(String name, String initStatus) {
        super(name, initStatus);
    }

    public SmartLamp(String name, String initStatus, int kelvinVal, int brightness) {
        super(name, initStatus);
        setKelvinVal(kelvinVal);
        setBrightness(brightness);
    }

    public void Switch(String status) {
        System.out.println("Mom");
    }
}
