import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class SmartSystem {
    private LocalDateTime initialTime;
    private ArrayList<String[]> inputArr;
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
        for (String[] command : inputArr) {
            this.currentCommand = command;
            String commandKeyword = command[0];
            functionSelector(commandKeyword);
        }
    }

    public void functionSelector(String commandKeyword) {
        switch (commandKeyword) {
            case "SetInitialTime":
                setInitialTime();
                break;
            case "Add":
                addSmartDevice();

        }
    }

    public void setInitialTime() {
        LocalDateTime initialTime = LocalDateTime.parse(currentCommand[1]);
        this.initialTime = initialTime;
    }

    public void addSmartDevice() {
        String deviceType = currentCommand[1];
        switch (deviceType) {
            case "SmartPlug":
                addSmartPlug();
                break;
            case "SmartCamera":
                break;
            case "SmartLamp":
                break;
            case "SmartColorLamp":
                break;
            default:
                System.out.println("ERROR: No such device type!");
        }
    }

    public void addSmartPlug() {
        String deviceName = currentCommand[2];
        String initStatus = null;
        double ampereVal = 0.0;
        if (currentCommand.length > 3)
            initStatus = currentCommand[3];
        if (currentCommand.length > 4)
            ampereVal = Double.parseDouble(currentCommand[4]);

        switch (currentCommand.length) {
            case 3:
                smartDevices.put(deviceName, new SmartPlug(deviceName));
            case 4:
                smartDevices.put(deviceName, new SmartPlug(deviceName, initStatus));
            case 5:
                smartDevices.put(deviceName, new SmartPlug(deviceName, initStatus, ampereVal));
        }
    }

    public void addSmartCamera() {
        String deviceName = currentCommand[2];
        double megabytePerMinute = 0.0;
        String initStatus = null;
        if (currentCommand.length > 3)
            megabytePerMinute = Double.parseDouble(currentCommand[3]);
        if (currentCommand.length > 4)
            initStatus = currentCommand[4];
        switch (currentCommand.length) {
            case 3:
                smartDevices.put(deviceName, new SmartCamera(deviceName));
                break;
            case 4:
                smartDevices.put(deviceName, new SmartCamera(deviceName, megabytePerMinute));
                break;
            case 5:
                smartDevices.put(deviceName, new SmartCamera(deviceName, initStatus, megabytePerMinute));
                break;
        }
    }

    public void addSmartLamp() {

    }

    public void addSmartColorLamp() {

    }
}
