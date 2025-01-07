package entity.comporator;

import entity.Route;
import struct.ArrayList;
import struct.hashtable.HashTable;

import java.util.Comparator;

public class RouteComparator implements Comparator<Route> {
    private final String startPoint;
    private final String endPoint;
    private final HashTable<String, Route> hashTable;

    public RouteComparator(String startPoint, String endPoint, HashTable<String, Route> hashTable) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.hashTable = hashTable;
    }

    @Override
    public int compare(Route o1, Route o2) {
        if (o1.isFavorite() && !o2.isFavorite()) {
            return -1;
        }
        if (!o1.isFavorite() && o2.isFavorite()) {
            return 1;
        }
        int pointSpacing1 = countDistance(o1.getLocationPoints());
        int pointSpacing2 = countDistance(o2.getLocationPoints());
        if (pointSpacing1 != pointSpacing2) {
            return Integer.compare(pointSpacing1, pointSpacing2);
        }
        if (o1.getPopularity() != o2.getPopularity()) {
            return Integer.compare(o2.getPopularity(), o1.getPopularity());
        }
        return Integer.compare(hashTable.getOrder(o1.getId()), hashTable.getOrder(o2.getId()));
    }

    private int countDistance(ArrayList<String> array) {
        int startIndex = array.indexOf(startPoint);
        int endIndex = array.indexOf(endPoint);
        return endIndex - startIndex -1;
    }
}
