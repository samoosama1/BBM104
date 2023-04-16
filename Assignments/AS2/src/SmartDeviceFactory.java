import java.util.HashMap;

public class SmartDeviceFactory {
    public static void addSmartPlug(ValidArgs validArgs, HashMap<String, SmartDevice> smartDevices) {
        PlugValidArgs plugValidArgs = (PlugValidArgs) validArgs;
        switch (plugValidArgs.argNum) {
            case 1:
                smartDevices.put(plugValidArgs.name, new SmartPlug(plugValidArgs.name));
                break;
            case 2:
                smartDevices.put(plugValidArgs.name, new SmartPlug(plugValidArgs.name, plugValidArgs.status));
                break;
            case 3:
                smartDevices.put(plugValidArgs.name, new SmartPlug(
                        plugValidArgs.name, plugValidArgs.status, plugValidArgs.ampereVal));
                break;
        }
    }

    public static void addSmartCamera() {

    }

    public static void addSmartLamp() {

    }

    public static void addSmartColorLamp() {

    }


}
