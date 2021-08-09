import java.io.Serializable;


import java.io.Serializable;

public class Passenger implements Serializable {
    private String name;
    private int seat;
    private int minutesInTheQueue;

    public void setName(String name) {this.name=name;}

    public void setSeat(int seat) {this.seat=seat;}

    public String getName() {
        return  name ;
    }


    public int getSeat() {return seat;}

    public int getMinutesInTheQueue() {return minutesInTheQueue;}


    public void setSecondsInQueue(int minutesInTheQueue) {this.minutesInTheQueue=this.minutesInTheQueue+minutesInTheQueue;
    }
}


