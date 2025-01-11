package entity;

import struct.ArrayList;
import util.Utils;

public class Route {
    private String id;
    private Double distance;
    private int popularity;
    private boolean isFavorite;
    private ArrayList<String> locationPoints;

    public Route(Double distance, int popularity, boolean isFavorite, ArrayList<String> locationPoints) {
        setDistance(distance);
        setPopularity(popularity);
        setFavorite(isFavorite);
        setLocationPoints(locationPoints);
        this.id = distance + locationPoints.getFirst() + locationPoints.getLast();
    }


    public Route() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        if (distance < 0) {
            throw new IllegalArgumentException("Расстояние не может быть отрицательным");
        }
        this.distance = distance;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        if (popularity < 0) {
            throw new IllegalArgumentException("Популярность не может быть отрицательной");
        }
        this.popularity = popularity;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public ArrayList<String> getLocationPoints() {
        return locationPoints;
    }

    public void setLocationPoints(ArrayList<String> locationPoints) {
        if (locationPoints == null || locationPoints.size() < 2) {
            throw new IllegalArgumentException("Маршрут должен содержать минимум 2 точки");
        }
        this.locationPoints = locationPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return route.distance.equals(distance)
                && route.locationPoints.getFirst().equals(locationPoints.getFirst())
                && route.locationPoints.getLast().equals(locationPoints.getLast());
    }


    @Override
    public int hashCode() {
        return id.hashCode();
    }


    @Override
    public String toString() {
        return "Route{" +
                "id='" + id + '\'' +
                ", distance=" + distance +
                ", popularity=" + popularity +
                ", isFavorite=" + isFavorite +
                ", locationPoints=" + Utils.joinStrings(locationPoints) +
                "}\n";
    }

}


//@Override
//public String toString() {
//    String template = """
//                ================
//                Маршрут %s-%s,
//                ID: %s,
//                Дистанция: %s,
//                Популярность: %s,
//                Избранный: %s,
//                Схема маршрута:
//                %s
//                ================
//                """;
//
//    return String.format(template,
//            locationPoints.getFirst(),
//            locationPoints.getLast(),
//            id,
//            distance,
//            popularity,
//            isFavorite ? "Да" : "Нет",
//            Utils.joinStrings(locationPoints));
//}