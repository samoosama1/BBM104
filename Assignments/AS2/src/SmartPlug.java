import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class SmartPlug extends SmartDevice{
    private static final int defaultVolts = 220;
    private boolean pluggedIn = false;
    private double ampereVal;
    private double energyConsumption;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public void setAmpereVal(double ampereVal) {
        if (ampereVal > 0) {
            this.ampereVal = ampereVal;
            pluggedIn = true;
        } else {
            ThrowException.ampereOutOfBounds();
        }
    }

    public SmartPlug(String name, LocalDateTime currentTime) {
        super(name, currentTime);
    }

    public SmartPlug(String name, String initStatus, LocalDateTime currentTime) {
        super(name, initStatus, currentTime);
    }

    public SmartPlug(
            String name, String initStatus, double ampereVal, LocalDateTime currentTime) {
        super(name, initStatus, currentTime);
        this.ampereVal = ampereVal;
        pluggedIn = true;
    }


    public String toString() {
        return String.format("Smart Plug %s is %s and consumed %f so far (excluding current device), and its time to"
                       + " switch its status is %s", getName(), getStatus().toLowerCase(),
                                                        energyConsumption, getStatus());
    }

    public void calculateEnergyConsumption() {
        double elapsedTime = calculateElapsedTime();
        startTime = null;
        endTime = null;
        energyConsumption += ampereVal * defaultVolts * elapsedTime;
    }

    public double getEnergyConsumption() {
        return energyConsumption;
    }

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

    public double calculateElapsedTime() {
        return (double) ChronoUnit.SECONDS.between(startTime, endTime) / 3600;
    }

    public void plugIn(double ampereVal) {
        if (pluggedIn) {
            ThrowException.alreadyPluggedIn();
        } else {
            setAmpereVal(ampereVal);
            pluggedIn = true;
            if (getStatus().equals("On"))
                startTime = getCurrentTime();
        }
    }

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