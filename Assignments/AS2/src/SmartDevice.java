/**
 * Parent class of every other smart device.
 */
public abstract class SmartDevice {
    public String getStatus() {
        return status;
    }

    private String status;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public SmartDevice(String name) {
        this.name = name;
    }

    public SmartDevice(String name, String status) {
        this.name = name;
        this.status = status;
    }

    public abstract void Switch(String status);
}
