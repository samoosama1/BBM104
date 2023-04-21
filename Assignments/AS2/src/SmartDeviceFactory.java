import java.util.ArrayList;
import java.util.HashMap;

public class SmartDeviceFactory {
    public static void addSmartPlug(ValidArgs validArgs, HashMap<String, SmartDevice> smartDevices,
                                    ArrayList<SmartDevice> smartDeviceList) {
        PlugValidArgs plugValidArgs = (PlugValidArgs) validArgs;
        SmartDevice device;
        switch (plugValidArgs.argNum) {
            case 1:
                device = new SmartPlug(plugValidArgs.name, SmartSystem.getCurrentTime());
                smartDevices.put(plugValidArgs.name, device);
                smartDeviceList.add(device);
                break;
            case 2:
                device = new SmartPlug(plugValidArgs.name, plugValidArgs.status, SmartSystem.getCurrentTime());
                smartDevices.put(plugValidArgs.name, device);
                smartDeviceList.add(device);
                break;
            case 3:
                device = new SmartPlug(plugValidArgs.name, plugValidArgs.status,
                        plugValidArgs.ampereVal, SmartSystem.getCurrentTime());
                smartDevices.put(plugValidArgs.name, device);
                smartDeviceList.add(device);
                break;
        }
    }

    public static void addSmartCamera(ValidArgs validArgs, HashMap<String, SmartDevice> smartDevices,
                                      ArrayList<SmartDevice> smartDeviceList) {
        CameraValidArgs cameraValidArgs = (CameraValidArgs) validArgs;
        SmartDevice device;
        switch (cameraValidArgs.argNum) {
            case 2:
                device = new SmartCamera(cameraValidArgs.name, cameraValidArgs.megaBytePerMinute,
                        SmartSystem.getCurrentTime());
                smartDevices.put(cameraValidArgs.name, device);
                smartDeviceList.add(device);
                break;
            case 3:
                device = new SmartCamera(cameraValidArgs.name, cameraValidArgs.status,
                        cameraValidArgs.megaBytePerMinute, SmartSystem.getCurrentTime());
                smartDevices.put(cameraValidArgs.name, device);
                smartDeviceList.add(device);
                break;
        }
    }

    public static void addSmartLamp(ValidArgs validArgs, HashMap<String, SmartDevice> smartDevices,
                                    ArrayList<SmartDevice> smartDeviceList) {
        LampValidArgs lampValidArgs = (LampValidArgs) validArgs;
        SmartDevice device;
        switch (lampValidArgs.argNum) {
            case 1:
                device = new SmartLamp(lampValidArgs.name, SmartSystem.getCurrentTime());
                smartDevices.put(lampValidArgs.name, device);
                smartDeviceList.add(device);
                break;
            case 2:
                device = new SmartLamp(lampValidArgs.name, lampValidArgs.status, SmartSystem.getCurrentTime());
                smartDevices.put(lampValidArgs.name, device);
                smartDeviceList.add(device);
                break;
            case 4:
                device = new SmartLamp( lampValidArgs.name, lampValidArgs.status,
                        lampValidArgs.kelvinVal, lampValidArgs.brightness, SmartSystem.getCurrentTime());
                smartDevices.put(lampValidArgs.name, device);
                smartDeviceList.add(device);
                break;
        }
    }

    public static void addSmartColorLamp(ValidArgs validArgs, HashMap<String, SmartDevice> smartDevices,
                                         ArrayList<SmartDevice> smartDeviceList) {
        LampValidArgs colorLampValidArgs = (LampValidArgs) validArgs;
        SmartDevice device;
        switch (colorLampValidArgs.argNum) {
            case 1:
                device = new SmartColorLamp(colorLampValidArgs.name, SmartSystem.getCurrentTime());
                smartDevices.put(colorLampValidArgs.name, device);
                smartDeviceList.add(device);
                break;
            case 2:
                device = new SmartColorLamp(
                        colorLampValidArgs.name, colorLampValidArgs.status, SmartSystem.getCurrentTime());
                smartDevices.put( colorLampValidArgs.name, device);
                smartDeviceList.add(device);
                break;
            case 4:
                if (colorLampValidArgs.isKelvin) {
                    device = new SmartColorLamp( colorLampValidArgs.name, colorLampValidArgs.status,
                            colorLampValidArgs.kelvinVal, colorLampValidArgs.brightness, SmartSystem.getCurrentTime());
                } else {
                    device = new SmartColorLamp(
                            colorLampValidArgs.name, colorLampValidArgs.status, colorLampValidArgs.hexCodeStr,
                            colorLampValidArgs.brightness, SmartSystem.getCurrentTime());
                }
                smartDevices.put(colorLampValidArgs.name, device);
                smartDeviceList.add(device);
                break;

        }
    }
}