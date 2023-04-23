import java.util.Comparator;

/**
 * This class is used to sort the Smart Device ArrayList according to their switch times.
 */
public class Checker implements Comparator<SmartDevice> {
    /**
     * Sorts the list in ascending order.
     * If both devices have switch times that are not null, compares them according to their switch times.
     * If switch time of one device is bigger than the other, it is considered bigger than the other device.
     * If switch times are equal, they are considered equal and their positions are preserved.
     * If both devices have switch times that are null, they are considered equal and their positions are preserved.
     * If one is null and the other is not, the one that is null is considered bigger.
     * @param a the first object to be compared.
     * @param b the second object to be compared.
     * @return positive value if a is bigger, zero if they are equal, negative value if b is bigger.
     */
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
