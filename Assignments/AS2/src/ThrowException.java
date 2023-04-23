/**
 * The class that is responsible for outputting the error info of exceptions occurred during the execution of the
 * program. Program handles exceptions by calling the static methods of this class.
 */
public class ThrowException {
    private static final Printable err = s -> "ERROR: " + s;

    public static void nameExists() {
        OutputHelper.write(err.prefix("There is already a smart device with same name!"));
    }

    public static void erroneousCommand() {
        OutputHelper.write(err.prefix("Erroneous command!"));
    }

    public static void kelvinOutOfBounds() {
        OutputHelper.write(err.prefix("Kelvin value must be in range of 2000K-6500K!"));
    }

    public static void brightnessOutOfBounds() {
        OutputHelper.write(err.prefix("Brightness must be in range of 0%-100%!"));
    }

    public static void ampereOutOfBounds() {
        OutputHelper.write(err.prefix("Ampere value must be a positive number!"));
    }

    public static void megaByteOutOfBounds() {
        OutputHelper.write(err.prefix("Megabyte value must be a positive number!"));
    }

    public static void invalidFirstCommand() {
        OutputHelper.write(err.prefix("First command must be set initial time! Program is going to terminate!"));
    }

    public static void noSuchDevice() {
        OutputHelper.write(err.prefix("There is not such a device!"));
    }

    public static void sameStatus(String status) {
        OutputHelper.write(String.format(err.prefix("This device is already switched %s!"), status.toLowerCase()));
    }

    public static void colorCodeOutOfBounds() {
        OutputHelper.write(err.prefix("Color code value must be in range of 0x0-0xFFFFFF!"));
    }

    public static void sameName() {
        OutputHelper.write(err.prefix("Both of the names are the same, nothing changed!"));
    }

    public static void timeCannotBeReversed() {
        OutputHelper.write(err.prefix("Time cannot be reversed!"));
    }

    public static void timeFormatNotCorrect() {
        OutputHelper.write(err.prefix("Time format is not correct!"));
    }

    public static void notSmartPlug() {
        OutputHelper.write(err.prefix("This device is not a smart plug!"));
    }

    public static void alreadyPluggedOut() {
        OutputHelper.write(err.prefix("This plug has no item to plug out from that plug!"));
    }

    public static void alreadyPluggedIn() {
        OutputHelper.write(err.prefix("There is already an item plugged in to that plug!"));
    }

    public static void notSmartLamp() {
        OutputHelper.write(err.prefix("This device is not a smart lamp!"));
    }

    public static void notSmartColorLamp() {
        OutputHelper.write(err.prefix("This device is not a smart color lamp!"));
    }

    public static void nothingToSwitch() {
        OutputHelper.write(err.prefix("There is nothing to switch!"));
    }

    public static void initialFormatWrong() {
        OutputHelper.write(err.prefix("Format of the initial date is wrong! Program is going to terminate!"));
    }

    public static void nothingToSkip() {
        OutputHelper.write(err.prefix("There is nothing to skip!"));
    }

    public static void nothingToChange() {
        OutputHelper.write(err.prefix("There is nothing to change!"));
    }

    public static void switchTimeInPast() {
        OutputHelper.write(err.prefix("Switch time cannot be in the past!"));

    }
}
