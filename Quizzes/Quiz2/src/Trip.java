import java.time.LocalTime;

public class Trip {
    private String tripName;
    private LocalTime departureTime ;
    private LocalTime arrivalTime;
    private int duration;
    private String state;

    public Trip(String tripName, LocalTime departureTime, int duration) {
        this.tripName = tripName;
        this.departureTime = departureTime;
        this.duration = duration;
        this.state = "IDLE";
        calculateArrival();
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void calculateArrival() {
        arrivalTime = departureTime.plusMinutes(duration);
    }

    public String toString(boolean isDeparture) {
        if (isDeparture)
            return tripName + " depart at " + departureTime.toString() + "\tTrip State:" + state;
        return tripName + " arrive at " + arrivalTime.toString() + "\tTrip State:" + state;
    }
}
