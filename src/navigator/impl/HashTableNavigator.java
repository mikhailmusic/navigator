package navigator.impl;

import entity.Route;
import entity.comporator.FavoriteComparator;
import entity.comporator.RouteComparator;
import entity.comporator.TopComparator;
import navigator.Navigator;
import struct.ArrayList;
import struct.hashtable.HashTable;

import java.util.Comparator;

public class HashTableNavigator implements Navigator {
    private final HashTable<String, Route> hashTable = new HashTable<>();

    @Override
    public void addRoute(Route route) {
        hashTable.addOrReplace(route.getId(), route);
    }

    @Override
    public void removeRoute(String routeId) {
        hashTable.remove(routeId);
    }

    @Override
    public boolean contains(Route route) {
        return hashTable.containsKey(route.getId());
    }

    @Override
    public int size() {
        return hashTable.size();
    }

    @Override
    public Route getRoute(String routeId) {
        return hashTable.get(routeId);
    }

    @Override
    public void chooseRoute(String routeId) {
        Route route = hashTable.get(routeId);
        if (route != null) {
            route.setPopularity(route.getPopularity() + 1);
            hashTable.addOrReplace(route.getId(), route);
        }
    }

    @Override
    public Iterable<Route> searchRoutes(String startPoint, String endPoint) {
        ArrayList<Route> matchingRoutes = new ArrayList<>();

        for (Route route: hashTable.values()) {
            if (hasLogicalMeaning(route, startPoint, endPoint)) {
                matchingRoutes.add(route);
            }
        }

        Comparator<Route> routeComparator = new RouteComparator(startPoint, endPoint, hashTable);
        matchingRoutes.sort(routeComparator);

        return matchingRoutes;
    }

    private boolean hasLogicalMeaning(Route route, String startPoint, String endPoint) {
        ArrayList<String> locationPoints = route.getLocationPoints();
        int startIndex = locationPoints.indexOf(startPoint);
        int endIndex = locationPoints.indexOf(endPoint);

        return startIndex != -1 && endIndex != -1 && startIndex < endIndex;
    }

    @Override
    public Iterable<Route> getFavoriteRoutes(String destinationPoint) {
        ArrayList<Route> routes = new ArrayList<>();
        for (Route route : hashTable.values()) {
            if (route.isFavorite() && route.getLocationPoints().contains(destinationPoint)
                    && !route.getLocationPoints().get(0).equals(destinationPoint)) {
                routes.add(route);
            }
        }

        Comparator<Route> comparator = new FavoriteComparator(hashTable);
        routes.sort(comparator);

        return routes;
    }

    @Override
    public Iterable<Route> getTop3Routes() {
        ArrayList<Route> values = new ArrayList<>();
        values.addAll(hashTable.values());


        TopComparator topComparator = new TopComparator(hashTable);
        values.sort(topComparator);

        ArrayList<Route> top5 = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            top5.add(values.get(i));
        }

        return top5;
    }
}
