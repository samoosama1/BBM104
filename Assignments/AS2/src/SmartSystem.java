import java.lang.invoke.SwitchPoint;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The driver class for the System.
 */
public class SmartSystem {
    private LocalDateTime time;
    private List<String[]> inputArr = new ArrayList<>();
    private String[] currentCommand;
    private HashMap<String, SmartDevice> smartDevices = new HashMap<>();

    public SmartSystem() {
        String[] inputLines = FileInput.readFile("input1.txt", true, true);
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
            case "Remove": break;
            case "SetTime": break;
            case "Switch": break;
            case "ChangeName": break;
            case "PlugIn": break;
            case "PlugOut": break;
            case "SetKelvin": break;
            case "SetBrightness": break;
            case "SetColorCode": break;
            case "SetWhite": break;
            case "SetColor": break;
            case "ZReport": break;
        }
    }

    public void setTime() {
        String localDateTimeStr = String.join("T", currentCommand[1].split("_"));
        this.time = LocalDateTime.parse(localDateTimeStr);
    }

    public void addSmartDevice() {
        String deviceType = currentCommand[1];
        switch (deviceType) {
            case "SmartPlug":
                if (CheckArgumentValidity.plugIsValid(currentCommand, smartDevices))
                    SmartDeviceFactory.addSmartPlug(CheckArgumentValidity.getValidArgs(), smartDevices);
                break;
            case "SmartCamera":
                if (CheckArgumentValidity.cameraIsValid(currentCommand, smartDevices))
                    SmartDeviceFactory.addSmartCamera(CheckArgumentValidity.getValidArgs(), smartDevices);
                break;
            case "SmartLamp":
                if (CheckArgumentValidity.lampIsValid(currentCommand, smartDevices))
                    SmartDeviceFactory.addSmartLamp(CheckArgumentValidity.getValidArgs(), smartDevices);
                break;
            case "SmartColorLamp":
                break;
            default:
                System.out.println("ERROR: No such device type!");
        }
    }

    public void addSmartLamp() {
        String deviceName = currentCommand[2];
        String initStatus = null;
        int kelvinVal = 0;
        int brightness = 0;
        if (currentCommand.length > 3)
            initStatus = currentCommand[3];
        if (currentCommand.length > 4) {
            kelvinVal = Integer.parseInt(currentCommand[4]);
            brightness = Integer.parseInt(currentCommand[5]);
        }
        switch (currentCommand.length) {
            case 3:
                smartDevices.put(deviceName, new SmartLamp(deviceName));
                break;
            case 4:
                smartDevices.put(deviceName, new SmartLamp(deviceName, initStatus));
                break;
            case 6:
                smartDevices.put(deviceName, new SmartLamp(deviceName, initStatus, kelvinVal, brightness));
                break;
        }
    }

    public void addSmartColorLamp() {

    }

    public void removeSmartDevice() {
        String deviceName = currentCommand[1];
        if(!smartDevices.containsKey(deviceName))
            ThrowException.throwNoSuchDevice();
        else {
//            smartDevices.get(deviceName)._switch();
        }
    }
}
