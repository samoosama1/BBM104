import java.util.HashMap;

/**
 * This class is responsible for checking the validity of arguments provided by Add commands. Sets the validArgs to
 * object that contains arguments that were checked for the validity.
 */
public class CheckArgumentValidity {

    /**
     * Lets other classes to reach the latest valid arguments that were set by this class.
     * @return valid arguments
     */
    public ValidArgs getValidArgs() {
        return validArgs;
    }

    private final HashMap<String, SmartDevice> smartDevices;
    private ValidArgs validArgs;

    /**
     * Constructor of the class. Sets HashMap provided to its smartDevices variable. This is done in order to check for
     * noSuchDevice exception.
     * @param smartDevices Smart Device HashMap in the Smart System.
     */
    public CheckArgumentValidity(HashMap<String, SmartDevice> smartDevices) {
        this.smartDevices = smartDevices;

    }
    private boolean allArgsValid;
    private boolean statusIsValid, megaByteIsValid, nameIsValid,
            ampereIsValid , kelvinIsValid, brightnessIsValid, hexCodeValid;
    private double megaBytePerMinute, ampereVal ;
    private String deviceName, initStatus, hexCodeStr;
    private int kelvinVal, brightness, hexCode;
    private String[] currentCommand;

    /**
     * Sets the boolean values for the Camera.
     */
    public void setCameraBools() {
        setNameBool();
        if (currentCommand.length > 3) {
            megaBytePerMinute = Double.parseDouble(currentCommand[3]);
            megaByteIsValid = megaBytePerMinute > 0.0;
        }
        if (currentCommand.length > 4) {
            initStatus = currentCommand[4];
            statusIsValid = initStatus.equals("On") || initStatus.equals("Off");
        }
    }

    /**
     * Checks for the validity of the arguments according to the boolean values that were set previously. Throws
     * exceptions according to the invalid arguments.
     * @return true if arguments are valid else false.
     */
    public boolean cameraIsValid() {
        allArgsValid = true;
        setCameraBools();
        switch (currentCommand.length) {
            case 4:
                if (!nameIsValid || !megaByteIsValid) {
                    if (!nameIsValid)
                        ThrowException.nameExists();
                    else
                        ThrowException.megaByteOutOfBounds();
                    allArgsValid = false;
                } else
                    validArgs = new CameraValidArgs(deviceName, megaBytePerMinute);
                break;
            case 5:
                if (!nameIsValid || !statusIsValid || !megaByteIsValid) {
                    if (!megaByteIsValid)
                        ThrowException.ampereOutOfBounds();
                    else if (!nameIsValid)
                        ThrowException.erroneousCommand();
                    else
                        ThrowException.nameExists();
                    allArgsValid = false;
                } else
                    validArgs = new CameraValidArgs(deviceName, initStatus, megaBytePerMinute);
                break;
            default:
                ThrowException.erroneousCommand();
                allArgsValid = false;
        }
        return allArgsValid;
    }

    /**
     * Sets boolean values for name of the device.
     */
    public void setNameBool() {
        deviceName = currentCommand[2];
        nameIsValid = !smartDevices.containsKey(deviceName);
    }

    /**
     * Sets boolean values for Plug specific arguments.
     */
    public void setPlugBools() {
        setNameBool();
        if (currentCommand.length > 3) {
            initStatus = currentCommand[3];
            statusIsValid = initStatus.equals("On") || initStatus.equals("Off");
        }
        if (currentCommand.length > 4) {
            ampereVal = Double.parseDouble(currentCommand[4]);
            ampereIsValid = ampereVal > 0.0;
        }
    }

    /**
     * Checks for the validity of the arguments according to the boolean values that were set previously. Throws
     * exceptions according to the invalid arguments.
     * @return true if arguments are valid else false.
     */
    public boolean plugIsValid() {
        allArgsValid = true;
        setPlugBools();
        switch (currentCommand.length) {
            case 3:
                if (nameIsValid) {
                    validArgs = new PlugValidArgs(deviceName);
                } else {
                    ThrowException.nameExists();
                    allArgsValid = false;
                }
                break;
            case 4:
                if (!nameIsValid || !statusIsValid) {
                   if (!nameIsValid)
                       ThrowException.nameExists();
                   else
                       ThrowException.erroneousCommand();
                   allArgsValid = false;
                } else
                    validArgs = new PlugValidArgs(deviceName, initStatus);
                break;
            case 5:
                if (!nameIsValid || !statusIsValid || !ampereIsValid) {
                    if (!ampereIsValid)
                        ThrowException.ampereOutOfBounds();
                    else if (!nameIsValid)
                        ThrowException.nameExists();
                    else
                        ThrowException.erroneousCommand();
                    allArgsValid = false;
                } else
                    validArgs = new PlugValidArgs(deviceName, initStatus, ampereVal);
                break;
            default:
                ThrowException.erroneousCommand();
                allArgsValid = false;
        }
        return allArgsValid;
    }

    /**
     * Sets boolean values for Lamp specific arguments.
     * @throws NumberFormatException When setting brightness or kelvin values fail.
     */
    public void setLampBools() throws NumberFormatException{
        setNameBool();
        if (currentCommand.length > 3) {
            initStatus = currentCommand[3];
            statusIsValid = initStatus.equals("On") || initStatus.equals("Off");
        }
        if (currentCommand.length > 4) {
            brightness = Integer.parseInt(currentCommand[5]);
            brightnessIsValid = brightness >= 0 && brightness <= 100;
            kelvinVal = Integer.parseInt(currentCommand[4]);
            kelvinIsValid = kelvinVal >= 2000 && kelvinVal <= 6500;
        }
    }

    /**
     * Checks for the validity of the arguments according to the boolean values that were set previously. Throws
     * exceptions according to the invalid arguments.
     * @return true if arguments are valid else false.
     */
    public boolean lampIsValid() {
        allArgsValid = true;
        setLampBools();
        if (currentCommand.length != 6)
            lampHelper1();
        else {
            lampHelper2();
        }
        return allArgsValid;
    }

    /**
     * This piece of code is turned into a method for code reuse.
     */
    public void lampHelper1() {
        switch (currentCommand.length) {
            case 3:
                if (nameIsValid) {
                    validArgs = new LampValidArgs(deviceName);
                } else {
                    ThrowException.nameExists();
                    allArgsValid = false;
                }
                break;
            case 4:
                if (!nameIsValid || !statusIsValid) {
                    if (!nameIsValid) {
                        ThrowException.nameExists();
                    } else {
                        ThrowException.erroneousCommand();
                    }
                    allArgsValid = false;
                } else
                    validArgs = new LampValidArgs(deviceName, initStatus);
                break;
            default:
                allArgsValid = false;
                ThrowException.erroneousCommand();
        }
    }

    /**
     * This piece of code is turned into method for code reuse.
     */
    public void lampHelper2() {
        if (!nameIsValid || !statusIsValid || !kelvinIsValid || !brightnessIsValid) {
            if (!kelvinIsValid)
                ThrowException.kelvinOutOfBounds();
            else if (!brightnessIsValid)
                ThrowException.brightnessOutOfBounds();
            else if (!statusIsValid)
                ThrowException.erroneousCommand();
            else
                ThrowException.nameExists();
            allArgsValid = false;
        } else
            validArgs = new LampValidArgs(deviceName, initStatus, kelvinVal, brightness);
    }
    public boolean colorLampIsValid() {
        allArgsValid = true;
        boolean isKelvinArg = true;
        boolean isHexCode = false;
        try {
            setLampBools();
        } catch (NumberFormatException e) {
            try {
                isKelvinArg = false;
                hexCodeStr = currentCommand[4];
                hexCode = Integer.decode(hexCodeStr);
                hexCodeValid = hexCode >= 0x000000 && hexCode <= 0xFFFFFF;
                isHexCode = true;
            } catch (NumberFormatException err) {
                ThrowException.erroneousCommand();
                allArgsValid = false;
            }
        }
        if (currentCommand.length != 6)
            lampHelper1();
        else {
            if (isKelvinArg) {
                lampHelper2();
            } else if (isHexCode) {
                if (!nameIsValid || !statusIsValid || !hexCodeValid || !brightnessIsValid) {
                    if (!hexCodeValid)
                        ThrowException.colorCodeOutOfBounds();
                    else if (!brightnessIsValid)
                        ThrowException.brightnessOutOfBounds();
                    else if (!statusIsValid)
                        ThrowException.erroneousCommand();
                    else
                        ThrowException.nameExists();
                    allArgsValid = false;
                } else
                    validArgs = new LampValidArgs(deviceName, initStatus, hexCodeStr, brightness);
            }
        }
        return allArgsValid;
    }

    /**
     * Sets the current command in the system to check for the validity of the arguments.
     * @param currentCommand
     */
    public void setCurrentCommand(String[] currentCommand) {
        this.currentCommand = currentCommand;
    }
}

/**
 * Parent class of all other valid argument classes. When system decides to create and object, it typecasts this in
 * order to reach device specific arguments. The fields of these classes are not set to private as they are just carrier
 * classes that are used to carry information from one class to another.
 */
class ValidArgs {
    int argNum;
    String name;
    String status;
    public ValidArgs(String name) {
        this.name = name;
        this.argNum = 1;
    }

    public ValidArgs(String name, String status) {
        this.name = name;
        this.status = status;
        this.argNum = 2;
    }
}

/**
 * Subclass of ValidArgs. Holds arguments that are specific to Smart Plug.
 */
class PlugValidArgs extends ValidArgs{
    double ampereVal;
    public PlugValidArgs(String name) {
        super(name);
    }

    public PlugValidArgs(String name, String status) {
        super(name, status);
    }

    public PlugValidArgs(String name, String status, double ampereVal) {
        super(name, status);
        this.ampereVal = ampereVal;
        this.argNum = 3;
    }
}

/**
 * Subclass of ValidArgs. Holds arguments that are specific to Smart Camera.
 */
class CameraValidArgs extends ValidArgs {
    double megaBytePerMinute;
    public CameraValidArgs(String name, double megaBytePerMinute) {
        super(name);
        this.megaBytePerMinute = megaBytePerMinute;
        this.argNum = 2;
    }

    public CameraValidArgs(String name, String status, double megaBytePerMinute) {
        super(name, status);
        this.megaBytePerMinute = megaBytePerMinute;
        this.argNum = 3;
    }
}

/**
 * Subclass of ValidArgs. Holds arguments that are specific to Smart Lamp.
 */
class LampValidArgs extends ValidArgs {
    int kelvinVal;
    int brightness;
    String hexCodeStr;
    boolean isKelvin;
    public LampValidArgs(String name) {
        super(name);
    }

    public LampValidArgs(String name, String status) {
        super(name, status);
    }

    public LampValidArgs(String name, String status, int kelvinVal, int brightness) {
        super(name, status);
        this.kelvinVal = kelvinVal;
        this.brightness = brightness;
        this.isKelvin = true;
        this.argNum = 4;
    }

    public LampValidArgs(String name, String status, String hexCodeStr, int brightness) {
        super(name, status);
        this.hexCodeStr = hexCodeStr;
        this.brightness = brightness;
        this.isKelvin = false;
        this.argNum = 4;
    }
}
