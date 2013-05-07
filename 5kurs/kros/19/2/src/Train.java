
public class Train implements Comparable<Train> {
    private String start;
    private String destination;
    private Integer number;

    public Train(String start, String destination, Integer number) {
        this.start = start;
        this.destination = destination;
        this.number = number;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public int compareTo(Train o) {
        return this.number > o.number ? 1 : -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Train)) return false;

        Train train = (Train) o;

        if (destination != null ? !destination.equals(train.destination) : train.destination != null) return false;
        if (number != null ? !number.equals(train.number) : train.number != null) return false;
        if (start != null ? !start.equals(train.start) : train.start != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = start != null ? start.hashCode() : 0;
        result = 31 * result + (destination != null ? destination.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Train{" +
                "start='" + start + '\'' +
                ", destination='" + destination + '\'' +
                ", number=" + number +
                '}';
    }

}