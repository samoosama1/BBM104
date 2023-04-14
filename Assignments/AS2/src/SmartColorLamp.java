public class SmartColorLamp extends SmartLamp{
    private int colorBaseHex;
    public SmartColorLamp(String name) {
        super(name);
    }

    public String getName() {
        return super.getName();
    }

    public SmartColorLamp(String name, String initStatus) {
        super(name, initStatus);
    }
}
