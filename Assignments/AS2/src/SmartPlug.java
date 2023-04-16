public class SmartPlug extends SmartDevice{
    private static int defaultVolts = 220;
    public void setAmpereVal(double ampereVal) {
        if (ampereVal > 0)
            this.ampereVal = ampereVal;
        else {
            System.out.println("ERROR: Ampere value must be a positive number!");
        }
    }
    private double ampereVal;
    public SmartPlug(String name) {
        super(name);
    }

    public SmartPlug(String name, String initStatus) {
        super(name, initStatus);
    }

    public SmartPlug(
            String name, String initStatus, double ampereVal) {
        super(name, initStatus);
        setAmpereVal(ampereVal);
    }

    public void Switch(String status) {
    }



}
//    public void addSmartPlug() {
//        String deviceName = currentCommand[2];
//        String initStatus = null;
//        double ampereVal = 0.0;
//        if (currentCommand.length > 3)
//            initStatus = currentCommand[3];
//        if (currentCommand.length > 4)
//            ampereVal = Double.parseDouble(currentCommand[4]);
//        switch (currentCommand.length) {
//            case 3:
//                smartDevices.put(deviceName, new SmartPlug(deviceName));
//                break;
//            case 4:
//                smartDevices.put(deviceName, new SmartPlug(deviceName, initStatus));
//                break;
//            case 5:
//                smartDevices.put(deviceName, new SmartPlug(deviceName, initStatus, ampereVal));
//                break;
//        }
//        checkErroneousDevice(deviceName);
//    }
