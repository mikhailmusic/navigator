package entity.comporator;

import entity.Route;
import struct.hashtable.HashTable;

import java.util.Comparator;

public class TopComparator implements Comparator<Route> {
    private final HashTable<String, Route> table;

    public TopComparator(HashTable<String, Route> table) {
        this.table = table;
    }


    @Override
    public int compare(Route o1, Route o2) {
        if (o1.getPopularity() != o2.getPopularity()) {
            return Integer.compare(o2.getPopularity(), o1.getPopularity());
        }
        if (!o1.getDistance().equals(o2.getDistance())) {
            return Double.compare(o1.getDistance(), o2.getDistance());
        }
        if (o1.getLocationPoints().size() != o2.getLocationPoints().size()) {
            return Integer.compare(o1.getLocationPoints().size(), o2.getLocationPoints().size());
        }
        return Integer.compare(table.getOrder(o1.getId()), table.getOrder(o2.getId()));
    }
}
