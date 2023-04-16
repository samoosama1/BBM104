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

    public SmartColorLamp(String name, String initStatus, int kelvinOrColorCode, int brightness) {
        super(name, initStatus);
        setBrightness(brightness);
    }
}
