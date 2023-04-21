import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Subclass of SmartDevice.
 */
public class SmartCamera extends SmartDevice {
    private final double megaBytePerMinute;
    private double storageUsage;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public SmartCamera(String name, double megaBytePerMin, LocalDateTime currentTime) {
            super(name, currentTime);
            this.megaBytePerMinute = megaBytePerMin;
        }

    public SmartCamera(String name, String status, double megaBytePerMin, LocalDateTime currentTime) {
            super(name, status, currentTime);
            this.megaBytePerMinute = megaBytePerMin;
    }

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

    public double calculateElapsedTime() {
        return (double) ChronoUnit.SECONDS.between(startTime, endTime) / 60;
    }

    public void calculateStorageUsage() {
        double elapsedTime = calculateElapsedTime();
        startTime = null;
        endTime = null;
        storageUsage += megaBytePerMinute * elapsedTime;
    }
}
