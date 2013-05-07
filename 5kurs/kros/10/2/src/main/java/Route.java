

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Route)) return false;

        Route route = (Route) o;

        if (end != null ? !end.equals(route.end) : route.end != null) return false;
        if (number != null ? !number.equals(route.number) : route.number != null) return false;
        if (start != null ? !start.equals(route.start) : route.start != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = start != null ? start.hashCode() : 0;
        result = 31 * result + (end != null ? end.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        return result;
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
