import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.time.format.DateTimeFormatter;

/**
 * The driver class for the System.
 */
public class SmartSystem {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");

    public static LocalDateTime getCurrentTime() {
        return currentTime;
    }

    private static LocalDateTime currentTime;
    private final List<String[]> inputArr = new ArrayList<>();
    private String[] currentCommand;
    private final HashMap<String, SmartDevice> smartDevices = new HashMap<>();
    private final ArrayList<SmartDevice> smartDeviceList = new ArrayList<>();
    private final CheckArgumentValidity checkArgVal = new CheckArgumentValidity(smartDevices);
    private final Printable com = s -> "COMMAND: " + s;
    private final Printable err = s -> "ERROR: " + s;

    public SmartSystem(String[] inputLines) {
        String[] rowArray;
        for (String inputLine : inputLines) {
            rowArray = inputLine.split("\t");
            inputArr.add(rowArray);
        }
    }

    public void startSystem() {
        boolean mustCheck = true;
        for (String[] command : inputArr) {
            this.currentCommand = command;
            OutputHelper.write(com.prefix(String.join("\t", command)));
            checkArgVal.setCurrentCommand(command);
            String commandKeyword = command[0];
            if (!mustCheck) {
                if (commandKeyword.equals("SetInitialTime")) {
                    ThrowException.erroneousCommand();
                    continue;
                }
            }
            if (mustCheck) {
                if (!commandKeyword.equals("SetInitialTime")) {
                    ThrowException.invalidFirstCommand();
                    System.exit(1);
                }
                mustCheck = false;
            }
            functionSelector(commandKeyword);
        }
    }

    public void functionSelector(String commandKeyword) {
        try {
            switch (commandKeyword) {
                case "SetInitialTime": setTime(); break;
                case "Add": addSmartDevice(); break;
                case "Remove": removeSmartDevice(); break;
                case "SetTime": setTime(); break;
                case "Switch": switchDevice(); break;
                case "SetSwitchTime": setDeviceSwitchTime(); break;
                case "ChangeName": changeDeviceName(); break;
                case "PlugIn": plugDeviceIn(); break;
                case "PlugOut": plugDeviceOut(); break;
                case "SetKelvin": setKelvin(); break;
                case "SetBrightness": setBrightness(); break;
                case "SetColorCode": setColorCode(); break;
                case "SetWhite": setWhite(); break;
                case "SetColor": setColor(); break;
                case "SkipMinutes": skipMinutes(); break;
                case "ZReport": zReport(); break;
                case "Nop": break;
                default: ThrowException.erroneousCommand();
            }
        } catch (Exception e) {
            ThrowException.erroneousCommand();
        }
    }

    public void setTime() {
        try {
            String localDateTimeStr = currentCommand[1];
            LocalDateTime timeToChange = LocalDateTime.parse(localDateTimeStr, formatter);
            if (currentTime != null) {
                if (timeToChange.isBefore(currentTime)) {
                    ThrowException.timeCannnotBeReversed();
                } else {
                    currentTime = timeToChange;
                    for (SmartDevice device : smartDeviceList) {
                        device.setTime(currentTime);
                    }
                    sortDevices();
                }
            } else {
                currentTime = timeToChange;
                setTimeOfDevices();
            }
        } catch (DateTimeParseException e) {
            ThrowException.timeFormatNotCorrect();
        }
    }

    public void addSmartDevice() {
        String deviceType = currentCommand[1];
        switch (deviceType) {
            case "SmartPlug":
                if (checkArgVal.plugIsValid())
                    SmartDeviceFactory.addSmartPlug(checkArgVal.getValidArgs(), smartDevices, smartDeviceList);
                break;
            case "SmartCamera":
                if (checkArgVal.cameraIsValid())
                    SmartDeviceFactory.addSmartCamera(checkArgVal.getValidArgs(), smartDevices, smartDeviceList);
                break;
            case "SmartLamp":
                if (checkArgVal.lampIsValid())
                    SmartDeviceFactory.addSmartLamp(checkArgVal.getValidArgs(), smartDevices, smartDeviceList);
                break;
            case "SmartColorLamp":
                if(checkArgVal.colorLampIsValid())
                    SmartDeviceFactory.addSmartColorLamp(checkArgVal.getValidArgs(), smartDevices, smartDeviceList);
                break;
            default:
                ThrowException.erroneousCommand();
        }
    }

    public void removeSmartDevice() {
        String deviceName = currentCommand[1];
        if (smartDevices.containsKey(deviceName)) {
            SmartDevice device = smartDevices.get(deviceName);
            if (device.getStatus().equals("On")){
                device._switch("Off");
            }
            OutputHelper.write("Information about removed smart device is as follows:");
            OutputHelper.write(device.toString());
            smartDevices.remove(deviceName);
            smartDeviceList.remove(device);
        } else
            ThrowException.noSuchDevice();
    }

    public void changeDeviceName() {
        String deviceName = currentCommand[1];
        String targetName = currentCommand[2];
        if (deviceName.equals(targetName))
            ThrowException.sameName();
        else if (!smartDevices.containsKey(deviceName))
            ThrowException.noSuchDevice();
        else if (smartDevices.containsKey(targetName))
            ThrowException.nameExists();
        else {
            smartDevices.get(deviceName).setName(targetName);
            smartDevices.put(targetName, smartDevices.get(deviceName));
            smartDevices.remove(deviceName);
        }
    }

    public void switchDevice() {
        String currentName = currentCommand[1];
        String status = currentCommand[2];
        if (smartDevices.containsKey(currentName)) {
            smartDevices.get(currentName)._switch(status);
            smartDeviceList.sort(new Checker());
        }
        else
            ThrowException.noSuchDevice();
    }

    public void plugDeviceIn() {
        String deviceName = currentCommand[1];
        double ampereVal = Double.parseDouble(currentCommand[2]);
        if (smartDevices.containsKey(deviceName)) {
            try {
                SmartPlug plug = (SmartPlug) smartDevices.get(deviceName);
                plug.plugIn(ampereVal);
            } catch (ClassCastException e) {
                ThrowException.notSmartPlug();
            }
        } else
            ThrowException.noSuchDevice();
    }

    public void plugDeviceOut() {
        String deviceName = currentCommand[1];
        if (smartDevices.containsKey(deviceName)) {
            try {
                SmartPlug plug = (SmartPlug) smartDevices.get(deviceName);
                plug.plugOut();
            } catch (ClassCastException e) {
                ThrowException.notSmartPlug();
            }
        } else
            ThrowException.noSuchDevice();
    }

    public void sortDevices() {
        smartDeviceList.sort(new Checker());
    }

    public void setKelvin() {
        String deviceName = currentCommand[1];
        int kelvinVal = Integer.parseInt(currentCommand[2]);
        if (smartDevices.containsKey(deviceName)) {
            try {
                SmartLamp lamp = (SmartLamp) smartDevices.get(deviceName);
                lamp.setKelvinVal(kelvinVal);
            } catch (ClassCastException e) {
                ThrowException.notSmartLamp();
            }
        } else
            ThrowException.noSuchDevice();
    }

    public void setBrightness() {
        String deviceName = currentCommand[1];
        int brightness = Integer.parseInt(currentCommand[2]);
        if (smartDevices.containsKey(deviceName)) {
            try {
                SmartLamp lamp = (SmartLamp) smartDevices.get(deviceName);
                lamp.setBrightness(brightness);
            } catch (ClassCastException e) {
                ThrowException.notSmartLamp();
            }
        }
    }

    public void setColorCode() {
        String deviceName = currentCommand[1];
        int colorCode = Integer.decode(currentCommand[2]);
        if (smartDevices.containsKey(deviceName)) {
            try {
                SmartColorLamp colorLamp = (SmartColorLamp) smartDevices.get(deviceName);
                colorLamp.setColorBaseHex(colorCode);
            } catch (ClassCastException e) {
                ThrowException.notSmartColorLamp();
            }
        } else
            ThrowException.noSuchDevice();
    }

    public void setWhite() {
        String deviceName = currentCommand[1];
        int kelvinVal = Integer.parseInt(currentCommand[2]);
        int brightness = Integer.parseInt(currentCommand[3]);
        if (smartDevices.containsKey(deviceName)) {
            try {
                SmartLamp lamp = (SmartLamp) smartDevices.get(deviceName);
                boolean kelvinValid = (kelvinVal >= 2000) && (kelvinVal <= 6500);
                boolean brightnessValid = (brightness >= 0) && (brightness <= 100);
                if (kelvinValid && brightnessValid) {
                    lamp.setKelvinVal(kelvinVal);
                    lamp.setBrightness(brightness);
                } else if (!kelvinValid)
                    ThrowException.kelvinOutOfBounds();
                else
                    ThrowException.brightnessOutOfBounds();
            } catch (ClassCastException e) {
                ThrowException.notSmartLamp();
            }
        } else {
            ThrowException.noSuchDevice();
        }
    }

    public void setColor() {
        String deviceName = currentCommand[1];
        int colorCode = Integer.decode(currentCommand[2]);
        int brightness = Integer.parseInt(currentCommand[3]);
        if (smartDevices.containsKey(deviceName)) {
            try {
                SmartColorLamp colorLamp = (SmartColorLamp) smartDevices.get(deviceName);
                boolean colorValid = (colorCode >= 0x000000) && (colorCode <= 0xFFFFFF);
                boolean brightnessValid = (brightness >= 0) && (brightness <= 100);
                if (colorValid && brightnessValid) {
                    colorLamp.setColorBaseHex(colorCode);
                    colorLamp.setBrightness(brightness);
                } else if (!colorValid)
                    ThrowException.colorCodeOutOfBounds();
                else
                    ThrowException.brightnessOutOfBounds();
            } catch (ClassCastException e) {
                ThrowException.notSmartColorLamp();
            }
        }
    }

    public void setDeviceSwitchTime() {
        String deviceName = currentCommand[1];
        LocalDateTime switchTime = LocalDateTime.parse(currentCommand[2], formatter);
        if (smartDevices.containsKey(deviceName)) {
            smartDevices.get(deviceName).setSwitchTime(switchTime);
            sortDevices();
        } else {
            ThrowException.noSuchDevice();
        }
    }

    public void zReport() {
        String out = String.format("Time is:\t%s", currentTime.format(formatter));
        OutputHelper.write(out);
        for (SmartDevice device : smartDeviceList) {
            OutputHelper.write(device.toString());
        }
    }

    public void skipMinutes() {
        int minutesToSkip = Integer.parseInt(currentCommand[1]);
        currentTime = currentTime.plusMinutes(minutesToSkip);
        setTimeOfDevices();
    }

    public void setTimeOfDevices() {
        for (SmartDevice device : smartDeviceList) {
            device.setTime(currentTime);
        }
    }

}
