

public class Route implements Comparable<Route> {
    private String start;
    private String end;
    private Integer number;

    public Route(String start, String end, Integer number) {
        this.start = start;
        this.end = end;
        this.number = number;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public int compareTo(Route o) {
        return this.number > o.number ? 1 : -1;
    }

    @Override
    public String toString() {
        return "Route{" +
                "start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", number=" + number +
                '}';
    }
}
