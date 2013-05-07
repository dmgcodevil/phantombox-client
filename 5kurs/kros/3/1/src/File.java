import java.util.Date;

public class File implements Comparable<File> {

    private String name;

    private Date created = new Date();

    private Integer numberOfCalls = new Integer(DEFAULT_VALUE);

    private static final int DEFAULT_VALUE = 0;

    public File(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreated() {
        return created;
    }

    public Integer getNumberOfCalls() {
        return numberOfCalls;
    }

    public synchronized void call() {
        numberOfCalls++;
    }

    @Override
    public int compareTo(File f) {
        return f.getNumberOfCalls().compareTo(this.numberOfCalls);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof File)) return false;

        File file = (File) o;

        if (created != null ? !created.equals(file.created) : file.created != null) return false;
        if (name != null ? !name.equals(file.name) : file.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (created != null ? created.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "File{" +
                "name='" + name + '\'' +
                ", created=" + created +
                ", numberOfCalls=" + numberOfCalls +
                '}';
    }
}
