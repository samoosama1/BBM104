public class ThrowException {
    private static final Printable err = s -> "ERROR: " + s;

    public static void throwNameExists() {
        try {
            throw new IllegalArgumentException(err.prefix("There is already a smart device with same name!"));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void throwErroneousCommand() {
        try {
            throw new IllegalArgumentException(err.prefix("Erroneous command!"));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void throwKelvinOutOfBounds() {
        try {
            throw new IllegalArgumentException(err.prefix("Kelvin value must be in range of 2000K-6500K!"));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void throwBrightnessOutOfBounds() {
        try {
            throw new IllegalArgumentException(err.prefix("Brightness must be in range of 0%-100%!"));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void throwAmpereOutOfBounds() {
        try {
            throw new IllegalArgumentException(err.prefix("Ampere value must be a positive number!"));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void throwMegaByteOutOfBounds() {
        try {
            throw new IllegalArgumentException(err.prefix("Megabyte value must be a positive number!"));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void throwInvalidFirstCommand() {
        try {
            throw new IllegalArgumentException(
                err.prefix("First command must be set initial time! Program is going to terminate!"));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void throwNoSuchDevice() {
        try {
            throw new IllegalArgumentException(err.prefix("No such device!"));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void throwSameStatus(String status) {
        try {
            throw new IllegalArgumentException(
                    String.format(err.prefix("This device is already switched %s!"), status.toLowerCase()));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void throwColorCodeOutOfBounds() {
        try {
            throw new IllegalArgumentException(err.prefix("Color code value must be in range of 0x0-0xFFFFFF!"));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void throwSameName() {
        try {
            throw new IllegalArgumentException(err.prefix("Both of the names are the same, nothing changed!"));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
