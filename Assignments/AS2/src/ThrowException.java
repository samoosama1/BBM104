public class ThrowException {
    public static void throwNameExists() {
        try {
            throw new IllegalArgumentException("ERROR: There is already a smart device with same name!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void throwErroneousCommand() {
        try {
            throw new IllegalArgumentException("ERROR: Erroneous command!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void throwKelvinOutOfBounds() {
        try {
            throw new IllegalArgumentException("ERROR: Kelvin value must be in range of 2000K-6500K!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void throwBrightnessOutOfBounds() {
        try {
            throw new IllegalArgumentException("ERROR: Brightness must be in range of 0%-100%!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void throwAmpereOutOfBounds() {
        try {
            throw new IllegalArgumentException("ERROR: Ampere value must be a positive number!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void throwMegaByteOutOfBounds() {
        try {
            throw new IllegalArgumentException("ERROR: Megabyte value must be a positive number!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void throwInvalidFirstCommand() {
        try {
            throw new IllegalArgumentException(
                    "ERROR: First command must be set initial time! Program is going to terminate!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void throwNoSuchDevice() {
        try {
            throw new IllegalArgumentException("ERROR: No such device!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void throwSameStatus(String status) {
        try {
            throw new IllegalArgumentException(
                    String.format("ERROR: This device is already switched %s!", status.toLowerCase()));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
