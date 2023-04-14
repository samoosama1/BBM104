/**
 * Parent class of every other smart device.
 */
public abstract class SmartDevice {
    public boolean getIsErroneous() {
        return thisObjectIsErroneous;
    }

    public void setIsErroneous(boolean IsErroneous) {
        thisObjectIsErroneous = IsErroneous;
    }

    private boolean thisObjectIsErroneous = false;
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
