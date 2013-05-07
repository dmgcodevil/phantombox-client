import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class TrainHolder {
    private Set<Train> trains;

    public Set<Train> getTrains() {
        return trains;
    }

    public void addTrain(Train train) {
        if (trains == null) {
            trains = new HashSet<Train>();
        }
        trains.add(train);
    }

    public Set<Train> filterTrainsByNumber(Integer num) {
        return filterTrains(trainNumberFilter, num);
    }

    public Set<Train> filterTrainsByDestination(String destination) {
        return filterTrains(trainDestinationFilter, destination);
    }

    private Set<Train> filterTrains(Filter filter, Object criteria) {
        Set<Train> foundedTrains = Collections.emptySet();
        if (CollectionUtils.isNotEmpty(trains)) {
            foundedTrains = new HashSet<Train>();
            for (Train train : trains) {
                if (filter.apply(train, criteria)) {
                    foundedTrains.add(train);
                }
            }
        }
        return foundedTrains;
    }

    private Filter trainDestinationFilter = new Filter<Train, String>() {

        @Override
        public boolean apply(Train train, String destination) {
            return train.getDestination().equals(destination);
        }

    };

    private Filter trainNumberFilter = new Filter<Train, Integer>() {

        @Override
        public boolean apply(Train train, Integer num) {
            return train.getNumber().equals(num);
        }

    };

    private interface Filter<T, V> {

        boolean apply(T t, V v);


    }
}
