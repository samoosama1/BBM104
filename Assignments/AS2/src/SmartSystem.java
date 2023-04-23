import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.time.format.DateTimeFormatter;

/**
 * The driver class for the system and other devices. Calls necessary methods and runs the system.
 */
public class SmartSystem {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");
    private static LocalDateTime currentTime;
    private final List<String[]> inputArr = new ArrayList<>();
    private String[] currentCommand;
    private final HashMap<String, SmartDevice> smartDevices = new HashMap<>();
    private final ArrayList<SmartDevice> smartDeviceList = new ArrayList<>();
    private final CheckArgumentValidity checkArgVal = new CheckArgumentValidity(smartDevices);
    // Lambda expressions to add prefixes to certain outputs.
    private final Printable com = s -> "COMMAND: " + s;
    private final Printable suc = s -> "SUCCESS: " + s;

    /**
     * Returns current time of the system.
     * @return current time
     */
    public static LocalDateTime getCurrentTime() {
        return currentTime;
    }

    /**
     * Constructor of the SmartSystem class. The input lines are required in order to operate over commands.
     * Iterates over each command string and splits it into a String array containing arguments of the command.
     * Adds these arrays into a ArrayList that holds String arrays.
     * @param inputLines A string array that contains lines of the input file as elements.
     */
    public SmartSystem(String[] inputLines) {
        String[] rowArray;
        for (String inputLine : inputLines) {
            rowArray = inputLine.split("\t");
            inputArr.add(rowArray);
        }
    }

    /**
     * Main loop of the system. Iterates over the command arrays that were created during construction of SmartSystem.
     * Sets the command keyword and calls functionSelector in order to call relevant functions according to the
     * command keyword.
     */
    public void startSystem() {
        boolean mustCheck = true;
        for (String[] command : inputArr) {
            this.currentCommand = command;
            OutputHelper.write(com.prefix(String.join("\t", command)));
            checkArgVal.setCurrentCommand(command);
            String commandKeyword = command[0];
            // This part checks if the first command is SetInitialTime or not at the start of the system.
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
        // If last command is not ZReport, this part is executed.
        if (!currentCommand[0].equals("ZReport")) {
            OutputHelper.write("ZReport:");
            zReport();
        }
    }

    /**
     * Selects the method that corresponds to given command keyword and calls the method.
     * @param commandKeyword The command keyword that maps to a method call.
     */
    public void functionSelector(String commandKeyword) {
        try {
            switch (commandKeyword) {
                case "SetInitialTime": setInitialTime(); break;
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
                case "Nop": nop(); break;
                default: ThrowException.erroneousCommand();
            }
        } catch (Exception e) {
            ThrowException.erroneousCommand();
        }
    }

    /**
     * The method that sets the time of the system and time of the Smart Devices that are being hold in the system.
     * Only sets the time when given time is after than the current time.
     */
    public void setTime() {
        try {
            String localDateTimeStr = currentCommand[1];
            LocalDateTime timeToChange = LocalDateTime.parse(localDateTimeStr, formatter);
            if (currentTime != null) {
                if (timeToChange.isBefore(currentTime))
                    ThrowException.timeCannotBeReversed();
                else if (timeToChange.isEqual(currentTime))
                    ThrowException.nothingToChange();
                else {
                    currentTime = timeToChange;
                    setTimeOfDevices();
                }
            } else {
                currentTime = timeToChange;
                setTimeOfDevices();
            }
        } catch (DateTimeParseException e) {
            ThrowException.timeFormatNotCorrect();
        }
    }

    /**
     * Tries to add a device whose name and type (or additional arguments that vary with device type) is specified
     * in the command. Fails to add the device if arguments provided for the device are not valid.
     */
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

    /**
     * Removes the device from the system whose name is given in the command. Throws an exception if the given name is
     * not contained in the HashMap that maps name of the device to their corresponding objects that were created by the
     * addSmartDevice method.
     */
    public void removeSmartDevice() {
        String deviceName = currentCommand[1];
        if (smartDevices.containsKey(deviceName)) {
            SmartDevice device = smartDevices.get(deviceName);
            if (device.getStatus().equals("On")){
                device._switch("Off");
            }
            OutputHelper.write(suc.prefix("Information about removed smart device is as follows:"));
            OutputHelper.write(device.toString());
            smartDevices.remove(deviceName);
            smartDeviceList.remove(device);
        } else
            ThrowException.noSuchDevice();
    }

    /**
     * Changes the name of the device to a target name that is specified in the command. Throws relevant exceptions if
     * target name is same with the current name of the device, if there is no such a device in the system or target
     * name already exists in the system.
     *
     */
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

    /**
     * Switches the device whose name is given on or off according to the status specified in the command. If given name
     * is not found in the system, throws noSuchDevice exception. After switching the device, as switch times are set
     * to null, sorts the Smart Device ArrayList in order to update it.
     */
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

    /**
     * Plugs a device whose ampere is given to the Smart Plug whose name is given. At first checks whether Smart Plug
     * exists in the system or not. If plug is not found throws noSuchDevice exception. If it is found, tries to
     * typecast the object to SmartPlug. If system doesn't throw any exception during this procedure it means it is a
     * Smart Plug and plugs the device to Smart Plug. If it throws an exception, then notSmartPlug exception is thrown.
     */
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

    /**
     * Plugs out the device from Smart Plug. If device is not in the system, noSuchDevice exception is thrown. If device
     * is not a Smart Plug notSmartPlug exception is thrown. Plugs device out of the Smart Plug if to exceptions are
     * thrown.
     */
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

    /**
     * Sorts the device ArrayList according to their switch times.
     */
    public void sortDevices() {
        smartDeviceList.sort(new Checker());
    }

    /**
     * Sets the kelvin value of the Smart Lamps. These are applicable to Smart Color Lamps as SmartColorLamp is subclass
     * of SmartLamp. If device is not found throws noSuchDevice exception. If device is not * a Smart Lamp then throws
     * notSmartLamp exception.
     */
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

    /**
     * Sets the brightness of Smart Lamps and Smart Color Lamps. Throws noSuchDevice and notSmartLamp exceptions
     * according to the problem occurred during the execution of the method.
     */
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

    /**
     * Sets the color code of Smart Color Lamps. Throws noSuchDevice and notSmartColorLamp.
     */
    public void setColorCode() {
        String deviceName = currentCommand[1];

        if (smartDevices.containsKey(deviceName)) {
            try {
                SmartColorLamp colorLamp = (SmartColorLamp) smartDevices.get(deviceName);
                int colorCode = Integer.decode(currentCommand[2]);
                colorLamp.setColorBaseHex(colorCode);
            } catch (ClassCastException e) {
                ThrowException.notSmartColorLamp();
            }
        } else
            ThrowException.noSuchDevice();
    }

    /**
     * Sets the brightness and kelvin value of a Smart Lamp or Smart Color Lamp. If method does not throw noSuchDevice
     * or notSmartLamp, then checks for the validity of the arguments provided by the command. Throws kelvinOutOfBounds
     * and brightnessOutOfBounds exceptions respectively if arguments are invalid.
     */
    public void setWhite() {
        String deviceName = currentCommand[1];
        if (smartDevices.containsKey(deviceName)) {
            try {
                SmartLamp lamp = (SmartLamp) smartDevices.get(deviceName);
                int kelvinVal = Integer.parseInt(currentCommand[2]);
                int brightness = Integer.parseInt(currentCommand[3]);
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

    /**
     * Sets both color code and brightness of Smart Color Lamps. Works same as setWhite.
     */
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

    /**
     * Sets the switch time of the device. Throws noSuchDevice if device is not found. If the switch time is
     * before current time, throws switchTimeInPast exception. If switch time is same as current time, sets the switch
     * time of the device and sets current time of device again in order to make it update itself. Sorts devices before
     * and after setTime so ZReport would be consistent. If switch time is in the future, then just sets the switch time
     * of the device and sorts the devices.
     */
    public void setDeviceSwitchTime() {
        String deviceName = currentCommand[1];
        LocalDateTime switchTime = LocalDateTime.parse(currentCommand[2], formatter);
        if (switchTime.isBefore(currentTime))
            ThrowException.switchTimeInPast();
        else if (switchTime.isEqual(currentTime)) {
            smartDevices.get(deviceName).setSwitchTime(switchTime);
            sortDevices();
            smartDevices.get(deviceName).setTime(currentTime);
            sortDevices();
        }
        else {
            if (smartDevices.containsKey(deviceName)) {
                smartDevices.get(deviceName).setSwitchTime(switchTime);
                sortDevices();
            } else {
                ThrowException.noSuchDevice();
            }
        }
    }

    /**
     * Writes Z Report to the output file. Iterates over the Smart Device ArrayList in order to write String
     * representation of each device.
     */
    public void zReport() {
        String out = String.format("Time is:\t%s", currentTime.format(formatter));
        OutputHelper.write(out);
        for (SmartDevice device : smartDeviceList) {
            OutputHelper.write(device.toString());
        }
    }

    /**
     * Sets current time to the new time that is obtained by adding amounts of minutes specified by the command to
     * current time.
     */
    public void skipMinutes() {
        if (currentCommand.length > 2) {
            ThrowException.erroneousCommand();
        } else {
            int minutesToSkip = Integer.parseInt(currentCommand[1]);
            if (minutesToSkip > 0) {
                currentTime = currentTime.plusMinutes(minutesToSkip);
                setTimeOfDevices();
            } else if (minutesToSkip == 0)
                ThrowException.nothingToSkip();
            else
                ThrowException.timeCannotBeReversed();
        }
    }

    /**
     * Iterates over the Smart Device ArrayList in order the set current time to each device. As setting the time of
     * the devices triggers the switching operations if switch time is before the time that is going to be set
     * in SmartDevice class, sets times of devices until a different or null switch time is reached. When this happens
     * breaks out of the loop and sorts the devices and makes recursive call in order to set times of all devices.
     * Base cases are when the first elements switch in the list is null or after the current time. If base case is
     * reached, sets current time of each device altogether without any interruptions and in the end sorts the devices again.
     */
    public void setTimeOfDevices() {
        if (smartDeviceList.get(0).getSwitchTime() == null) {
            for (SmartDevice device : smartDeviceList)
                device.setTime(currentTime);
            sortDevices();
        }
        else {
            LocalDateTime prevSwitchTime;
            LocalDateTime nextSwitchTime;
            int compareVal = currentTime.compareTo(smartDeviceList.get(0).getSwitchTime());
            if (compareVal < 0) {
                for (SmartDevice device : smartDeviceList)
                    device.setTime(currentTime);
                sortDevices();
            } else {
                compareVal = 0;
                for (int i = 0; i < smartDeviceList.size() && (compareVal == 0); i++) {
                    prevSwitchTime = smartDeviceList.get(i).getSwitchTime();
                    smartDeviceList.get(i).setTime(currentTime);
                    if (i + 1 == smartDeviceList.size())
                        continue;
                    nextSwitchTime = smartDeviceList.get(i + 1).getSwitchTime();
                    if (nextSwitchTime == null) {
                        break;
                    } else {
                        compareVal = nextSwitchTime.compareTo(prevSwitchTime);
                    }
                }
                sortDevices();
                setTimeOfDevices();
            }
        }
    }

    /**
     * Sets devices current time to the switch time of first device in the Smart Device ArrayList as it is the closest
     * switch time to the current time. If first switch time is null, throws nothingToSwitch exception.
     *
     */
    public void nop() {
        if (smartDeviceList.size() == 0)
            ThrowException.nothingToSwitch();
        else {
            LocalDateTime nextSwitchTime = smartDeviceList.get(0).getSwitchTime();
            if (nextSwitchTime != null) {
                currentTime = nextSwitchTime;
                setTimeOfDevices();
            } else {
                ThrowException.nothingToSwitch();
            }
        }
    }

    /**
     * Sets the initial time of the system. This piece of code is executed in the very beginning of the program.
     * Halts the program instantly if anything goes wrong during this process. Throws initialFormatWrong exception
     * if the time format is not correct, throws invalidFirstCommand exception if it does not contain the time to set.
     */
    public void setInitialTime() {
        try {
            currentTime = LocalDateTime.parse(currentCommand[1], formatter);
            OutputHelper.write(suc.prefix(String.format("Time has been set to %s!", currentTime.format(formatter))));
        } catch (DateTimeParseException e) {
            ThrowException.initialFormatWrong();
            System.exit(1);
        } catch (ArrayIndexOutOfBoundsException e) {
            ThrowException.invalidFirstCommand();
            System.exit(1);
        } catch (Exception e) {
            System.exit(1);
        }
    }
}

