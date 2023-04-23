import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Subclass of SmartDevice. Has ability to hold storage usage information and can be switched "On" or "Off".
 */
public class SmartCamera extends SmartDevice {
    private final double megaBytePerMinute;
    private double storageUsage;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Constructor of Smart Camera.
     * @param name Name of the device.
     * @param megaBytePerMin Megabyte value that is used from storage per minute.
     * @param currentTime Current time of the device.
     */
    public SmartCamera(String name, double megaBytePerMin, LocalDateTime currentTime) {
            super(name, currentTime);
            this.megaBytePerMinute = megaBytePerMin;
        }

    /**
     * Constructor of Smart Camera.
     * @param name Name of the device.
     * @param status Status of the device.
     * @param megaBytePerMin Megabyte value that is used from storage per minute.
     * @param currentTime Current time of the device.
     */
    public SmartCamera(String name, String status, double megaBytePerMin, LocalDateTime currentTime) {
            super(name, status, currentTime);
            this.megaBytePerMinute = megaBytePerMin;
            if (status.equals("On"))
                startTime = currentTime;
    }

    /**
     * Implementation of the abstract method in Smart Device. If device is switched "On" sets to start time switch time.
     * If device is switched "Off" sets end time to switch time.
     */
    public void _switch() {
        if (getStatus().equals("Off")) {
            setStatus("On");
            startTime = getSwitchTime();
        } else {
            setStatus("Off");
            endTime = getSwitchTime();
            calculateStorageUsage();
        }
        setSwitchTime(null);

    }

    /**
     * Implementation of the abstract method in Smart Device. If device is switched "On" sets to start time current time.
     * If device is switched "Off" sets end time to current time. Throws sameStatus exception if wanted switch status is
     * same of the device's status.
     * @param status Status to switch.
     */
    public void _switch(String status) {
        if (getStatus().equals(status)) {
            System.out.println("Same status.");
        } else {
            setStatus(status);
            if (status.equals("Off")) {
                endTime = getCurrentTime();
                calculateStorageUsage();
            }
            else
                startTime = getCurrentTime();
            setSwitchTime(null);
        }
    }

    /**
     * @return String representation of the Smart Camera.
     */
    public String toString() {
        return String.format("Smart Camera %s is %s and used %.2f MB of storage so far (excluding current status), " +
                "and its time to switch its status is %s.", getName(), getStatus().toLowerCase(),
                storageUsage, (getSwitchTime() == null) ? "null" : getSwitchTime().format(formatter));
    }

    /**
     * Calculate elapsed time by calculating the seconds between start time and end time. Seconds are divided by 60 to
     * convert it to minutes.
     * @return elapsed time in minutes.
     */
    public double calculateElapsedTime() {
        return (double) ChronoUnit.SECONDS.between(startTime, endTime) / 60;
    }

    /**
     * Calculate the storage usage by multiplying elapsed time with megabytes per minute.
     */
    public void calculateStorageUsage() {
        double elapsedTime = calculateElapsedTime();
        startTime = null;
        endTime = null;
        storageUsage += megaBytePerMinute * elapsedTime;
    }
}
