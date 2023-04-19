import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The driver class for the System.
 */
public class SmartSystem {
    private LocalDateTime time;
    private final List<String[]> inputArr = new ArrayList<>();
    private String[] currentCommand;
    private final HashMap<String, SmartDevice> smartDevices = new HashMap<>();
    private final CheckArgumentValidity checkArgVal = new CheckArgumentValidity(smartDevices);

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
            case "SetInitialTime":          setTime();                  break;
            case "Add":                     addSmartDevice();           break;
            case "Remove":                  removeSmartDevice();        break;
            case "SetTime":                 setTime();                  break;
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
                if (checkArgVal.plugIsValid())
                    SmartDeviceFactory.addSmartPlug(checkArgVal.getValidArgs(), smartDevices);
                break;
            case "SmartCamera":
                if (checkArgVal.cameraIsValid())
                    SmartDeviceFactory.addSmartCamera(checkArgVal.getValidArgs(), smartDevices);
                break;
            case "SmartLamp":
                if (checkArgVal.lampIsValid())
                    SmartDeviceFactory.addSmartLamp(checkArgVal.getValidArgs(), smartDevices);
                break;
            case "SmartColorLamp":
                if(checkArgVal.colorLampIsValid())
                    SmartDeviceFactory.addSmartColorLamp(checkArgVal.getValidArgs(), smartDevices);
                break;
            default:
                System.out.println("ERROR: No such device type!");
        }
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
