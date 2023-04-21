import java.time.LocalDateTime;

public class SmartColorLamp extends SmartLamp{
    public int getColorBaseHex() {
        return colorBaseHex;
    }

    public void setColorBaseHex(int colorBaseHex) {
        this.colorBaseHex = colorBaseHex;
    }

    private boolean colorMode = false;
    private int colorBaseHex;
    public SmartColorLamp(String name, LocalDateTime currentTime) {
        super(name, currentTime);
    }

    public SmartColorLamp(String name, String initStatus, LocalDateTime currentTime) {
        super(name, initStatus, currentTime);
    }

    public SmartColorLamp(String name, String initStatus, int kelvinVal, int brightness, LocalDateTime currentTime) {
        super(name, initStatus, kelvinVal, brightness, currentTime);
    }

    public SmartColorLamp(String name, String initStatus,
                          String hexCodeStr, int brightness, LocalDateTime currentTime) {
        super(name, initStatus, currentTime);
        colorBaseHex = Integer.decode(hexCodeStr);
        setBrightness(brightness);
        colorMode = true;
    }
}
