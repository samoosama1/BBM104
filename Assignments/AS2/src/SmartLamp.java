import java.time.LocalDateTime;

/**
 * Subclass of Smart Device. Has ability to be switched "On" or "Off", its kelvin values, brightness values can be set.
 */
public class SmartLamp extends SmartDevice{
    private int kelvinVal = 4000;
    private int brightness = 100;

    /**
     * @return Kelvin value of the device.
     */
    public int getKelvinVal() {
        return kelvinVal;
    }

    /**
     * Sets the kelvin value of the device. Throws kelvinOutOfBounds if it is out ouf the boundaries.
     * @param kelvinVal Kelvin value to set.
     */
    public void setKelvinVal(int kelvinVal) {
        if (kelvinVal >= 2000 && kelvinVal <= 6500)
            this.kelvinVal = kelvinVal;
        else {
            ThrowException.kelvinOutOfBounds();
        }
    }

    /**
     * @return Brightness value of the device.
     */
    public int getBrightness() {
        return brightness;
    }

    /**
     * Sets the brightness value of the device. Throws brightnessOutOfBounds if it is out of boundaries.
     * @param brightness Brightness to set.
     */
    public void setBrightness(int brightness) {
        if (brightness >= 0 && brightness <= 100)
            this.brightness = brightness;
        else {
            ThrowException.brightnessOutOfBounds();
        }
    }

    /**
     * Constructor of the Smart Lamp.
     * @param name Name of the device.
     * @param currentTime Current time of the system.
     */
    public SmartLamp(String name, LocalDateTime currentTime) {
        super(name, currentTime);
    }

    /**
     * Constructor of the Smart Lamp.
     * @param name Name of the device.
     * @param initStatus Initial status of the device.
     * @param currentTime Current time of the system.
     */
    public SmartLamp(String name, String initStatus, LocalDateTime currentTime) {
        super(name, initStatus, currentTime);
    }

    /**
     *
     * @param name Name of the device.
     * @param initStatus Initial status of the device.
     * @param kelvinVal Kelvin value of the device.
     * @param brightness Brightness value of the device.
     * @param currentTime Current time of the system.
     */
    public SmartLamp(String name, String initStatus, int kelvinVal, int brightness, LocalDateTime currentTime) {
        super(name, initStatus, currentTime);
        setKelvinVal(kelvinVal);
        setBrightness(brightness);
    }

    /**
     * @return String representation of the Smart Lamp.
     */
    public String toString() {
        return String.format("Smart Lamp %s is %s and its kelvin value is %dK with %d%% brightness, and its " +
                "time to switch its status is %s.", getName(), getStatus().toLowerCase(), kelvinVal, brightness,
                (getSwitchTime() == null) ? "null" : getSwitchTime().format(formatter));
    }

    /**
     * Implementation of the abstract method in Smart Device. Switches the status of the device.
     */
    public void _switch() {
        if (getStatus().equals("Off")) {
            setStatus("On");
        } else {
            setStatus("Off");
        }
        setSwitchTime(null);
    }

    /**
     * Implementation of the abstract method in Smart Device. Switches the status of the device to given status.
     * @param status Status to switch.
     */
    public void _switch(String status) {
        if (getStatus().equals(status)) {
            System.out.println("Same status.");
        } else {
            setStatus(status);
            setSwitchTime(null);
        }
    }
}
