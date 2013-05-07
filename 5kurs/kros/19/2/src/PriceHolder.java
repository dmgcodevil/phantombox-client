import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class PriceHolder {
    private Set<Price> prices;

    public Set<Price> getPrices() {
        return prices;
    }

    public void addPrice(Price price) {
        if (prices == null) {
            prices = new HashSet<Price>();
        }
        prices.add(price);
    }

    public Set<Price> filterPricesByStoreName(String storeName) {
        return filterTrains(storeNameFilter, storeName);
    }


    private Set<Price> filterTrains(Filter filter, Object criteria) {
        Set<Price> foundedPrices = Collections.emptySet();
        if (CollectionUtils.isNotEmpty(prices)) {
            foundedPrices = new HashSet<Price>();
            for (Price price : prices) {
                if (filter.apply(price, criteria)) {
                    foundedPrices.add(price);
                }
            }
        }
        return foundedPrices;
    }

    private Filter storeNameFilter = new Filter<Price, String>() {

        @Override
        public boolean apply(Price price, String storeName) {
            return price.getStoreName().equals(storeName);
        }

    };


    private interface Filter<T, V> {

        boolean apply(T t, V v);


    }
}
