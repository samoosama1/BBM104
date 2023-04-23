import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Subclass of SmartDevice. Has ability to hold energy consumption information and can be switched "On" or "Off".
 * Can be plugged in and out.
 */
public class SmartPlug extends SmartDevice{
    private static final int defaultVolts = 220;
    private boolean pluggedIn = false;
    private double ampereVal;
    private double energyConsumption;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Sets the ampere value of the device. Throws ampereOutOfBounds exception if ampere value is out of boundaries.
     * @param ampereVal Ampere value to set.
     */
    public void setAmpereVal(double ampereVal) {
        if (ampereVal > 0) {
            this.ampereVal = ampereVal;
            pluggedIn = true;
        } else {
            ThrowException.ampereOutOfBounds();
        }
    }

    /**
     * Constructor of the SmartPlug.
     * @param name Name of the device.
     * @param currentTime Current time of the system.
     */
    public SmartPlug(String name, LocalDateTime currentTime) {
        super(name, currentTime);
    }

    /**
     * Constructor of the SmartPlug.
     * @param name Name of the device.
     * @param initStatus Initial status of the device.
     * @param currentTime Current time of the system.
     */
    public SmartPlug(String name, String initStatus, LocalDateTime currentTime) {
        super(name, initStatus, currentTime);
    }

    /**
     * Constructor of the SmartPlug
     * @param name Name of the device.
     * @param initStatus Initial status of the device.
     * @param ampereVal Ampere value of the device.
     * @param currentTime Current time of the system.
     */
    public SmartPlug(
            String name, String initStatus, double ampereVal, LocalDateTime currentTime) {
        super(name, initStatus, currentTime);
        this.ampereVal = ampereVal;
        pluggedIn = true;
        if (initStatus.equals("On"))
            startTime = currentTime;
    }

    /**
     * @return String representation of Smart Plug.
     */
    public String toString() {
        return String.format("Smart Plug %s is %s and consumed %.2fW so far (excluding current device), and its time to"
                       + " switch its status is %s.", getName(), getStatus().toLowerCase(),
                                                        energyConsumption, (getSwitchTime() == null) ? "null" :
                                                        getSwitchTime().format(formatter));
    }

    /**
     * Calculates the energy consumption between start time and end time. Adds calculated value to energy consumption
     * variable.
     */
    public void calculateEnergyConsumption() {
        double elapsedTime = calculateElapsedTime();
        startTime = null;
        endTime = null;
        energyConsumption += ampereVal * defaultVolts * elapsedTime;
    }

    /**
     * @return Total energy consumption of the device.
     */
    public double getEnergyConsumption() {
        return energyConsumption;
    }

    /**
     * Implements the abstract method from Smart Device. If the device if plugged in and switched on, sets start time to
     * current time. Else sets end time to current time. Throws sameStatus if status is same as before.
     * @param status Status to switch.
     */
    @Override
    public void _switch(String status) {
        if (getStatus().equals(status))
            ThrowException.sameStatus(status);
        else {
            setStatus(status);
            if (status.equals("On") && pluggedIn)
                startTime = getCurrentTime();
            else if (status.equals("Off") && pluggedIn) {
                endTime = getCurrentTime();
                calculateEnergyConsumption();
            }
            setSwitchTime(null);
        }
    }

    /**
     * Implements the abstract method from Smart Device. If the device if plugged in and switched on, sets start time to
     * switch time. Else sets end time to switch time.
     */
    @Override
    public void _switch() {
        if (getStatus().equals("On")) {
            if (pluggedIn) {
                endTime = getSwitchTime();
                calculateEnergyConsumption();
                setStatus("Off");
            }
        }
        else {
            setStatus("On");
            if (pluggedIn)
                startTime = getSwitchTime();
        }
        setSwitchTime(null);
    }

    /**
     * Calculates elapsed time between start time and end time.
     * @return Elapsed time in hours.
     */
    public double calculateElapsedTime() {
        return (double) ChronoUnit.SECONDS.between(startTime, endTime) / 3600;
    }

    /**
     * Plug in a device to Plug. If device was on, set start time to current time. If another device is already plugged
     * into the Plug, throws alreadyPluggedIn exception.
     * @param ampereVal Ampere value of the device.
     */
    public void plugIn(double ampereVal) {
        if (pluggedIn) {
            ThrowException.alreadyPluggedIn();
        } else {
            setAmpereVal(ampereVal);
            if (getStatus().equals("On") && pluggedIn)
                startTime = getCurrentTime();
        }
    }

    /**
     * Plug out a device from the Plug. If device was on, set end time to current time. Calculate the energy consumption
     * afterward. Throw alreadyPluggedOut exception if there was already no devices plugged into the Plug.
     */
    public void plugOut() {
        if (!pluggedIn)
            ThrowException.alreadyPluggedOut();
        else {
            pluggedIn = false;
            if (getStatus().equals("On")) {
                endTime = getCurrentTime();
                calculateEnergyConsumption();
            }
        }
    }
}