import java.time.LocalDateTime;

/**
 * Subclass of Smart Lamp. Has ability to be switched "On" or "Off", its kelvin values, brightness values and color
 * code values can be set.
 */
public class SmartColorLamp extends SmartLamp{
    private boolean colorMode = false;
    private int colorBaseHex;

    /**
     * Constructor of Smart Color Lamp.
     * @param name Name of the device.
     * @param currentTime Current time of the system.
     */
    public SmartColorLamp(String name, LocalDateTime currentTime) {
        super(name, currentTime);
    }

    /**
     * Constructor of Smart Color Lamp.
     * @param name Name of the device.
     * @param initStatus Initial status of the device.
     * @param currentTime Current time of the system.
     */
    public SmartColorLamp(String name, String initStatus, LocalDateTime currentTime) {
        super(name, initStatus, currentTime);
    }

    /**
     * Constructor of Smart Color Lamp
     * @param name Name of the device.
     * @param initStatus Initial status of the device.
     * @param kelvinVal Kelvin value of the device.
     * @param brightness Brightness value of the device.
     * @param currentTime Current time of the system.
     */
    public SmartColorLamp(String name, String initStatus, int kelvinVal, int brightness, LocalDateTime currentTime) {
        super(name, initStatus, kelvinVal, brightness, currentTime);
    }

    /**
     * Constructor of Smart Color Lamp.
     * @param name Name of the device.
     * @param initStatus Initial status of the device.
     * @param hexCodeStr Color code of the device.
     * @param brightness Brightness value of the device.
     * @param currentTime Current time of the system.
     */
    public SmartColorLamp(String name, String initStatus,
                          String hexCodeStr, int brightness, LocalDateTime currentTime) {
        super(name, initStatus, currentTime);
        colorBaseHex = Integer.decode(hexCodeStr);
        setBrightness(brightness);
        colorMode = true;
    }

    /**
     * @return Color code of the device.
     */
    public int getColorBaseHex() {
        return colorBaseHex;
    }

    /**
     * Sets the color code of the device. Throws colorCodeOutOfBounds exception if color code is not within the boundaries.
     * @param colorBaseHex Color code of the device.
     */
    public void setColorBaseHex(int colorBaseHex) {
        if (colorBaseHex >= 0x000000 && colorBaseHex <= 0xFFFFFF) {
            this.colorBaseHex = colorBaseHex;
            colorMode = true;
        } else {
            ThrowException.colorCodeOutOfBounds();
        }
    }

    /**
     * Sets the kelvin value of the device. Calls super class Smart Lamps setKelvinVal method. Sets colorMode false.
     * @param kelvinVal
     */
    @Override
    public void setKelvinVal(int kelvinVal) {
        super.setKelvinVal(kelvinVal);
        colorMode = false;
    }

    /**
     * @return String representation of Smart Color Lamp. If color mode is true, writes the color code of the device.
     * Else writes the kelvin value.
     */
    public String toString() {
        return String.format("Smart Color Lamp %s is %s and its color value is %s with %d%% brightness, and its " +
                "time to switch its status is %s.", getName(), getStatus().toLowerCase(),
                colorMode ? String.format("0x%06X", colorBaseHex) :
                String.format("%dK", getKelvinVal()), getBrightness(),
                (getSwitchTime() == null) ? "null" : getSwitchTime().format(formatter));
    }
}
