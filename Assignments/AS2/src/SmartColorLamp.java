import java.time.LocalDateTime;

public class SmartColorLamp extends SmartLamp{
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

    public int getColorBaseHex() {
        return colorBaseHex;
    }

    public void setColorBaseHex(int colorBaseHex) {
        if (colorBaseHex >= 0x000000 && colorBaseHex <= 0xFFFFFF) {
            this.colorBaseHex = colorBaseHex;
            colorMode = true;
        } else {
            ThrowException.colorCodeOutOfBounds();
        }
    }

    @Override
    public void setKelvinVal(int kelvinVal) {
        super.setKelvinVal(kelvinVal);
        colorMode = false;
    }
}
