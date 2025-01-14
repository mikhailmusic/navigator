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

        return customArrayInit(); //customArrayInit()
    }

    private static ArrayList<String> generateLocationPoints() {
        int arraySize = random.nextInt(5) + 2;
        ArrayList<String> locationPoints = new ArrayList<>();

        while (locationPoints.size() < arraySize) {
            String cityName = generateCityName();
            if (!locationPoints.contains(cityName)) {
                locationPoints.add(cityName);
            }
        }

        return locationPoints;
    }

    private static String generateCityName() {
        return Constants.cities[random.nextInt(Constants.cities.length)];
    }


    private static Route[] customArrayInit(){
        Route[] routes = new Route[20];

        ArrayList<String> l0 = ArrayList.asList("Сараево", "Ялта", "Чебоксары", "Рим", "Измир");
        Route route0 = new Route(67.3, 5, true, l0);
        routes[0] = route0;

        ArrayList<String> l1 = ArrayList.asList("Москва", "Тверь", "Благовещенск"); //
        Route route1 = new Route(19.6, 7, false, l1);
        routes[1] = route1;

        ArrayList<String> l2 = ArrayList.asList("Ялта", "Рим", "Анталия");
        Route route2 = new Route(24.2, 5, true, l2);
        routes[2] = route2;

        ArrayList<String> l3 = ArrayList.asList("Владивосток", "Хабаровск", "Уссурийск", "Казань"); //
        Route route3 = new Route(22.4, 8, false, l3);
        routes[3] = route3;

        ArrayList<String> l4 = ArrayList.asList("Измир", "Калуга", "Владимир", "Ялта", "Рим");
        Route route4 = new Route(40.8, 3, true, l4);
        routes[4] = route4;

        ArrayList<String> l5 = ArrayList.asList("Ялта", "Рим", "Ростов-на-Дону", "Мурманск");
        Route route5 = new Route(40.0, 4, false, l5);
        routes[5] = route5;

        ArrayList<String> l6 = ArrayList.asList("Петрозаводск", "Сегежа", "Кемь", "Лоухи", "Мурманск"); //
        Route route6 = new Route(19.6, 7, false, l6);
        routes[6] = route6;

        ArrayList<String> l7 = ArrayList.asList("Калининград", "Советск", "Неман", "Гусев", "Гвардейск"); //
        Route route7 = new Route(20.1, 6, false, l7);
        routes[7] = route7;

        ArrayList<String> l8 = ArrayList.asList("Ницца", "Ялта", "Нюрнберг", "Рим", "Хельсинки");
        Route route8 = new Route(50.6, 5, false, l8);
        routes[8] = route8;

        ArrayList<String> l9 = ArrayList.asList("Екатеринбург", "Тюмень", "Тобольск", "Сургут"); //
        Route route9 = new Route(20.6, 8, false, l9);
        routes[9] = route9;

        ArrayList<String> l10 = ArrayList.asList("Бургас", "Курск", "Калуга", "Москва"); //2
        Route route10 = new Route(30.4, 2, true, l10);
        routes[10] = route10;

        ArrayList<String> l11 = ArrayList.asList("Ярославль", "Тверь", "Москва", "Калуга"); //2
        Route route11 = new Route(30.4, 2, true, l11);
        routes[11] = route11;

        ArrayList<String> l12 = ArrayList.asList("Копенгаген", "Тула", "Москва", "Чебоксары"); //2
        Route route12 = new Route(27.4, 5, true, l12);
        routes[12] = route12;

        ArrayList<String> l13 = ArrayList.asList("Барселона", "Стокгольм", "Москва"); //2
        Route route13 = new Route(25.2, 4, true, l13);
        routes[13] = route13;

        ArrayList<String> l14 = ArrayList.asList("Москва", "Чебоксары", "Тбилиси", "Париж", "Ницца"); //2 но не участвует
        Route route14 = new Route(38.5, 0, true, l14);
        routes[14] = route14;

        ArrayList<String> l15 = ArrayList.asList("Львов", "Москва", "Саратов", "Красноярск"); //2
        Route route15 = new Route(30.4, 4, true, l15);
        routes[15] = route15;

        ArrayList<String> l16 = ArrayList.asList( "Неаполь", "Смоленск", "Москва", "Мурманск"); //2
        Route route16 = new Route(37.0, 6, true, l16);
        routes[16] = route16;

        ArrayList<String> l17 = ArrayList.asList( "Осло", "Ялта", "Уфа", "Рим", "Сараево", "Фаро");
        Route route17 = new Route(72.8, 2, false, l17);
        routes[17] = route17;

        ArrayList<String> l18 = ArrayList.asList( "Керчь", "Ялта", "Батуми", "Рим", "Ницца");
        Route route18 = new Route(72.8, 4, false, l18);
        routes[18] = route18;

        ArrayList<String> l19 = ArrayList.asList("Амстердам", "Неаполь", "Ялта", "Омск", "Рим");
        Route route19 = new Route(67.3, 2, true, l19);
        routes[19] = route19;

        return routes;
    }
}
