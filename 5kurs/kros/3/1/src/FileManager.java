import java.util.*;


public class FileManager {

    private List<File> files;

    public void addFile(File file) {
        if (files == null) {
            files = new ArrayList<File>();
        }
        files.add(file);
    }

    public List<File> getFiles() {
        return files;
    }

    public void removeFilesBefore(Date created) {
        if (isFilesNotEmpty() && created != null) {
            Iterator<File> i = files.iterator();
            while (i.hasNext()) {
                File file = i.next();
                if (file.getCreated().compareTo(created) == -1) {
                    i.remove();
                }
            }
        }
    }

    public File getByName(String name) {
        if (isFilesNotEmpty() && name != null && name.length() > 0) {
            for (File file : files) {
                if (name.equalsIgnoreCase(file.getName())) {
                    file.call();
                    return file;
                }
            }
        }
        return null;
    }

    public File getWithMaxCalls() {
        if (isFilesNotEmpty()) {
            Collections.sort(files);
            return files.iterator().next();
        }
        return null;

    }

    public boolean isFilesNotEmpty() {
        if (files != null && files.size() > 0) {
            return true;
        }
        return false;
    }

}
