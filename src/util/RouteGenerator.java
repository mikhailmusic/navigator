package util;

import entity.Route;
import struct.ArrayList;

import java.security.SecureRandom;

public class RouteGenerator {
    private static final SecureRandom random = new SecureRandom();

    public static Route[] generate(int number) {
        Route[] routes = new Route[number];

        for (int i = 0; i < number; i++) {
            double distance = 5.0 + random.nextDouble()*10;

            ArrayList<String> locationPoints = generateLocationPoints();
            distance*=locationPoints.size();
            distance = Math.round(distance * 10.0) / 10.0;

            Route route = new Route(
                    distance,
                    random.nextInt(10),
                    random.nextInt(10) > 8,
                    locationPoints
            );
            routes[i] = route;
        }

        return routes;
    }

    private static ArrayList<String> generateLocationPoints() {
        int arraySize = random.nextInt(5) + 2;
        ArrayList<String> locationPoints = new ArrayList<>();

        for (int i = 0; i < arraySize; i++) {
            locationPoints.add(generateCityName());
        }

        return locationPoints;
    }

    private static String generateCityName() {
        return Constants.cities[random.nextInt(Constants.cities.length)];
    }
}
