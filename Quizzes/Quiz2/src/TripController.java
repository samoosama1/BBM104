public class TripController implements DepartureController, ArrivalController {
    protected TripSchedule tripSchedule = new TripSchedule();

    public TripController() {
        DepartureSchedule(tripSchedule);
        ArrivalSchedule(tripSchedule);
    }


    @Override
    public void ArrivalSchedule(TripSchedule tripSchedule) {
        StringBuilder out = new StringBuilder();
        out.append("Arrival order:\n");
        BubbleSort.sortArrival(tripSchedule.trips);
        for (Trip tr : tripSchedule.trips) {
            out.append(tr.toString(false)).append("\n");
        }
        FileOutput.writeToFile(Main.outputFile, out.toString(), true, true);
    }

    @Override
    public void DepartureSchedule(TripSchedule tripSchedule) {
        StringBuilder out = new StringBuilder();
        out.append("Departure order:\n");
        BubbleSort.sortDeparture(tripSchedule.trips);
        for (Trip tr : tripSchedule.trips) {
            out.append(tr.toString(true)).append("\n");
            tr.setState("IDLE");
        }
        FileOutput.writeToFile(Main.outputFile, out.toString(), true, true);
    }
}
