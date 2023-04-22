import java.util.Comparator;

public class Checker implements Comparator<SmartDevice> {
    public int compare(SmartDevice a, SmartDevice b) {
        if (a.getSwitchTime() != null && b.getSwitchTime() != null)
            return a.getSwitchTime().compareTo(b.getSwitchTime());
        else {
            if (a.getSwitchTime() == null && b.getSwitchTime() == null)
                return 0;
            else if (a.getSwitchTime() == null)
                return 1;
            else
                return -1;
        }
    }
}
