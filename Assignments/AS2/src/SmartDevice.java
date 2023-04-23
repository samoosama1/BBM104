import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Abstract parent class of every other smart device. This class is not meant to be instantiated.
 */
public abstract class SmartDevice {
    private LocalDateTime currentTime;
    private LocalDateTime switchTime;
    private String status = "Off";
    private String name;
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");

    /**
     * @return Status of the device.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Set the status of the device.
     * @param status status to set.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return current time of the device
     */
    public LocalDateTime getCurrentTime() {
        return currentTime;
    }

    /**
     * Sets the current time of the device.
     * @param currentTime Time to set.
     */
    public void setCurrentTime(LocalDateTime currentTime) {
        this.currentTime = currentTime;
    }

    /**
     * @return Switch time of the device.
     */
    public LocalDateTime getSwitchTime() {
        return switchTime;
    }

    /**
     * Sets the switch time of the device.
     * @param switchTime Switch time to set.
     */
    public void setSwitchTime(LocalDateTime switchTime) {
        this.switchTime = switchTime;
    }

    /**
     * @return Name of the device.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the device.
     * @param name Name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Constructor of Smart Device. Note that these constructors are only executed by the subclasses as Smart Device is
     * an abstract class and is not meant to be instantiated.
     * @param name Name of the device.
     * @param currentTime Current time of the system.
     */
    public SmartDevice(String name, LocalDateTime currentTime) {
        this.name = name;
        this.currentTime = currentTime;
    }

    /**
     * Constructor of Smart Device. Note that these constructors are only executed by the subclasses as Smart Device is
     * an abstract class and is not meant to be instantiated.
     * @param name Name of the device.
     * @param status Status of the device.
     * @param currentTime Current time of the system.
     */
    public SmartDevice(String name, String status, LocalDateTime currentTime) {
        this.name = name;
        this.status = status;
        this.currentTime = currentTime;
    }

    /**
     * Sets the current time, does necessary switch actions if there is any. If device is switched, sets its switch time
     * to null.
     * @param currentTime Time to set.
     */
    public void setTime(LocalDateTime currentTime) {
        if (getSwitchTime() != null) {
            int compareVal = currentTime.compareTo(getSwitchTime());
            if (compareVal >= 0) {
                _switch();
                setSwitchTime(null);
            }
        }
        setCurrentTime(currentTime);
    }

    /**
     * Abstract method for switching. Each subclass implements these methods according to their structure. Switches the
     * device to given status.
     * @param status Status to switch.
     */
    public abstract void _switch(String status);

    /**
     * Abstract method for switching. Each subclass implements these methods according to their structure. Switches
     * device "On" if its "Off" and vice versa.
     */
    public abstract void _switch();
}
