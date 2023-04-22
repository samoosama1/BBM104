import java.util.HashMap;

public class CheckArgumentValidity {
    public ValidArgs getValidArgs() {
        return validArgs;
    }

    private final HashMap<String, SmartDevice> smartDevices;
    private ValidArgs validArgs;

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
                allArgsValid = false;
        }
        return allArgsValid;
    }

    public void setNameBool() {
        deviceName = currentCommand[2];
        nameIsValid = !smartDevices.containsKey(deviceName);
    }
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
                allArgsValid = false;
        }
        return allArgsValid;
    }

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
        }
    }

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

    public void setCurrentCommand(String[] currentCommand) {
        this.currentCommand = currentCommand;
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
