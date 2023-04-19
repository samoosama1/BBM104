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

    public static void addSmartCamera(ValidArgs validArgs, HashMap<String, SmartDevice> smartDevices) {
        CameraValidArgs cameraValidArgs = (CameraValidArgs) validArgs;
        switch (cameraValidArgs.argNum) {
            case 2:
                smartDevices.put(cameraValidArgs.name,
                        new SmartCamera(cameraValidArgs.name, cameraValidArgs.megaBytePerMinute));
                break;
            case 3:
                smartDevices.put(cameraValidArgs.name,
                      new SmartCamera(cameraValidArgs.name, cameraValidArgs.status, cameraValidArgs.megaBytePerMinute));
                break;
        }
    }

    public static void addSmartLamp(ValidArgs validArgs, HashMap<String, SmartDevice> smartDevices) {
        LampValidArgs lampValidArgs = (LampValidArgs) validArgs;
        switch (lampValidArgs.argNum) {
            case 1:
                smartDevices.put(lampValidArgs.name, new SmartLamp(lampValidArgs.name));
                break;
            case 2:
                smartDevices.put(lampValidArgs.name, new SmartLamp(lampValidArgs.name, lampValidArgs.status));
                break;
            case 4:
                smartDevices.put(lampValidArgs.name, new SmartLamp(
                        lampValidArgs.name, lampValidArgs.status, lampValidArgs.kelvinVal, lampValidArgs.brightness));
                break;
        }
    }

    public static void addSmartColorLamp(ValidArgs validArgs, HashMap<String, SmartDevice> smartDevices) {
        LampValidArgs colorLampValidArgs = (LampValidArgs) validArgs;
        switch (colorLampValidArgs.argNum) {
            case 1:
                smartDevices.put(colorLampValidArgs.name, new SmartColorLamp(colorLampValidArgs.name));
                break;
            case 2:
                smartDevices.put(
                        colorLampValidArgs.name, new SmartColorLamp(colorLampValidArgs.name, colorLampValidArgs.status));
                break;
            case 4:
                if (colorLampValidArgs.isKelvin) {
                    smartDevices.put(colorLampValidArgs.name, new SmartColorLamp(
                            colorLampValidArgs.name, colorLampValidArgs.status,
                            colorLampValidArgs.kelvinVal, colorLampValidArgs.brightness));
                } else {
                    smartDevices.put(colorLampValidArgs.name, new SmartColorLamp(
                            colorLampValidArgs.name, colorLampValidArgs.status,
                            colorLampValidArgs.hexCodeStr, colorLampValidArgs.brightness));
                }
                break;

        }
    }
}