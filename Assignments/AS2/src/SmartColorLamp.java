public class SmartColorLamp extends SmartLamp{
    public int getColorBaseHex() {
        return colorBaseHex;
    }

    public void setColorBaseHex(int colorBaseHex) {
        this.colorBaseHex = colorBaseHex;
    }

    private int colorBaseHex;
    public SmartColorLamp(String name) {
        super(name);
    }

    public SmartColorLamp(String name, String initStatus) {
        super(name, initStatus);
    }

    public SmartColorLamp(String name, String initStatus, int kelvinVal, int brightness) {
        super(name, initStatus, kelvinVal, brightness);
    }

    public SmartColorLamp(String name, String initStatus, String hexCodeStr, int brightness) {
        super(name, initStatus);
        colorBaseHex = Integer.decode(hexCodeStr);
        setBrightness(brightness);
    }
}
