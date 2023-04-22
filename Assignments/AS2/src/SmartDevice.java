import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Parent class of every other smart device.
 */
public abstract class SmartDevice {
    private LocalDateTime currentTime;
    private LocalDateTime switchTime;
    private String status = "Off";
    private String name;
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(LocalDateTime currentTime) {
        this.currentTime = currentTime;
    }

    public LocalDateTime getSwitchTime() {
        return switchTime;
    }

    public void setSwitchTime(LocalDateTime switchTime) {
        this.switchTime = switchTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SmartDevice(String name, LocalDateTime currentTime) {
        this.name = name;
        this.currentTime = currentTime;
    }

    public SmartDevice(String name, String status, LocalDateTime currentTime) {
        this.name = name;
        this.status = status;
        this.currentTime = currentTime;
    }

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

    public abstract void _switch(String status);
    public abstract void _switch();
}
