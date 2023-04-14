public class SmartLamp extends SmartDevice{
    private int kelvinVal = 4000;
    private int brightness = 100;
    public SmartLamp(String name) {
        super(name);
    }

    public SmartLamp(String name, String initStatus) {
        super(name, initStatus);
    }

    public void Switch(String status) {
        System.out.println("Mom");
    }
}
