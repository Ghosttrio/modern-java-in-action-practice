package lambda.test.eee;

import lambda.test.Apple;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Test {

    public void test() {

        List<Apple> inventory = new ArrayList<>();
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });

        inventory.sort(Comparator.comparing(Apple::getWeight));


    }
}
