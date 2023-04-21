import java.time.LocalDateTime;

public class SmartLamp extends SmartDevice{
    public int getKelvinVal() {
        return kelvinVal;
    }

    public void setKelvinVal(int kelvinVal) {
        if (kelvinVal >= 0 && kelvinVal <= 2000)
            this.kelvinVal = kelvinVal;
        else {
            System.out.println("ERROR: Kelvin value must be in range of 2000K-6500K!");
        }
    }

    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
        if (brightness >= 0 && brightness <= 100)
            this.brightness = brightness;
        else {
            System.out.println("ERROR: Brightness must be in range of 0%-100%!");
        }
    }


    private int kelvinVal = 4000;
    private int brightness = 100;

    public SmartLamp(String name, LocalDateTime currentTime) {
        super(name, currentTime);
    }

    public SmartLamp(String name, String initStatus, LocalDateTime currentTime) {
        super(name, initStatus, currentTime);
    }

    public SmartLamp(String name, String initStatus, int kelvinVal, int brightness, LocalDateTime currentTime) {
        super(name, initStatus, currentTime);
        setKelvinVal(kelvinVal);
        setBrightness(brightness);
    }

    public void _switch() {
        if (getStatus().equals("Off")) {
            setStatus("On");
        } else {
            setStatus("Off");
        }
        setSwitchTime(null);
    }

    public void _switch(String status) {
        if (getStatus().equals(status)) {
            System.out.println("Same status.");
        } else {
            setStatus(status);
            setSwitchTime(null);
        }
    }
}
