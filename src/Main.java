import entity.Route;
import navigator.impl.HashTableNavigator;
import struct.ArrayList;
import util.RouteGenerator;

import java.util.Scanner;

public class Main {
    private static final HashTableNavigator navigator = new HashTableNavigator();
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean isRunning = true;
    private static final int initialSize = 10000;


    public static void main(String[] args) {
        seedData(initialSize);

        do {
            int choice = mainMenu();
            switch (choice) {
                case 1 -> routeList();
                case 2 -> createRoute();
                case 3 -> deleteRoute();
                case 4 -> searchById();
                case 5 -> checkContains();
                case 6 -> chooseRote();
                case 7 -> findByStartAndEnd();
                case 8 -> favoriteRoutes();
                case 9 -> top5();
                case 10 -> allRoutes();
                case 0 -> isRunning = false;
                default -> System.out.println("Попробуйте ещё раз");
            }

        } while (isRunning);
    }

    private static int mainMenu() {
        String menu =
                """
                    ======= Навигатор =======
                    1. Количество маршрутов
                    2. Добавить маршрут
                    3. Удалить маршрут
                    4. Поиск маршрута по ID
                    5. Проверить есть ли маршрут
                    6. Выбрать маршрут
                    7. Поиск маршрута по начальной и конечной точке
                    8. Избранные маршруты (поиск, изменение)
                    9. Топ 5 маршрутов
                    10. Вывести все маршруты
                    0. Выход
                    """;

        System.out.println(menu);
        System.out.print("Выберите пункт: ");
        return Integer.parseInt(scanner.nextLine());
    }

    private static void createRoute() {
        System.out.print("Введите расстояние маршрута: ");
        double distance = Double.parseDouble(scanner.nextLine());

        System.out.print("Популярность маршрута: ");
        int popularity = Integer.parseInt(scanner.nextLine());

        System.out.print("Этот маршрут избранный (true/false): ");
        boolean isFavorite = Boolean.parseBoolean(scanner.nextLine());

        System.out.print("Введите количество точек маршрута: ");
        int size = Integer.parseInt(scanner.nextLine());

        ArrayList<String> locationPoints = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int number = i + 1;
            System.out.print("Введите название " + number + "-го города: ");
            locationPoints.add(scanner.nextLine());
        }

        try {
            Route route = new Route(distance, popularity, isFavorite, locationPoints);
            if (navigator.contains(route)) {
                System.out.println("Такой маршрут уже есть");
                return;
            }
            navigator.addRoute(route);

            System.out.println("Добавлен следующий маршрут:");
            System.out.println(route);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }
    }

    private static void deleteRoute() {
        System.out.print("Введите id маршрута для удаления: ");
        navigator.removeRoute(scanner.nextLine());
    }

    private static void searchById() {
        System.out.println("Введите нужную информацию");
        System.out.print("Введите id: ");
        String id = scanner.nextLine();

        boolean contains = navigator.contains(id);

        if (!contains) {
            System.out.println("Маршрут не найден");
            return;
        }

        System.out.println("Маршрут найден");
        System.out.println(navigator.getRoute(id));
    }

    private static void checkContains() {
        System.out.print("Введите идентификатор пути: ");
        String routeId = scanner.nextLine();

        boolean contains = navigator.contains(routeId);
        System.out.println("Путь (есть/нет): " + contains);
    }


    private static void routeList() {
        System.out.println("Общее количество маршрутов: " + navigator.size());
    }

    private static void chooseRote() {
        System.out.print("Введите id маршрута: ");
        String id = scanner.nextLine();
        navigator.chooseRoute(id);
    }

    private static void findByStartAndEnd() {
        System.out.print("Введите начальную точку маршрута: ");
        String startPoint = scanner.nextLine();
        System.out.print("Введите конечную точку маршрута: ");
        String endPoint = scanner.nextLine();
        System.out.println("Найденные маршруты");
        Iterable<Route> routes = navigator.searchRoutes(startPoint, endPoint);
        for (Route route : routes) {
            System.out.print(route);
        }
    }

    private static void favoriteRoutes() {
        System.out.println("""
                1. Просмотреть
                2. Изменить
                """);

        System.out.print("Введите вариант: ");
        switch (Integer.parseInt(scanner.nextLine())) {
            case 1 -> showFavorite();
            case 2 -> changeFavorite();
        }
    }

    private static void showFavorite() {
        System.out.print("Введите точку назначения: ");
        String destination = scanner.nextLine();

        System.out.println("Избранные маршруты");
        for (Route favorite : navigator.getFavoriteRoutes(destination)) {
            System.out.print(favorite);
        }
    }

    private static void changeFavorite() {
        System.out.println("Введите нужную информацию");
        System.out.print("Введите id: ");
        String id = scanner.nextLine();
        System.out.print("Этот маршрут избранный (true/false): ");
        boolean favorite = Boolean.parseBoolean(scanner.nextLine());
        navigator.changeFavorite(id, favorite);
        System.out.println("Маркер избранности установлен");
    }

    private static void top5() {
        System.out.println("Топ 5 маршрутов");
        for (Route route : navigator.getTop3Routes()) {
            System.out.print(route);
        }
    }

    private static void allRoutes(){
        for (Route route : navigator.getAllRoutes()) {
            System.out.print(route);
        }
    }

    private static void seedData(int size) {
        Route[] routes = RouteGenerator.generate(size);
        for (Route route : routes) {
            navigator.addRoute(route);
        }
    }

}