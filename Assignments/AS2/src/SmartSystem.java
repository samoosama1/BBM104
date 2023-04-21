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
            checkArgVal.setCurrentCommand(command);
            String commandKeyword = command[0];
            if (mustCheck) {
                if (!commandKeyword.equals("SetInitialTime")) {
                    ThrowException.throwInvalidFirstCommand();
                    System.exit(1);
                }
                mustCheck = false;
            }
            functionSelector(commandKeyword);
        }
    }

    public void functionSelector(String commandKeyword) {
        switch (commandKeyword) {
            case "SetInitialTime": setTime(); break;
            case "Add": addSmartDevice(); break;
            case "Remove": removeSmartDevice(); break;
            case "SetTime": setTime(); break;
            case "Switch": break;
            case "ChangeName": changeDeviceName(); break;
            case "PlugIn": break;
            case "PlugOut": break;
            case "SetKelvin": break;
            case "SetBrightness": break;
            case "SetColorCode": break;
            case "SetWhite": break;
            case "SetColor": break;
            case "ZReport": break;
            case "Nop": break;
        }
    }

    public void setTime() {
        try {
            String localDateTimeStr = currentCommand[1];
            SmartSystem.currentTime = LocalDateTime.parse(localDateTimeStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println(err.prefix("Time format is not correct!"));
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
                System.out.println(err.prefix("No such device type!"));
        }
    }


    public void removeSmartDevice() {
        String out;
        String deviceName = currentCommand[1];
        SmartDevice device = smartDevices.get(deviceName);
        if(!smartDevices.containsKey(deviceName))
            ThrowException.throwNoSuchDevice();
        else if (device.getStatus().equals("On")){
            device._switch("Off");
        }
    }

    public void changeDeviceName() {
        try {
            String currentName = currentCommand[1];
            String targetName = currentCommand[2];
            if (!smartDevices.containsKey(currentName))
                ThrowException.throwNoSuchDevice();
            else if (currentName.equals(targetName))
                ThrowException.throwSameName();
            else if (smartDevices.containsKey(targetName))
                ThrowException.throwNameExists();
            else {
                smartDevices.get(currentName).setName(targetName);
                smartDevices.put(targetName, smartDevices.get(currentName));
                smartDevices.remove(currentName);
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            ThrowException.throwErroneousCommand();
        }
    }

    public String instanceFinder(SmartDevice device) {
        if (device instanceof SmartPlug)
            return "Smart Plug";
        else if (device instanceof SmartColorLamp)
            return "Smart Color Lamp";
        else if (device instanceof SmartLamp)
            return "Smart Lamp";
        else
            return "Smart Camera";
    }
}
