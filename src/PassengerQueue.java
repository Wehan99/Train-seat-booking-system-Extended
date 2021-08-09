

public class PassengerQueue<die> {
    private static Passenger[] passengerQueue = new Passenger[TrainStation.SEATING_CAPACITY];

    public static void addToQueue(Passenger passenger, int index) {
        System.out.println(passenger.getName() + " added to " + (index + 1));
        passengerQueue[index] = passenger;
    }


    public static boolean isEmpty() {
        for (Passenger passenger : passengerQueue) {
            if (passenger != null) return false;
        }
        return true;
    }

    public static boolean isFull() {
        for (Passenger passenger : passengerQueue) {
            if (passenger == null) return false;
        }
        return true;
    }


    public static Passenger[] getPassengerQueue() {return passengerQueue;}

    public static void delete(int seat) {passengerQueue[seat]=null;}


}

