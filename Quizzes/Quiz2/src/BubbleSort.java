public class BubbleSort {

    static void sortArrival(Trip[] arr) {
        int n = arr.length;
        int value;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                value = arr[j].getArrivalTime().compareTo(arr[j + 1].getArrivalTime());
                if (value > 0) {
                    Trip temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                } else if (value == 0) {
                    arr[j].setState("DELAYED");
                    arr[j + 1].setState("DELAYED");
                }
            }
        }
    }

    static void sortDeparture(Trip[] arr) {
        int n = arr.length;
        int value;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                value = arr[j].getDepartureTime().compareTo(arr[j + 1].getDepartureTime());
                if (value > 0) {
                    Trip temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                } else if (value == 0) {
                    arr[j].setState("DELAYED");
                    arr[j + 1].setState("DELAYED");
                }
            }
        }
    }
}

