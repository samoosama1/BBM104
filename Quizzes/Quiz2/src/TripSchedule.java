import java.time.LocalTime;

public class TripSchedule {
    public Trip[] trips;
    public TripSchedule() {
        String tripName;
        LocalTime departureTime;
        int duration;

        String[] inputArray = FileInput.readFile(Main.inputFile, true, true);
        trips = new Trip[inputArray.length];
        for(int i = 0; i < inputArray.length; i++) {
            String[] lineElements = inputArray[i].split("\t");
            tripName = lineElements[0];
            departureTime = LocalTime.parse(lineElements[1]);
            duration = Integer.parseInt(lineElements[2]);
            trips[i] = new Trip(tripName, departureTime, duration);
            trips[i].calculateArrival();
        }
    }
}
