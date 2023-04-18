import java.util.HashMap;

public class CheckArgumentValidity {
    public static ValidArgs getValidArgs() {
        return validArgs;
    }

    private static ValidArgs validArgs;
    public static boolean lampIsValid(String[] currentCommand, HashMap<String, SmartDevice> smartDevices) {
        String deviceName = currentCommand[2], initStatus = null;
        int kelvinVal = 0, brightness = 0;
        boolean allArgsValid, statusIsValid, kelvinIsValid, brightnessIsValid, nameIsValid;
        allArgsValid = kelvinIsValid = brightnessIsValid = statusIsValid = true;
        nameIsValid = !smartDevices.containsKey(deviceName);
        if (currentCommand.length > 3) {
            initStatus = currentCommand[3];
            statusIsValid = initStatus.equals("on") || initStatus.equals("off");
        }
        if (currentCommand.length > 4) {
            kelvinVal = Integer.parseInt(currentCommand[4]);
            brightness = Integer.parseInt(currentCommand[5]);
            kelvinIsValid = kelvinVal >= 2000 && kelvinVal <= 6500;
            brightnessIsValid = brightness >= 0 && brightness <= 100;
        }
        switch (currentCommand.length) {
            case 3:
                if (nameIsValid) {
                    validArgs = new LampValidArgs(deviceName);
                } else {
                    ThrowException.throwNameExists();
                    allArgsValid = false;
                }
                break;
            case 4:
                if (!nameIsValid || !statusIsValid) {
                    if (!nameIsValid) {
                        ThrowException.throwNameExists();
                    } else {
                        ThrowException.throwErroneousCommand();
                    }
                    allArgsValid = false;
                } else {
                    validArgs = new LampValidArgs(deviceName, initStatus);
                }
                break;
            case 6:
                if (!nameIsValid || !statusIsValid || !kelvinIsValid || !brightnessIsValid) {
                    if (!kelvinIsValid)
                        ThrowException.throwKelvinOutOfBounds();
                    else if (!brightnessIsValid)
                        ThrowException.throwBrightnessOutOfBounds();
                    else if (!statusIsValid)
                        ThrowException.throwErroneousCommand();
                    else
                        ThrowException.throwNameExists();
                    allArgsValid = false;
                } else
                    validArgs = new LampValidArgs(deviceName, initStatus, kelvinVal, brightness);
                break;
        }
        return allArgsValid;
    }

    public static void colorLampIsValid() {

    }

    public static boolean cameraIsValid(String[] currentCommand, HashMap<String, SmartDevice> smartDevices) {
        String deviceName = currentCommand[2], initStatus = null;
        double megaBytePerMinute = 0.0;
        boolean allArgsValid, statusIsValid, megaByteIsValid, nameIsValid;
        allArgsValid = statusIsValid = megaByteIsValid = true;
        nameIsValid = !smartDevices.containsKey(deviceName);
        if (currentCommand.length > 3) {
            megaBytePerMinute = Double.parseDouble(currentCommand[3]);
            megaByteIsValid = megaBytePerMinute > 0.0;
        }
        if (currentCommand.length > 4) {
            initStatus = currentCommand[4];
            statusIsValid = initStatus.equals("on") || initStatus.equals("off");
        }
        switch (currentCommand.length) {
            case 4:
                if (!nameIsValid || !megaByteIsValid) {
                    if (!nameIsValid)
                        ThrowException.throwNameExists();
                    else
                        ThrowException.throwMegaByteOutOfBounds();
                    allArgsValid = false;
                } else
                    validArgs = new CameraValidArgs(deviceName, megaBytePerMinute);
                break;
            case 5:
                if (!nameIsValid || !statusIsValid || !megaByteIsValid) {
                    if (!megaByteIsValid)
                        ThrowException.throwAmpereOutOfBounds();
                    else if (!nameIsValid)
                        ThrowException.throwErroneousCommand();
                    else
                        ThrowException.throwNameExists();
                    allArgsValid = false;
                } else
                    validArgs = new CameraValidArgs(deviceName, initStatus, megaBytePerMinute);
        }
        return allArgsValid;
    }

    public static boolean plugIsValid(String[] currentCommand, HashMap<String, SmartDevice> smartDevices) {
        String deviceName = currentCommand[2], initStatus = null;
        double ampereVal = 0.0;
        boolean allArgsValid, statusIsValid, ampereIsValid, nameIsValid;
        allArgsValid = statusIsValid = ampereIsValid = true;
        nameIsValid = !smartDevices.containsKey(deviceName);
        if (currentCommand.length > 3) {
            initStatus = currentCommand[3];
            statusIsValid = initStatus.equals("On") || initStatus.equals("Off");
        }
        if (currentCommand.length > 4) {
            ampereVal = Double.parseDouble(currentCommand[4]);
            ampereIsValid = ampereVal > 0.0;
        }
        switch (currentCommand.length) {
            case 3:
                if (nameIsValid) {
                    validArgs = new PlugValidArgs(deviceName);
                } else {
                    ThrowException.throwNameExists();
                    allArgsValid = false;
                }
                break;
            case 4:
                if (!nameIsValid || !statusIsValid) {
                   if (!nameIsValid)
                       ThrowException.throwNameExists();
                   else
                       ThrowException.throwErroneousCommand();
                   allArgsValid = false;
                } else
                    validArgs = new PlugValidArgs(deviceName, initStatus);
                break;
            case 5:
                if (!nameIsValid || !statusIsValid || !ampereIsValid) {
                    if (!ampereIsValid)
                        ThrowException.throwAmpereOutOfBounds();
                    else if (!nameIsValid)
                        ThrowException.throwNameExists();
                    else
                        ThrowException.throwErroneousCommand();
                    allArgsValid = false;
                } else
                    validArgs = new PlugValidArgs(deviceName, initStatus, ampereVal);
        }
        return allArgsValid;
    }
}

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

class LampValidArgs extends ValidArgs {
    int kelvinVal;
    int brightness;
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
        this.argNum = 4;
    }
}

class ColorLampValidArgs extends LampValidArgs {
    int hexCode;
    boolean isKelvin;

    public ColorLampValidArgs(String name) {
        super(name);
    }

    public ColorLampValidArgs(String name, String status) {
        super(name, status);
    }

    public ColorLampValidArgs(String name, String status, int kelvinVal, int brightness) {
        super(name, status, kelvinVal, brightness);
        isKelvin = true;
    }

    public ColorLampValidArgs(String name, String status, String hexCodeStr, int brightness) {
        super(name, status);
        this.hexCode = Integer.decode(hexCodeStr);
        this.brightness = brightness;
        isKelvin = false;
        argNum = 4;
    }
}
