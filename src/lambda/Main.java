package lambda;

import lambda.test.Apple;
import lambda.test.ApplePredicate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Apple> inventory = new ArrayList<>();
//        List<Apple> apples = filterApples(inventory, new ApplePredicate() {
//            @Override
//            public boolean lambda.test(Apple apple) {
//                return RED.equals(apple.getColor());
//            }
//        });
//        List<Apple> apples = filterApples(inventory, apple -> RED.equals(apple.getColor()));

        inventory.sort((o1, o2) -> o1.getColor().compareTo(o2.getColor()));
        inventory.sort(Comparator.comparing(Apple::getColor));

    }

//    private static List<Apple> filterGreenApples(List<Apple> inventory, Color color) {
//        List<Apple> result = new ArrayList<>();
//
//        for (Apple apple : inventory) {
//            if (color.equals(apple.getColor())) {
//                result.add(apple);
//            }
//        }
//        return result;
//    }

//    private static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
//        List<Apple> result = new ArrayList<>();
//
//        for (Apple apple : inventory) {
//            if (p.lambda.test(apple)) {
//                result.add(apple);
//            }
//        }
//        return result;
//    }

    private static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();

        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

}