import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class for adding the devices to HashMap and ArrayList that is in the Smart System. It should be noted having two
 * data structures for holding the reference variables to these objects is not causing inefficiency in the program as
 * they are just variables pointing to the objects in the heap. System uses HashMap when it has to query the objects
 * according to their names, and uses ArrayList when it has to sort the devices according to their switch times.
 */
public class SmartDeviceFactory {
    /**
     * Adds newly created Smart Plug object to HashMap and ArrayList in the Smart System.
     * @param validArgs Valid arguments that were checked beforehand for validity are passed in as validArgs object.
     * @param smartDevices Smart Device HashMap in the Smart System.
     * @param smartDeviceList Smart Device ArrayList in the Smart System.
     */
    public static void addSmartPlug(ValidArgs validArgs, HashMap<String, SmartDevice> smartDevices,
                                    ArrayList<SmartDevice> smartDeviceList) {
        // Typecasting is done in order to reach Plug specific arguments.
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

    /**
     * Adds newly created Smart Camera object to HashMap and ArrayList in the Smart System.
     * @param validArgs Valid arguments that were checked beforehand for validity are passed in as validArgs object.
     * @param smartDevices Smart Device HashMap in the Smart System.
     * @param smartDeviceList Smart Device ArrayList in the Smart System.
     */
    public static void addSmartCamera(ValidArgs validArgs, HashMap<String, SmartDevice> smartDevices,
                                      ArrayList<SmartDevice> smartDeviceList) {
        // Typecasting is done in order to reach Camera specific arguments.
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

    /**
     * Adds newly created Smart Lamp object to HashMap and ArrayList in the Smart System.
     * @param validArgs Valid arguments that were checked beforehand for validity are passed in as validArgs object.
     * @param smartDevices Smart Device HashMap in the Smart System.
     * @param smartDeviceList Smart Device ArrayList in the Smart System.
     */
    public static void addSmartLamp(ValidArgs validArgs, HashMap<String, SmartDevice> smartDevices,
                                    ArrayList<SmartDevice> smartDeviceList) {
        // Typecasting is done in order to reach Lamp specific arguments.
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

    /**
     * Adds newly created Smart Color Lamp object to HashMap and ArrayList in the Smart System.
     * @param validArgs Valid arguments that were checked beforehand for validity are passed in as validArgs object.
     * @param smartDevices Smart Device HashMap in the Smart System.
     * @param smartDeviceList Smart Device ArrayList in the Smart System.
     */
    public static void addSmartColorLamp(ValidArgs validArgs, HashMap<String, SmartDevice> smartDevices,
                                         ArrayList<SmartDevice> smartDeviceList) {
        // Typecasting is done in order to reach Color Lamp specific arguments.
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